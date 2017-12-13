/**
 * 
 */
package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-13
 */
@XmlRootElement(name="ThirdPartyRediectRegisterResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyRediectRegisterResponse extends ResponseDTO {

	private static final long serialVersionUID = 1L;
	private String lineNo;
	/**
	 * @return the lineNo
	 */
	public String getLineNo() {
		return lineNo;
	}
	/**
	 * @param lineNo the lineNo to set
	 */
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	
	
}
