/**
 * 
 */
package com.lakala.mini.server.core.service.impl;

import com.lakala.ca.util.StringHelper;
import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.ServiceException;
import com.lakala.common.util.CollectionHelper;
import com.lakala.common.util.Tools;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.objmapper.IObjectMapper;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CardType;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.dto.PsamInfoDTO;
import com.lakala.mini.dto.UserMiniInfoDTO;
import com.lakala.mini.dto.UserMiniInfosDTO;
import com.lakala.mini.dto.card.UDServiceRequestDTO;
import com.lakala.mini.dto.card.UDServiceRequestExtDTO;
import com.lakala.mini.dto.work.*;
import com.lakala.mini.exception.CardUserNotFoundException;
import com.lakala.mini.exception.UserCardNotBindException;
import com.lakala.mini.server.core.dao.ICardOrgRuleParamDAO;
import com.lakala.mini.server.core.dao.ICardUserExtInfoDAO;
import com.lakala.mini.server.core.dao.ILoginMiniUserDao;
import com.lakala.mini.server.core.domain.*;
import com.lakala.mini.server.core.manager.*;
import com.lakala.mini.service.IMiniService;
import com.lakala.mini.service.IUDService;
import com.lakala.mini.service.IWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.*;

/**
 * U盾用户服务实现类
 * 
 * @author qw
 * @UDServiceImpl.java
 * @2011-5-20 下午03:59:15
 */
@Service(value = "uDService")
@WebService
@Transactional
public class UDServiceImpl implements IUDService {
	private Logger logger = LoggerFactory.getLogger(UDServiceImpl.class);
	@Autowired
	private IPsamInfoManager psamInfoManager;
	@Autowired
	private ICardManager cardManager;
	@Autowired
	private IMiniUserManager miniUserManager;
	@Autowired
	private ILoginMiniUserDao loginMiniUserDao;
	@Autowired
	private ICardOrganizationManager cardOrganizationManager;

