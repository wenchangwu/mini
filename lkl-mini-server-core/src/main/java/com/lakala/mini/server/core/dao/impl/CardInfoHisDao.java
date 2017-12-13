package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.server.core.dao.ICardInfoHisDAO;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardInfoHis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-8 上午10:11:04
 * 类说明
 */
@Repository
public class CardInfoHisDao extends GenericDAOImpl<CardInfoHis> implements ICardInfoHisDAO {
	private Logger logger = LoggerFactory.getLogger(CardInfoHisDao.class);

	@Override
	public void save(CardInfo cardInfo) {
//		CardInfoHis cardInfoHis = new CardInfoHis();
//		BeanUtils.copyProperties(cardInfo, cardInfoHis);
//		cardInfoHis.setModifyDate(new Timestamp(new Date().getTime()));
//		save(cardInfoHis);
		CardInfoHis cardInfoHis = new CardInfoHis();
		logger.info("cardinfo:" +this.toString());
		String[] createHisCopyIgnore = { "id", "cardInfoId" };
		BeanUtils.copyProperties(this, cardInfoHis,createHisCopyIgnore);
		cardInfoHis.setId(null);
		cardInfoHis.setCardInfoId(cardInfo.getId());
		cardInfoHis.setModifyDate(new Timestamp(System.currentTimeMillis()));
		logger.info("cardinfoHis:" +cardInfoHis.toString());

	}
	
}
