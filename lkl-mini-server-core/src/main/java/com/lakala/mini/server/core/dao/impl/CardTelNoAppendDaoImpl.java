/**
 * 
 */
package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.server.core.dao.ICardTelNoAppendDAO;
import com.lakala.mini.server.core.domain.CardTelNoAppend;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午04:47:01
 */
@Repository
public class CardTelNoAppendDaoImpl extends GenericDAOImpl<CardTelNoAppend> implements ICardTelNoAppendDAO {

	@Override
	public List<CardTelNoAppend> getCardTelNoAppends(long cardInfoId, String telNo, int status) {
		String jpql = " from CardTelNoAppend cardTelNoAppend where 1 = 1";
		Map<String, Object> paras = new HashMap<String, Object>();
		if(cardInfoId > 0){
			jpql += " and cardTelNoAppend.cardInfoId = :cardInfoId";
			paras.put("cardInfoId", cardInfoId);
		}
		if(telNo != null && !"".equals(telNo)){
			jpql += " and cardTelNoAppend.bindingTelNo = :bindingTelNo";
			paras.put("bindingTelNo", telNo);
		}
		jpql += " and cardTelNoAppend.status = :status";
		paras.put("status", status);		
		return this.getJPQLReuslt(jpql, paras);
	}

}
