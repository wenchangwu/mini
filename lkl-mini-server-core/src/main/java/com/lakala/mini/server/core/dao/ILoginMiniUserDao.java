package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.mini.server.core.domain.LoginMiniUser;

public interface ILoginMiniUserDao extends GenericDAO<LoginMiniUser> {

	LoginMiniUser findUserByCardNo(String userCardNo);
	LoginMiniUser getByUid(long uid);
	LoginMiniUser getBindUidByPsam(String psamNo);

}
