/**
 * 
 */
package com.lakala.mini.server.core.manager;

import com.lakala.common.exception.ApplicationException;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.CardOrgPublicParam;
import com.lakala.mini.server.core.domain.CardOrgRuleParam;

import java.util.List;

/**
 * 规则管理类
 * 
 * @author leijiajian@lakala.com
 * @since 1.3.0
 * @crate_date 2012-12-26
 */
public interface ICardOrgRuleManager {

	/**
	 * @return
	 */
	Pagination<CardOrgRuleParam> queryCardRuleParam(List<Long> id,
                                                    List<Integer> ruleId, List<String> paramCode,
                                                    List<String> paramName, List<String> paramValue, Page page);

	/**
	 * @param ruleId
	 * @param ruleCode
	 * @param ruleName
	 * @param ruleDesc
	 * @param paramValue
	 */
	void batchUpdateParam(Integer ruleId, String paramCode, String paramName,
                          String paramDesc, List<String> paramValue, boolean isAppend);

	/**
	 * @param id
	 */
	void deleteParamById(List<Long> id);

	/**
	 * @param cardOrgRuleParam
	 */
	Long saveRuleParam(CardOrgRuleParam cardOrgRuleParam);

	/**
	 * @param orgId
	 * @return
	 * @since 1.4.0
	 */
	CardOrgPublicParam getCardOrgPublicParam(Long orgId);

	/**
	 * @param orgId
	 * @param param
	 * @throws ApplicationException 
	 */
	void saveCardOrgPublicParam(Long orgId, CardOrgPublicParam param) throws ApplicationException;
}
