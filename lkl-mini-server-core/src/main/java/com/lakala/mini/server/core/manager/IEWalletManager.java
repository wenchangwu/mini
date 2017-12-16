/**
 * 
 */
package com.lakala.mini.server.core.manager;

import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.CardUserExtInfo;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.1
 * @crate_date 2013-5-15
 */
public interface IEWalletManager {
	CardUser account(Long userId, String areaCode, CardUserExtInfo cardUserExInfo);
	
	

	/**
	 * @param userId
	 * @return
	 */
	CardUser getByUid(Long userId);
	
	
}
