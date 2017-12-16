/**
 * @author ray
 * @filename: MiniServiceImpl.java
 * @create date: 上午09:55:38
 */
package com.lakala.mini.server.core.service.impl;

import com.lakala.ca.dto.*;
import com.lakala.ca.service.IUserService;
import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.ServiceException;
import com.lakala.common.util.CollectionHelper;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.dto.PageDTO;
import com.lakala.core.dto.PageResultDTO;
import com.lakala.core.objmapper.IObjectMapper;
import com.lakala.core.objmapper.MappingException;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.enquire.api.dto.QueryTermInfoResponse;
import com.lakala.enquire.api.dto.TermInfoDTO;
import com.lakala.enquire.api.dto.TermInfosQueryRequest;
import com.lakala.enquire.api.service.IDealEnquiryService;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CardType;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.dto.*;
import com.lakala.mini.dto.card.*;
import com.lakala.mini.exception.*;
import com.lakala.mini.server.core.dao.ICardOrgRuleDAO;
import com.lakala.mini.server.core.dao.ILoginMiniUserDao;
import com.lakala.mini.server.core.dao.ILoginMiniUserHisDao;
import com.lakala.mini.server.core.dao.ISpecialTelNoDAO;
import com.lakala.mini.server.core.domain.*;
import com.lakala.mini.server.core.manager.*;
import com.lakala.mini.server.core.manager.exception.CardOrgManagerException;
import com.lakala.mini.service.IMiniService;
import org.hibernate.annotations.common.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.*;
import java.util.Map.Entry;

@Service(value = "miniService")
@WebService
@Transactional
public class MiniServiceImpl implements IMiniService {
	private Logger logger = LoggerFactory.getLogger(MiniServiceImpl.class);
	@Autowired
	private ICardManager cardManager;
	@Autowired
	private IMiniUserManager miniUserManager;
	@Autowired
	private IObjectMapper mapper;
	@Autowired
	private ICardOrganizationManager cardOrganizationManager;
	@Autowired
	private ILoginMiniUserDao loginMiniUserDao;
	@Autowired
	private ILoginMiniUserHisDao loginMiniUserHisDao;
	@Autowired
	private ICardOrgRuleDAO cardOrgRuleDao;
	@Autowired
	private ISpecialTelNoDAO specialTelNoDAO;
	@Resource
	private ICardOrgRuleManager cardOrgRuleManager;
	@Resource
	private IUserService userService;
	@Resource
	private IPsamInfoManager psamInfoManager;
	@Resource
	private IDealEnquiryService dealEnquireService;
	@Override
	@Transactional(readOnly = true)
	public UserMiniInfosDTO getUserMiniInfo(long uid, ApplicationContext context) {
		LoginMiniUser user = miniUserManager.getLoginMiniUser(uid);
		UserMiniInfosDTO map = mapper.map(user, UserMiniInfosDTO.class);
		return map;
	}

	@Override
	public void changePassword(long uid, String userCardNum,
			String oldPassword, String newPassword, ApplicationContext context)
			throws ServiceException, UserCardUnbindException,
            UserCardNotFoundException, UserCardPasswordException,
            NotUserCardOwnerException {
		cardManager.changeUserCardPasswd(uid, userCardNum, oldPassword,
				newPassword, context.getOperatorId());

	}

	@Override
	public String bind(long uid, String password, String userCardNo,
			ApplicationContext context) throws ServiceException {
		String returnCode = CodeDefine.SUCCESS;
		try {
			miniUserManager.bindUserToUserCard(uid, userCardNo, password,
					context.getOperatorId());
		} catch (ApplicationException e) {
			logger.error(e.getErrorCode());
			throw new ServiceException(e.getErrorCode());
		}
		return returnCode;
	}

