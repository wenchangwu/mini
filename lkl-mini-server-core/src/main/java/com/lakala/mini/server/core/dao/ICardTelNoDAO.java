/**
 * 
 */
package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.mini.server.core.domain.CardTelNo;

import java.util.List;

/**
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午04:32:34
 */
public interface ICardTelNoDAO extends GenericDAO<CardTelNo> {
	public List<CardTelNo> getCardTelNoByCardInfoID(long cardInfoId);
}
