/*
 * 
 */
package com.lakala.mini.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;


/**
 * 
 * @author ray
 * @since 1.0.0
 */
@XmlRootElement(name = "TransferMiniQueryResult")
@XmlAccessorType(XmlAccessType.FIELD)
@WSDLDocumentation(value = "迷你移机申请单", placement = Placement.BINDING)
public class TransferMiniOrderDTO implements Serializable {
	private static final long serialVersionUID = -1312468309636656778L;
	
	private String orderId;
	/**
	 * 用户id
	 */
	private Long uid;
	@Size(min = 11, max = 11)
	private String mobileNum;
	
	/**
	 * 实名
	 */
	private String realname;
	
	/**
	 * 座机号
	 */
	private PhoneNumDTO phoneNum;
	
	/**
	 * 图片
	 */
	private List<String> idCardPic;
	
	/**
	 * 图片上传类型
	 */
	private IdCardPicUploadType idCardPicUploadType;
	
	/**
	 * 版本号
	 */
	private Integer version;
	
	/**
	 * 审核状态
	 */
	private TransferMiniReviewStatus status;

	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * @return the mobileNum
	 */
	public String getMobileNum() {
		return mobileNum;
	}

	/**
	 * @param mobileNum the mobileNum to set
	 */
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the phoneNum
	 */
	public PhoneNumDTO getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(PhoneNumDTO phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the idCardPic
	 */
	public List<String> getIdCardPic() {
		return idCardPic;
	}

	/**
	 * @param idCardPic the idCardPic to set
	 */
	public void setIdCardPic(List<String> idCardPic) {
		this.idCardPic = idCardPic;
	}

	/**
	 * @return the idCardPicUploadType
	 */
	public IdCardPicUploadType getIdCardPicUploadType() {
		return idCardPicUploadType;
	}

	/**
	 * @param idCardPicUploadType the idCardPicUploadType to set
	 */
	public void setIdCardPicUploadType(IdCardPicUploadType idCardPicUploadType) {
		this.idCardPicUploadType = idCardPicUploadType;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the status
	 */
	public TransferMiniReviewStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(TransferMiniReviewStatus status) {
		this.status = status;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
