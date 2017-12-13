/**
 * 
 */
package com.lakala.mini.dto.ewallet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.mini.dto.ResponseDTO;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.1
 * @crate_date 2013-5-14
 */
@XmlRootElement(name="GetEWalletInfoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetEWalletInfoResponse extends ResponseDTO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 线路号
	 */
	private String lineNo;
	
	/**
	 * 终端号
	 */
	private String psamNo;

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

	/**
	 * @return the psamNo
	 */
	public String getPsamNo() {
		return psamNo;
	}

	/**
	 * @param psamNo the psamNo to set
	 */
	public void setPsamNo(String psamNo) {
		this.psamNo = psamNo;
	}
	
	
}
