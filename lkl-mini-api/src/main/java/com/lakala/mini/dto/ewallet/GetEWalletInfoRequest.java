/**
 * 
 */
package com.lakala.mini.dto.ewallet;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lakala.core.dto.BaseRequestDTO;

/**
 *
 * @author leijiajian@lakala.com
 * @since 1.4.1
 * @crate_date 2013-5-14
 */
@XmlRootElement(name = "GetEWalletInfoRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetEWalletInfoRequest extends BaseRequestDTO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 拉卡拉用户序号
	 */
	@NotNull
	private Long userId;

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

	/**
	 * @param userId
	 */
	public GetEWalletInfoRequest(Long userId) {
		this();
		this.userId = userId;
	}

	/**
	 * 
	 */
	public GetEWalletInfoRequest() {
		super();
	}
	
	

}
