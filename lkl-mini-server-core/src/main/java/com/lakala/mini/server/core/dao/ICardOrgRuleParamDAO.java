/**
 * 
 */
package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.CardOrgRuleParam;

import java.util.List;

/**
 * @author QW
 * 
 */
public interface ICardOrgRuleParamDAO extends GenericDAO<CardOrgRuleParam> {

	public List<CardOrgRuleParam> getsByRuleId(int ruleId);

	public List<CardOrgRuleParam> getsByRuleIdAndParamCode(int ruleId,
                                                           String paramCode);

	/**
	 * @param ids
	 * @since 1.3.0
	 */
	public void batchRemove(List<Long> ids);

	/**
	 * @param paramCode
	 * @param ruleId
	 * @since 1.3.0
	 */
	public void batchRemove(String paramCode, Integer ruleId);

	/**
	 * @param ruleId
	 * @param paramCode
	 * @param paramValue
	 * @param paramName
	 * @param paramDesc
	 * @since 1.3.0
	 */
	public void batchUpdateNameAndDesc(Integer ruleId, String paramCode,
                                       List<String> paramValue, String paramName, String paramDesc);

	/**
	 * @param ruleId
	 * @param paramCode
	 * @param paramValue
	 * @return
	 * @since 1.3.0
	 */
	public List<CardOrgRuleParam> getByRuleIdAndCodeAndValues(Integer ruleId,
                                                              String paramCode, List<String> paramValue);

	public Pagination<CardOrgRuleParam> query(List<Long> id, List<Integer> ruleId,
                                              List<String> paramCode, List<String> paramName, List<String> paramValue, Page page);
}
