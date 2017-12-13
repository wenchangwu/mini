package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardInfoHis;

public interface ICardInfoHisDAO extends GenericDAO<CardInfoHis> {

	public void save(CardInfo cardInfo);
}
