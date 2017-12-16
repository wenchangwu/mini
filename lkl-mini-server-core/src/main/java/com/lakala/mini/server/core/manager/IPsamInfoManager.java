/**
 * com.lakala.mini.service.IPsamInfoService.java
 */
package com.lakala.mini.server.core.manager;

import com.lakala.common.exception.ApplicationException;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.PsamInfo;
import com.lakala.mini.server.core.domain.ViewPsamNo;

import java.util.Collection;
import java.util.List;

/**
 * PSAM模块提供的服务接口
 * @author QW
 * 2010-12-2 下午10:44:11
 */
public interface IPsamInfoManager {
	PsamInfo getPsamInfo(Long id);
	PsamInfo getPsamInfoByPsamNo(String psamNo);
	/**
	 * 从PSAM卡视图中通过PSAM卡号取得视图信息
	 * @param psamNo
	 * @return
	 */
	ViewPsamNo getViewPsamNofoByPsamNo(String psamNo);
	boolean replacePsamNo(String oldPsamNo, String newPsamNo) throws ApplicationException;
	PsamInfo updatePsamInfo(PsamInfo psamInfo);
	Pagination<ViewPsamNo> queryViewPsamInfo(String[] psamNos,
                                             Integer[] status, Integer[] types, Page page);
	Pagination<ViewPsamNo> queryViewPsamInfo(String[] psamNos,
                                             Integer[] status, Integer[] types, String[] cardNos, String[] userMobilePhones, String[] telNos, String[] telAreaNos, Long[] cardOrgIds, Page page);

	List<ViewPsamNo> queryViewPsamInfo(String userMobilePhone, String telNo, String psam, Long userId);
	/**
	 * @param psamsCollection
	 * @return
	 */
	List<ViewPsamNo> getByPsams(Collection<String> psamsCollection);
}
