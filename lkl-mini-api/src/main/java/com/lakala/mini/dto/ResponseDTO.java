/**
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.ServiceException;
import com.lakala.mini.common.CodeDefine;

/**
 * 响应信息
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-12
 */
@XmlRootElement(name="ResponseDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 服务执行结果响应码。
	 */
	@XmlElement(name="serviceResult")
	private String serviceResult=CodeDefine.SUCCESS;
	
	private boolean systemError=false;
	/**
	 * 错误信息。
	 */
	@XmlElement(name="errorMsg")
	private String msg;

	/**
	 * @return the serviceResult
	 */
	public String getServiceResult() {
		return serviceResult;
	}

	/**
	 * @param serviceResult the serviceResult to set
	 */
	public void setServiceResult(String serviceResult) {
		this.serviceResult = serviceResult;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * @return the systemError
	 */
	public boolean isSystemError() {
		return systemError;
	}

	/**
	 * @param systemError the systemError to set
	 */
	public void setSystemError(boolean systemError) {
		this.systemError = systemError;
	}

	public boolean isSuccess(){
		return CodeDefine.SUCCESS.equals(this.serviceResult);
	}
	
	public void assertSuccess() throws ServiceException,ApplicationException{
		if(!isSuccess()){
			if(this.systemError){
				throw new ServiceException(this.serviceResult,this.msg);
			}else{
				throw new ApplicationException(this.serviceResult, this.msg);
			}
		}
	}
	
	
}
