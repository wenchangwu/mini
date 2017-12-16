/**
 * com.lakala.mini.server.core.service.impl.PsamInfoHisServiceImpl.java
 */
package com.lakala.mini.server.core.manager.impl;

import com.lakala.mini.server.core.dao.IPsamInfoHisDAO;
import com.lakala.mini.server.core.domain.PsamInfoHis;
import com.lakala.mini.server.core.manager.IPsamInfoHisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QW
 * 2010-12-3 下午12:49:08
 */
@Service(value="psamInfoHisManager")
public class PsamInfoHisManagerImpl implements IPsamInfoHisManager {
	private Logger logger = LoggerFactory.getLogger(PsamInfoHisManagerImpl.class);
	@Autowired
	private IPsamInfoHisDAO psamInfoHisDao;

	@Override
	public PsamInfoHis getPsamInfoHis(Long id) {
		
		return psamInfoHisDao.get(id);
	}


	public void setPsamInfoHisDao(IPsamInfoHisDAO psamInfoHisDao) {
		this.psamInfoHisDao = psamInfoHisDao;
	}



}
