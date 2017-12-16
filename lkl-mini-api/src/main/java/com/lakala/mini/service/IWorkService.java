/**
 * 
 */
package com.lakala.mini.service;

import com.lakala.mini.dto.work.*;
import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * 综合作业系统提供的服务接口
 * 
 * @author QW
 * @IWorkService.java
 * @2011-6-7 下午05:29:24
 */
// @WebService(serviceName = "importUser", name = "importUser")
@WebService(targetNamespace = "importUser", name = "importUserPortType")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@WSDLDocumentation(value = "综合作业管理系统服务", placement = Placement.BINDING)
public interface IWorkService {
	@WebMethod(operationName = "importSUUser")
	public ImportSUUserResponseType importSUUser(
            @WebParam(partName = "parameters", name = "importSUUser", targetNamespace = "importUser") ImportSUUserRequestType parameters)
			throws Exception;
	@WebMethod(operationName = "importMiniUser")
	public ImportMiniUserResponseType importMiniUser(
            @WebParam(partName = "parameters", name = "importMiniUser", targetNamespace = "importUser") ImportMiniUserRequestType parameters)
			throws Exception;
	@WebMethod(operationName = "importUser")
	public ImportUserResponseType importUser(
            @WebParam(partName = "parameters", name = "importUser", targetNamespace = "importUser") ImportUserRequestType parameters)
			throws Exception;
}
