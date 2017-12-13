package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.mini.server.core.domain.PsamInfo;
import com.lakala.mini.server.core.domain.PsamInfoHis;

public interface IPsamInfoHisDAO extends GenericDAO<PsamInfoHis> {

	public void save(PsamInfo psamInfo);
}
