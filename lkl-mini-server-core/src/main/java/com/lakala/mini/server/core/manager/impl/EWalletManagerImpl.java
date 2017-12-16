/**
 * 
 */
package com.lakala.mini.server.core.manager.impl;

import com.lakala.ca.dto.UserDTO;
import com.lakala.ca.service.IUserService;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.objmapper.IObjectMapper;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CardType;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.dto.work.CommonUserInfo;
import com.lakala.mini.dto.work.Context;
import com.lakala.mini.dto.work.ImportUserRequestType;
import com.lakala.mini.dto.work.ImportUserResponseType;
import com.lakala.mini.server.core.dao.*;
import com.lakala.mini.server.core.domain.*;
import com.lakala.mini.server.core.manager.IEWalletManager;
import com.lakala.mini.server.core.manager.IPsamInfoManager;
import com.lakala.mini.server.core.manager.exception.BuzException;
import com.lakala.mini.service.IWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DecimalFormat;

/**
 * 
 * @author leijiajian@lakala.com
 * @since 1.4.1
 * @crate_date 2013-5-15
 */
@Service
public class EWalletManagerImpl implements IEWalletManager {
	final private Logger logger= LoggerFactory.getLogger(EWalletManagerImpl.class);
	/**
	 * 
	 */
	private static final String PSAM_HEAD = "CBC3A1";
	private static final String AREA_CODE="0000";
	@Resource
	private IUserService userService;
	@Resource
	private ICardUserDAO cardUserDAO;
	@Resource
	private ICardInfoDAO cardInfoDAO;
	@Resource
	private IPsamInfoManager psamInfoManager;
	@Resource
	private IPsamInfoDAO psamInfoDAO;
	@Resource
	private IWorkService workService;
	@Resource
	private ILoginMiniUserDao loginMiniUserDao;
	@Resource
	private ICardUserExtInfoDAO cardUserExtInfoDAO;
	@Resource
	private IObjectMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.server.core.manager.IEWalletManager#account(java.lang
	 * .Long)
	 */
	@Override
	public CardUser account(Long userId, String areaCode, CardUserExtInfo cardUserExInfo) {
		if (userId == null) {
			throw new BuzException();
		}
		areaCode=AREA_CODE;
		ApplicationContext context = new ApplicationContext();
		UserDTO user = userService.getByUid(userId, context);
		if (user == null) {
			throw new BuzException(CodeDefine.USER_CARD_BINDING_ALLREADY, CodeDefine.USER_CARD_BINDING_ALLREADY_MSG);
		}
		String psamNo = getPSAM(userId);
		PsamInfo psam = psamInfoManager.getPsamInfoByPsamNo(psamNo);
		if (psam != null) {
			throw new BuzException(CodeDefine.USER_ALLREADY_BIND, CodeDefine.USER_ALLREADY_BIND_MSG);
		}
		psam = new PsamInfo();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		psam.setBindDate(now);
		psam.setDonationCount(0);
		psam.setInsertDate(now);
		psam.setInBatch("");
		psam.setPasmNo(psamNo);
		psam.setPasmState(CardState.BINGDING);
		psamInfoDAO.save(psam);
		CardInfo cardInfo = new CardInfo();
		cardInfo.setType(CardType.USER_EWALLET);
		cardInfo.setStatus(CardState.BINGDING);
		cardInfo = cardInfoDAO.save(cardInfo);
		CardUser cardUser = new CardUser();
		cardUser.setPsamNo(psamNo);
		cardUser.setBindingDate(now);
		cardUser.setSelfOpenDate(now);
		cardUser.setChangeDate(now);
		cardUser.setArea(areaCode);
		cardUser.setUserMobile(user.getMobileNum());
		cardUser.setOperatType(OperateType.AUTO_OPEN);
		cardUser.setCardInfoId(cardInfo.getId());
		cardUser = cardUserDAO.save(cardUser);
		cardUser.setBindingTelNo(cardInfo.getEWalletLineNo());
		cardInfo.setCardUser(cardUser);
		cardUserExInfo.setCardUserId(cardUser.getId());
		cardUserExtInfoDAO.save(cardUserExInfo);
		
		ImportUserResponseType response = new ImportUserResponseType();
		try {
			ImportUserRequestType request=new ImportUserRequestType();
			Context ct=new Context();
			ct.setSystemCode("mini");
			request.setContext(ct);
			CommonUserInfo userInfo=mapper.map(cardUser, CommonUserInfo.class);
			userInfo.setUserId(getFixUserId(user.getId()));
			userInfo.setUserType(CardType.USER_EWALLET);
			userInfo.setStatus(1);
			request.setUserInfo(userInfo);
			userInfo.setAreaNo(areaCode);
			userInfo.setPhoneNo(cardInfo.getEWalletLineNo());
			
			response = workService.importUser(request);
			if (logger.isDebugEnabled()) {
				logger.debug("response:" + response.isSuccess());
			}
			if (response != null && response.isSuccess()) {
				cardUser.setNodeNo(response.getStoreNo());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		LoginMiniUser loginMiniUser=this.loginMiniUserDao.get(userId);
		if(loginMiniUser==null){
			loginMiniUser=new LoginMiniUser(userId);
		}
		loginMiniUser.setUid(user.getId());
		cardInfo.setMiniUser(loginMiniUser);
		cardUser.setCardInfo(cardInfo);
		return cardUser;
	}

	private String getPSAM(Long userId) {
		return PSAM_HEAD + getFixUserId(userId);
	}

	/**
	 * @param userId
	 * @return
	 */
	private String getFixUserId(Long userId) {
		return new DecimalFormat("0000000000") .format(userId);
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.IEWalletManager#getByUid(java.lang.Long)
	 */
	@Override
	public CardUser getByUid(Long userId) {
		if(userId==null){
			return null;
		}
		return this.cardUserDAO.getByPsamNo(this.getPSAM(userId));
	}
}
