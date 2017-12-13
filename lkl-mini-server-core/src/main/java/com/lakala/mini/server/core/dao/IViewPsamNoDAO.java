/**
 * 
 */
package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.ViewPsamNo;

import java.util.Collection;
import java.util.List;

/**
 * @author 全伟(QW)
 * @date 2011-11-15
 * @time 下午02:20:57
 */
public interface IViewPsamNoDAO extends GenericDAO<ViewPsamNo> {
	public Pagination<ViewPsamNo> queryViewPsamInfo(String[] psamNos, Integer[] status, Integer[] types, Page page);
	public Pagination<ViewPsamNo> queryViewPsamInfo(String[] psamNos, Integer[] status, Integer[] types, String[] cardNos, String[] userMobilePhones, String[] telNos, String[] telAreaNos, Long[] cardOrgIds, Page page);
	/**
	 * @param userMobilePhones
	 * @param telNos
	 * @param psams
	 * @return
	 */
	public List<ViewPsamNo> queryViewPsamInfo(String userMobilePhone, String bindingTelNo, String psam, Long userId);
	/**
	 * @param psamsCollection
	 * @return
	 */
	public List<ViewPsamNo> getPsamsBy(Collection<String> psamsCollection);
}
