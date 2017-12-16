package com.lakala.mini.server.core.cardCommand;


/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 上午09:37:51
 * 类说明，命令模式接口
 */

public interface ICommand {

	String execute(Object obj) throws Exception;
}
