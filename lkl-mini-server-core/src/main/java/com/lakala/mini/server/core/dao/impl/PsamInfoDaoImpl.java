/**
 * 
 */
package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.common.CardState;
import com.lakala.mini.server.core.dao.IPsamInfoDAO;
import com.lakala.mini.server.core.domain.PsamInfo;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ray
 *
 */
@Repository
public class PsamInfoDaoImpl  extends GenericDAOImpl<PsamInfo> implements IPsamInfoDAO {

	@Override
	public PsamInfo inStore(String psamNo, String batchNo, PsamInfo psamInfo) {
		if(psamInfo != null){
			psamInfo.setInsertDate(new Timestamp(new Date().getTime()));
			psamInfo.setPasmNo(psamNo);
			psamInfo.setPasmState(CardState.UNINITIALIZATION);
			psamInfo.setInBatch(batchNo);
			psamInfo.setBindDate(null);
			psamInfo.setReleaseDate(null);
			update(psamInfo);
		}else{
			psamInfo = new PsamInfo();
			psamInfo.setInsertDate(new Timestamp(new Date().getTime()));
			psamInfo.setPasmNo(psamNo);
			psamInfo.setPasmState(CardState.UNINITIALIZATION);
			psamInfo.setInBatch(batchNo);
			psamInfo = save(psamInfo);
		}
		return psamInfo;
	}

	

}
