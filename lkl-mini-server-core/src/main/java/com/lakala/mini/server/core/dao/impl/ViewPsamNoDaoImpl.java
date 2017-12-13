/**
 * 
 */
package com.lakala.mini.server.core.dao.impl;

import com.lakala.common.util.CollectionHelper;
import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.dao.IViewPsamNoDAO;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.LoginMiniUser;
import com.lakala.mini.server.core.domain.ViewPsamNo;
import org.hibernate.annotations.common.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author 全伟(QW)
 * @date 2011-11-15
 * @time 下午02:40:10
 */
@Repository
public class ViewPsamNoDaoImpl extends GenericDAOImpl<ViewPsamNo> implements
        IViewPsamNoDAO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Pagination<ViewPsamNo> queryViewPsamInfo(String[] psamNos,
                                                    Integer[] status, Integer[] types, Page page) {

		String jpql = " from ViewPsamNo viewPsamNo ";
		Map<String, Object> paras = new HashMap<String, Object>();
		if ((psamNos == null || psamNos.length <= 0)
				&& (status == null || status.length <= 0)
				&& (types == null || types.length <= 0))
			logger.debug("SQL : {}", jpql);
		else {
			jpql += " where viewPsamNo.psamNo is not null ";
			if (psamNos != null && psamNos.length > 0) {
				jpql += " and viewPsamNo.psamNo in (:psamNos) ";
				paras.put("psamNos", Arrays.asList(psamNos));
			}
			if (status != null && status.length > 0) {
				jpql += " and viewPsamNo.psamState in (:status)";
				paras.put("status", Arrays.asList(status));
			}
			if (types != null && types.length > 0) {
				jpql += " and viewPsamNo.type in (:types)";
				paras.put("types", Arrays.asList(types));
			}
			logger.debug("SQL : {}", jpql);
		}
		return this.getJPQLPageReuslt(jpql, paras, page);

	}

	@Override
	public Pagination<ViewPsamNo> queryViewPsamInfo(String[] psamNos,
                                                    Integer[] status, Integer[] types, String[] cardNos,
                                                    String[] userMobilePhones, String[] telNos, String[] telAreaNos,
                                                    Long[] cardOrgIds, Page page) {
		String jpql = " from ViewPsamNo viewPsamNo ";
		Map<String, Object> paras = new HashMap<String, Object>();
		if ((psamNos == null || psamNos.length <= 0)
				&& (status == null || status.length <= 0)
				&& (types == null || types.length <= 0)
				&& (cardNos == null || cardNos.length <= 0)
				&& (userMobilePhones == null || userMobilePhones.length <= 0)
				&& (telNos == null || telNos.length <= 0)
				&& (telAreaNos == null || telAreaNos.length <= 0)
				&& (cardOrgIds == null || cardOrgIds.length <= 0))
			logger.debug("SQL : {}", jpql);
		else {
			jpql += " where viewPsamNo.psamNo is not null ";
			if (psamNos != null && psamNos.length > 0) {
				jpql += " and viewPsamNo.psamNo in (:psamNos) ";
				paras.put("psamNos", Arrays.asList(psamNos));
			}
			if (status != null && status.length > 0) {
				jpql += " and viewPsamNo.psamState in (:status)";
				paras.put("status", Arrays.asList(status));
			}
			if (types != null && types.length > 0) {
				jpql += " and viewPsamNo.type in (:types)";
				paras.put("types", Arrays.asList(types));
			}
			if (cardNos != null && cardNos.length > 0) {
				jpql += " and viewPsamNo.cardNo in (:cardNos)";
				paras.put("cardNos", Arrays.asList(cardNos));
			}
			if (userMobilePhones != null && userMobilePhones.length > 0) {
				jpql += " and viewPsamNo.userMobile in (:userMobilePhones)";
				paras.put("userMobilePhones", Arrays.asList(userMobilePhones));
			}
			if (telNos != null && telNos.length > 0) {
				jpql += " and viewPsamNo.telNo in (:telNos)";
				paras.put("telNos", Arrays.asList(telNos));
			}
			if (telAreaNos != null && telAreaNos.length > 0) {
				jpql += " and viewPsamNo.telAreaNo in (:telAreaNos)";
				paras.put("telAreaNos", Arrays.asList(telAreaNos));
			}
			if (cardOrgIds != null && cardOrgIds.length > 0) {
				jpql += " and viewPsamNo.copFlag in (:cardOrgIds)";
				paras.put("cardOrgIds", Arrays.asList(cardOrgIds));
			}
			logger.debug("SQL : {}", jpql);
		}
		return this.getJPQLPageReuslt(jpql, paras, page);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.server.core.dao.IViewPsamNoDAO#queryViewPsamInfo(java
	 * .util.List, java.util.List, java.util.List)
	 */
	@Override
	public List<ViewPsamNo> queryViewPsamInfo(String userMobilePhone, String bindingTelNo, String psam, Long userId) {
		CriteriaBuilder criteriaBuilder = this.getEntityManager()
				.getCriteriaBuilder();
		CriteriaQuery<ViewPsamNo> query = criteriaBuilder
				.createQuery(ViewPsamNo.class);
		
		Root<ViewPsamNo> viewPsamNo = query.from(ViewPsamNo.class);
		viewPsamNo.fetch("cardUserExtInfo",JoinType.LEFT);
		Join<ViewPsamNo, CardInfo> cardInfoJoin = viewPsamNo.join("cardInfo");
		
		
		Join<CardInfo, LoginMiniUser> loginMiniUserJoin = cardInfoJoin.join("miniUser");
		
		Predicate disjunction = criteriaBuilder.disjunction();
		Predicate criteria=disjunction;
		if (StringHelper.isNotEmpty(userMobilePhone)) {
			criteria= criteriaBuilder.or(criteria,criteriaBuilder.equal(
					viewPsamNo.get("userMobile"), userMobilePhone));
		}
		if (StringHelper.isNotEmpty(bindingTelNo)) {
			criteria=criteriaBuilder.or(criteria,criteriaBuilder.equal(
					viewPsamNo.get("bindingTelNo"), bindingTelNo));
		}
		if (StringHelper.isNotEmpty(psam)) {
			criteria=criteriaBuilder.or(criteria,criteriaBuilder.equal(
					viewPsamNo.get("psamNo"), psam));
		}
		if (userId!=null&&userId>0) {
			criteria=criteriaBuilder.or(criteria,criteriaBuilder.equal(
					loginMiniUserJoin.get("uid"), userId));
		}
		
		if (criteria!=disjunction) {
			query.where(criteriaBuilder.and(criteriaBuilder.conjunction(),criteria));
			return this.getEntityManager().createQuery(query).getResultList();
		} else {
			return new ArrayList<ViewPsamNo>();
		}
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.dao.IViewPsamNoDAO#getPsamsBy(java.util.Collection)
	 */
	@Override
	public List<ViewPsamNo> getPsamsBy(Collection<String> psamsCollection) {
		if(CollectionHelper.isNotEmpty(psamsCollection)){
			String jpql = " from ViewPsamNo viewPsamNo where psamNo in(:psamNo)";
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("psamNo", psamsCollection);
			return this.getJPQLReuslt(jpql, paras);
		}
		return new ArrayList<ViewPsamNo>();
	}

}
