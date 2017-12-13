/**
 * 
 */
package com.lakala.mini.server.core.dao.impl;

import com.lakala.common.util.CollectionHelper;
import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.dao.ICardOrgRuleParamDAO;
import com.lakala.mini.server.core.domain.CardOrgRuleParam;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QW 2012-10-12下午03:01:53
 */
@Repository
public class CardOrgRuleParamDAOImpl extends GenericDAOImpl<CardOrgRuleParam>
		implements ICardOrgRuleParamDAO {

	@Override
	public List<CardOrgRuleParam> getsByRuleId(int ruleId) {
		return this.getsBy("ruleId", ruleId);
	}

	@Override
	public List<CardOrgRuleParam> getsByRuleIdAndParamCode(int ruleId,
                                                           String paramCode) {
		String jpql = " from CardOrgRuleParam cardOrgRuleParam where cardOrgRuleParam.ruleId = :ruleId and cardOrgRuleParam.paramCode = :paramCode";
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("ruleId", ruleId);
		paras.put("paramCode", paramCode);
		return this.getJPQLReuslt(jpql, paras);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.server.core.dao.ICardOrgRuleParamDAO#batchRemove(java
	 * .util.List)
	 */
	@Override
	public void batchRemove(List<Long> ids) {
		if (CollectionHelper.isNotEmpty(ids)) {
			this.getEntityManager().createNamedQuery("batchRemove")
					.setParameter("ids", ids).executeUpdate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.server.core.dao.ICardOrgRuleParamDAO#batchRemove(java
	 * .lang.String, java.lang.Integer)
	 */
	@Override
	public void batchRemove(String paramCode, Integer ruleId) {
		this.getEntityManager().createNamedQuery("removeByCodeAndRuleId")
				.setParameter("paramCode", paramCode)
				.setParameter("ruleId", ruleId).executeUpdate();

	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.dao.ICardOrgRuleParamDAO#batchUpdateNameAndDesc(java.lang.Integer, java.lang.String, java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void batchUpdateNameAndDesc(Integer ruleId, String paramCode,
			List<String> paramValue, String paramName, String paramDesc) {
		this.getEntityManager().createNamedQuery("batchUpdateNameAndDesc")
		.setParameter("paramCode", paramCode)
		.setParameter("ruleId", ruleId)
		.setParameter("paramName", paramName)
		.setParameter("paramDesc", paramDesc)
		.setParameter("paramValue", paramValue)
		.executeUpdate();
		
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.dao.ICardOrgRuleParamDAO#getByRuleIdAndCodeAndNameAndValues(java.lang.Integer, java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public List<CardOrgRuleParam> getByRuleIdAndCodeAndValues(
			Integer ruleId, String paramCode, List<String> paramValue) {
		return this.getEntityManager().createNamedQuery("getByRuleIdAndCodeAndValues",CardOrgRuleParam.class)
		.setParameter("paramCode", paramCode)
		.setParameter("ruleId", ruleId)
		.setParameter("paramValue", paramValue)
		.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.dao.ICardOrgRuleParamDAO#query(java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public Pagination<CardOrgRuleParam> query(List<Long> id,
                                              List<Integer> ruleId, List<String> paramCode,
                                              List<String> paramName, List<String> paramValue, Page page) {
		String jpql = " from CardOrgRuleParam p where 1=1 ";
		Map<String,Object> param=new HashMap<String,Object>();
		if(CollectionHelper.isNotEmpty(id)){
			jpql+=" and  p.id in (:id) ";
			param.put("id", id);
		}
		if(CollectionHelper.isNotEmpty(ruleId)){
			jpql+=" and p.ruleIdin (:ruleId) ";
			param.put("ruleId", ruleId);
		}
		if(CollectionHelper.isNotEmpty(paramCode)){
			jpql+=" and p.paramCode in (:paramCode) ";
			param.put("paramCode", paramCode);
		}
		if(CollectionHelper.isNotEmpty(paramName)){
			jpql+=" and p.paramName in (:paramName) ";
			param.put("paramName", paramName);
		}
		if(CollectionHelper.isNotEmpty(paramValue)){
			jpql+=" and p.paramValue in (:paramValue)";
			param.put("paramValue", paramValue);
		}
		return this.getJPQLPageReuslt(jpql, param, page);
	}

}
