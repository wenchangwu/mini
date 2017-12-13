/**
 * 
 */
package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.dao.ISpecialTelNoDAO;
import com.lakala.mini.server.core.domain.SpecialTelNo;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 全伟(QW)
 * @date 2012-8-1
 * @time 下午03:31:35
 */
@Repository
public class SpecialTelNoDaoImpl extends GenericDAOImpl<SpecialTelNo> implements ISpecialTelNoDAO {

	@Override
	public Pagination<SpecialTelNo> querySpecialTelNo(String[] telNos, String[] areaNos, String[] types, String[] status, Page page) {
		String jpql = " from SpecialTelNo specialTelNo where 1 = 1";
		Map<String, Object> paras = new HashMap<String, Object>();
		if(telNos != null && telNos.length > 0){
			jpql += " and specialTelNo.telNo in (:telNo)";
			paras.put("telNo", Arrays.asList(telNos));
		}
		if(areaNos != null && areaNos.length > 0){
			jpql += " and specialTelNo.areaNo in (:areaNo)";
			paras.put("areaNo", Arrays.asList(areaNos));
		}
		
		if(types != null && types.length > 0){
			Integer[] tempTypes = new Integer[types.length];
			for(int i = 0; i< types.length ; i++)
				tempTypes[i] = Integer.valueOf(types[i]);
			jpql += " and specialTelNo.type in (:type)";
			paras.put("type", Arrays.asList(tempTypes));
		}
		if(status != null && status.length > 0){
			Integer[] tempStatus = new Integer[status.length];
			for(int i = 0; i< status.length ; i++)
				tempStatus[i] = Integer.valueOf(status[i]);
			jpql += " and specialTelNo.status in (:status)";
			paras.put("status", Arrays.asList(tempStatus));
		}
		return this.getJPQLPageReuslt(jpql,paras,page);
	}


}
