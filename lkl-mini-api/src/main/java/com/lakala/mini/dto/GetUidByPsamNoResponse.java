/**
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.mini.common.CodeDefine;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-27
 */
@XmlRootElement(name="GetUidByPsamNoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetUidByPsamNoResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlElement(name="ServiceResult")
	private String serviceResult=CodeDefine.SUCCESS;
	private Long userId;
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
