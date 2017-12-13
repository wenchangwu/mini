package com.lakala.mini.common;

public enum UserCardStatus {
	 ABANDON(0), // 废卡
	 CLOSED(1); //停用状态

	 private final int status;
	 
	 UserCardStatus(int status) {
		 this.status = status;
	 } 
	 
	 public int getStatus() {
		 return this.status;
	 }
}
