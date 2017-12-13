package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.mini.server.core.domain.PsamInfo;

public interface IPsamInfoDAO extends GenericDAO<PsamInfo> {

	public PsamInfo inStore(String psamNo, String batchNo, PsamInfo psamInfo);
}
