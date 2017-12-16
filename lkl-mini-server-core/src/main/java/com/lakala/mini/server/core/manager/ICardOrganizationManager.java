package com.lakala.mini.server.core.manager;

import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.CardOrg;
import com.lakala.mini.server.core.manager.exception.CardOrgManagerException;

import java.util.List;

public interface ICardOrganizationManager {
	/**
	 * @param id id
	 * @param code 代码
	 * @param name 名称
	 * @param needPage 是否需要分页
	 * @param pageLimit 每页大小
	 * @param pageNo 页号
	 * @param operatorId 操作人员id,0为系统
	 * @return
	 */
	Pagination<CardOrg> get(Long[] id, String[] code, String[] name,
                            boolean needPage, int pageSize, int pageNo, Long operatorId);

	/**
	 * @param id
	 * @param operatorId
	 * @return
	 */
	CardOrg getById(Long id, Long operatorId);

	/**
	 * @param code
	 * @param operatorId
	 * @return
	 */
	CardOrg getByCode(String code, Long operatorId);

	/**
	 * 更新卡机构信息
	 *
	 * @param cardOrg
	 * @param operatorId
	 * @throws CardOrgManagerException
	 */
	void update(CardOrg cardOrg, Long operatorId)
			throws CardOrgManagerException;

	/**
	 * 新建卡机构信息。<br/>
	 * 如果原有机构已存在，并且不是卡机构，则将原有机构设置为卡机构，并添加卡机构信息。
	 * @param cardOrg
	 * @param operatorId
	 * @return id
	 * @throws CardOrgManagerException
	 */
	Long create(CardOrg cardOrg, Long operatorId)
			throws CardOrgManagerException;

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param movingRule
	 * @param needPage
	 * @param pageSize
	 * @param pageNo
	 * @param operatorId
	 * @return
	 * @since 1.4.0
	 */
	Pagination<CardOrg> get(Long[] id, String[] code, String[] name,
                            List<Integer> movingRule, boolean needPage, int pageSize,
                            int pageNo, Long operatorId);
}
