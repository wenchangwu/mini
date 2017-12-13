/**
 * 
 */
package com.lakala.mini.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.core.wrap.BaseResponseType;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.3.0
 * @crate_date 2013-1-9
 */
@XmlRootElement(name="CustomerInfos")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerInfosDTO extends BaseResponseType {
	private static final long serialVersionUID = 1L;
	private List<CustomerInfoDTO> datas;
	/**
	 * @return the datas
	 */
	public List<CustomerInfoDTO> getDatas() {
		return datas;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<CustomerInfoDTO> datas) {
		this.datas = datas;
	}
	

}
