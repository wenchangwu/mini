package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardOrg;
import com.lakala.mini.server.core.domain.CardResource;

import java.util.List;

public interface ICardInfoDAO extends GenericDAO<CardInfo> {

	public void update(CardInfo cardInfo, CardResource cardResource, CardOrg cardOrgInfo);
	public CardInfo save(CardInfo cardInfo, CardResource cardResource, CardOrg cardOrgInfo);
	public CardInfo getByCardNo(String cardNo);
	public Pagination<CardInfo> queryCardInfo(String[] cardNos, Long[] cardOrgIds, Long[] cardInfoIds, Integer[] status, Integer[] types, Page page);
	public Pagination<CardInfo> queryCardInfo(String[] cardNos, int status, Page page);
	public Pagination<CardInfo> queryCardInfo(Long[] cardInfoIds, String[] cardNos, int status, Page page);
	public List<CardInfo> getByUserId(Long userId);
	
}

