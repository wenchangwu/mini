/**
 * 
 */
package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.mini.server.core.domain.CardTelNoUnAppend;

import java.util.List;

/**
 * @author 全伟(QW)
 * @date 2012-2-29
 * @time 下午04:36:17
 */
public interface ICardTelNoUnAppendDAO extends GenericDAO<CardTelNoUnAppend> {
	public List<CardTelNoUnAppend> getCardTelNoUnAppends(long cardInfoId, String telNo, int status);

}
