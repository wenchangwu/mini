/**
 * 
 */
package com.lakala.mini.service;

import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.ServiceException;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.mini.dto.PsamInfoDTO;
import com.lakala.mini.dto.UserMiniInfosDTO;
import com.lakala.mini.dto.card.UDServiceRequestDTO;
import com.lakala.mini.dto.card.UDServiceRequestExtDTO;
import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 超级U盾服务接口
 * @author QW
 * @IUDService.java
 * @2011-5-18 上午10:33:13
 */
@WebService(serviceName = "UDService", name = "UDService")
@WSDLDocumentation(value = "超级U盾用户管理服务", placement = Placement.BINDING)
public interface IUDService {
	/**
	 * 验证U盾的状态，如果状态是未
	 * @param psamNo
	 * @param context
	 * @return 状态码 0表示为初始化（需先初始化密码），1表示绑定（使用中）
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "CheckUDStatus")
	public String checkUDStatus(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException;
	/**
	 * 初始化U盾的使用密码
	 * @param psamNo    U盾的PSAM卡号
	 * @param passwd    初始化的密码，会做密码强度校验，长度12位以内
	 * @param context
	 * @return 返回码 00表示初始化成功
	 * @throws ServiceException,ApplicationException
	 */
	@WebMethod(operationName = "InitUDPasswd")
	public String initUDPasswd(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "passwd") String passwd,
            @WebParam(name = "areaNo") String areaNo,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 验证U盾的使用密码
	 * @param psamNo
	 * @param passwd
	 * @param context
	 * @return 返回码  00表示验证成功，其它表示验证出错
	 * @throws ServiceException,ApplicationException
	 */
	@WebMethod(operationName = "CheckUDPasswd")
	public String checkUDPasswd(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "passwd") String passwd,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 用户自己修改U盾使用密码
	 * @param psamNo        U盾PSAM卡号
	 * @param oldPasswd     旧密码
	 * @param newPasswd     新密码
	 * @param context
	 * @return 返回码  00表示修改成功，其它表示修改出错
	 * @throws ServiceException,ApplicationException
	 */
	@WebMethod(operationName = "ChangeUDPasswd")
	public String changeUDPasswd(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "oldPasswd") String oldPasswd,
            @WebParam(name = "newPasswd") String newPasswd,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 重置用户名密码，前端控制验证流程，后台暂只提供resert方法
	 * @param psamNo            U盾PSAM卡号
	 * @param newPasswd         新密码
	 * @param context
	 * @return 返回码  00表示重置成功
	 * @throws ServiceException,ApplicationException
	 */
	@WebMethod(operationName = "ResertUDPasswd")
	public String resertUDPasswd(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "newPasswd") String newPasswd,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 将U盾挂失，处于不可用状态
	 * @param psamNo
	 * @param context
	 * @return  返回码  00表示挂失成功
	 * @throws ServiceException,ApplicationException
	 */
	@WebMethod(operationName = "DropUD")
	public String dropUD(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 重新启用U盾（出现在挂失后，找回U盾的业务场景），需验证原先的密码
	 * @param psamNo      U盾PSAM卡号
	 * @param passwd	  密码
	 * @param context
	 * @return 返回码  00表示成功
	 * @throws ServiceException,ApplicationException
	 */
	@WebMethod(operationName = "RestartUD")
	public String restartUD(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "passwd") String passwd,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 将U盾绑定到用户中心的一个用户UID上
	 * @param uid            用户中心的用户ID
	 * @param psamNo         U盾PSAM卡号
	 * @param passwd          U盾密码
	 * @param context
	 * @return  返回码  00表示绑定成功，其它表示绑定失败
	 * @throws ServiceException,ApplicationException
	 */
	@WebMethod(operationName = "BindUDToUser")
	public String bindUDToUser(
            @WebParam(name = "uid") long uid,
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "passwd") String passwd,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 在U盾绑定到用户中心的一个用户UID上的情况下，解除绑定
	 * @param uid             用户中心的用户ID
	 * @param psamNo          U盾PSAM卡号
	 * @param passwd          U盾密码
	 * @param context
	 * @return  返回码  00表示成功，其它绑定失败
	 * @throws ServiceException,ApplicationException
	 */
	@WebMethod(operationName = "UnBindUDToUser")
	public String unBindUDToUser(
            @WebParam(name = "uid") long uid,
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "passwd") String passwd,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 用户绑定的U盾终端列表
	 *
	 * @param uid
	 * @param context
	 * @return
	 */
	@WebMethod(operationName = "GetUserUDInfo")
	public UserMiniInfosDTO getUserUDInfo(@WebParam(name = "uid") long uid,
                                          @WebParam(name = "context") ApplicationContext context)
			throws ServiceException;
	/**
	 * 用户绑定的终端列表,根据输入的类型
	 *
	 * @param uid
	 * @param cardType 卡类型见CardType类，如果需返回所有终端，cardType设置为-1
	 * @param context
	 * @return
	 */
	@WebMethod(operationName = "getUserTerminalInfoByType")
	public UserMiniInfosDTO getUserTerminalInfoByType(@WebParam(name = "uid") long uid,
                                                      @WebParam(name = "cardType") int cardType,
                                                      @WebParam(name = "context") ApplicationContext context)
			throws ServiceException;

	/**
	 * 根据psamNo获取终端信息
	 * @since 1.5.0
	 * @param psamNo
	 * @param context
	 * @return null为终端不存在
	 */
	@WebMethod(operationName = "getTerminalInfoByPsam")
	public PsamInfoDTO getTerminalInfoByPsam(@WebParam(name = "psam") String psamNo,
                                             @WebParam(name = "context") ApplicationContext context)
			throws ServiceException;

	/**
	 * 判断U盾PSAM卡号是否已经被绑定到用户
	 * @param psamNo
	 * @param context
	 * @return 已经绑定返回true，否则返回false
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "CheckPsamNoBindUser")
	public boolean checkPsamNoBindUser(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;
	/**
	 * 根据U盾的PSAM卡号得到绑定的用户的UID
	 * @param psamNo
	 * @param context
	 * @return 如果没有绑定用户的UID返回-1，否则返回该PSAM卡绑定用户的UID
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "GetUidByPsamNo")
	public long getUidByPsamNo(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;
	/**
	 * 根据U盾的PSAM卡号得到绑定的虚拟线路号
	 * @param psamNo
	 * @param context
	 * @return 如果没有绑定用户的UID返回-1，否则返回该PSAM卡绑定的虚拟线路号
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "GetLineNoByPsamNo")
	public String getLineNoByPsamNo(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;
	/**
	 * 根据PSAM卡号重新初始化终端信息（用于终端的转赠流程）
	 * @param psamNo
	 * @param context
	 * @return 如果成功返回00
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "ReInitTerminalByPsamNo")
	public String reInitTerminalByPsamNo(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;


	/**
	 * 初始化终端
	 * @param requestDTO 请求参数(psamNo,passwd,areaNo必填;bindingNo选填)
	 * @param context
	 * @return 返回码 00表示初始化成功
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "InitTerminal")
	public String initTerminal(
            @WebParam(name = "udServiceRequestDTO") UDServiceRequestDTO requestDTO,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;
	/**
	 * 根据PSAM卡号得到绑定的虚拟线路号，如规则需验证绑定号码，同时验证绑定号码
	 * @param requestDTO 请求参数(psamNo必填;bindingNo选填)
	 * @param context
	 * @return 如果没有绑定用户的UID返回-1，否则返回该PSAM卡绑定的虚拟线路号
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "GetLineNo")
	public String getLineNo(
            @WebParam(name = "udServiceRequestDTO") UDServiceRequestDTO requestDTO,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;

	/**
	 * 根据PSAM卡号判断该终端是否可以被转赠
	 * @param requestDTO 请求参数(psamNo必填)
	 * @param context
	 * @return 如果可以被转赠，返回00，否则返回false（String字符串）
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "CanReInitTerminalByPsamNo")
	public String canReInitTerminalByPsamNo(
            @WebParam(name = "udServiceRequestDTO") UDServiceRequestDTO requestDTO,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;

	/**
	 * 初始化终端(支持开通是验证手机号运营商号段、手机sim卡运营商标识、手机型号、手机厂商)
	 * @param requestDTO 请求参数(psamNo,passwd,areaNo必填;bindingNo选填)
	 * @param requestExtDTO  请求扩展参数（mobilePhoneNum、telecomOperators、mobilePhoneModel、mobilePhoneProduct、mobilePhoneManuFacturer选填）
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "InitSJTerminal")
	public String initSJTerminal(
            @WebParam(name = "udServiceRequestDTO") UDServiceRequestDTO requestDTO,
            @WebParam(name = "udServiceRequestExtDTO") UDServiceRequestExtDTO requestExtDTO,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException, ApplicationException;

	@WebMethod(operationName = "InitTDTerminal")
	public String initTDTerminal(
            @WebParam(name = "udServiceRequestDTO") UDServiceRequestDTO requestDTO,
            @WebParam(name = "udServiceRequestExtDTO") UDServiceRequestExtDTO requestExtDTO,
            @WebParam(name = "channel") String channel,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException, ApplicationException;
	/**
	 * 根据PSAM卡号得到绑定的虚拟线路号，如规则需验证绑定号码,同时验证绑定号码、运营商信息、手机型号、手机厂商信息
	 * @param requestDTO   请求参数(psamNo,passwd,areaNo必填;bindingNo选填)
	 * @param requestExtDTO   请求扩展参数（mobilePhoneNum、telecomOperators、mobilePhoneModel、mobilePhoneProduct、mobilePhoneManuFacturer选填）
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WebMethod(operationName = "GetSJLineNo")
	public String getSJLineNo(
            @WebParam(name = "udServiceRequestDTO") UDServiceRequestDTO requestDTO,
            @WebParam(name = "udServiceRequestExtDTO") UDServiceRequestExtDTO requestExtDTO,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;

	/**
	 * 直接转赠U盾。对UnBindUDToUser和BindUDToUser的封装
	 * @param fromuid 原来所有人的id
	 * @param touid 新所有人的id
	 * @param psamNo
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 * @since 1.4.2
	 */
	@WebMethod(operationName = "transferUDToUser")
	public String transferUDToUser(
            @WebParam(name = "fromuid") long fromuid,
            @WebParam(name = "touid") long touid,
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException,ApplicationException;

}
