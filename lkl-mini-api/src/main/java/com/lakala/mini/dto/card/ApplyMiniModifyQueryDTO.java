/**
 * 
 */
package com.lakala.mini.dto.card;

import javax.xml.bind.annotation.XmlType;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.core.dto.BaseQueryDTO;

/**
 * 查询mini绑定号码变更申请单参数对象
 * @author 全伟(QW)
 * @date 2012-4-13
 * @time 下午03:38:38
 */
@XmlType(name = "ApplyMiniModifyQueryDTO")
@WSDLDocumentation(value = "查询mini绑定号码变更申请单参数对象", placement = Placement.BINDING)
public class ApplyMiniModifyQueryDTO extends BaseQueryDTO {
	private long uid;
	private long[] cardInfoIds;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long[] getCardInfoIds() {
		return cardInfoIds;
	}
	public void setCardInfoIds(long[] cardInfoIds) {
		this.cardInfoIds = cardInfoIds;
	}

}
