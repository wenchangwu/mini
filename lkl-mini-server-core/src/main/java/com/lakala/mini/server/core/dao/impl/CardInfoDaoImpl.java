package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.dao.ICardInfoDAO;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardOrg;
import com.lakala.mini.server.core.domain.CardResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-8 上午10:06:03
 * 类说明
 */
@Repository
public class CardInfoDaoImpl extends GenericDAOImpl<CardInfo> implements ICardInfoDAO {

	@Override
	public void update(CardInfo cardInfo, CardResource cardResource, CardOrg cardOrgInfo) {
		if(cardOrgInfo != null){
			cardInfo.setCopFlag(cardOrgInfo.getId());
			cardInfo.setMovingRule(cardOrgInfo.getMovingRule());
		}
		cardInfo.setPasswd(cardResource.getPasswd());
		cardInfo.setPvn(cardResource.getPvn());
		cardInfo.setTrack2(cardResource.getTrack2());
		cardInfo.setStatus(cardResource.getStatus());
		update(cardInfo);
	}

	@Override
	public CardInfo save(CardInfo cardInfo, CardResource cardResource,
                         CardOrg cardOrgInfo) {
		cardInfo = new CardInfo();
		if(cardOrgInfo != null){
			cardInfo.setCopFlag(cardOrgInfo.getId());
			cardInfo.setMovingRule(cardOrgInfo.getMovingRule());
		}
		cardInfo.setCardNo(cardResource.getCardNo());
		cardInfo.setPasswd(cardResource.getPasswd());
		cardInfo.setPvn(cardResource.getPvn());
		cardInfo.setTrack2(cardResource.getTrack2());
		cardInfo.setStatus(cardResource.getStatus());
		cardInfo = save(cardInfo);
		return cardInfo;
	}

	@Override
	public CardInfo getByCardNo(String cardNo) {
		return this.getBy("cardNo", cardNo);
	}
	/********************    Add by QW 2011-04-19 11:08      **************************/
	@Override
	public Pagination<CardInfo> queryCardInfo(String[] cardNos,
                                              Long[] cardOrgIds, Long[] cardInfoIds, Integer[] status , Integer[] types, Page page) {
		String jpql = " from CardInfo cardInfo where 1 = 1";
		Map<String, Object> paras = new HashMap<String, Object>();
		if(cardOrgIds != null && cardOrgIds.length > 0){
			jpql += " and cardInfo.copFlag in (:copFlag)";
			paras.put("copFlag", Arrays.asList(cardOrgIds));
		}
		if(cardNos != null && cardNos.length > 0){
			jpql += " and cardInfo.cardNo in (:cardNo)";
			paras.put("cardNo", Arrays.asList(cardNos));
		}
		if(cardInfoIds != null && cardInfoIds.length > 0){
			jpql += " and cardInfo.id in (:cardInfoId)";
			paras.put("cardInfoId", Arrays.asList(cardInfoIds));
		}
		if(status != null && status.length > 0){
			jpql += " and cardInfo.status in (:status)";
			paras.put("status", Arrays.asList(status));
		}
		if(types != null && types.length > 0){
			jpql += " and cardInfo.type in (:type)";
			paras.put("type", Arrays.asList(types));
		}
		return this.getJPQLPageReuslt(jpql,paras,page);
	}
	@Override
	public Pagination<CardInfo> queryCardInfo(String[] cardNos, int status, Page page){
		if(cardNos == null || cardNos.length <= 0)
			return null;
		String jpql = " from CardInfo cardInfo where cardInfo.status <> :status and cardInfo.cardNo in (:cardNo)";
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("status", status);
		paras.put("cardNo", Arrays.asList(cardNos));
		return this.getJPQLPageReuslt(jpql, paras, page);
	}
	@Override
	public Pagination<CardInfo> queryCardInfo(Long[] cardInfoIds, String[] cardNos, int status, Page page){
		String jpql = " from CardInfo cardInfo where cardInfo.status <> :status";
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("status", status);
		if(cardInfoIds != null && cardInfoIds.length > 0){
			jpql += " and cardInfo.id in (:cardInfoIds)";
			paras.put("cardInfoIds", Arrays.asList(cardInfoIds));
		}
		if(cardNos != null && cardNos.length > 0){
			jpql += " and cardInfo.cardNo in (:cardNo) ";
			paras.put("cardNo", Arrays.asList(cardNos));
		}
		return this.getJPQLPageReuslt(jpql, paras, page);
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.dao.ICardInfoDAO#getByUserId(java.lang.Long)
	 */
	@Override
	public List<CardInfo> getByUserId(Long userId) {
		if(userId!=null){
		String jpql = " from CardInfo  a  where a.miniUser.uid =:userId";
		return this.getEntityManager()
				.createQuery(jpql,CardInfo.class)
				.setParameter("userId", userId).getResultList();
		}else{
			return new ArrayList<CardInfo>();
		}
	}

}
