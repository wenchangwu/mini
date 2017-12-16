package com.lakala.mini.server.core.cardCommand.impl;

import com.lakala.common.exception.ApplicationException;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.dto.ManuServiceRequest;
import com.lakala.mini.exception.PSAMCardInfoNotFoundException;
import com.lakala.mini.exception.PsamCardBindException;
import com.lakala.mini.exception.UserCardBindException;
import com.lakala.mini.exception.UserCardNotFoundException;
import com.lakala.mini.server.core.cardCommand.AbstractCardCommand;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardOrg;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.PsamInfo;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 上午10:25:24
 * 类说明，人工开通或人工关闭后重新开通
 */


@Component(value="manuOpenCommand")
public class ManuOpenCommand extends AbstractCardCommand {

	@Override
	public String execute(Object obj) throws Exception {
		ManuServiceRequest serviceParam = (ManuServiceRequest)obj;
		CardInfo cardInfo = null;
		PsamInfo psamInfo = null;
		CardOrg cardOrgInfo = null;
//		对传入参数进行验证
		cardInfo = validate(serviceParam,psamInfo,cardInfo,cardOrgInfo);
				
		CardUser cardUser = cardInfo.getCardUser();
		
		cardUser.setUserName(serviceParam.getUserName());
		cardUser.setUserMobile(serviceParam.getMobile());
		cardUser.setIdentityCard(serviceParam.getIdentityCard());
		cardUser.setEmail(serviceParam.getEmail());
		cardUser.setProvoice(serviceParam.getProvince());
		cardUser.setCity(serviceParam.getCity());
		cardUser.setArea(serviceParam.getArea());
		cardUser.setAddress(serviceParam.getAddr());
		cardUser.setPost(serviceParam.getPost());
		cardUser.setTelAreaNo(serviceParam.getTelAreaNo());
		cardUser.setTelNo(serviceParam.getTelNo());
		cardUser.setPsamNo(serviceParam.getPsam());
		cardUser.setOperatType(OperateType.HAND_OPEN);
		cardUser.setChangeDate(new Timestamp(new Date().getTime()));
		cardUser.setTelMovingCount(0);
		cardUser.setSelfOpenDate(new Timestamp(new Date().getTime()));//首次开通时间
		cardUser.setBindingDate(new Date(System.currentTimeMillis()));
		cardUser.setNodeNo(serviceParam.getDeviceNo());
		miniUserManager.updateBindMini(cardUser, false);
				
		return CodeDefine.SUCCESS;
	
	}


	/**
	 * 
	 * @param serviceParam
	 * @param psamInfo
	 * @param cardResource
	 * @param cardOrgInfo
	 * @throws Exception
	 */
	private CardInfo validate(ManuServiceRequest serviceParam, PsamInfo psamInfo, CardInfo cardInfo, CardOrg cardOrgInfo ) throws Exception{

		
		if(serviceParam.getPsam() == null || serviceParam.getPsam().trim().equals("")){
			logger.info("人工开通，PSAM卡不能为空");
			throw new PSAMCardInfoNotFoundException();
		}
		
//		a)	验证用户卡不为null时，用户卡是否存在且状态为初始化或解绑状态
		
		
		if (serviceParam.getCardNo() != null
				&& !serviceParam.getCardNo().trim().equals("")) {
			cardInfo = cardInfoDAO.getByCardNo(serviceParam.getCardNo());
		} else
			cardInfo = miniUserManager.findCardUserByPsamNo(
					serviceParam.getPsam(), null).getCardInfo();

		if (cardInfo == null) {
			logger.info("人工开通，卡号为：" + serviceParam.getCardNo() + "的用户卡不存在");
			throw new UserCardNotFoundException();
		}
		if (cardInfo.getStatus() == CardState.BAD) {
			logger.info("人工开通，卡号为：" + serviceParam.getCardNo() + "的用户卡为废卡");
			throw new UserCardNotFoundException();
		}
		if (cardInfo.getStatus() == CardState.BINGDING) {
			logger.info("人工开通，卡号为：" + serviceParam.getCardNo()
					+ "的用户卡已经和其他终端绑定");
			throw new UserCardBindException();
		}
		if (cardInfo.getStatus() != CardState.INITIALIZATION) {
			logger.info("人工开通，卡号为：" + serviceParam.getCardNo()
					+ "的用户卡状态不正确,目前的用户状态为：" + cardInfo.getStatus());
			throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
		}		
		
//		a)	验证PSAM卡是否存在且为解绑状态，PSAM卡不存在不允许开通
		
		psamInfo = psamInfoDAO.getBy("pasmNo",serviceParam.getPsam());
		if(psamInfo == null){
			logger.info("人工开通，卡号为："+serviceParam.getPsam()+"的PSAM卡不存在");
			throw new PSAMCardInfoNotFoundException();
		}
		if(psamInfo.getPasmState() == CardState.BAD){
			logger.info("人工开通，卡号为："+serviceParam.getPsam()+"的PSAM卡为废卡");
			throw new PSAMCardInfoNotFoundException();
		}
		if(psamInfo.getPasmState() == CardState.BINGDING){
			logger.info("人工开通，卡号为："+serviceParam.getPsam()+"的PSAM卡已经和其他终端绑定");
			throw new PsamCardBindException();
		}
		return cardInfo;
	}
}
