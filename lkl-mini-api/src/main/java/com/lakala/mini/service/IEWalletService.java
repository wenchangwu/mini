/**
 * 
 */
package com.lakala.mini.service;

import com.lakala.mini.dto.ewallet.AccountRequest;
import com.lakala.mini.dto.ewallet.AccountResponse;
import com.lakala.mini.dto.ewallet.GetEWalletInfoRequest;
import com.lakala.mini.dto.ewallet.GetEWalletInfoResponse;
import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.0.0
 * @crate_date 2013-5-14
 */

/**
 * 电子钱包服务
 * @author leijiajian@lakala.com
 * @since 1.4.1
 * @crate_date 2013-3-14
 */
@WebService(serviceName = "EWalletService", name = "EWalletService")
@WSDLDocumentation(value = "电子钱包服务", placement = Placement.BINDING)
public interface IEWalletService {
	
	/**
	 * 开通
	 * @param request
	 * @return
	 */
	@WSDLDocumentation(value="开通",placement= Placement.BINDING)
	@WebMethod(operationName = "account")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
    AccountResponse account(AccountRequest request);
	
	/**
	 * 获取信息
	 * @param request
	 * @return
	 */
	@WSDLDocumentation(value="获取信息",placement= Placement.BINDING)
	@WebMethod(operationName = "getEWalletInfo")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
    GetEWalletInfoResponse getEWalletInfo(GetEWalletInfoRequest request);
}
