package com.lakala.mini.common;
/**
 * @author 刘文成 
 * @version 创建时间：2010-12-2 下午04:33:07
 * 类说明	用户资料审核状态
 */

public class UserAuditing {
	/**
	 * 不合格
	 */
	public static final int DENY = -1;
	/**
	 * 未提交用户资料
	 */
	public static final int NO_COMMIT = 0;
	/**
	 * 待审核
	 */
	public static final int AUDITING_WAITING = 1;
	/**
	 * 审核通过
	 */
	public static final int AUDITING_OVER = 2;
	/**
	 * 逾期未提交用户资料
	 */
	public static final int NO_COMMIT_OUT = 3;
}