	// add by QW 2011-04-08
	@Override
	@Transactional(readOnly = false, timeout = 600, rollbackFor = Exception.class)
	public String cardInStore(List<CardResourceDTO> cardResourceDTOs,
			int cardType, ApplicationContext context) throws ServiceException {
		try {
			List<CardResource> cardResources = new ArrayList<CardResource>();
			if (cardResourceDTOs != null) {
				Iterator<CardResourceDTO> i = cardResourceDTOs.iterator();
				while (i.hasNext()) {
					CardResourceDTO cardResourceDTO = i.next();
					CardResource cardResource = mapper.map(cardResourceDTO,
							CardResource.class);
					cardResources.add(cardResource);
				}
			}
			return cardManager.cardInStore(cardResources, cardType, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	// add by QW 2011-04-08
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String initCardOrg(Long[] cardInfoIds, String[] cardNos,
			String orgId, ApplicationContext context) throws ServiceException {

		if (orgId != null && !"".equals(orgId)) {

			CardOrg cardOrg = cardOrganizationManager.getById(
					Long.valueOf(orgId), 0l);
			if (cardOrg == null)
				return CodeDefine.NO_CARD_ORG;
			try {
				return cardManager.initCardOrg(cardInfoIds, cardNos, cardOrg,
						context);
			} catch (ApplicationException e) {
				logger.error(e.getMessage());
				return CodeDefine.SYS_ERR;
			}
		}
		return CodeDefine.PARAM_ERROR;

	}

	@Override
	public CardQueryResultDTO queryCard(CardQueryDTO cardQueryDTO,
                                        String resultType, ApplicationContext context)
			throws ServiceException {
		CardQueryResultDTO resultDTO = null;
		try {
			logger.debug("查询参数为：{}", cardQueryDTO.toString());
			return cardManager.queryCard(cardQueryDTO, resultType, context);
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			resultDTO = new CardQueryResultDTO();
			resultDTO.setRetCode(e.getErrorCode());
		}
		return resultDTO;
	}

	@Override
	public CardOrgQueryResultDTO getMiniCardOrg(CardOrgQueryDTO query,
                                                ApplicationContext context) throws ServiceException {
		if (query != null) {
			Pagination<CardOrg> cardOrgPages = cardOrganizationManager.get(
					query.getIds(), query.getCodes(), query.getNames(),query.getMovingRule(),
					query.isNeedPage(), query.getPageSize(),
					query.getPageStart(), 0l);
			CardOrgQueryResultDTO resultDTO = new CardOrgQueryResultDTO();
			resultDTO.setPageResultDTO(mapper.map(cardOrgPages.getPage(),
					PageResultDTO.class));
			if (cardOrgPages.getData() != null
					&& cardOrgPages.getData().size() > 0) {

				Iterator<CardOrg> cardOrgs = cardOrgPages.getData().iterator();
				List<CardOrgDTO> cardOrgDTOs = new ArrayList<CardOrgDTO>();
				while (cardOrgs.hasNext()) {
					CardOrg cardOrg = cardOrgs.next();
					cardOrgDTOs.add(mapper.map(cardOrg, CardOrgDTO.class));
				}
				resultDTO.setCardOrgDTOs(cardOrgDTOs);
				resultDTO.setRetCode(CodeDefine.SUCCESS);
			}
			return resultDTO;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String resertUserCardPasswd(String cardNo, String mobilePhone,
			ApplicationContext context) throws ServiceException {
		String retCode = null;
		try {
			this.cardManager.resertUserCardPasswd(cardNo, mobilePhone, context);
			retCode = CodeDefine.SUCCESS;
		} catch (ApplicationException e) {
			retCode = e.getErrorCode();
			logger.error(e.getMessage());
		}
		return retCode;
	}

	@Override
	public String changeMiniInfo(String userCardId, String newCardNo,
			String newPsamNo, ApplicationContext context)
			throws ServiceException {
		try {
			miniUserManager.changeMiniInfo(userCardId, newCardNo, newPsamNo,
					context);
			return CodeDefine.SUCCESS;
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			return CodeDefine.SYS_ERR;
		}
	}

	@Override
	public CardOrgDTO updateMiniCardOrg(CardOrgDTO cardOrgDTO,
                                        ApplicationContext context) throws ServiceException {

		try {
			cardOrganizationManager.update(
					mapper.map(cardOrgDTO, CardOrg.class), 0l);
			CardOrg newCardOrg = cardOrganizationManager.getById(
					cardOrgDTO.getId(), 0l);
			return mapper.map(newCardOrg, CardOrgDTO.class);
		} catch (CardOrgManagerException e) {
			logger.error(e.getMessage());
		} catch (MappingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public CardOrgDTO createMiniCardOrg(CardOrgDTO cardOrgDTO,
                                        ApplicationContext context) throws ServiceException,
			ApplicationException {
		try {
			Long newCardOrgID = cardOrganizationManager.create(
					mapper.map(cardOrgDTO, CardOrg.class), 0l);
			CardOrg newCardOrg = cardOrganizationManager.getById(newCardOrgID,
					0l);
			return mapper.map(newCardOrg, CardOrgDTO.class);
		} catch (CardOrgManagerException e) {
			logger.error(e.getMessage());
			throw new ApplicationException(e.getMessage());
		} catch (MappingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String changeCardOrg(Long[] cardInfoIds, String[] cardNos,
			Long cardOrgId, ApplicationContext context) throws ServiceException {
		try {
			return cardManager.changeCardOrg(cardInfoIds, cardNos, cardOrgId,
					context);
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			return e.getErrorCode();
		}

	}

	@Override
	public CardQueryResultDTO getUserCardOperate(String[] operateType,
                                                 Date startTime, Date endTime, ApplicationContext context)
			throws ServiceException {

		try {
			return cardManager.getUserCardOperate(operateType, startTime,
					endTime, context);
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			CardQueryResultDTO result = new CardQueryResultDTO();
			result.setRetCode(CodeDefine.SYS_ERR);
			return result;
		}
	}

	@Override
	@Transactional(readOnly = false, timeout = 600)
	public String udPsamInStore(String[] psamNos, ApplicationContext context)
			throws ServiceException {
		try {
			return cardManager.cardInStore(psamNos, CardType.USER_UD, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	@Override
	@Transactional(readOnly = false, timeout = 600)
	public String psamInStore(String[] psamNos, int type,
			ApplicationContext context) throws ServiceException {
		try {
			return cardManager.psamCardInStore(psamNos, type, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String stopTerminal(Long cardInfoId, ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfo(cardInfoId, null);
		if (cardInfo == null)
			return CodeDefine.NO_USER_CARD;
		if (cardInfo.getStatus() != CardState.BINGDING)
			return CodeDefine.USER_CARD_STATUS_ERROR;
		CardUser cardUser = cardInfo.getCardUser();
		if (cardUser == null)
			return CodeDefine.CARD_USER_NOT_FOUND;
		cardUser.setOperatType(OperateType.HAND_CLOSE);
		cardUser.setChangeDate(new Date(System.currentTimeMillis()));
		miniUserManager.updateCardUser(cardUser, null);
		cardInfo.setStatus(CardState.STOP);
		cardManager.updateCardInfo(cardInfo, null);
		return CodeDefine.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String restartTerminal(Long cardInfoId, ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfo(cardInfoId, null);
		if (cardInfo == null)
			return CodeDefine.NO_USER_CARD;
		if (cardInfo.getStatus() != CardState.STOP)
			return CodeDefine.USER_CARD_STATUS_ERROR;
		CardUser cardUser = cardInfo.getCardUser();
		if (cardUser == null)
			return CodeDefine.CARD_USER_NOT_FOUND;
		cardUser.setOperatType(OperateType.RESTART_CARD);
		cardUser.setChangeDate(new Date(System.currentTimeMillis()));
		cardInfo.setStatus(CardState.BINGDING);
		cardManager.updateCardInfo(cardInfo, null);
		return CodeDefine.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String unBindToUser(Long cardInfoId, ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfo(cardInfoId, null);
		//只返回string，不抛出异常
		String retStr = this.cardManager.unBindToUser(cardInfo);
		if(!CodeDefine.SUCCESS.equals(retStr)){
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
		}
		return retStr;
	}

	@Override
	public CardOrgRuleResultDTO getCardOrgRules(ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardOrgRuleResultDTO result = new CardOrgRuleResultDTO();
		List<CardOrgRule> cardOrgRules = cardOrgRuleDao.findAll();
		List<CardOrgRuleDTO> cardOrgRuleDTOs = new ArrayList<CardOrgRuleDTO>();
		if (cardOrgRules != null && cardOrgRules.size() > 0) {
			Iterator<CardOrgRule> temp = cardOrgRules.iterator();
			while (temp.hasNext()) {
				cardOrgRuleDTOs.add(mapper.map(temp.next(),
						CardOrgRuleDTO.class));
			}
		}
		result.setCardOrgRuleDTOs(cardOrgRuleDTOs);
		result.setRetCode(CodeDefine.SUCCESS);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String recyclePsams(String[] psamNos, ApplicationContext context)
			throws ServiceException, ApplicationException {
		String retStr = cardManager.recyclePsams(psamNos, context);
		if(!CodeDefine.SUCCESS.equals(retStr)){
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
		}
		return retStr;
	}

	@Override
	public String deliverConfirm(Long[] cardInfoIds, ApplicationContext context)
			throws ServiceException, ApplicationException {
		return miniUserManager.deliverConfirm(cardInfoIds, context);
	}

	@Override
	public String revokeDeliverConfirm(Long[] cardInfoIds,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		return miniUserManager.revokeDeliverConfirm(cardInfoIds, context);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String recedeTerminal(Long[] cardInfoIds, ApplicationContext context)
			throws ServiceException, ApplicationException {
		if (cardInfoIds == null || cardInfoIds.length <= 0)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		Collection<CardInfo> cardInfos = cardManager.getCardInfos(cardInfoIds, null);
		String retStr = cardManager.recedeTerminalAndUnBindToUser(cardInfos,context);
		if(!CodeDefine.SUCCESS.equals(retStr)){
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
		}
		return retStr;
	}

	@Override
	public CardQueryResultDTO queryPsamCard(CardQueryDTO cardQueryDTO,
                                            String resultType, ApplicationContext context)
			throws ServiceException {

		CardQueryResultDTO resultDTO = null;
		try {
			logger.debug("查询参数为：{}", cardQueryDTO.toString());
			return cardManager.queryPsamCard(cardQueryDTO, resultType, context);
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			resultDTO = new CardQueryResultDTO();
			resultDTO.setRetCode(e.getErrorCode());
		}
		return resultDTO;

	}

	@Override
	public String miniPsamInStore(String[] psamNos, int type,
			ApplicationContext context) throws ServiceException {
		try {
			return cardManager.psamCardInStore(psamNos, type, null);
		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	/**** ADD BY QW 2011-12-07 ****/
	@Override
	public String revokeInitCardOrg(Long[] cardInfoIds, String[] cardNos,
			String orgId, ApplicationContext context) throws ServiceException {
		try {
			return cardManager.revokeInitCardOrg(cardInfoIds, cardNos, context);
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			return e.getErrorCode();
		}
	}

	@Override
	public CardTelNoQueryResultDTO queryCardTelNos(String cardInfoId,
                                                   String cardNo, ApplicationContext context) throws ServiceException {
		try {
			return cardManager.queryCardTelNos(cardInfoId, cardNo, context);
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public CardTelNoQueryResultDTO replaceCardTelNo(String cardInfoId,
                                                    String oldTelNo, String newTelNo, ApplicationContext context)
			throws ServiceException {
		try {
			return cardManager.replaceCardTelNo(cardInfoId, oldTelNo, newTelNo,
					context);
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public String unBind(long uid, String password, String userCardNum,
			ApplicationContext context) throws ServiceException {
		try {
			CardInfo cardInfo = cardManager.getCardInfo(userCardNum, null);
			if (cardInfo == null)
				throw new ApplicationException(CodeDefine.NO_USER_CARD);
			if (cardInfo.getMiniUser() != null) {
				LoginMiniUser user = cardInfo.getMiniUser();
				if (uid != user.getUid())
					throw new ApplicationException(
							CodeDefine.USER_CARD_BINDING_OTHER);
				if (!cardInfo.getPasswd().equals(password))
					throw new ApplicationException(
							CodeDefine.USER_CARD_PASSWORD_NOT_MATCH);
				return unBindToUser(cardInfo.getId(), context);
			} else
				throw new ApplicationException(CodeDefine.USER_CARD_BINDING);
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getErrorCode());
		}
	}

	@Override
	public TransferMiniApplyResultDTO transferMiniApply(
			TransferMiniApplyDTO apply) throws ServiceException,
            TransferMiniException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferMiniOrderDTO transferMiniReview(TransferMiniReviewDTO review)
			throws ServiceException, TransferMiniException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferMiniQueryResultDTO transferMiniQuery(
            TransferMiniQueryDTO query, ApplicationContext context)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveSpecialTelNos(List<SpecialTelNoDTO> specialTelNos,
			ApplicationContext context) throws ServiceException {
		if (specialTelNos == null || specialTelNos.size() <= 0)
			return CodeDefine.PARAM_ERROR;
		for (Iterator<SpecialTelNoDTO> i = specialTelNos.iterator(); i
				.hasNext();) {
			specialTelNoDAO.save(mapper.map(i.next(), SpecialTelNo.class));
		}
		return CodeDefine.SUCCESS;
	}

	@Override
	public SpecialTelNoQueryResultDTO querySpecialTelNos(
			SpecialTelNoQueryDTO specialTelNoQueryDTO,
			ApplicationContext context) throws ServiceException {
		Pagination<SpecialTelNo> specialTelNos = specialTelNoDAO
				.querySpecialTelNo(specialTelNoQueryDTO.getTelNos(),
						specialTelNoQueryDTO.getAreaNos(), specialTelNoQueryDTO
								.getTypes(), specialTelNoQueryDTO.getStatus(),
						mapper.map(specialTelNoQueryDTO.getPageDTO(),
								Page.class));
		if (specialTelNos != null) {
			SpecialTelNoQueryResultDTO resultDTO = new SpecialTelNoQueryResultDTO();
			resultDTO.setRetCode(CodeDefine.SUCCESS);
			if (specialTelNos.getData() != null
					&& specialTelNos.getData().size() > 0) {
				List<SpecialTelNoDTO> specialTelNoDTOs = new ArrayList<SpecialTelNoDTO>();
				for (Iterator<SpecialTelNo> i = specialTelNos.getData()
						.iterator(); i.hasNext();) {
					specialTelNoDTOs.add(mapper.map(i.next(),
							SpecialTelNoDTO.class));
				}
				resultDTO.setSpecialTelNoDTOs(specialTelNoDTOs);
			}
			resultDTO.setPageResultDTO(mapper.map(specialTelNos.getPage(),
					PageResultDTO.class));
			return resultDTO;
		}
		return null;
	}

	@Override
	public ManuServiceResponse manuOpen(ManuServiceRequest serviceParam,
                                        ApplicationContext context) throws ApplicationException {

		return cardManager.manuOpen(serviceParam, null);
	}

	@Override
	public ManuServiceResponse manuMove(ManuServiceRequest serviceParam,
                                        ApplicationContext context) throws ApplicationException {

		return cardManager.manuMove(serviceParam, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IMiniService#updateRuleParam(java.lang.Long,
	 * java.lang.String, java.lang.String, java.lang.String, java.util.List,
	 * boolean, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	public void updateRuleParam(Integer ruleId, String paramCode,
			String paramName, String paramdesc, List<String> paramValues,
			boolean isAppend, ApplicationContext context)
			throws ApplicationException {
		cardOrgRuleManager.batchUpdateParam(ruleId, paramCode, paramdesc,
				paramdesc, paramValues, isAppend);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IMiniService#deleteRuleParam(java.util.List,
	 * com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	public void deleteRuleParam(List<Long> id, ApplicationContext context)
			throws ApplicationException {
		cardOrgRuleManager.deleteParamById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniService#queryRuleParam(com.lakala.mini.dto
	 * .RuleParamQueryDTO, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	public RuleParamsDTO queryRuleParam(RuleParamQueryDTO query,
                                        ApplicationContext context) throws ApplicationException {
		Page page = mapper.map(query, Page.class);
		Pagination<CardOrgRuleParam> reuslt = this.cardOrgRuleManager
				.queryCardRuleParam(query.getId(), query.getRuleId(),
						query.getParamCode(), query.getParamName(),
						query.getParamValue(), page);
		return mapper.map(reuslt, RuleParamsDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniService#saveRuleParam(com.lakala.mini.dto
	 * .RuleParamDTO, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	public SaveRuleParamResponse saveRuleParam(RuleParamDTO ruleParam,
                                               ApplicationContext context) throws ApplicationException {
		CardOrgRuleParam cardOrgRuleParam = this.mapper.map(ruleParam,
				CardOrgRuleParam.class);
		Long id = cardOrgRuleManager.saveRuleParam(cardOrgRuleParam);
		return new SaveRuleParamResponse(id, CodeDefine.SUCCESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IMiniService#resetUDPasswd(java.lang.String,
	 * java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String resetUDPasswd(Long cardInfoId,
			String mobilePhone, ApplicationContext context) {
		String retCode = null;
		try {
			this.cardManager.resetUDPasswdByCardInfoId(cardInfoId, mobilePhone,
					context);
			retCode = CodeDefine.SUCCESS;
		} catch (ApplicationException e) {
			retCode = e.getErrorCode();
			logger.error(e.getMessage());
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniService#queryUserInfo(com.lakala.mini.dto
	 * .QueryUserInfoRequest, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	public CustomerInfosDTO queryCustomerInfo(QueryUserInfoRequest query,
                                              ApplicationContext context) {
		CustomerInfosDTO result=new CustomerInfosDTO();
		List<CustomerInfoDTO> customerInfoDTOs=new ArrayList<CustomerInfoDTO>();
		result.setDatas(customerInfoDTOs);
		String psam = query.getPsam();
		String bindingTelNo = query.getBindingTelNo();
		String customerMobileNum = query.getCustomerMobileNum();
		String openMobileNum = query.getOpenMobileNum();
		Map<String,ViewPsamNo> psams=   new HashMap<String,ViewPsamNo>();
		Map<Long,UserDTO> users=new HashMap<Long,UserDTO>();
		Map<String,TermInfoDTO> termInfos=new HashMap<String,TermInfoDTO>();
		Long userId=null;
		Set<Long> crops=new HashSet<Long>();
		Map<Long,CardOrg> cropMap=new HashMap<Long,CardOrg>();
		Set<Long> userIds=new HashSet<Long> ();
		//查找用户信息
		if(logger.isDebugEnabled()){
			logger.debug("customerMobileNum:"+customerMobileNum);
		}
		if(StringUtils.hasLength(customerMobileNum)){
			List<AuthenticationDetailLevel> authenticationDetailLevels=new ArrayList<AuthenticationDetailLevel>();
			authenticationDetailLevels.add(AuthenticationDetailLevel.USER_INFO);
			List<String> metaTypeCode=new ArrayList<String>();
			AuthenticationDTO authentication = userService.getAuthentication(customerMobileNum, AuthenticationType.MOBILE_NUM, authenticationDetailLevels, metaTypeCode, context);
			if(authentication!=null&&authentication.getUser()!=null){
				UserDTO user = authentication.getUser();
				users.put(user.getId(), user);
				userId=user.getId();
				if(logger.isDebugEnabled()){
					logger.debug("userid:"+userId);
				}
			}
		}
		//查找psam信息
		Set<String> psamsCollection=new HashSet<String>();
		if(StringHelper.isNotEmpty(psam)){
			psamsCollection.add(psam);
		}
		
		List<CardUser> cadrUsers = miniUserManager.findCardUsers(bindingTelNo,openMobileNum,userId);
		if(CollectionHelper.isNotEmpty(cadrUsers)){
			for (CardUser cardUser : cadrUsers) {
				String psamNo = cardUser.getPsamNo();
				psamsCollection.add(psamNo);
			}
		}
		
		List<ViewPsamNo> queryViewPsamInfo = this.psamInfoManager.getByPsams(psamsCollection);
		for (ViewPsamNo viewPsamNo : queryViewPsamInfo) {
			psams.put(viewPsamNo.getPsamNo(), viewPsamNo);
			CardInfo cardInfo = viewPsamNo.getCardInfo();
			if(cardInfo!=null&&cardInfo.getMiniUser()!=null){
				userIds.add(cardInfo.getMiniUser().getUid());
				crops.add(cardInfo.getCopFlag());
			}
		}
		//去除已获得的用户信息信息
		userIds.remove(userId);
		//查找psam用户信息
		if(userIds.size()>0){
			long[] userIdArray=new long[userIds.size()];
			Iterator<Long> iterator = userIds.iterator();
			for (int i=0 ; iterator
					.hasNext();i++) {
				userIdArray[i]=iterator.next();
			}
			PageDTO page=null;
			UsersDTO userInfosByUids = userService.getUserInfosByUids(userIdArray, page, context);
			if(userInfosByUids!=null&&userInfosByUids.getDatas()!=null){
				for (UserDTO user : userInfosByUids.getDatas()) {
					users.put(user.getId(), user);
				}
			}
		}
		if(psams.keySet().size()>0){
			TermInfosQueryRequest request=new TermInfosQueryRequest(new ArrayList<String>(psams.keySet()));
			QueryTermInfoResponse termInfoLists = dealEnquireService.queryTermInfo(request);
			List<TermInfoDTO> datas = termInfoLists.getDatas();
			if(datas!=null){
				for (TermInfoDTO termInfo : datas) {
					termInfos.put(termInfo.getTermid(), termInfo);
				}
			}
		}
		if(crops.size()>0){
			Long[] idarray=new Long[crops.size()];
			idarray=crops.toArray(idarray);
			String[] codes=null;
			String [] name=null;
			Pagination<CardOrg> pagination = this.cardOrganizationManager.get(idarray, codes, name, false, 0, 0, 0l);
			if(pagination!=null&&pagination.getData()!=null){
				List<CardOrg> data = pagination.getData();
				if(data.size()>0){
					for (CardOrg cardOrg : data) {
						cropMap.put(cardOrg.getId(), cardOrg);
					}
				}
			}
		}
		Set<Entry<String, ViewPsamNo>> entrySet = psams.entrySet();
		for (Entry<String, ViewPsamNo> entry : entrySet) {
			ViewPsamNo viewPsamNo = entry.getValue();
			CustomerInfoDTO dto = mapper.map(viewPsamNo, CustomerInfoDTO.class);
			CardInfo cardInfo = viewPsamNo.getCardInfo();
			if(cardInfo!=null&&cardInfo.getMiniUser()!=null){
				UserDTO user = users.get( new Long(cardInfo.getMiniUser().getUid()));
				mapper.map(cardInfo, dto);
				mapper.map(user, dto);
				mapper.map(cropMap.get(cardInfo.getCopFlag()), dto);
			}
			mapper.map(termInfos.get(entry.getKey()), dto);
			
			customerInfoDTOs.add(dto);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.service.IMiniService#getUidByPsamNo(java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@WebMethod(operationName = "GetUidByPsamNo")
	public GetUidByPsamNoResponse getUidByPsamNo(
			@WebParam(name = "psamNo") String psamNo,
			@WebParam(name = "context") ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if(cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		LoginMiniUser loginMiniUser = cardInfo.getMiniUser();
		Long userId=null;
		if(loginMiniUser != null){
			userId= loginMiniUser.getUid();
		}
		GetUidByPsamNoResponse getUidByPsamNoResponse = new GetUidByPsamNoResponse();
		getUidByPsamNoResponse.setUserId(userId);
		return getUidByPsamNoResponse;
	}

}
