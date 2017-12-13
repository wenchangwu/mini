package com.lakala.mini.server.core.dao;


import com.lakala.core.dao.GenericDAO;
import com.lakala.mini.server.core.domain.ThirdPartyUserMapping;

/**
 * 
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-12
 */
public interface IThirdPartyUserMappingDAO extends
		GenericDAO<ThirdPartyUserMapping> {

	/**
	 * @param orgId
	 * @param userId
	 * @return
	 */
	ThirdPartyUserMapping getByOrgIdAndOrgUserId(String orgId, String orgUserId);
	
	ThirdPartyUserMapping getByOrgIdAndOrgUserIdAndPsamNo(String orgId, String orgUserId, String psamNo);
	ThirdPartyUserMapping getByOrgIdAndOrgUserIdAndUserId(String orgId, String orgUserId, Long userId);

}
