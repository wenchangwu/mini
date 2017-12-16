package com.lakala.mini.server.core.cardCommand.impl;
/**
 * @author 刘文成 
 * @version 创建时间：2010-12-22 下午02:29:08
 * 类说明
 */

public class MoveMessage {

	private int moveCount;
	private boolean isAllowMove;
	
	public int getMoveCount() {
		return moveCount;
	}
	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}
	public boolean isAllowMove() {
		return isAllowMove;
	}
	public void setAllowMove(boolean isAllowMove) {
		this.isAllowMove = isAllowMove;
	}
}