	@Autowired
	private ICardOrgRuleParamDAO cardOrgRuleParamDAO;
	@Autowired
	private ICardUserExtInfoDAO cardUserExtInfoDAO;
	@Autowired
	private IObjectMapper mapper;
	@Autowired
	private IMiniService miniService;
	@Resource
	private IWorkService workService;
	@Resource
	private ICardOrgRuleManager cardOrgRuleManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#checkUDStatus(java.lang.String,
	 * com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	public String checkUDStatus(String psamNo, ApplicationContext context)
			throws ServiceException {
		PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);
		if (psamInfo == null)
			return CodeDefine.NO_PSAM;
		if (psamInfo.getPasmState() == CardState.BINGDING) {
			return "1";
		} else {
			return String.valueOf(psamInfo.getPasmState());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#initUDPasswd(java.lang.String,
	 * java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String initUDPasswd(String psamNo, String passwd, String areaNo,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		return initTerminal(psamNo, passwd, areaNo, null, null,null, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#checkUDPasswd(java.lang.String,
	 * java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	public String checkUDPasswd(String psamNo, String passwd,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if (passwd == null || "".equals(passwd))
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		if (passwd.equals(cardInfo.getPasswd()))
			return CodeDefine.SUCCESS;
		return CodeDefine.PWD_ERR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#changeUDPasswd(java.lang.String,
	 * java.lang.String, java.lang.String,
	 * com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String changeUDPasswd(String psamNo, String oldPasswd,
			String newPasswd, ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if (oldPasswd == null || "".equals(oldPasswd) || newPasswd == null
				|| "".equals(newPasswd))
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		if (!oldPasswd.equals(cardInfo.getPasswd()))
			throw new ApplicationException(CodeDefine.PWD_ERR);
		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if (newPasswd == null || newPasswd.length() < 6
				|| newPasswd.length() > 12)
			throw new ApplicationException(CodeDefine.PWD_STRENGTH_ERR);
		if (cardInfo.getStatus() == CardState.BINGDING) {
			cardInfo.setPasswd(newPasswd);
			cardUser.setOperatType(OperateType.CHANGE_CARD_PASSWD);
			cardUser.setTelModifyDate(new Date());
			cardUser.setChangeDate(new Date());
			cardManager.updateCardInfo(cardInfo, null);
			miniUserManager.updateCardUser(cardUser, null);
			return CodeDefine.SUCCESS;
		} else {
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#resertUDPasswd(java.lang.String,
	 * java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String resertUDPasswd(String psamNo, String newPasswd,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if (newPasswd == null || newPasswd.length() < 6
				|| newPasswd.length() > 12)
			throw new ApplicationException(CodeDefine.PWD_STRENGTH_ERR);
		if (cardInfo.getStatus() == CardState.BINGDING) {
			cardInfo.setPasswd(newPasswd);
			cardUser.setOperatType(OperateType.RESET_CARD_PASSWD);
			cardUser.setTelModifyDate(new Date());
			cardUser.setChangeDate(new Date());
			cardManager.updateCardInfo(cardInfo, null);
			miniUserManager.updateCardUser(cardUser, null);
			return CodeDefine.SUCCESS;
		} else {
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#dropUD(java.lang.String,
	 * com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String dropUD(String psamNo, ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if (cardInfo.getStatus() == CardState.BINGDING) {
			cardInfo.setStatus(CardState.DROP);
			cardUser.setOperatType(OperateType.DROP_CARD);
			cardUser.setTelModifyDate(new Date());
			cardUser.setChangeDate(new Date());
			cardManager.updateCardInfo(cardInfo, null);
			miniUserManager.updateCardUser(cardUser, null);
			return CodeDefine.SUCCESS;
		} else {
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#restartUD(java.lang.String,
	 * java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	public String restartUD(String psamNo, String passwd,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if (cardInfo.getStatus() == CardState.DROP
				&& CodeDefine.SUCCESS.equals(this.checkUDPasswd(psamNo, passwd,
						context))) {
			cardInfo.setStatus(CardState.BINGDING);
			cardUser.setOperatType(OperateType.RESTART_CARD);
			cardUser.setTelModifyDate(new Date());
			cardUser.setChangeDate(new Date());
			cardManager.updateCardInfo(cardInfo, null);
			miniUserManager.updateCardUser(cardUser, null);
			return CodeDefine.SUCCESS;
		} else {
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#bindUDToUser(long,
	 * java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { ApplicationException.class })
	public String bindUDToUser(long uid, String psamNo, String passwd,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if (passwd == null || "".equals(passwd)
				|| !passwd.equals(cardInfo.getPasswd()))
			throw new ApplicationException(CodeDefine.PWD_ERR);
		if (this.checkPsamNoBindUser(psamNo, context))
			throw new ApplicationException(CodeDefine.PSAM_HAS_BINDING);
		if (cardInfo.getStatus() != CardState.BINGDING)
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		LoginMiniUser user = loginMiniUserDao.getByUid(uid);
		if (user == null) {
			user = new LoginMiniUser();
			user.setUid(uid);
		}
		Set<CardInfo> cardInfos = user.getCardInfos();
		if (cardInfos == null)
			cardInfos = new HashSet<CardInfo>();
		cardInfos.add(cardInfo);
		loginMiniUserDao.save(user);
		return CodeDefine.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IUDService#unBindUDToUser(long,
	 * java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String unBindUDToUser(long uid, String psamNo, String passwd,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if (passwd == null || "".equals(passwd)
				|| !passwd.equals(cardInfo.getPasswd()))
			throw new ApplicationException(CodeDefine.PWD_ERR);
		LoginMiniUser user = loginMiniUserDao.getByUid(uid);
		if (user == null || user.getCardInfos() == null
				|| user.getCardUsers() == null)
			throw new UserCardNotBindException();
		Iterator<CardInfo> cardInfos = user.getCardInfos().iterator();

		while (cardInfos.hasNext()) {
			CardInfo oCardInfo = cardInfos.next();
			if (oCardInfo.getId().longValue() == cardInfo.getId().longValue())
				cardInfos.remove();
		}
		loginMiniUserDao.save(user);
		// if(user.getCardInfos() == null || user.getCardInfos().size() == 0)
		// this.deleteBindUDUser(uid, context);

		return CodeDefine.SUCCESS;
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	private void deleteBindUDUser(long uid, ApplicationContext context)
			throws ApplicationException {
		loginMiniUserDao.remove(uid);
	}

	@Override
	@Transactional(readOnly = true)
	public UserMiniInfosDTO getUserUDInfo(long uid, ApplicationContext context)
			throws ServiceException {
		LoginMiniUser user = miniUserManager.getLoginMiniUser(uid);
		if (user != null && user.getCardInfos() != null) {
			Iterator<CardInfo> cardInfos = user.getCardInfos().iterator();
			while (cardInfos.hasNext()) {
				CardInfo cardInfo = cardInfos.next();
				if (cardInfo.getType() != CardType.USER_UD) {
					cardInfos.remove();
					Iterator<CardUser> cardUsers = user.getCardUsers()
							.iterator();
					while (cardUsers.hasNext()) {
						CardUser cardUser = cardUsers.next();
						if (cardUser.getCardInfoId() == cardInfo.getId()
								.longValue())
							cardUsers.remove();
					}
				}
			}
		}
		UserMiniInfosDTO map = mapper.map(user, UserMiniInfosDTO.class);
		return map;

	}

	@Override
	@Transactional(readOnly = true)
	public UserMiniInfosDTO getUserTerminalInfoByType(long uid, int cardType,
                                                      ApplicationContext context) throws ServiceException {
		LoginMiniUser user = miniUserManager.getLoginMiniUser(uid);
		if (user != null && user.getCardInfos() != null) {
			if (cardType >= 0) {
				Iterator<CardInfo> cardInfos = user.getCardInfos().iterator();
				while (cardInfos.hasNext()) {
					CardInfo cardInfo = cardInfos.next();
					if (cardInfo.getType() != cardType) {
						cardInfos.remove();
						Iterator<CardUser> cardUsers = user.getCardUsers()
								.iterator();
						while (cardUsers.hasNext()) {
							CardUser cardUser = cardUsers.next();
							if (cardUser.getCardInfoId() == cardInfo.getId()
									.longValue())
								cardUsers.remove();
						}
					}
				}
			}
		}
		UserMiniInfosDTO map = mapper.map(user, UserMiniInfosDTO.class);
		return map;

	}

	@Override
	public boolean checkPsamNoBindUser(String psamNo, ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);

		LoginMiniUser loginMiniUser = cardInfo.getMiniUser();
		if (loginMiniUser == null)
			return false;
		return true;
	}

	@Override
	public long getUidByPsamNo(String psamNo, ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		LoginMiniUser loginMiniUser = cardInfo.getMiniUser();
		if (loginMiniUser == null)
			return -1;
		return loginMiniUser.getUid();
	}

	@Override
	public String getLineNoByPsamNo(String psamNo, ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);

		return cardInfo.getLineNo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IUDService#reInitTerminalByPsamNo(java.lang.String
	 * , com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String reInitTerminalByPsamNo(String psamNo,
			ApplicationContext context) throws ServiceException,
			ApplicationException {

		CardInfo cardInfo = null;
		CardInfo recycleCardInfo = null;
		String status = CodeDefine.SUCCESS;
		String retCode= CodeDefine.SUCCESS;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("初始化终端信息 start. psam:" + psamNo);
			}
			this.assertNotNull(psamNo, CodeDefine.NO_PSAM);
			cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);

			if (cardInfo == null)
				throw new ApplicationException(CodeDefine.NO_PSAM);
			/*************** Begin 2012-7-20 QW 增加是否可以转赠的判断 *********************/
			if (this.isMpos(cardInfo.getCardUser().getPsamNo())||!cardInfo.canReInitTerminal())
				throw new ApplicationException(
						CodeDefine.CAN_NOT_REINITTERMINAL);
			/*************** Eed *********************/
			// 保存机构号，回库时可能会清空机构
			final Long cardOrgId = cardInfo.getCopFlag();
			
			// 增加转赠次数限制
			CardOrgPublicParam cardOrgPublicParam = cardOrgRuleManager
					.getCardOrgPublicParam(cardOrgId);
			// 回库后变为0,需要设置为原来的值
			PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);
			Integer donationCount = psamInfo
					.increaceDonationCount(cardOrgPublicParam);
			
			if (cardInfo.getStatus() != CardState.BINGDING)
				throw new ApplicationException(
						CodeDefine.USER_CARD_STATUS_ERROR);

			int cardType = cardInfo.getType();
			//解绑并退机
			retCode=cardManager.recedeTerminalAndUnBindToUser(cardInfo, context);
			if(!CodeDefine.SUCCESS.equals(retCode)){
				throw new ApplicationException(retCode);
			}
			//回库
			recycleCardInfo = cardManager.recyclePsam(cardInfo, context);
			// miniService.recyclePsams(new String[]{psamNo}, context);

			if (logger.isDebugEnabled()) {
				logger.debug("newCardInfo.getId:{},cardOrgId:{}", cardInfo
						.getId().longValue(), cardOrgId);
				logger.debug("newCardInfo:{}", cardInfo.toString());
			}

			CardOrg cardOrg = cardOrganizationManager.getById(cardOrgId, 0l);
			//初始化
			retCode=cardManager.initCardOrg(recycleCardInfo, cardOrg, context);
			if(!CodeDefine.SUCCESS.equals(retCode)){
				throw new ApplicationException(retCode);
			}
			//自助开通
			importUser(psamNo, cardInfo, cardOrg, cardType);

			// 退机初始化后变为0,设置为原来的值
			psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);
			psamInfo.setDonationCount(donationCount);
			if (logger.isDebugEnabled()) {
				logger.debug("初始化终端信息 end. psam:" + psamNo);
			}
			return status;
		}catch(ApplicationException e) {
			status = e.getErrorCode();
			throw e;
		}
		catch (Exception e) {
			status = CodeDefine.OTHER_ERR;
			throw new ApplicationException(e);
		} finally {
			//记录移机日志
			ReInitTerminalAudit entity = new ReInitTerminalAudit(cardInfo,
					recycleCardInfo, psamNo, status);
			cardManager.saveReInitTerminalAudit(entity);
		}
	}

	/**
	 * @param psamNo
	 * @param noPsam
	 * @throws ApplicationException
	 */
	private void assertNotNull(String psamNo, String msg)
			throws ApplicationException {
		if (StringHelper.hasNoText(psamNo)) {
			throw new ApplicationException(msg);
		}
	}

	/**
	 * 自助开通用户
	 * 
	 * @param psamNo
	 * @param cardInfo
	 * @param cardOrgId
	 * @param cardType
	 * @throws ApplicationException
	 */
	private void importUser(String psamNo, CardInfo cardInfo, CardOrg cardOrg,
                            int cardType) throws ApplicationException {
		if (logger.isDebugEnabled()) {
			logger.debug("自助开通用户 start. cardInfoId:" + cardInfo.getId());
		}
		CommonUserInfo commonUserInfo = new CommonUserInfo();
		CardUser cardUser = cardInfo.getCardUser();
		Context workContext = new Context();
		workContext.setSystemCode("mini");
		commonUserInfo.setPsamNo(psamNo);
		commonUserInfo.setAreaNo(cardUser.getArea());
		commonUserInfo.setStatus(0);// 关闭并进行终端转赠
		String userId = String.valueOf(cardUser.getCardInfoId());
		if (userId.length() < 8)
			userId = Tools.getStringFromString(userId, 8, "0", true);
		commonUserInfo.setUserId(userId);
		commonUserInfo.setChangeDate(new Date(System.currentTimeMillis()));
		commonUserInfo.setPhoneNo("4600L00" + userId);
		commonUserInfo.setCardOrg(cardOrg.getCode());
		commonUserInfo.setUserType(cardType);
		ImportUserRequestType parameters = new ImportUserRequestType();
		parameters.setContext(workContext);
		parameters.setUserInfo(commonUserInfo);
		try {
			ImportUserResponseType response = new ImportUserResponseType();
			response = workService.importUser(parameters);
			if (logger.isDebugEnabled()) {
				logger.debug("response:" + response.isSuccess());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ApplicationException(e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("自助开通用户 end. cardInfoId:" + cardInfo.getId());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String initTerminal(UDServiceRequestDTO requestDTO,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		String psamNo = requestDTO.getPsamNo();
		String areaNo = requestDTO.getAreaNo();
		String passwd = requestDTO.getPasswd();
		String bindingNo = requestDTO.getBindingNo();
		return initTerminal(psamNo, passwd, areaNo, bindingNo, null,null, context);
	}

	@Override
	public String getLineNo(UDServiceRequestDTO requestDTO,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		if (requestDTO == null)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		String psamNo = requestDTO.getPsamNo();
		String bindingNo = requestDTO.getBindingNo();
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if (cardInfo.getMovingRule() == 4) {
			if (bindingNo == null || "".equals(bindingNo))
				throw new ApplicationException(CodeDefine.PARAM_ERROR,
						"bindingNo不能为空");
			if (cardInfo.getCardUser().getBindingTelNo() == null
					|| "".equals(cardInfo.getCardUser().getBindingTelNo()))
				throw new ApplicationException(CodeDefine.SYS_ERR,
						"cardInfo.getCardUser().getBindingTelNo()为空");
			if (!bindingNo.equals(cardInfo.getCardUser().getBindingTelNo())) {
				if (cardInfo.getCardUser().getBindingDate() == null)
					throw new ApplicationException(CodeDefine.SYS_ERR);
				Calendar bindingDate = Calendar.getInstance();
				bindingDate.setTime(cardInfo.getCardUser().getBindingDate());
				bindingDate.set(Calendar.MONTH,
						bindingDate.get(Calendar.MONTH) + 24);
				if (System.currentTimeMillis() - bindingDate.getTimeInMillis() < 0)
					throw new ApplicationException(CodeDefine.BINDING_NO_ERROR);
			}

		}
		return cardInfo.getLineNo();
	}

	private String initTerminal(String psamNo, String passwd, String areaNo,
			String bindingNo, String mobilePhoneNum,String channel, ApplicationContext context)
			throws ServiceException, ApplicationException {

		PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);
		if (psamInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if (cardUser == null)
			throw new CardUserNotFoundException();
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null
				|| cardInfo.getStatus() != CardState.INITIALIZATION)
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);

		if (cardInfo != null
				&& cardInfo.getStatus() == CardState.INITIALIZATION) {
			if (cardInfo.getMovingRule() == 2
					&& !OperateType.DELIVER_CONFIRM.equals(cardUser
							.getOperatType()))
				throw new ApplicationException(CodeDefine.CARD_HAS_NOT_DELIVER);
			if (passwd == null || passwd.length() < 6 || passwd.length() > 12)
				throw new ApplicationException(CodeDefine.PWD_STRENGTH_ERR);
			if (cardInfo.getMovingRule() == 4
					&& (bindingNo == null || "".equals(bindingNo)))
				throw new ApplicationException(CodeDefine.BINDING_NO_ISNULL);
			cardInfo.setPasswd(passwd);
			cardInfo.setStatus(CardState.BINGDING);
			cardUser.setOperatType(OperateType.AUTO_OPEN);
			cardUser.setBindingDate(new Date());
			cardUser.setChangeDate(new Date());
			cardUser.setSelfOpenDate(new Date());
			cardUser.setTelMovingCount(0);
			cardUser.setTelModifyDate(new Date());
			cardUser.setTelAreaNo(areaNo);
			cardUser.setArea(areaNo);
			if (bindingNo != null && !"".equals(bindingNo))
				cardUser.setBindingTelNo(bindingNo);
			if (mobilePhoneNum != null && !"".equals(mobilePhoneNum))
				cardUser.setUserMobile(mobilePhoneNum);
			Context workContext = new Context();
			workContext.setSystemCode("mini");
			if (cardInfo.getType() == CardType.USER_UD) {
				UserInfo userInfo = new UserInfo();
				userInfo.setPsamNo(psamNo);
				userInfo.setAreaNo(areaNo);
				String userId = String.valueOf(cardUser.getCardInfoId());
				if (userId.length() < 8)
					userId = Tools.getStringFromString(userId, 8, "0", true);
				userInfo.setUserId(userId);
				userInfo.setChangeDate(new Date(System.currentTimeMillis()));
				userInfo.setPhoneNo("4600L00" + userId);
				userInfo.setCardOrg((cardOrganizationManager.getById(
						cardInfo.getCopFlag(), 0l)).getCode());
				// userInfo.setMobile(mobilePhoneNum);
				ImportSUUserRequestType parameters = new ImportSUUserRequestType();
				parameters.setContext(workContext);
				parameters.setUserInfo(userInfo);
				ImportSUUserResponseType response = new ImportSUUserResponseType();
				try {
					response = workService.importSUUser(parameters);
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new ApplicationException(e);
				}
				if (response != null && response.isSuccess()) {
					cardUser.setNodeNo(response.getStoreNo());
				}
			} else if (cardInfo.getType() == CardType.UESR_SJ) {
				CommonUserInfo userInfo = new CommonUserInfo();
				userInfo.setPsamNo(psamNo);
				userInfo.setAreaNo(areaNo);
				userInfo.setStatus(1);
				String userId = String.valueOf(cardUser.getCardInfoId());
				if (userId.length() < 8)
					userId = Tools.getStringFromString(userId, 8, "0", true);
				userInfo.setUserId(userId);
				userInfo.setChangeDate(new Date(System.currentTimeMillis()));
				userInfo.setPhoneNo("4600L00" + userId);
				userInfo.setCardOrg((cardOrganizationManager.getById(
						cardInfo.getCopFlag(), 0l)).getCode());
				userInfo.setUserType(cardInfo.getType());
				ImportUserRequestType parameters = new ImportUserRequestType();
				parameters.setContext(workContext);
				parameters.setUserInfo(userInfo);
				ImportUserResponseType response = new ImportUserResponseType();
				try {
					response = workService.importUser(parameters);
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new ApplicationException(e);
				}
				if (response != null && response.isSuccess()) {
					cardUser.setNodeNo(response.getStoreNo());
				}
			} else if (cardInfo.getType() == CardType.USER_POS_PLUS) {
				CommonUserInfo userInfo = new CommonUserInfo();
				userInfo.setPsamNo(psamNo);
				userInfo.setAreaNo(areaNo);
				userInfo.setStatus(1);
				String userId = String.valueOf(cardUser.getCardInfoId());
				if (userId.length() < 8)
					userId = Tools.getStringFromString(userId, 8, "0", true);
				userInfo.setUserId(userId);
				userInfo.setChangeDate(new Date(System.currentTimeMillis()));
				userInfo.setPhoneNo("4600L00" + userId);
				userInfo.setCardOrg((cardOrganizationManager.getById(
						cardInfo.getCopFlag(), 0l)).getCode());
				userInfo.setUserType(cardInfo.getType());
				ImportUserRequestType parameters = new ImportUserRequestType();
				parameters.setContext(workContext);
				parameters.setUserInfo(userInfo);
				ImportUserResponseType response = new ImportUserResponseType();
				try {
					response = workService.importUser(parameters);
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new ApplicationException(e);
				}
				if (response != null && response.isSuccess()) {
					cardUser.setNodeNo(response.getStoreNo());
				}
			}
			cardManager.updateCardInfo(cardInfo, null);
			miniUserManager.updateCardUser(cardUser, null);
			return CodeDefine.SUCCESS;
		}
		return CodeDefine.OTHER_ERR;
	}

	@Override
	public String canReInitTerminalByPsamNo(UDServiceRequestDTO requestDTO,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		if (requestDTO == null)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		String psamNo = requestDTO.getPsamNo();
		if (psamNo == null || "".equals(psamNo))
			throw new ApplicationException(CodeDefine.PARAM_ERROR, "PsamNo为空");
		if(isMpos(psamNo)){
			return "false";
		}
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if (!cardInfo.canReInitTerminal())
			return "false";
		return CodeDefine.SUCCESS;
	}

	/**
	 * @param psamNo
	 */
	private boolean isMpos(String psamNo) {
//		return psamNo.startsWith("CBC3A3B2")
//				||psamNo.startsWith("CBC4A4BF");
		return false;
			
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String initSJTerminal(UDServiceRequestDTO requestDTO,
                                 UDServiceRequestExtDTO requestExtDTO, ApplicationContext context)
			throws ServiceException, ApplicationException {
		if (requestDTO == null)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		String psamNo = requestDTO.getPsamNo();
		String areaNo = requestDTO.getAreaNo();
		String passwd = requestDTO.getPasswd();
		String bindingNo = requestDTO.getBindingNo();
		String telecomOperators = null;
		String mobilePhoneNum = null;
		String mobilePhoneManuFacturer = null;
		String mobilePhoneModel = null;
		String mobilePhoneProduct = null;

		if (requestExtDTO != null) {
			telecomOperators = requestExtDTO.getTelecomOperators();
			mobilePhoneNum = requestExtDTO.getMobilePhoneNum();
			mobilePhoneManuFacturer = requestExtDTO
					.getMobilePhoneManuFacturer();
			mobilePhoneModel = requestExtDTO.getMobilePhoneModel();
			mobilePhoneProduct = requestExtDTO.getMobilePhoneProduct();
		}

		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if (cardUser == null)
			throw new CardUserNotFoundException();
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null
				|| cardInfo.getStatus() != CardState.INITIALIZATION)
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		CardUserExtInfo extInfo = new CardUserExtInfo();
		extInfo.setRecordDate(new Date(System.currentTimeMillis()));
		extInfo.setMobilePhoneNum(mobilePhoneNum);
		extInfo.setTelecomOperators(telecomOperators);
		extInfo.setMobilePhoneModel(mobilePhoneModel);
		extInfo.setMobilePhoneProduct(mobilePhoneProduct);
		extInfo.setMobilePhoneManuFacturer(mobilePhoneManuFacturer);
		extInfo.setCardUserId(cardUser.getId());
		cardUserExtInfoDAO.save(extInfo);

		this.validCardUserExtInfo(requestDTO, requestExtDTO, true);
		return initTerminal(psamNo, passwd, areaNo, bindingNo, mobilePhoneNum,null,
				context);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String initTDTerminal(UDServiceRequestDTO requestDTO,
                                 UDServiceRequestExtDTO requestExtDTO, String channel, ApplicationContext context)
			throws ServiceException, ApplicationException {
		if (requestDTO == null)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		String psamNo = requestDTO.getPsamNo();
		String areaNo = requestDTO.getAreaNo();
		String passwd = requestDTO.getPasswd();
		String bindingNo = requestDTO.getBindingNo();
		String telecomOperators = null;
		String mobilePhoneNum = null;
		String mobilePhoneManuFacturer = null;
		String mobilePhoneModel = null;
		String mobilePhoneProduct = null;

		if (requestExtDTO != null) {
			telecomOperators = requestExtDTO.getTelecomOperators();
			mobilePhoneNum = requestExtDTO.getMobilePhoneNum();
			mobilePhoneManuFacturer = requestExtDTO
					.getMobilePhoneManuFacturer();
			mobilePhoneModel = requestExtDTO.getMobilePhoneModel();
			mobilePhoneProduct = requestExtDTO.getMobilePhoneProduct();
		}

		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if (cardUser == null)
			throw new CardUserNotFoundException();
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null
				|| cardInfo.getStatus() != CardState.INITIALIZATION)
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		CardUserExtInfo extInfo = new CardUserExtInfo();
		extInfo.setRecordDate(new Date(System.currentTimeMillis()));
		extInfo.setMobilePhoneNum(mobilePhoneNum);
		extInfo.setTelecomOperators(telecomOperators);
		extInfo.setMobilePhoneModel(mobilePhoneModel);
		extInfo.setMobilePhoneProduct(mobilePhoneProduct);
		extInfo.setMobilePhoneManuFacturer(mobilePhoneManuFacturer);
		extInfo.setCardUserId(cardUser.getId());
		cardUserExtInfoDAO.save(extInfo);

		this.validCardUserExtInfo(requestDTO, requestExtDTO, true);
		return initTerminal(psamNo, passwd, areaNo, bindingNo, mobilePhoneNum,channel,
				context);
	}
	
	@Override
	public String getSJLineNo(UDServiceRequestDTO requestDTO,
                              UDServiceRequestExtDTO requestExtDTO, ApplicationContext context)
			throws ServiceException, ApplicationException {

		if (requestDTO == null)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		String psamNo = requestDTO.getPsamNo();
		String bindingNo = requestDTO.getBindingNo();

		String telecomOperators = null;
		String mobilePhoneNum = null;
		String mobilePhoneManuFacturer = null;
		String mobilePhoneModel = null;
		String mobilePhoneProduct = null;

		if (requestExtDTO != null) {
			telecomOperators = requestExtDTO.getTelecomOperators();
			mobilePhoneNum = requestExtDTO.getMobilePhoneNum();
			mobilePhoneManuFacturer = requestExtDTO
					.getMobilePhoneManuFacturer();
			mobilePhoneModel = requestExtDTO.getMobilePhoneModel();
			mobilePhoneProduct = requestExtDTO.getMobilePhoneProduct();
		}

		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		/************ Add By Qw 2012.10.15 增加运营商标识的验证 **************/
		if (cardInfo.getMovingRule() == 5 || cardInfo.getMovingRule() == 6
				|| cardInfo.getMovingRule() == 7) {
			if (telecomOperators == null || "".equals(telecomOperators)) {
				logger.error("参数：运营商标识错误！" + "传入参数(telecomOperators):"
						+ requestDTO.toString());
				throw new ApplicationException(CodeDefine.PARAM_ERROR,
						"参数：运营商标识错误！");
			}
			List<CardOrgRuleParam> cardOrgRuleParams = cardOrgRuleParamDAO
					.getsByRuleId(cardInfo.getMovingRule());
			boolean isTelecomOperators = false;
			if (cardOrgRuleParams == null || cardOrgRuleParams.size() <= 0)
				throw new ApplicationException(CodeDefine.SYS_ERR);
			for (Iterator<CardOrgRuleParam> i = cardOrgRuleParams.iterator(); i
					.hasNext();) {
				CardOrgRuleParam tempParam = i.next();
				if (telecomOperators.equals(tempParam.getParamValue()))
					isTelecomOperators = true;
			}
			if (!isTelecomOperators) {
				logger.error("客户端取到运营商标识(" + telecomOperators
						+ ")与绑定的运营商标识不匹配！");
				throw new ApplicationException(
						CodeDefine.TELECOM_OPERATORS_NOT_MATCH);
			}
		}

		if (cardInfo.getMovingRule() == 8 || cardInfo.getMovingRule() == 9
				|| cardInfo.getMovingRule() == RULE_MOBILE_CMCC_RANGE
				|| cardInfo.getMovingRule() == RULE_MOBILE_CUCC_RANGE
				|| cardInfo.getMovingRule() == RULE_MOBILE_CT_RANGE) {
			validCardUserExtInfo(requestDTO, requestExtDTO, false);
		}
		// TODO 第三方交易添加第三方orgId限制
		String orgIdStr = requestDTO.getOrgId();
		if (StringHelper.hasText(orgIdStr)) {
			try {
				Long orgId = Long.parseLong(orgIdStr);
				if (!orgId.equals(cardInfo.getCopFlag())) {
					logger.error("参数：机构标识错误！" + "传入参数(orgId):"
							+ requestDTO.toString());
					throw new ApplicationException(CodeDefine.COPFLAG_NO_EQUAL);
				}
			} catch (Exception e) {
				logger.error("参数：机构标识错误！" + "传入参数(orgId):"
						+ requestDTO.toString());
				throw new ApplicationException(CodeDefine.PARAM_ERROR,
						"参数：机构标识错误！");
			}
		}
		return this.getLineNo(requestDTO, context);
	}

	/**
	 * @param requestDTO
	 * @param requestExtDTO
	 * @param isInit
	 *            是否初始化。true是初始化，false是交易
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	private void validCardUserExtInfo(UDServiceRequestDTO requestDTO,
                                      UDServiceRequestExtDTO requestExtDTO, boolean isInit)
			throws ServiceException, ApplicationException {
		if (requestDTO == null)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		String psamNo = requestDTO.getPsamNo();
		String areaNo = requestDTO.getAreaNo();
		String passwd = requestDTO.getPasswd();
		String bindingNo = requestDTO.getBindingNo();
		String telecomOperators = null;
		String mobilePhoneNum = null;
		String mobilePhoneManuFacturer = null;
		String mobilePhoneModel = null;
		String mobilePhoneProduct = null;

		if (requestExtDTO != null) {
			telecomOperators = requestExtDTO.getTelecomOperators();
			mobilePhoneNum = requestExtDTO.getMobilePhoneNum();
			mobilePhoneManuFacturer = requestExtDTO
					.getMobilePhoneManuFacturer();
			mobilePhoneModel = requestExtDTO.getMobilePhoneModel();
			mobilePhoneProduct = requestExtDTO.getMobilePhoneProduct();
		}

		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if (cardUser == null)
			throw new CardUserNotFoundException();
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, null);

		if (cardInfo.getMovingRule() == 5 || cardInfo.getMovingRule() == 6
				|| cardInfo.getMovingRule() == 7) {
			boolean canInit_mobile = false;
			boolean canInit_telecom = false;

			List<CardOrgRuleParam> cardOrgRuleParams = cardOrgRuleParamDAO
					.getsByRuleId(cardInfo.getMovingRule());

			assertMobile(mobilePhoneNum);
			if (telecomOperators == null || "".equals(telecomOperators)) {
				logger.error("参数：运营商标识错误！" + "传入参数(telecomOperators):"
						+ requestDTO.toString());
				throw new ApplicationException(CodeDefine.PARAM_ERROR,
						"参数：运营商标识错误！");

			}
			if (cardOrgRuleParams == null || cardOrgRuleParams.size() <= 0)
				throw new ApplicationException(CodeDefine.SYS_ERR);
			for (Iterator<CardOrgRuleParam> i = cardOrgRuleParams.iterator(); i
					.hasNext();) {
				CardOrgRuleParam tempParam = i.next();
				if (mobilePhoneNum.substring(0, 3).equals(
						tempParam.getParamValue()))
					canInit_mobile = true;
				if (telecomOperators.equals(tempParam.getParamValue()))
					canInit_telecom = true;
			}
			if (!canInit_mobile) {
				logger.info("手机号(" + mobilePhoneNum + ")与绑定的运营商号段不匹配！");
				throw new ApplicationException(
						CodeDefine.MOBILE_PHONE_NOT_MATCH);

			}
			if (!canInit_telecom) {
				logger.info("客户端取到运营商标识(" + telecomOperators + ")与绑定的运营商标识不匹配！");
				throw new ApplicationException(
						CodeDefine.TELECOM_OPERATORS_NOT_MATCH);
			}
		}
		/*************** 验证是否绑定验证手机厂商的相关信息 *************/
		if (cardInfo.getMovingRule() == 8) {
			List<CardOrgRuleParam> cardOrgRuleParams = cardOrgRuleParamDAO
					.getsByRuleIdAndParamCode(cardInfo.getMovingRule(),
							String.valueOf(cardInfo.getCopFlag().longValue()));
			if (cardOrgRuleParams == null || cardOrgRuleParams.size() <= 0) {
				logger.error("规则ID为：" + cardInfo.getMovingRule() + " ,集团标识："
						+ String.valueOf(cardInfo.getCopFlag().longValue())
						+ " 的手机型号标识列表配置有误,为空！");
				throw new ApplicationException(CodeDefine.SYS_ERR, "规则ID为："
						+ cardInfo.getMovingRule() + " ,集团标识："
						+ String.valueOf(cardInfo.getCopFlag().longValue())
						+ " 的手机型号标识列表配置有误,为空！");
			}

			if (mobilePhoneModel == null || "".equals(mobilePhoneModel)) {
				logger.error("参数：手机型号标识错误！" + "传入参数(mobilePhoneModel):"
						+ requestExtDTO.toString());
				throw new ApplicationException(CodeDefine.PARAM_ERROR,
						"参数：手机型号标识错误！");
			}

			boolean canInit_mobilePhoneModel = false;
			for (Iterator<CardOrgRuleParam> i = cardOrgRuleParams.iterator(); i
					.hasNext();) {
				CardOrgRuleParam tempParam = i.next();
				if (mobilePhoneModel.equals(tempParam.getParamValue()))
					canInit_mobilePhoneModel = true;
			}
			if (!canInit_mobilePhoneModel) {
				logger.info("客户端取到手机型号标识(" + mobilePhoneModel
						+ ")与绑定的手机型号标识不匹配！");
				throw new ApplicationException(
						CodeDefine.MOBILE_PHONE_MODEL_NOT_MATCH);
			}
		}
		if (cardInfo.getMovingRule() == 9) {
			List<CardOrgRuleParam> cardOrgRuleParams = cardOrgRuleParamDAO
					.getsByRuleIdAndParamCode(cardInfo.getMovingRule(),
							String.valueOf(cardInfo.getCopFlag().longValue()));
			if (cardOrgRuleParams == null || cardOrgRuleParams.size() <= 0) {
				logger.error("规则ID为：" + cardInfo.getMovingRule() + " ,集团标识："
						+ String.valueOf(cardInfo.getCopFlag().longValue())
						+ " 的手机厂商标识列表配置有误,为空！");
				throw new ApplicationException(CodeDefine.SYS_ERR, "规则ID为："
						+ cardInfo.getMovingRule() + " ,集团标识："
						+ String.valueOf(cardInfo.getCopFlag().longValue())
						+ " 的手机厂商标识列表配置有误,为空！");
			}

			if (mobilePhoneManuFacturer == null
					|| "".equals(mobilePhoneManuFacturer)) {
				logger.error("参数：手机厂商标识错误！" + "传入参数(mobilePhoneManuFacturer):"
						+ requestExtDTO.toString());
				throw new ApplicationException(CodeDefine.PARAM_ERROR,
						"参数：手机厂商标识错误！");
			}

			boolean canInit_mobilePhoneManuFacturer = false;
			for (Iterator<CardOrgRuleParam> i = cardOrgRuleParams.iterator(); i
					.hasNext();) {
				CardOrgRuleParam tempParam = i.next();
				if (mobilePhoneManuFacturer.equals(tempParam.getParamValue()))
					canInit_mobilePhoneManuFacturer = true;
			}
			if (!canInit_mobilePhoneManuFacturer) {
				logger.info("客户端取到手机厂商标识(" + mobilePhoneManuFacturer
						+ ")与绑定的手机厂商标识不匹配！");
				throw new ApplicationException(
						CodeDefine.MOBILE_PHONE_FACTORY_NOT_MATCH);
			}
		}
		// 手机号段验证
		if (cardInfo.getMovingRule() == RULE_MOBILE_CMCC_RANGE
				|| cardInfo.getMovingRule() == RULE_MOBILE_CUCC_RANGE
				|| cardInfo.getMovingRule() == RULE_MOBILE_CT_RANGE) {
			assertTelecomOperators(telecomOperators);
			// 初始化则校验手机号码
			if (isInit) {
				assertMobile(mobilePhoneNum);
			}
			int rangeRuleId = 0;
			String rangeRuleName = "";
			if (cardInfo.getMovingRule() == RULE_MOBILE_CMCC_RANGE) {
				rangeRuleId = 5;
				rangeRuleName = "yidongyunyingshang";
			}
			if (cardInfo.getMovingRule() == RULE_MOBILE_CUCC_RANGE) {
				rangeRuleId = 6;
				rangeRuleName = "liantongyunyingshang";
			}
			if (cardInfo.getMovingRule() == RULE_MOBILE_CT_RANGE) {
				rangeRuleId = 7;
				rangeRuleName = "dianxinyunyingshang";
			}
			// 运营商参数
			List<CardOrgRuleParam> optRuleParams = cardOrgRuleParamDAO
					.getsByRuleIdAndParamCode(rangeRuleId, rangeRuleName);
			boolean canOpt = false;
			if (CollectionHelper.isNotEmpty(optRuleParams)) {
				for (CardOrgRuleParam cardOrgRuleParam : optRuleParams) {
					if (cardOrgRuleParam.getParamValue().equals(
							telecomOperators)) {
						canOpt = true;
						break;
					}
				}
			}
			if (!canOpt) {
				logger.info("客户端取到运营商(" + telecomOperators + ")与绑定的运营商不匹配！");
				throw new ApplicationException(
						CodeDefine.MOBILE_PHONE_FACTORY_NOT_MATCH);
			}

			// 初始化则校验手机号码
			if (isInit) {
				checkMobileRange(mobilePhoneNum, cardInfo);
			}

		}
	}

	private void checkMobileRange(String mobilePhoneNum, CardInfo cardInfo)
			throws ApplicationException {
		List<String> mobileBin = getMobileBin(mobilePhoneNum);
		List<CardOrgRuleParam> cardOrgRuleParams = cardOrgRuleParamDAO
				.getByRuleIdAndCodeAndValues(cardInfo.getMovingRule(),
						String.valueOf(cardInfo.getCopFlag().longValue()),
						mobileBin);

		if (CollectionHelper.isEmpty(cardOrgRuleParams)) {
			// 手机区号不存在
			logger.info("客户端取到手机号(" + mobilePhoneNum + ")与绑定的集团手机号段标识不匹配！");
			throw new ApplicationException(
					CodeDefine.MOBILE_PHONE_FACTORY_NOT_MATCH);
		}

	}

	/**
	 * 获取手机区号。截取手机号的3到9位
	 * 
	 * @param mobilePhoneNum
	 * @return
	 */
	private List<String> getMobileBin(String mobilePhoneNum) {
		List<String> result = new ArrayList<String>();
		for (int i = 4; i <= 9; i++) {
			result.add(mobilePhoneNum.substring(0, i));
		}
		return result;
	}

	/**
	 * 判断运营商标识是否有效，无效则跑出异常
	 * 
	 * @param telecomOperators
	 */
	private void assertTelecomOperators(String telecomOperators)
			throws ApplicationException {
		if (StringHelper.hasNoText(telecomOperators))
			throw new ApplicationException(CodeDefine.PARAM_ERROR,
					"参数：运营商标识错误！");

	}

	/**
	 * 判断手机号是否有效，无效时抛出异常
	 * 
	 * @param mobilePhoneNum
	 * @throws ApplicationException
	 *             无效时抛出
	 */
	private void assertMobile(String mobilePhoneNum)
			throws ApplicationException {
		if (mobilePhoneNum == null || "".equals(mobilePhoneNum)
				|| 11 != mobilePhoneNum.length())
			throw new ApplicationException(CodeDefine.NO_MOBILE);
	}

	final static private int RULE_MOBILE_CMCC_RANGE = 10;
	final static private int RULE_MOBILE_CUCC_RANGE = 11;
	final static private int RULE_MOBILE_CT_RANGE = 12;

	/* (non-Javadoc)
	 * @see com.lakala.mini.service.IUDService#transferUDToUser(long, long, java.lang.String, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { ApplicationException.class })
	public String transferUDToUser(long fromuid, long touid, String psamNo,
			ApplicationContext context) throws ServiceException,
			ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if(fromuid==touid){
			return CodeDefine.SUCCESS;
		}
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		String result = this.unBindUDToUser(fromuid, psamNo, cardInfo.getPasswd(), context);
		if("00".equals(result)){
			LoginMiniUser user = loginMiniUserDao.getByUid(touid);
			if (user == null) {
				user = new LoginMiniUser();
				user.setUid(touid);
			}
			Set<CardInfo> cardInfos = user.getCardInfos();
			if (cardInfos == null)
				cardInfos = new HashSet<CardInfo>();
			cardInfos.add(cardInfo);
			loginMiniUserDao.save(user);
		}else{
			throw new ApplicationException(result);
		}
		return result;
	}

	@Override
	public PsamInfoDTO getTerminalInfoByPsam(String psamNo, ApplicationContext context) throws ServiceException {
		CardInfo cardInfo;
		PsamInfoDTO result =null;
		PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);
		if(psamInfo!=null){
			result=new PsamInfoDTO();
			result.setPsamNo(psamNo);
			result.setPsamStatus( String.valueOf(psamInfo.getPasmState()));
		}else{
			return result;
		}
		try {
			cardInfo = this.cardManager.getCardInfoByPsamNo(psamNo, context);
			if (cardInfo != null ) {
				UserMiniInfoDTO miniInfo = mapper.map(cardInfo, UserMiniInfoDTO.class);
				LoginMiniUser loginMiniUser = this.loginMiniUserDao.getBindUidByPsam(psamNo);
				result.setMiniUserInfo(miniInfo);
				if(loginMiniUser!=null&&loginMiniUser.getUid()>0){
					result.setBindUserId(Long.toString(loginMiniUser.getUid()));
				}
			}
		} catch (ApplicationException e) {
			logger.warn(e.getMessage(),e);
			throw new ServiceException(e.getErrorCode(),e);
		}
		return result;
	}

}
