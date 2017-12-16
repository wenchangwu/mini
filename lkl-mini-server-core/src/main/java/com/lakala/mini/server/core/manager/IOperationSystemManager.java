/**
 * 
 */
package com.lakala.mini.server.core.manager;

/**
 * @author ray
 *
 */
public interface IOperationSystemManager {

	String changePSAMCardAuditingStatus(String psamNo, String cardNo,
                                        String telNo, int bingding);
	
	
}
