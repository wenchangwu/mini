/**
 * 
 */
package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.mini.server.core.domain.CardTelNoAppend;

import java.util.List;

/**
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午04:34:50
 */
public interface ICardTelNoAppendDAO extends GenericDAO<CardTelNoAppend> {
	public List<CardTelNoAppend> getCardTelNoAppends(long cardInfoId, String telNo, int status);
}
