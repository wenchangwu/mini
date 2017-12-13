package com.lakala.mini.server.core.dao.impl;

import com.lakala.ca.util.StringHelper;
import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.server.core.dao.ILoginMiniUserDao;
import com.lakala.mini.server.core.domain.LoginMiniUser;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ray
 * @version 创建时间：2010-12-14 上午10:06:03
 * 类说明
 */
@Repository
public class LoginMiniUserDaoImpl extends GenericDAOImpl<LoginMiniUser> implements ILoginMiniUserDao {

	@Override
	public LoginMiniUser findUserByCardNo(String userCardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginMiniUser getByUid(long uid) {
		return this.getBy("uid", uid);
	}

	@Override
	public LoginMiniUser getBindUidByPsam(String psamNo) {
		if(StringHelper.hasText(psamNo)){
			String jpqlStr = " select l  from LoginMiniUser l join l.cardUsers c where c.psamNo=:psamNo ";
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("psamNo", psamNo);
			List<LoginMiniUser> result = this.getJPQLReuslt(jpqlStr, paras);
			if(result!=null){
				if(result.size()==1){
					return result.get(0);
				}else{
					return null;
				}
			}else{
				throw new IllegalStateException(
						"worning  --more than one object find!!");
			}
		}
		return null;
	}
	
	
}
