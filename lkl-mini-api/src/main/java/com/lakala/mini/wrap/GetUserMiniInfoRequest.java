/**
 * @author ray
 * @filename: GetUserMiniInfoRequest.java
 * @create date: 下午03:22:58
 */
package com.lakala.mini.wrap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.lakala.core.wrap.BaseRequestType;

@XmlType(name = "GetUserMiniInfoRequest")
public class GetUserMiniInfoRequest extends BaseRequestType {

	private static final long serialVersionUID = -7560555897445711200L;
	@XmlElement(nillable = false, required = true)
	private long uid;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

}
