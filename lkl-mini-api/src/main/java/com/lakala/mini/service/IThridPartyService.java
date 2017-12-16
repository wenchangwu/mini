/**
 * 
 */
package com.lakala.mini.service;

import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.ServiceException;
import com.lakala.mini.dto.*;
import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

/**
 * 第三方服务
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-14
 */
@WebService(serviceName = "ThridPartyService", name = "ThridPartyService")
@WSDLDocumentation(value = "第三方服务", placement = Placement.BINDING)
public interface IThridPartyService {
	
	
	/**
	 * 第三方注册跳转方式绑定。支持第三方机构用户和拉卡拉用户绑定。如果已绑定不会重复绑定。一个拉卡拉用户可以绑定同一机构下的多个第三方用户。
	 * @param request
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value="第三方注册跳转方式绑定。支持第三方机构用户和拉卡拉用户绑定。如果已绑定不会重复绑定。一个拉卡拉用户可以绑定同一机构下的多个第三方用户。",placement= Placement.BINDING)
	@WebMethod(operationName = "ThirdPartyRediectRegister")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
	public ThirdPartyRediectRegisterResponse thirdPartyRediectRegister(
            ThirdPartyRediectRegisterRequest request
    );

	/**
	 * 第三方用户通用绑定。支持第三方机构用户和拉卡拉用户或psam卡绑定。如果已绑定不会重复绑定。一个拉卡拉用户可以绑定同一机构下的多个第三方用户。
	 * @param request
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value="第三方用户通用绑定。支持第三方机构用户和拉卡拉用户或psam卡绑定。如果已绑定不会重复绑定。一个拉卡拉用户可以绑定同一机构下的多个第三方用户。",placement= Placement.BINDING)
	@WebMethod(operationName = "ThirdPartyRegister")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
	public ThirdPartyRegisterResponse thirdPartyRegister(
            ThirdPartyRegisterRequest request
    );

	/**
	 * 第三方登陆
	 * @param requestDTO
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value="第三方登陆",placement= Placement.BINDING)
	@WebMethod(operationName = "ThirdPartyLogin")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
	public ThirdPartyLoginResponse thirdPartyLogin(
            ThirdPartyLoginRequest request
    );

	/**
	 * 查询第三方商户，商户客户，拉卡拉客户，psam之间是否绑定。<br/>
	 * 只传入机构号和机构用户号，返回是否已存在。传入机构号、机构用户号和拉卡拉用户号判断是否绑定并返回线路号。
	 * 传入机构号、机构用户号和拉卡拉用户号和PSAM判断是否同psam绑定并返回线路号。
	 * @param request
	 * @return
	 */
	@WSDLDocumentation(value="查询第三方商户，商户客户，拉卡拉客户，psam之间是否绑定",placement= Placement.BINDING)
	@WebMethod(operationName = "ThirdPartyVerifyBind")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
	public ThirdPartyVerifyBindResponse thirdPartyVerifyBind(
            ThirdPartyVerifyBindRequest request);

	/**
	 * 查询第三方商户解绑。支持第三方机构用户和拉卡拉用户或psam卡解除绑定。只解除第三方用户同拉卡拉用户之间关系。不提供刷卡器解绑。
	 * @param request
	 * @return
	 */
	@WSDLDocumentation(value="查询第三方商户解绑。只解除第三方用户同拉卡拉用户之间关系。不提供刷卡器解绑。",placement= Placement.BINDING)
	@WebMethod(operationName = "ThirdPartyUnbind")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
	public ThirdPartyUnbindResponse thirdPartyUnbind(
            ThirdPartyUnbindRequest request);


	/**
	 * 查询第三方商户绑定信息查询
	 * @param request
	 * @return
	 */
	@WSDLDocumentation(value="查询第三方商户绑定信息查询",placement= Placement.BINDING)
	@WebMethod(operationName = "ThirdPartyBindInfoQuery")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
	public ThirdPartyBindInfoQueryResponse thirdPartyBindInfoQuery(
            ThirdPartyBindInfoQueryRequest request);

	/**
	 * 查询第三方商户绑定信息查询,如果没有绑定则绑定，并通知用户中心
	 * @param request
	 * @return
	 */
	@WSDLDocumentation(value="查询第三方商户绑定信息查询,如果没有绑定则绑定，并通知用户中心",placement= Placement.BINDING)
	@WebMethod(operationName = "ThirdPartyRegisterAndNoticToUserCenter")
	@SOAPBinding(parameterStyle=ParameterStyle.BARE)
    ThirdPartyRegisterResponse thirdPartyRegisterAndNoticToUserCenter(
            ThirdPartyRegisterRequest request);

}
