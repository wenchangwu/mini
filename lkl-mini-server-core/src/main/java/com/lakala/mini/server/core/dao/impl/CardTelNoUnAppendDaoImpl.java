/**
 * 
 */
package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.server.core.dao.ICardTelNoUnAppendDAO;
import com.lakala.mini.server.core.domain.CardTelNoUnAppend;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午04:48:16
 */
@Repository
public class CardTelNoUnAppendDaoImpl extends GenericDAOImpl<CardTelNoUnAppend> implements ICardTelNoUnAppendDAO {

	@Override
	public List<CardTelNoUnAppend> getCardTelNoUnAppends(long cardInfoId, String telNo, int status) {
		String jpql = " from CardTelNoUnAppend cardTelNoUnAppend where 1 = 1";
		Map<String, Object> paras = new HashMap<String, Object>();
		if(cardInfoId > 0){
			jpql += " and cardTelNoUnAppend.cardInfoId = :cardInfoId";
			paras.put("cardInfoId", cardInfoId);
		}
		if(telNo != null && !"".equals(telNo)){
			jpql += " and cardTelNoUnAppend.bindingTelNo = :bindingTelNo";
			paras.put("bindingTelNo", telNo);
		}
		jpql += " and cardTelNoUnAppend.status = :status";
		paras.put("status", status);
		
		return this.getJPQLReuslt(jpql, paras);
	}

}
