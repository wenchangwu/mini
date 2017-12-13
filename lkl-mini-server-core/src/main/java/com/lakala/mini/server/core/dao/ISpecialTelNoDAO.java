/**
 * 
 */
package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.SpecialTelNo;

/**
 * @author 全伟(QW)
 * @date 2012-8-1
 * @time 下午03:29:15
 */
public interface ISpecialTelNoDAO extends GenericDAO<SpecialTelNo> {
	public Pagination<SpecialTelNo> querySpecialTelNo(String[] telNos, String[] areaNos, String[] types, String[] status, Page page);
 
}
