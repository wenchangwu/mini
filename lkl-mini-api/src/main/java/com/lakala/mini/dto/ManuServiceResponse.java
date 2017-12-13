package com.lakala.mini.dto;

import java.io.Serializable;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-7 下午02:49:30
 * 类说明，返回人工开通、人工移机结果参数,与大作业交互
 */

public class ManuServiceResponse implements Serializable {

	private static final long serialVersionUID = -5290852268343718657L;
	String deviceNo;//大作业设备号
	String ServiceResult;//服务执行结果响应码
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getServiceResult() {
		return ServiceResult;
	}
	public void setServiceResult(String serviceResult) {
		ServiceResult = serviceResult;
	}

}
