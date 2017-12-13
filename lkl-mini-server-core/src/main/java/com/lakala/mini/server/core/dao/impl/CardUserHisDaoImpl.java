package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.dao.ICardUserHisDAO;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.CardUserHis;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-8 上午10:08:51
 * 类说明
 */
@Repository
public class CardUserHisDaoImpl extends GenericDAOImpl<CardUserHis> implements ICardUserHisDAO {

	@Override
	public void save(CardUser cardUser) {
		CardUserHis cardUserHis = new CardUserHis();
		BeanUtils.copyProperties(cardUser, cardUserHis);
		cardUserHis.setModifyDate(new Timestamp(new Date().getTime()));
		save(cardUserHis);
		
	}

	@Override
	public Pagination<CardUserHis> getsByCardUserId(long cardUserId, Page page) {
		return getsBy("cardUserId", cardUserId, page);
	}

	@Override
	public Pagination<CardUserHis> getsByPsamNo(String psamNo, Page page) {
		return getsBy("psamNo", psamNo, page);
	}

	@Override
	public Pagination<CardUserHis> getsByCardInfoId(long cardInfoId, Page page) {
		return getsBy("cardInfoId", cardInfoId, page);
	}

	@Override
	public Pagination<CardUserHis> getsByCardUserId(List<Long> ids, Page page) {
		return getsByInList("cardUserId", ids, page);
	}

	
	
}
