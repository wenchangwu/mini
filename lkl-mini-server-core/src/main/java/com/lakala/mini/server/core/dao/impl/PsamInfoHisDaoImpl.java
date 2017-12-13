/**
 * 
 */
package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.server.core.dao.IPsamInfoHisDAO;
import com.lakala.mini.server.core.domain.PsamInfo;
import com.lakala.mini.server.core.domain.PsamInfoHis;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ray
 *
 */
@Repository
public class PsamInfoHisDaoImpl  extends GenericDAOImpl<PsamInfoHis> implements IPsamInfoHisDAO {

	@Override
	public void save(PsamInfo psamInfo) {
		PsamInfoHis psamInfoHis = new PsamInfoHis();
		BeanUtils.copyProperties(psamInfo, psamInfoHis);
		psamInfoHis.setModifyDate(new Timestamp(new Date().getTime()));				
		save(psamInfoHis);
		
	}

	

}
