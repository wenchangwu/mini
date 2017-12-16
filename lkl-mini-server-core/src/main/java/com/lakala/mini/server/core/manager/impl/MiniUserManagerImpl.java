/**
 * 
 */
package com.lakala.mini.server.core.manager.impl;

import com.lakala.ca.dto.AuthenticationDTO;
import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.IllegalStateException;
import com.lakala.common.util.Tools;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.common.*;
import com.lakala.mini.dto.CardUserSubmitRequest;
import com.lakala.mini.dto.CardUsersFindRequest;
import com.lakala.mini.dto.work.Context;
import com.lakala.mini.dto.work.ImportMiniUserRequestType;
import com.lakala.mini.dto.work.ImportMiniUserResponseType;
import com.lakala.mini.dto.work.MiniUserInfo;
import com.lakala.mini.exception.*;
import com.lakala.mini.server.core.dao.*;
import com.lakala.mini.server.core.domain.*;
import com.lakala.mini.server.core.manager.*;
import com.lakala.mini.server.core.util.Config;
import com.lakala.mini.service.IWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author ray
 * 
 */
@Service(value = "miniUserManager")
@WebService
@Transactional(readOnly = true)
public class MiniUserManagerImpl implements IMiniUserManager {
	final static private Logger logger = LoggerFactory.getLogger(MiniUserManagerImpl.class);
	@Autowired
	private ICardUserDAO cardUserDao;
	@Autowired
	private ICardUserHisDAO cardUserHisDao;
	@Autowired
	private ILoginMiniUserDao loginMiniUserDao;
	@Autowired
	private ICardInfoDAO cardInfoDao;
	@Autowired
	private IViewCardTelNoDAO viewCardTelNoDao;
	@Autowired
	private ICardManager cardManager;
//	@Resource()
//	private ISmsService smsService;
	@Autowired
	private IOperationSystemManager operationSystemService;
	@Autowired
	private IPsamInfoManager psamInfoManager;
	@Resource
	private IWorkService workService;
	@Autowired
	private ICardOrganizationManager cardOrganizationManager;

	@Autowired
	private Config config;
	
