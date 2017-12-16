/**
 * @author ray
 * @filename: IMiniService.java
 * @create date: 上午09:49:04
 */
package com.lakala.mini.service;

import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.ServiceException;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.mini.dto.GetUidByPsamNoResponse;
import com.lakala.mini.dto.UserMiniInfosDTO;
import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-4-7
 */
@WebService(serviceName = "EnquireService", name = "EnquireService")
@WSDLDocumentation(value = "查询服务", placement = Placement.BINDING)
public interface IEnquireService {

	
	
	/**
	 * 
	 * 根据U盾的PSAM卡号得到绑定的用户的UID
	 * @param psamNo
	 * @param context
	 * @return 
	 * @throws ServiceException
	 * @throws ApplicationException
	 * @since 1.4.0
	 */
	@WebMethod(operationName = "GetUidByPsamNo")
	public GetUidByPsamNoResponse getUidByPsamNo(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;

	/**
	 * 获取用户psam
	 *
	 * @param uid
	 * @param context
	 * @return
	 */
	@WebMethod(operationName = "GetUserUDInfo")
	public UserMiniInfosDTO getUserUDInfo(@WebParam(name = "uid") long uid,
                                          @WebParam(name = "context") ApplicationContext context)
			throws ServiceException;
	
	
	
}
