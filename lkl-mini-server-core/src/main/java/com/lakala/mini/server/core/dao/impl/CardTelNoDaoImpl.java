/**
 * 
 */
package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.server.core.dao.ICardTelNoDAO;
import com.lakala.mini.server.core.domain.CardTelNo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午04:41:01
 */
@Repository
public class CardTelNoDaoImpl extends GenericDAOImpl<CardTelNo> implements ICardTelNoDAO {


	@Override
	public List<CardTelNo> getCardTelNoByCardInfoID(long cardInfoId) {
		
		return this.getsBy("cardInfoId", cardInfoId);
	}

}
