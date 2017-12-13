package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.CardUserHis;

import java.util.List;

public interface ICardUserHisDAO extends GenericDAO<CardUserHis> {

	public void save(CardUser cardUser);

	public Pagination<CardUserHis> getsByCardUserId(
            long cardUserId, Page page);

	public Pagination<CardUserHis> getsByPsamNo(String psamNo,
                                                Page page);

	public Pagination<CardUserHis> getsByCardInfoId(
            long cardInfoId, Page page);

	public Pagination<CardUserHis> getsByCardUserId(List<Long> ids, Page page);


}
