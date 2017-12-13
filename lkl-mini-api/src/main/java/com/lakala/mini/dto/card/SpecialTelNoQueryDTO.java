/**
 * 
 */
package com.lakala.mini.dto.card;

import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.BaseQueryDTO;

/**
 * 特殊号码查询参数
 * @author 全伟(QW)
 * @date 2012-8-2
 * @time 下午04:14:32
 */
@XmlType(name = "SpecialTelNoQuery")
@WSDLDocumentation(value = "特殊号码查询参数", placement = Placement.BINDING)
public class SpecialTelNoQueryDTO extends BaseQueryDTO {

	private static final long serialVersionUID = -6403645374493994180L;
	
	private String[] telNos;
	private String[] areaNos;
	private String[] types;
	private String[] status;
	public String[] getTelNos() {
		return telNos;
	}
	public void setTelNos(String[] telNos) {
		this.telNos = telNos;
	}
	public String[] getAreaNos() {
		return areaNos;
	}
	public void setAreaNos(String[] areaNos) {
		this.areaNos = areaNos;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}

}
