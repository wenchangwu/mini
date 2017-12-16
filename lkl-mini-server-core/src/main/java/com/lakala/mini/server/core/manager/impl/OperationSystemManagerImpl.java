/**
 * 
 */
package com.lakala.mini.server.core.manager.impl;

import com.lakala.mini.server.core.manager.IOperationSystemManager;
import org.springframework.stereotype.Service;

/**
 * @author ray
 *
 */
@Service(value="OperationSystemManagerImpl")
public class OperationSystemManagerImpl implements IOperationSystemManager {

	/* (non-Javadoc)
	 * @see com.lakala.mini.service.IOperationSystemService#changePSAMCardAuditingStatus(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public String changePSAMCardAuditingStatus(String psamNo, String cardNo,
			String telNo, int bingding) {
		return "00";
	}

}
