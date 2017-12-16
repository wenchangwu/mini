package com.lakala.mini.server.core.cardCommand;
/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 下午06:11:50
 * 类说明
 */

public interface ICardCommandInvoker {
	public static final int CARDINFOINSTORECOMMAND = 1;//用户卡入库命令
    public static final int PSAMINFOINSTORECOMMAND = 2;//PSAM卡入库命令
    public static final int MANUOPENCOMMAND = 3;//人工开通命令
    public static final int MANUMOVECOMMAND = 4;//人工移机命令
    public static final int WITHDRAWCOMMAND = 5;//退机命令
    public static final int CLOSEWITHMANUALCOMMAND = 6;//人工关闭命令
    public static final int CHANGECOMMAND = 7;//换机、换用户卡命令
    public static final int DATAPUSHTOJOBCOMMAND = 8;//数据推送大作业命令
    public static final int AUDITCLOSEAUTOMATICCOMMAND = 9;//核审、自动关闭命令
    public static final int RESETUSERCARDPASSWDCOMMAND = 10;//密码重置
    public static final int GETCARDINFOCOMMAND = 11;//获得用户卡信息
    public static final int UD_PSAMINFOINSTORECOMMAND = 12;//U盾PSAM卡入库命令
    public static final int MINI_USECARD_PSAMINFOINSTORECOMMAND = 13;//使用用户卡的MINI的PSAM卡入库命令
    public static final int MINI_NOUSECARD_PSAMINFOINSTORECOMMAND = 14;//不使用用户卡的MINI的PSAM卡入库命令
    public static final int SJ_PSAMINFOINSTORECOMMAND = 15;//手机刷卡器PSAM卡入库命令
    public static final int MANUAL_PSAMINFOINSTORECOMMAND = 16;//人工开通的终端PSAM卡入库命令
    public static final int POS_PLUS_PSAMINFOINSTORECOMMAND = 17;//POSPLUS PSAM卡入库命令
    
    public String execute(Object obj, int command) throws Exception;
}
