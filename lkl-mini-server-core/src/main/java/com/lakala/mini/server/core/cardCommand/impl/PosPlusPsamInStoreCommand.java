/**
 * 
 */
package com.lakala.mini.server.core.cardCommand.impl;

import com.lakala.common.util.Tools;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CardType;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.exception.PsamCardHasExistException;
import com.lakala.mini.server.core.cardCommand.AbstractCardCommand;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.PsamInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * POS+PSAM卡入库
 * @author leijiajian@lakala.com
 * PosPlusPsamInStoreCommand.java
 * @2015-9-06 下午05:00:00
 */
@Component(value = "posPlusPsamInStoreCommand")
public class PosPlusPsamInStoreCommand extends AbstractCardCommand {

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.cardCommand.ICommand#execute(java.lang.Object)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=false)
	public String execute(Object obj) throws Exception {
		
		String batchNo = Tools.getPlatSeq();// 生成批次号
		int count = 0;
		if (obj instanceof String[]) {
			String[] psamNos = (String[]) obj;
			if (psamNos != null && psamNos.length > 0) {
				String psamNo = "";
				for (int i = 0; i < psamNos.length; i++) {
					psamNo = psamNos[i];
					PsamInfo psamInfo = psamInfoDAO.getBy("pasmNo", psamNo);
					if (psamInfo != null) {
						logger.info("卡号为：{}的PSAM卡已绑定，所有数据回滚，该批次入库失败", psamNo);
						throw new PsamCardHasExistException();
					}
					psamInfo = psamInfoDAO.inStore(psamNo,batchNo,null);
					CardInfo cardInfo = new CardInfo();
					cardInfo.setType(CardType.USER_POS_PLUS);
					cardInfo.setStatus(CardState.UNINITIALIZATION);
					cardInfo = cardInfoDAO.save(cardInfo);
					CardUser cardUser = new CardUser();
					cardUser.setPsamNo(psamNo);
					cardUser.setCardInfoId(cardInfo.getId());
					cardUser.setTelMovingCount(0);
					cardUser.setOperatType(OperateType.IN_STORAGE);
					cardUser.setChangeDate(new Date());
					cardUserDAO.save(cardUser);
					count++;
				}
				logger.info("共入库PSAM卡：{}张，入库批次号：{}",count,batchNo);
				return CodeDefine.SUCCESS;
			}
		}
		return CodeDefine.PARAM_ERROR;
	
	}

}