	@Autowired
	private ILoginMiniUserHisDao loginMiniUserHisDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IMiniUserService#findCardUserByUid(long,
	 * com.lakala.core.support.Page)
	 */
	@Override
	public List<CardUser> findCardUserByUid(long uid) {
		LoginMiniUser user = this.loginMiniUserDao.getByUid(uid);
		List<CardUser> result = new ArrayList<CardUser>();
		if (user != null) {
			result.addAll(user.getCardUsers());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniUserService#findCardUserByPsamNo(java.lang
	 * .String)
	 */
	@Override
	public CardUser findCardUserByPsamNo(String psamNo,
                                         AuthenticationDTO authenication) {
		return cardUserDao.getByPsamNo(psamNo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniUserService#findCardUserByCardInfoId(long)
	 */
	@Override
	public CardUser findCardUserByCardInfoId(long cardInfoId,
                                             AuthenticationDTO authenication) {
		return cardUserDao.getByCardInfoId(cardInfoId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IMiniUserService#findHisByCardUserId(long,
	 * com.lakala.core.support.Page)
	 */
	@Override
	public Pagination<CardUserHis> findHisByCardUserId(long cardUserId,
                                                       Page page, AuthenticationDTO authenication) {
		return this.cardUserHisDao.getsByCardUserId(cardUserId, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniUserService#findHisByPsamNo(java.lang.String
	 * , com.lakala.core.support.Page)
	 */
	@Override
	public Pagination<CardUserHis> findHisByPsamNo(String psamNo, Page page,
                                                   AuthenticationDTO authenication) {
		return cardUserHisDao.getsByPsamNo(psamNo, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IMiniUserService#findHisByCardInfoId(long,
	 * com.lakala.core.support.Page)
	 */
	@Override
	public Pagination<CardUserHis> findHisByCardInfoId(long cardInfoId,
                                                       Page page, AuthenticationDTO authenication) {
		return cardUserHisDao.getsByCardInfoId(cardInfoId, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniUserService#updateCardUser(com.lakala.mini
	 * .domain.CardUser)
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateCardUser(CardUser cardUser,
			AuthenticationDTO authenication) {
		createHis(cardUser, authenication);
		cardUserDao.update(cardUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniUserService#createUser(com.lakala.mini.domain
	 * .CardUser)
	 */
	@Override
	@Transactional(readOnly = false)
	public long createUser(CardUser cardUser, AuthenticationDTO authenication) {
		createHis(cardUser, authenication);
		CardUser result = this.cardUserDao.save(cardUser);
		if (null != result) {
			
			return result.getId();
		}
		return -1;
	}

	/**
	 * 
	 * @param cardUser
	 * @return
	 */

	private long createHis(CardUser cardUser, AuthenticationDTO authenication) {
		if(cardUser.getHis() != null){
			CardUserHis his = cardUser.getHis();
			his.setModifyDate(new Timestamp(System.currentTimeMillis()));
			his.setId(null);
			his.setCardUserId(cardUser.getId());
			his = this.cardUserHisDao.save(his);
			if(null != his){
				return his.getId();
			}
			return -1;
		}else{
			CardUserHis his = new CardUserHis();
			Long id = cardUser.getId();
			CardUser load = this.cardUserDao.load(id);
			BeanUtils.copyProperties(load, his, createHisCopyIgnore);
			his.setId(null);
			his.setCardUserId(id);
			his.setModifyDate(new Timestamp(System.currentTimeMillis()));
			his = this.cardUserHisDao.save(his);
			if (null != his) {
				return his.getId();
			}
			return -1;

		}
	}
	
	

	@Override
	public Pagination<CardUserHis> findHis(long uid, Page page,
                                           AuthenticationDTO authenication) {
		LoginMiniUser loginMiniUser = this.loginMiniUserDao.getByUid(uid);
		if (loginMiniUser == null) {
			return new Pagination<CardUserHis>();
		} else {
			Set<CardUser> cardUsers = loginMiniUser.getCardUsers();
			List<Long> ids = new LinkedList<Long>();
			for (CardUser cardUser : cardUsers) {
				ids.add(cardUser.getId());
			}
			return this.cardUserHisDao.getsByCardUserId(ids, page);
		}
	}

	@Override
	public Pagination<CardUser> findCardUser(CardUsersFindRequest user,
                                             AuthenticationDTO authenication) {
		Pagination<CardUser> search = cardUserDao.search(user.getPsamNo(),
				user.getUserCardNo(), user.getUserName(), user.getMobile(),
				user.getPage());
		if (search == null) {
			search = new Pagination<CardUser>();
		}

		return search;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IMiniUserService#bindUserToUserCard(long,
	 * java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false,rollbackFor={ApplicationException.class})
	public void bindUserToUserCard(long uid, String userCardNo,
			String password, long operatorId) throws PasswordNotMatchException,
            CardUserNotFoundException, UserCardBindOtherUserException,
            UserCardAllreadyBindException, UserCardNotFoundException {
		if (logger.isDebugEnabled()) {
			logger.debug("用户绑定用户卡开始,loginId:{}, userCardNo:{}", uid,
					userCardNo);
		}
		CardInfo cardInfo = cardInfoDao.getByCardNo(userCardNo);
		LoginMiniUser user=this.loginMiniUserDao.get(uid);
		if(user==null){
			user=new LoginMiniUser(uid);
		}
		if (cardInfo != null) {
			cardInfo.bind(user,password);
		} else {
			throw UserCardNotFoundException.ERROR;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("用户绑定用户卡结束,loginId:{}, userCardNo:{}", uid,
					userCardNo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.service.IMiniUserService#findUserBindUserCard(long)
	 */
	@Override
	public List<CardInfo> findUserBindUserCard(long uid,
                                               AuthenticationDTO authenication) {
		LoginMiniUser miniUser = loginMiniUserDao.getByUid(uid);
		if (null != miniUser) {
			return new ArrayList<CardInfo>(miniUser.getCardInfos());
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional(readOnly = false,noRollbackFor={ApplicationException.class})
	public long submitCardUser(CardUserSubmitRequest cardUserRequest,
			AuthenticationDTO authenication) throws CardUserNotFoundException {
		CardUser cardUser = cardUserDao.getByIdAndPsamNoAndUserCardNo(
				cardUserRequest.getId(), cardUserRequest.getPsamNo(),
				cardUserRequest.getCardInfoNo());
		if (null == cardUser) {
			throw CardUserNotFoundException.ERROR;
		}
		BeanUtils.copyProperties(cardUserRequest, cardUser,
				submitCardUserCopyIgnore);
		cardUser.setAuditingState(UserAuditing.NO_COMMIT);
		return 0;
	}

	@Override
	public Pagination<CardUser> checkCardUser(long[] ids, Page page,
                                              AuthenticationDTO authenication)  {
		if (null != ids && ids.length > 0) {
			return cardUserDao.getByAuditingStatus(ids,
					UserAuditing.AUDITING_WAITING, page);
		} else {
			return cardUserDao.getByAuditingStatus(
					UserAuditing.AUDITING_WAITING, page);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniUserService#changeCardUserAuditingStatus
	 * (long, int)
	 */
	@Override
	@Transactional(readOnly = false,noRollbackFor={ApplicationException.class})
	public long changeCardUserAuditingStatus(long id, int auditingStatus,
			AuthenticationDTO authenication) throws UserCardNotFoundException, SMSSendException, IllegalStateException, CardUserNotFoundException {
		CardUser cardUser = this.cardUserDao.get(id);
		if (null != cardUser) {
			if (UserAuditing.AUDITING_OVER == auditingStatus) {
				cardUser.setAuditingState(UserAuditing.AUDITING_OVER);
				CardInfo cardInfo = this.cardInfoDao.get(cardUser
						.getCardInfoId());
				if (cardInfo == null) {
					throw UserCardNotFoundException.ERROR;
				}
				String toOperationSysStatus = operationSystemService
						.changePSAMCardAuditingStatus(cardUser.getPsamNo(),
								cardInfo.getCardNo(), cardUser.getTelNo(),
								CardState.BINGDING);
				// 大作业成功接受
				Object[] pars = { cardUser.getPsamNo(), CardState.BINGDING,
						toOperationSysStatus };
				logger.info(
						"Send PSAM:{} status:{} to OperationSys return {}",
						pars);
			} else if (UserAuditing.DENY == auditingStatus) {
				String userMobile = cardUser.getUserMobile();
				String msg = getChangeCardUserAuditingFailureMsg();
				String sendSmsACK = null;
//				String sendSmsACK = smsService.sendSms(userMobile, msg);
				if (!CodeDefine.SUCCESS.equals(sendSmsACK)) {
					throw new SMSSendException(sendSmsACK);
				}
			} else {
				throw IllegalStateException.ERROR;
			}
			long cardInfoId = cardUser.getCardInfoId();
			return cardInfoId;
		}
		throw CardUserNotFoundException.ERROR;

	}

	private String getChangeCardUserAuditingFailureMsg() {
		MessageFormat messageFormat = new MessageFormat(
				config.getChangeCardUserAuditingFailureMsgTemplate());
		String msg = messageFormat.format(messageFormat);
		return msg;
	}

	final static private String[] createHisCopyIgnore = { "id", "cardUserId" };
	final static private String[] submitCardUserCopyIgnore = { "id",
			"cardUserId" };

	@Override
	public LoginMiniUser getLoginMiniUser(long uid) {
		return this.loginMiniUserDao.get(uid);
	}
	
	public static void main(String[] args) {
		
		logger.info("Test logger:{},{}","1","2");
	}

	@Override
	@Transactional(readOnly = false,noRollbackFor={ApplicationException.class})
	public String changeMiniInfo(String userCardId, String newCardNo, String newPsamNo, ApplicationContext context) throws ApplicationException {
		String retCode = null;
		if(userCardId != null || !"".equals(userCardId)){
			CardUser cardUser = cardUserDao.load(Long.valueOf(userCardId));
			if(cardUser == null){
				retCode = CodeDefine.CARD_USER_NOT_FOUND;
				return retCode;
			}else{
				if(newCardNo != null && !"".equals(newCardNo)){
					cardManager.replaceCardNo(cardUser.getCardInfoId(), newCardNo);
					cardUser.setOperatType(OperateType.CHANGE_USER_CARD);
					cardUser.setChangeDate(new Date());
					this.updateCardUser(cardUser, null);
				}
				if(newPsamNo !=null && !"".equals(newPsamNo)){
					CardInfo cardInfo = cardUser.getCardInfo();
					if(CardType.USER_CARD == cardInfo.getType()){
						if(checkPsamNoForChange(newPsamNo)){
							psamInfoManager.replacePsamNo(cardUser.getPsamNo(), newPsamNo);
							cardUser.setOperatType(OperateType.CHANGE_PSAM);
							cardUser.setPsamNo(newPsamNo);
							cardUser.setChangeDate(new Date());
							this.updateCardUser(cardUser, null);
						}
					}
					if(CardType.USER_CARD != cardInfo.getType()){
						String oldPsamNo = String.valueOf(cardUser.getPsamNo());
						if(checkPsamNoForChangeUD(oldPsamNo,newPsamNo)){							
							CardUser newCardUser = this.findCardUserByPsamNo(newPsamNo, null);
							newCardUser.setPsamNo(oldPsamNo);
							newCardUser.setOperatType(OperateType.CHANGE_PSAM);
							cardUser.setChangeDate(new Date());
							CardInfo newCardInfo = newCardUser.getCardInfo();
							newCardInfo.setStatus(CardState.REPLACE);
							cardManager.updateCardInfo(newCardInfo, null);
							this.updateCardUser(newCardUser, null);
							cardUser.setPsamNo(newPsamNo);
							cardUser.setOperatType(OperateType.CHANGE_PSAM);
							cardUser.setChangeDate(new Date());
							this.updateCardUser(cardUser, null);
//							LoginMiniUserHis loginMiniUserHis = new LoginMiniUserHis();
//							loginMiniUserHis.setCardInfoId(newCardInfo.getId());
//							loginMiniUserHis.setUid(cardInfo.getMiniUser().getUid());
//							loginMiniUserHis.setReleaseDate(new Date(System.currentTimeMillis()));
//							loginMiniUserHisDAO.save(loginMiniUserHis);
						}
					}
					PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(newPsamNo);
					if(psamInfo.getPasmState() != cardInfo.getStatus()){
						psamInfo.setPasmState(cardInfo.getStatus());
						psamInfoManager.updatePsamInfo(psamInfo);
					}
				}
			}
			
		}
		return CodeDefine.SUCCESS;
	}

	private boolean checkPsamNoForChange(String newPsamNo) throws ApplicationException{
		PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(newPsamNo);
		ViewPsamNo viewPsamNo = psamInfoManager.getViewPsamNofoByPsamNo(newPsamNo);
		if( psamInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if(psamInfo.getPasmState() != CardState.UNINITIALIZATION )
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		if(CardType.USER_CARD != viewPsamNo.getType())
			throw new ApplicationException(CodeDefine.CARD_TYPE_ERROR);
		return true;
	}
	private boolean checkPsamNoForChangeUD(String oldPsamNo ,String newPsamNo) throws ApplicationException{
		PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(newPsamNo);
		if( psamInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if(psamInfo.getPasmState() != CardState.UNINITIALIZATION && psamInfo.getPasmState() != CardState.INITIALIZATION)
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		if(psamInfo.getPasmState() == CardState.INITIALIZATION){
			CardInfo newCardInfo = cardManager.getCardInfoByPsamNo(newPsamNo, null);
			CardInfo oldCardInfo = cardManager.getCardInfoByPsamNo(oldPsamNo, null);
			if(newCardInfo == null || oldCardInfo == null)
				throw new ApplicationException(CodeDefine.NO_USER_CARD);
			if(newCardInfo.getCopFlag().longValue() != oldCardInfo.getCopFlag().longValue())
				throw new ApplicationException(CodeDefine.COPFLAG_NO_EQUAL);
			if(OperateType.DELIVER_CONFIRM == newCardInfo.getCardUser().getOperatType())
				throw new ApplicationException(CodeDefine.CARD_HAS_DELIVER);
		}
		
		return true;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	public String deliverConfirm(Long[] cardInfoIds, ApplicationContext context) throws ApplicationException {
		if(cardInfoIds == null || cardInfoIds.length <= 0)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		CardInfo cardInfo = null;
		for(int i = 0; i < cardInfoIds.length ; i++){
			cardInfo = cardManager.getCardInfo(cardInfoIds[i].longValue(), null);
			if(cardInfo == null)
				throw new ApplicationException(CodeDefine.NO_USER_CARD);
			if(cardInfo.getStatus() != CardState.INITIALIZATION)
				throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
			CardUser cardUser = cardInfo.getCardUser();
			if(cardUser.getOperatType()!= null && !"".equals(cardUser.getOperatType()) && OperateType.DELIVER_CONFIRM .equals(cardUser.getOperatType()))
				throw new ApplicationException(CodeDefine.CARD_HAS_DELIVER);
			cardUser.setOperatType(OperateType.DELIVER_CONFIRM);
			cardUser.setChangeDate(new Date(System.currentTimeMillis()));
			this.updateCardUser(cardUser, null);
		}
		return CodeDefine.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	public String revokeDeliverConfirm(Long[] cardInfoIds, ApplicationContext context) throws ApplicationException {
		if(cardInfoIds == null || cardInfoIds.length <= 0)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		CardInfo cardInfo = null;
		for(int i = 0; i < cardInfoIds.length ; i++){
			cardInfo = cardManager.getCardInfo(cardInfoIds[i].longValue(), null);
			if(cardInfo == null)
				throw new ApplicationException(CodeDefine.NO_USER_CARD);
			if(cardInfo.getStatus() != CardState.INITIALIZATION)
				throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
			
			CardUser cardUser = cardInfo.getCardUser();
			if(!OperateType.DELIVER_CONFIRM.equals(cardUser.getOperatType()))
				throw new ApplicationException(CodeDefine.CARD_HAS_NOT_DELIVER);
			cardUser.setOperatType(OperateType.REVOKE_DELIVER_CONFIRM);
			cardUser.setChangeDate(new Date(System.currentTimeMillis()));
			this.updateCardUser(cardUser, null);
		}
		return CodeDefine.SUCCESS;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public String updateBindMini(CardUser cardUser, boolean isNotify) throws ApplicationException {
		if(cardUser == null)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);

		CardInfo tempCardInfo = cardManager.getCardInfo(cardUser.getCardInfoId(), null);
		if(isNotify){
			if(workService != null){	
				Context workContext = new Context();
				workContext.setSystemCode("mini");
				MiniUserInfo miniUserInfo = new MiniUserInfo();
				miniUserInfo.setCardNo(tempCardInfo.getCardNo());
				miniUserInfo.setAreaNo(cardUser.getArea());
				miniUserInfo.setChangeDate(new Date(System.currentTimeMillis()));
				miniUserInfo.setMobile(cardUser.getUserMobile());
				miniUserInfo.setPsamNo(cardUser.getPsamNo());
				String userId = String.valueOf(cardUser.getCardInfoId());
				if(userId.length() < 8)
					userId = Tools.getStringFromString(userId, 8, "0", true);
				miniUserInfo.setUserId(userId);
				miniUserInfo.setPhoneNo(cardUser.getTelNo().substring(cardUser.getArea().length()));
				miniUserInfo.setCardOrg((cardOrganizationManager.getById(tempCardInfo.getCopFlag(), 0l)).getCode());
				ImportMiniUserRequestType parameters = new ImportMiniUserRequestType();
				parameters.setContext(workContext);
				parameters.setUserInfo(miniUserInfo);
				try {
					ImportMiniUserResponseType response = workService.importMiniUser(parameters);
					if(response != null && response.isSuccess()){
						cardUser.setNodeNo(response.getStoreNo());
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new ApplicationException(e);
				}				

			}
		}
		this.updateCardUser(cardUser, null);
		if(OperateType.AUTO_OPEN.equals(cardUser.getOperatType()) || OperateType.HAND_OPEN.equals(cardUser.getOperatType())){
			tempCardInfo.setStatus(CardState.BINGDING);
			cardManager.updateCardInfo(tempCardInfo, null);			
			PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(cardUser.getPsamNo());
			psamInfo.setBindDate(new Timestamp(System.currentTimeMillis()));
			psamInfo.setPasmState(CardState.BINGDING);
			psamInfoManager.updatePsamInfo(psamInfo);
		}

		return CodeDefine.SUCCESS;
	}

	@Override
	public PsamInfo getPsamInfoByPsamNo(String psamNo)  throws ApplicationException {
		return this.psamInfoManager.getPsamInfoByPsamNo(psamNo);
	}

	@Override
	public String[] getTelNos(long cardInfoId, ApplicationContext context) throws ApplicationException {
		if (cardInfoId <= 0 )
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		List<ViewCardTelNo> viewCardTelNos = viewCardTelNoDao.getsBy("cardInfoId", cardInfoId);
		if(viewCardTelNos != null && viewCardTelNos.size() > 0){
			String[] telNos = new String[viewCardTelNos.size()];
			for(int i =0;i<viewCardTelNos.size();i++){
				telNos[i] = viewCardTelNos.get(i).getTelNo();
			}
			return telNos;
		}
		
		return null;
	}

	@Override
	public List<CardUser> findCardUsersUseTelNo(String telNo, ApplicationContext context) throws ApplicationException {
		if(telNo == null || "".equals(telNo))
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		return cardUserDao.getsBy("telNo", telNo);
	}


	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.IMiniUserManager#findCardUsers(java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public List<CardUser> findCardUsers(String bindingTelNo,
                                        String openMobileNum, Long userId) {
		
		return this.cardUserDao.findCardUsers(bindingTelNo,openMobileNum,userId);
	}
	
}
