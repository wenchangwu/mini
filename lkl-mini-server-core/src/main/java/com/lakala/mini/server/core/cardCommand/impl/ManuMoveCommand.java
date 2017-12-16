package com.lakala.mini.server.core.cardCommand.impl;

import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.dto.ManuServiceRequest;
import com.lakala.mini.exception.PSAMCardInfoNotFoundException;
import com.lakala.mini.exception.PsamCardNotBindException;
import com.lakala.mini.server.core.cardCommand.AbstractCardCommand;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.PsamInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;


/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 上午11:08:24
 * 类说明,人工移机
 */
@Component(value="manuMoveCommand")
public class ManuMoveCommand extends AbstractCardCommand {

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String execute(Object obj) throws Exception {
		ManuServiceRequest serviceParam = (ManuServiceRequest)obj;
		PsamInfo psamInfo = null;

		CardUser cardUser =  validate(serviceParam, psamInfo);

		if(serviceParam.getTelNo() != null && !cardUser.getTelNo().equals(serviceParam.getTelNo())){
			cardUser.setTelAreaNo(serviceParam.getTelAreaNo());
			cardUser.setTelNo(serviceParam.getTelNo());
			cardUser.setTelModifyDate(new Timestamp(new Date().getTime()));
			cardUser.setTelMovingCount(cardUser.getTelMovingCount()+1);
			cardUser.setChangeDate(new Timestamp(new Date().getTime()));
			cardUser.setOperatType(OperateType.HAND_MOVING);
			miniUserManager.updateBindMini(cardUser, false);
		}
		
		return CodeDefine.SUCCESS;
	
	}
	private CardUser validate(ManuServiceRequest serviceParam, PsamInfo psamInfo) throws Exception{
//		c)	验证终端是否存在且为绑定
		if(serviceParam.getPsam() == null || serviceParam.getPsam().trim().equals("")){
			logger.info("人工移机，PSAM卡不能为空");
			throw new PSAMCardInfoNotFoundException();
		}
		psamInfo = psamInfoDAO.getBy("pasmNo",serviceParam.getPsam());
		if(psamInfo == null){
			logger.info("人工移机，卡号为："+serviceParam.getPsam()+"的PSAM卡不存在");
			throw new PSAMCardInfoNotFoundException();
		}
		if(psamInfo.getPasmState() != CardState.BINGDING){
			logger.info("人工移机，卡号为："+serviceParam.getPsam()+"的PSAM卡还没有和任何终端绑定");
			throw new PsamCardNotBindException();
		}
		
		CardUser cardUser = null;
		cardUser = miniUserManager.findCardUserByPsamNo(serviceParam.getPsam(), null);
		if(cardUser == null){
			logger.info("人工移机，卡号为："+serviceParam.getPsam()+"的PSAM卡还没有和任何终端绑定");
			throw new PsamCardNotBindException();
		}
		return cardUser ;
	}
}
