package com.lakala.mini.server.core.dao.impl;

import com.lakala.ca.util.StringHelper;
import com.lakala.common.util.CollectionHelper;
import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.server.core.dao.IThirdPartyUserMappingDAO;
import com.lakala.mini.server.core.domain.ThirdPartyUserMapping;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-12
 */
@Repository
public class ThirdPartyUserMappingDaoImpl extends
		GenericDAOImpl<ThirdPartyUserMapping> implements
        IThirdPartyUserMappingDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.server.core.dao.IThridPartyUserMappingDAO#
	 * findByOrgIdAndOrgUserId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public ThirdPartyUserMapping getByOrgIdAndOrgUserId(String orgId,
                                                        String orgUserId) {
		if (orgId == null || StringHelper.hasNoText(orgUserId)) {
			return null;
		}
		List<ThirdPartyUserMapping> resultList = this
				.getEntityManager()
				.createNamedQuery("findByOrgIdAndOrgUserId",
						ThirdPartyUserMapping.class)
				.setParameter("orgId", orgId)
				.setParameter("orgUserId", orgUserId).getResultList();
		if (CollectionHelper.isNotEmpty(resultList)) {
			return resultList.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.server.core.dao.IThridPartyUserMappingDAO#
	 * getByOrgIdAndOrgUserIdAndPsamNo(java.lang.Long, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ThirdPartyUserMapping getByOrgIdAndOrgUserIdAndPsamNo(String orgId,
                                                                 String orgUserId, String psamNo) {
		if (orgId == null || StringHelper.hasText(orgUserId)
				|| StringHelper.hasText(psamNo)) {
			return null;
		}
		return this
				.getEntityManager()
				.createNamedQuery("getByOrgIdAndOrgUserIdAndPsamNo",
						ThirdPartyUserMapping.class)
				.setParameter("orgId", orgId)
				.setParameter("orgUserId", orgUserId)
				.setParameter("psamNo", psamNo).getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lakala.mini.server.core.dao.IThridPartyUserMappingDAO#
	 * getByOrgIdAndOrgUserIdAndUserId(java.lang.Long, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ThirdPartyUserMapping getByOrgIdAndOrgUserIdAndUserId(String orgId,
                                                                 String orgUserId, Long userId) {
		if (orgId == null || StringHelper.hasNoText(orgUserId) || userId == null) {
			return null;
		}
		return this
				.getEntityManager()
				.createNamedQuery("getByOrgIdAndOrgUserIdAndUserId",
						ThirdPartyUserMapping.class)
				.setParameter("orgId", orgId)
				.setParameter("orgUserId", orgUserId)
				.setParameter("userId", userId).getSingleResult();
	}


}
