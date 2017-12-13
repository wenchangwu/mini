/**
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.3.0
 * @crate_date 2012-12-28
 */
@XmlRootElement(name="SaveRuleParamResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveRuleParamResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlElement(name="ServiceResult")
	private String serviceResult;
	private Long id;
	
	
	/**
	 * 
	 */
	public SaveRuleParamResponse() {
		super();
	}
	/**
	 * @param id
	 * @param ServiceResult
	 */
	public SaveRuleParamResponse(Long id, String serviceResult) {
		this.id=id;
		this.serviceResult=serviceResult;
	}
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
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
