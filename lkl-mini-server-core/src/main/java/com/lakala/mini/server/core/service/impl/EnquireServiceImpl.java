/**
 * @author ray
 * @filename: MiniServiceImpl.java
 * @create date: 上午09:55:38
 */
package com.lakala.mini.server.core.service.impl;

import com.lakala.ca.service.IUserService;
import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.ServiceException;
import com.lakala.common.util.CollectionHelper;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.objmapper.IObjectMapper;
import com.lakala.enquire.api.service.IDealEnquiryService;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.dto.GetUidByPsamNoResponse;
import com.lakala.mini.dto.UserMiniInfosDTO;
import com.lakala.mini.server.core.dao.ICardOrgRuleDAO;
import com.lakala.mini.server.core.dao.ILoginMiniUserDao;
import com.lakala.mini.server.core.dao.ILoginMiniUserHisDao;
import com.lakala.mini.server.core.dao.ISpecialTelNoDAO;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.LoginMiniUser;
import com.lakala.mini.server.core.manager.*;
import com.lakala.mini.service.IEnquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Service(value = "enquireService")
@WebService
@Transactional(readOnly = true)
public class EnquireServiceImpl implements IEnquireService {
	@Autowired
	private ICardManager cardManager;
	@Autowired
	private IMiniUserManager miniUserManager;
	@Autowired
	private IObjectMapper mapper;
	@Autowired
	private ICardOrganizationManager cardOrganizationManager;
	@Autowired
	private ILoginMiniUserDao loginMiniUserDao;
	@Autowired
	private ILoginMiniUserHisDao loginMiniUserHisDao;
	@Autowired
	private ICardOrgRuleDAO cardOrgRuleDao;
	@Autowired
	private ISpecialTelNoDAO specialTelNoDAO;
	@Resource
	private ICardOrgRuleManager cardOrgRuleManager;
	@Resource
	private IUserService userService;
	@Resource
	private IPsamInfoManager psamInfoManager;
	@Resource
	private IDealEnquiryService dealEnquireService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IMiniService#getUidByPsamNo(java.lang.String,
	 * com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@WebMethod(operationName = "GetUidByPsamNo")
	public GetUidByPsamNoResponse getUidByPsamNo(
			@WebParam(name = "psamNo") String psamNo,
			@WebParam(name = "context") ApplicationContext context)
			throws ServiceException, ApplicationException {
		CardInfo cardInfo = cardManager.getCardInfoByPsamNo(psamNo, context);
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		LoginMiniUser loginMiniUser = cardInfo.getMiniUser();
		Long userId = null;
		if (loginMiniUser != null) {
			userId = loginMiniUser.getUid();
		}
		GetUidByPsamNoResponse getUidByPsamNoResponse = new GetUidByPsamNoResponse();
		getUidByPsamNoResponse.setUserId(userId);
		return getUidByPsamNoResponse;
	}

	@Override
	public UserMiniInfosDTO getUserUDInfo(long uid, ApplicationContext context)
			throws ServiceException {
		LoginMiniUser user = miniUserManager.getLoginMiniUser(uid);
		UserMiniInfosDTO map =null;
		if (user != null && CollectionHelper.isNotEmpty(user.getCardInfos())) {
			map = mapper.map(user, UserMiniInfosDTO.class);
		}
		return map;

	}
}
