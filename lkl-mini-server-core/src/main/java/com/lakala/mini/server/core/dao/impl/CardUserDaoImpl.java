package com.lakala.mini.server.core.dao.impl;

import com.lakala.ca.util.StringHelper;
import com.lakala.common.util.Tools;
import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.server.core.dao.ICardUserDAO;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardInfoHis;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.PsamInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author 刘文成
 * @version 创建时间：2010-12-8 上午10:09:34 类说明
 */
@SuppressWarnings("unchecked")
@Repository
public class CardUserDaoImpl extends GenericDAOImpl<CardUser> implements
        ICardUserDAO {
	private Logger logger= LoggerFactory.getLogger(CardUserDaoImpl.class);
	@Override
	public Pagination<CardUser> search(String psamNo, String userCardNo,
                                       String userName, String userMobile, Page page) {
		int max = 0;
		int begin = 0;
		if (null != page) {
			max = page.getPageSize();
			begin = page.getPageStart();
		}

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery cqt = cb.createQuery();

		Root cardUserRoot = cqt.from(CardUser.class);
		// 生成where
		Predicate predicate = cb.conjunction();
		List<Predicate> whereList = new LinkedList<Predicate>();
		if (Tools.isNotEmptyStr(psamNo)) {
			predicate=cb.and(predicate,cb.like(cardUserRoot.get("psamNo"), psamNo + "%"));
		}

		if (Tools.isNotEmptyStr(userName)) {
			predicate=cb.and(predicate,cb.like(cardUserRoot.get("userName"), userName + "%"));
		}
		if (Tools.isNotEmptyStr(userMobile)) {
			predicate=cb.and(predicate,cb.like(cardUserRoot.get("userMobile"), userMobile + "%"));
		}

		if (Tools.isNotEmptyStr(userCardNo)) {
			// 生成join
			Root<CardInfo> cardInfo = cqt.from(CardInfo.class);
			Root<CardInfo> cardInfoHis = cqt.from(CardInfoHis.class);
			Path cardInfoId = cardInfo.get("id");
			
			Path<Object> userCardNoPath = cardInfo.get("cardNo");
			Path<Object> userCardHisNoPath = cardInfoHis.get("cardNo");
			
			Path userCardIdPath = cardUserRoot.get("cardInfoId");
			Path<Object> cardInfoHisCardInfoId = cardInfoHis.get("cardInfoId");
			// 添加where
			//关联cardinfo
			Predicate and1 = cb.and(cb.equal((Expression) cardInfoId,
					(Expression) userCardIdPath),cb
					.like((Expression) userCardNoPath, userCardNo + "%"));
			//关联cardinfohis
			Predicate and2 = cb.and(cb.equal((Expression) cardInfoHisCardInfoId,
					(Expression) userCardIdPath),cb
					.like((Expression) userCardHisNoPath, userCardNo + "%"));
			
			predicate=cb.and(predicate,cb.and(cb.or(and1,and2)));

		}
		cqt.where(predicate);
		
		Long total = 0l;
		if (max > 0 && begin >= 0) {
			// 计算总数
			total = getTotalFromWhere(cqt, cardUserRoot);
		}
		
		List<CardUser> results = getFromWhere(cqt, cardUserRoot, begin, max);
		Page pageResult = new Page(begin, max, total);
		Pagination<CardUser> pagination = new Pagination(results, pageResult);
		return pagination;

	}

	@Override
	public void init(PsamInfo psamInfo, CardInfo cardInfo) {
		CardUser cardUser = new CardUser();
		cardUser.setPsamNo(psamInfo.getPasmNo());
		cardUser.setCardInfoId(cardInfo.getId());
		cardUser.setOperatType(OperateType.INITIALIZATION);
		cardUser.setChangeDate(new Timestamp(new Date().getTime()));
		save(cardUser);

	}

	@Override
	public CardUser getByCardInfoId(long cardInfoId) {
		return getBy("cardInfoId", cardInfoId);
	}

	@Override
	public CardUser getByPsamNo(String psamNo) {
		return getBy("psamNo", psamNo);
	}

	@Override
	public CardUser getByIdAndPsamNoAndUserCardNo(long id, String psamNo,
                                                  String cardInfoNo) {
		String ql = "select u from CardUser u,CardInfo ci where u.cardInfoId=ci.id and ci.cardNo=:cardInfoNo and u.id=:id and u.psamNo=:psamNo ";
		List<CardUser> resultList = this.getEntityManager().createQuery(ql,CardUser.class)
				.setParameter("cardInfoNo", cardInfoNo).setParameter("id", id)
				.setParameter("psamNo", psamNo).getResultList();
		if(resultList!=null&&resultList.size()>0){
			if(resultList.size()>1){
				throw new IllegalStateException(
				"worning  --more than one object find!!");
			}
			else{
				return resultList.get(0);
			}
		}
		return null;
	}

	@Override
	public Pagination<CardUser> getByAuditingStatus(int auditingState,
                                                    Page page) {
		return this.getsBy("auditingState", auditingState,page);
	}

	@Override
	public Pagination<CardUser> getByAuditingStatus(long[] ids,
                                                    int auditingState, Page page) {
		int max = 0;
		int begin = 0;
		if (null != page) {
			max = page.getPageSize();
			begin = page.getPageStart();
		}

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery cqt = cb.createQuery();
		Root<CardUser> cardUserRoot = cqt.from(CardUser.class);
		Path idPath = cardUserRoot.get("id");
		Predicate predicate=cb.conjunction();
		for (long id : ids) {
			predicate  = cb.or(predicate,cb.equal(idPath, id));
		}
		cqt.where(cb.and(predicate,cb.equal(cardUserRoot.get("auditingState"), auditingState)));
		Long total = 0l;
		if (max > 0 && begin >= 0) {
			// 计算总数
			total = getTotalFromWhere(cqt, cardUserRoot);
		}
		List<CardUser> results = getFromWhere(cqt, cardUserRoot, begin, max);
		Page pageResult = new Page(begin, max, total);
		Pagination<CardUser> pagination = new Pagination(results, pageResult);
		return pagination;
	}

	@Override
	public Pagination<CardUser> queryCardUser(String[] userMobilePhones,
                                              String[] psamNos, String[] telNos, String[] telAresNos, Page page) {
		String jpql_cardUser = " from CardUser cardUser where 1 = 1";
		Map<String, Object> cardUser_paras = new HashMap<String, Object>();
		if(userMobilePhones != null && userMobilePhones.length > 0){
			jpql_cardUser += " and cardUser.userMobile in (:userMobile)";
			
			cardUser_paras.put("userMobile", Arrays.asList(userMobilePhones));
		}
		if(psamNos != null && psamNos.length > 0){
			jpql_cardUser += " and cardUser.psamNo in (:psamNo)";
			cardUser_paras.put("psamNo", Arrays.asList(psamNos));					
		}
		if(telNos != null && telNos.length > 0){
			jpql_cardUser += " and cardUser.telNo in (:telNo)";
			cardUser_paras.put("telNo", Arrays.asList(telNos));										
		}
		if(telAresNos != null && telAresNos.length > 0){
			jpql_cardUser += " and cardUser.telAreaNo in (:telAreaNo)";
			cardUser_paras.put("telAreaNo", Arrays.asList(telAresNos));										
		}

		return this.getJPQLPageReuslt(jpql_cardUser, cardUser_paras, page);
	}

	@Override
	public Pagination<CardUser> queryCardUserByOperateType(String[] operateType, Date startTime, Date endTime, Page page) {
		if(operateType == null || operateType.length <=0 )
			return null;
		String jpql = " from CardUser cardUser where cardUser.operatType in (:operatTypes)";
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("operatTypes", Arrays.asList(operateType));
		if(startTime != null){
			jpql += " and cardUser.telModifyDate >= :startTime";
			paras.put("startTime", startTime);
		}
		if(endTime != null){
			jpql += " and cardUser.telModifyDate < :endTime";
			paras.put("endTime", endTime);
		}			
		return this.getJPQLPageReuslt(jpql, paras, page);
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.dao.ICardUserDAO#findCardUsers(java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public List<CardUser> findCardUsers(String bindingTelNo,
                                        String userMobile, Long userId) {
		String sql=null;
		if(userId==null){
			sql =" from CardUser c where 1=0 " ;
		}else{
			sql=" select c from LoginMiniUser l join l.cardUsers c where 1=0 ";
		}
		Map<String ,Object> paras=new HashMap<String,Object>();
		if(StringHelper.hasText(bindingTelNo)){
			sql+=" or c.bindingTelNo=:bindingTelNo ";
			paras.put("bindingTelNo", bindingTelNo);
		}
		if(StringHelper.hasText(userMobile)){
			sql+=" or c.userMobile=:userMobile ";
			paras.put("userMobile", userMobile);
		}
		if(userId!=null){
			sql+=" or  l.uid=:userId ";
			paras.put("userId", userId);
		}
		return this.getJPQLReuslt(sql, paras);
	}

}
