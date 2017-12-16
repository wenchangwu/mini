/**
 * 
 */
package com.lakala.mini.server.core.service.impl;

import com.lakala.common.exception.ApplicationException;
import com.lakala.core.objmapper.IObjectMapper;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.dto.ResponseDTO;
import com.lakala.mini.dto.ewallet.AccountRequest;
import com.lakala.mini.dto.ewallet.AccountResponse;
import com.lakala.mini.dto.ewallet.GetEWalletInfoRequest;
import com.lakala.mini.dto.ewallet.GetEWalletInfoResponse;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.CardUserExtInfo;
import com.lakala.mini.server.core.manager.IEWalletManager;
import com.lakala.mini.server.core.manager.exception.BuzException;
import com.lakala.mini.service.IEWalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.jws.WebService;

/**
 * 第三方机构服务实现类
 * 
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-14
 */
@Service("eWalletService")
@WebService
@Transactional(readOnly=true)
public class EWalletServiceImpl implements IEWalletService {
	final private Logger logger = LoggerFactory
			.getLogger(EWalletServiceImpl.class);
	@Resource
	private IObjectMapper mapper;

	@Resource
	private IEWalletManager eWalletManager;
	
	

	/* (non-Javadoc)
	 * @see com.lakala.mini.service.IEWalletService#account(com.lakala.mini.dto.ewallet.AccountRequest)
	 */
	@Override
	@Transactional(readOnly = false)
	public AccountResponse account(AccountRequest request) {
		AccountResponse result=new AccountResponse();
		CardUserExtInfo cardUserExInfo = mapper.map(request, CardUserExtInfo.class);
		try{
			CardUser account = eWalletManager.account(request.getUserId(),request.getAreaCode(),cardUserExInfo);
			result.setPsamNo(account.getPsamNo());
			result.setLineNo(account.getCardInfo().getEWalletLineNo());
		}catch(Exception e){
			handleMessage(result, e);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.service.IEWalletService#getEWalletInfo(com.lakala.mini.dto.ewallet.GetEWalletInfoRequest)
	 */
	@Override
	
	public GetEWalletInfoResponse getEWalletInfo(GetEWalletInfoRequest request) {
		GetEWalletInfoResponse result=new GetEWalletInfoResponse();
		
		CardUser cardUser=eWalletManager.getByUid(request.getUserId());
		if(cardUser!=null){
			result.setPsamNo(cardUser.getPsamNo());
			result.setLineNo(cardUser.getCardInfo().getEWalletLineNo());
		}
		return result;
		
	}
	/**
	 * 异常转换
	 * 
	 * @param result
	 * @param e
	 */
	private void handleMessage(ResponseDTO result, Exception e) {
		if (result != null && e != null) {
			logger.error(e.getMessage(), e);
			if (e instanceof BuzException) {
				result.setSystemError(false);
				result.setServiceResult(((BuzException) e)
						.getErrorCode());
				result.setMsg(e.getMessage());
			} else {
				if (e instanceof ApplicationException) {
					result.setSystemError(false);
					result.setServiceResult(((ApplicationException) e)
							.getErrorCode());
					result.setMsg(e.getMessage());
				} else {
					result.setSystemError(true);
					result.setServiceResult(CodeDefine.SYS_ERR);
					result.setMsg(e.getMessage());
				}
			}
			// rollback
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
	}
	
}
