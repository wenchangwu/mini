package com.lakala.mini.common;

public enum PSAMCardStatus {
     UNBOUND(0),   //解绑
     CLOSED(1),    //停用状态
     BOUND(2);     //绑定     
     
	 private final int status;
	 
	 PSAMCardStatus(int status) {
		 this.status = status;
	 } 
	 
	 public int getStatus() {
		 return this.status;
	 }
}
