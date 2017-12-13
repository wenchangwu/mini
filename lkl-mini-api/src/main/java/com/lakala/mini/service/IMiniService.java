/**
 * @author ray
 * @filename: IMiniService.java
 * @create date: 上午09:49:04
 */
package com.lakala.mini.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentation.Placement;

import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.ServiceException;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.mini.dto.CustomerInfosDTO;
import com.lakala.mini.dto.GetUidByPsamNoResponse;
import com.lakala.mini.dto.ManuServiceRequest;
import com.lakala.mini.dto.ManuServiceResponse;
import com.lakala.mini.dto.QueryUserInfoRequest;
import com.lakala.mini.dto.RuleParamDTO;
import com.lakala.mini.dto.RuleParamQueryDTO;
import com.lakala.mini.dto.RuleParamsDTO;
import com.lakala.mini.dto.SaveRuleParamResponse;
import com.lakala.mini.dto.TransferMiniApplyDTO;
import com.lakala.mini.dto.TransferMiniApplyResultDTO;
import com.lakala.mini.dto.TransferMiniOrderDTO;
import com.lakala.mini.dto.TransferMiniQueryDTO;
import com.lakala.mini.dto.TransferMiniQueryResultDTO;
import com.lakala.mini.dto.TransferMiniReviewDTO;
import com.lakala.mini.dto.UserMiniInfosDTO;
import com.lakala.mini.dto.card.CardOrgDTO;
import com.lakala.mini.dto.card.CardOrgQueryDTO;
import com.lakala.mini.dto.card.CardOrgQueryResultDTO;
import com.lakala.mini.dto.card.CardOrgRuleResultDTO;
import com.lakala.mini.dto.card.CardQueryDTO;
import com.lakala.mini.dto.card.CardQueryResultDTO;
import com.lakala.mini.dto.card.CardResourceDTO;
import com.lakala.mini.dto.card.CardTelNoQueryResultDTO;
import com.lakala.mini.dto.card.SpecialTelNoDTO;
import com.lakala.mini.dto.card.SpecialTelNoQueryDTO;
import com.lakala.mini.dto.card.SpecialTelNoQueryResultDTO;
import com.lakala.mini.exception.NotUserCardOwnerException;
import com.lakala.mini.exception.TransferMiniException;
import com.lakala.mini.exception.UserCardNotFoundException;
import com.lakala.mini.exception.UserCardPasswordException;
import com.lakala.mini.exception.UserCardUnbindException;

@WebService(serviceName = "MiniService", name = "MiniService")
@WSDLDocumentation(value = "Mini用户管理服务", placement = Placement.BINDING)
public interface IMiniService {

	/**
	 * 用户家用mini机列表
	 * 
	 * @param uid
	 * @param context
	 * @return
	 */
	@WebMethod(operationName = "GetUserMiniInfo")
	@WebResult(name = "userMiniInfosDTO")
	public UserMiniInfosDTO getUserMiniInfo(@WebParam(name = "uid") long uid,
                                            @WebParam(name = "context") ApplicationContext context)
			throws ServiceException;

	/**
	 * 重设密码
	 * 
	 * @param uid
	 *            用户id
	 * @param userCardNum
	 *            用户卡号
	 * @param oldPassword
	 *            老秘密
	 * @param newPassword
	 *            新密码
	 * @param context
	 * @throws UserCardPasswordException
	 * @throws UserCardNotFoundException
	 * @throws UserCardUnbindException
	 * @throws NotUserCardOwnerException 
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "ChangeMiniUserCardPassword")
	public void changePassword(@WebParam(name = "uid") long uid,
                               @WebParam(name = "userCardNum") String userCardNum,
                               @WebParam(name = "oldPassword") String oldPassword,
                               @WebParam(name = "newPassword") String newPassword,
                               @WebParam(name = "context") ApplicationContext context)
			throws ServiceException, UserCardUnbindException,
			UserCardNotFoundException, UserCardPasswordException, NotUserCardOwnerException;

	/**
	 * 绑定网站用户和MINI终端用户卡
	 * 
	 * @param uid
	 *            用户id
	 * @param password
	 *            用户卡密码
	 * @param userCardNum
	 *            用户卡号
	 * @param context
	 * @return 00:成功<br/>
	 *         UO:用户卡被其他用户绑定<br/>
	 *         UJ：用户卡状态错误<br/>
	 *         UP:用户卡已被绑定<br/>
	 *         UQ:卡用户不存在<br/>
	 *         PE:密码不正确<br/>
	 *         U3:用户卡不存在<br/>
	 * 
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "绑定网站用户和MINI终端用户卡", placement = Placement.BINDING)
	@WebMethod(operationName = "MiniBindUserCard")
	@WebResult(name = "returnCode")
	public String bind(@WebParam(name = "uid") long uid,
                       @WebParam(name = "password") String password,
                       @WebParam(name = "userCardNum") String userCardNum,
                       @WebParam(name = "context") ApplicationContext context)
			throws ServiceException;
	/**
	 * 解除绑定网站用户和MINI终端用户卡
	 * 
	 * @param uid
	 *            用户id
	 * @param password
	 *            用户卡密码
	 * @param userCardNum
	 *            用户卡号
	 * @param context
	 * @return 00:成功<br/>
	 *         UO:用户卡被其他用户绑定<br/>
	 *         UJ：用户卡状态错误<br/>
	 *         UP:用户卡已被绑定<br/>
	 *         UQ:卡用户不存在<br/>
	 *         PE:密码不正确<br/>
	 *         U3:用户卡不存在<br/>
	 * 
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "解除绑定网站用户和MINI终端用户卡", placement = Placement.BINDING)
	@WebMethod(operationName = "MiniUnBindUserCard")
	@WebResult(name = "returnCode")
	public String unBind(@WebParam(name = "uid") long uid,
                         @WebParam(name = "password") String password,
                         @WebParam(name = "userCardNum") String userCardNum,
                         @WebParam(name = "context") ApplicationContext context)
			throws ServiceException;
		
	/**
	 * 导入用户卡资源信息
	 * @param cardResourceDTOs 用户卡资源List
	 * @param CardType      卡类型（1用户卡  2PSAM卡）
	 * @param context       用户上下文
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "Mini用户卡或者PSAM卡的信息导入", placement = Placement.BINDING)
	@WebMethod(operationName = "MiniCardInStore")
	public String cardInStore(
            @WebParam(name = "cardResourceDTOs") List<CardResourceDTO> cardResourceDTOs,
            @WebParam(name = "cardType") int cardType,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 导入U盾的PSAM卡资源信息
	 * @param psamNos      U盾的PSAM信息列表
	 * @param context      用户上下文
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "U盾用户的PSAM卡的信息导入", placement = Placement.BINDING)
	@WebMethod(operationName = "UDPsamInStore")
	public String udPsamInStore(
            @WebParam(name = "psamNos") String[] psamNos,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
    /**
     * 导入MINI的PSAM卡资源信息
     * @param psamNos MINI的PSAM信息列表
     * @param type    类型,0代表使用用户服务卡,1代表不使用用户服务卡
     * @param context 用户上下文
     * @return
     * @throws ServiceException
     */
	@WSDLDocumentation(value = "MINI用户的PSAM卡的信息导入", placement = Placement.BINDING)
	@WebMethod(operationName = "MINIPsamInStore")
	public String miniPsamInStore(
            @WebParam(name = "psamNos") String[] psamNos,
            @WebParam(name = "type") int type,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
    /**
     * 导入PSAM卡资源信息
     * @param psamNos PSAM信息列表
     * @param type    类型,0代表使用用户服务卡,1代表不使用用户服务卡,2代表超盾PSAM卡,3代表手机刷卡头PSAM卡
     * @param context 用户上下文
     * @return
     * @throws ServiceException
     */
	@WSDLDocumentation(value = "用户的PSAM卡的信息导入", placement = Placement.BINDING)
	@WebMethod(operationName = "PsamInStore")
	public String psamInStore(
            @WebParam(name = "psamNos") String[] psamNos,
            @WebParam(name = "type") int type,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 初始化用户卡的联名商户机构标识
	 * @param cardInfoIds  用户的ID
	 * @param cardNos 用户卡卡号
	 * @param orgId   联名商户机构标识ID
	 * @param context 用户上下文
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "初始化Mini用户卡的联名商户机构", placement = Placement.BINDING)
	@WebMethod(operationName = "InitCardOrg")
	public String initCardOrg(
            @WebParam(name = "cardInfoIds") Long[] cardInfoIds,
            @WebParam(name = "cardNos") String[] cardNos,
            @WebParam(name = "orgId") String orgId,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 撤销初始化的用户卡的联名商户机构标识
	 * @param cardInfoIds
	 * @param cardNos
	 * @param orgId
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "撤销初始化的Mini用户卡的联名商户机构", placement = Placement.BINDING)
	@WebMethod(operationName = "RevokeInitCardOrg")
	public String revokeInitCardOrg(
            @WebParam(name = "cardInfoIds") Long[] cardInfoIds,
            @WebParam(name = "cardNos") String[] cardNos,
            @WebParam(name = "orgId") String orgId,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 根据查询参数查询取得用户卡的相关信息
	 * @param cardQueryDTO  用户卡查询参数
	 * @param resultType  每一位为“1”表示需返回该信息（第一位:CardResource,第二位：CardInfo，第三位：CardUser）
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "查询取得用户卡的相关信息", placement = Placement.BINDING)
	@WebMethod(operationName = "QueryCard")
	public CardQueryResultDTO queryCard(
            @WebParam(name = "cardQueryDTO") CardQueryDTO cardQueryDTO,
            @WebParam(name = "resultType") String resultType,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 根据查询参数查询用户卡联名机构商户信息
	 * @param query
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "查询用户卡联名机构商户信息", placement = Placement.BINDING)
	@WebMethod(operationName = "GetMiniCardOrg")
	public CardOrgQueryResultDTO getMiniCardOrg(
            @WebParam(name = "query") CardOrgQueryDTO query,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 更新用户卡联名机构商户信息
	 * @param cardOrgDTO
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "更新用户卡联名机构商户信息", placement = Placement.BINDING)
	@WebMethod(operationName = "UpdateMiniCardOrg")
	public CardOrgDTO updateMiniCardOrg(
            @WebParam(name = "cardOrgDTO") CardOrgDTO cardOrgDTO,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	
	/**
	 * 创建用户卡联名机构商户信息
	 * @param cardOrgDTO
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "创建用户卡联名机构商户信息", placement = Placement.BINDING)
	@WebMethod(operationName = "CreateMiniCardOrg")
	public CardOrgDTO createMiniCardOrg(
            @WebParam(name = "cardOrgDTO") CardOrgDTO cardOrgDTO,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException,ApplicationException;
	
		
	/**
	 * 重置用户卡密码
	 * @param cardNo
	 * @param mobilePhone
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "重置用户卡密码", placement = Placement.BINDING)
	@WebMethod(operationName = "ResertUserCardPasswd")
	public String resertUserCardPasswd(
            @WebParam(name = "cardNo") String cardNo,
            @WebParam(name = "mobilePhone") String mobilePhone,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 更换用户卡卡号，或者更换PSAM卡号（換机）
	 * @param userCardId mini用户绑定关系ID
	 * @param oldCardNo  旧用户卡卡号
	 * @param oldPsamNo  旧PSAM卡卡号
	 * @param newCardNo  新用户卡卡号
	 * @param newPsamNo  新PSAM卡卡号
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "更换用户卡卡号，或者更换PSAM卡号（換机）", placement = Placement.BINDING)
	@WebMethod(operationName = "ChangeMiniInfo")
	public String changeMiniInfo(
            @WebParam(name = "userCardId") String userCardId,
            @WebParam(name = "newCardNo") String newCardNo,
            @WebParam(name = "newPsamNo") String newPsamNo,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 更换用户卡对应的联名卡商户标识的绑定关系
	 * @param cardInfoIds 需更换联名商户标识的用户的ID
	 * @param cardNos     需跟換联名商户标识的用户卡的卡号
	 * @param cardOrgId   需跟換联名商户标识的机构标识
	 * @param context
	 * @return 更换结果
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "更换用户卡对应的联名卡商户标识的绑定关系", placement = Placement.BINDING)
	@WebMethod(operationName = "ChangeCardOrg")		
	public String changeCardOrg(
            @WebParam(name = "cardInfoIds") Long[] cardInfoIds,
            @WebParam(name = "cardNos") String[] cardNos,
            @WebParam(name = "cardOrgId") Long cardOrgId,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException;
	/**
	 * 取得用户绑定关系变更的列表（默认是查询前一天的用户自助开通、自助移机的所有记录）
	 * @param operateType 操作类型
	 * @param startTime   开始时间
	 * @param endTime     结束时间
	 * @param context
	 * @return CardQueryResultDTO 查询结果
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "取得用户绑定关系变更的列表", placement = Placement.BINDING)
	@WebMethod(operationName = "GetUserCardOperate")		
	public  CardQueryResultDTO getUserCardOperate(
            @WebParam(name = "operateType") String[] operateType,
            @WebParam(name = "startTime") Date startTime,
            @WebParam(name = "endTime") Date endTime,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException;
	/**
	 * 停用或者关闭终端
	 * @param cardInfoId
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value = "停用或者关闭终端", placement = Placement.BINDING)
	@WebMethod(operationName = "StopTerminal")		
	public String stopTerminal(
            @WebParam(name = "cardInfoId") Long cardInfoId,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException,ApplicationException;
	/**
	 * 重新启用终端
	 * @param cardInfoId
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value = "重新启用终端", placement = Placement.BINDING)
	@WebMethod(operationName = "RestartTerminal")	
	public String restartTerminal(
            @WebParam(name = "cardInfoId") Long cardInfoId,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;
	/**
	 * 将网站用户和终端的关系强行解除绑定
	 * @param cardInfoId
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value = "将网站用户和终端的关系强行解除绑定", placement = Placement.BINDING)
	@WebMethod(operationName = "UnBindToUser")	
	public String unBindToUser(
            @WebParam(name = "cardInfoId") Long cardInfoId,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;
	/**
	 * 查询联名卡机构规则信息
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value = " 查询联名卡机构规则信息", placement = Placement.BINDING)
	@WebMethod(operationName = "GetCardOrgRules")	
	public CardOrgRuleResultDTO getCardOrgRules(
            @WebParam(name = "context") ApplicationContext context) throws ServiceException,ApplicationException;
	/**
	 * 
	 * @param psamNos
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value = "PSAM卡循环再利用", placement = Placement.BINDING)
	@WebMethod(operationName = "RecyclePsams")	
	public String recyclePsams(
            @WebParam(name = "psamNos") String[] psamNos,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException,ApplicationException;
	/**
	 * 分发（发货）确认
	 * @param cardInfoIds
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value = "终端分发（发货）确认", placement = Placement.BINDING)
	@WebMethod(operationName = "DeliverConfirm")	
	public String deliverConfirm(
            @WebParam(name = "cardInfoIds") Long[] cardInfoIds,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;
	/**
	 * 撤销分发（发货）确认
	 * @param cardInfoIds
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value = "撤销终端分发（发货）确认", placement = Placement.BINDING)
	@WebMethod(operationName = "RevokeDeliverConfirm")	
	public String revokeDeliverConfirm(
            @WebParam(name = "cardInfoIds") Long[] cardInfoIds,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;
	/**
	 * 退机
	 * @param cardInfoIds
	 * @param context
	 * @return
	 * @throws ServiceException
	 * @throws ApplicationException
	 */
	@WSDLDocumentation(value = "退机", placement = Placement.BINDING)
	@WebMethod(operationName = "RecedeTerminal")	
	public String recedeTerminal(
            @WebParam(name = "cardInfoIds") Long[] cardInfoIds,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException,ApplicationException;
	/**
	 * 根据查询参数查询取得PSAM卡的相关信息
	 * @param cardQueryDTO  PSAM卡查询参数
	 * @param resultType 
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "查询取得PSAM卡的相关信息", placement = Placement.BINDING)
	@WebMethod(operationName = "QueryPsamCard")
	public CardQueryResultDTO queryPsamCard(
            @WebParam(name = "cardQueryDTO") CardQueryDTO cardQueryDTO,
            @WebParam(name = "resultType") String resultType,
            @WebParam(name = "context") ApplicationContext context) throws ServiceException;
	/**
	 * 根据cardinfoID或者用户服务卡号查询绑定的电话号码信息列表
	 * @param cardInfoId
	 * @param cardNo
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "根据cardinfoID或者用户服务卡号查询绑定的电话号码信息列表", placement = Placement.BINDING)
	@WebMethod(operationName = "QueryCardTelNos")
	public CardTelNoQueryResultDTO queryCardTelNos(
            @WebParam(name = "cardInfoId") String cardInfoId,
            @WebParam(name = "cardNo") String cardNo,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException;
	/**
	 * 使用新号码替换曾经绑定的号码
	 * @param cardInfoId
	 * @param oldTelNo
	 * @param newTelNo
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "使用新号码替换曾经绑定的号码", placement = Placement.BINDING)
	@WebMethod(operationName = "ReplaceCardTelNo")
	public CardTelNoQueryResultDTO replaceCardTelNo(
            @WebParam(name = "cardInfoId") String cardInfoId,
            @WebParam(name = "oldTelNo") String oldTelNo,
            @WebParam(name = "newTelNo") String newTelNo,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException;
	
	/**
	 * 迷你移机申请。如果当前已有已提交未审核的单子，则不能新建新的审核申请。
	 * 获取当前审核单可以使用 
	 * {@link com.lakala.mini.service.IMiniService#transferMiniQuery}
	 * @param apply 申请单
	 * @return 申请接受结果
	 * @throws ServiceException
	 * @throws TransferMiniException
	 */
	@WSDLDocumentation(value = "迷你移机申请", placement = Placement.BINDING)
	@WebMethod(operationName = "transferMiniApply")
	@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
	public TransferMiniApplyResultDTO transferMiniApply(
            @WebParam(name = "apply") TransferMiniApplyDTO apply
    )throws ServiceException,TransferMiniException;
	
	/**
	 * 迷你移机申请审核
	 * @param review 审核信息
	 * @return 移机申请审核结果
	 * @throws ServiceException
	 * @throws TransferMiniException
	 */
	@WSDLDocumentation(value = "迷你移机申请审核", placement = Placement.BINDING)
	@WebMethod(operationName = "transferMiniReview")
	@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
	public TransferMiniOrderDTO transferMiniReview(
            @WebParam(name = "review") TransferMiniReviewDTO review
    )throws ServiceException,TransferMiniException;
	
	/**
	 * 迷你移机申请查询,按申请日期倒排
	 * @param query 请求参数
	 * @param context
	 * @return 移机申请单列表,按申请日期倒排
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "迷你移机申请查询", placement = Placement.BINDING)
	@WebMethod(operationName = "transferMiniQuery")
	@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
	public TransferMiniQueryResultDTO transferMiniQuery(
            @WebParam(name = "query") TransferMiniQueryDTO query, @WebParam(name = "context") ApplicationContext context
    )throws ServiceException;
	/**
	 * 特殊号码信息新增、修改
	 * @param specialTelNos 特殊号码信息列表
	 * @param context
	 * @return
	 * @throws ServiceException
	 */
	@WSDLDocumentation(value = "特殊号码信息新增、修改",placement = Placement.BINDING)
	@WebMethod(operationName = "SaveSpecialTelNos")
	public String saveSpecialTelNos(
            @WebParam(name = "specialTelNos") List<SpecialTelNoDTO> specialTelNos,
            @WebParam(name = "context") ApplicationContext context
    )throws ServiceException;
	/**
	 * 查询特殊号码列表
	 * @param specialTelNoQueryDTO  查询参数
	 * @param context
	 * @return 特殊号码分页列表
	 * @throws ServiceException
	 */
	@WSDLDocumentation (value = "查询特殊号码列表", placement = Placement.BINDING)
	@WebMethod(operationName = "QuerySpecialTelNos")
	public SpecialTelNoQueryResultDTO querySpecialTelNos(
            @WebParam(name = "specialTelNos") SpecialTelNoQueryDTO specialTelNoQueryDTO,
            @WebParam(name = "context") ApplicationContext context
    )throws ServiceException;
	
	/**
	 * 人工开通
	 * @param serviceParam
	 * @return
	 */
	@WSDLDocumentation(value = "人工开通终端", placement = Placement.BINDING)
	@WebMethod(operationName = "ManuOpen")
	public ManuServiceResponse manuOpen(
            @WebParam(name = "serviceParam") ManuServiceRequest serviceParam,
            @WebParam(name = "context") ApplicationContext context) throws ApplicationException;
	/**
	 * 人工移机
	 * @param serviceParam
	 * @return
	 */
	@WSDLDocumentation(value = "人工移机", placement = Placement.BINDING)
	@WebMethod(operationName = "ManuMove")
	public ManuServiceResponse manuMove(
            @WebParam(name = "serviceParam") ManuServiceRequest serviceParam,
            @WebParam(name = "context") ApplicationContext context) throws ApplicationException;
	
	/**
	 * 机构规则参数管理
	 * @param ruleId 规则id
	 * @param paramCode 参数代码
	 * @param paramName 参数名字
	 * @param paramdesc 参数备注
	 * @param paramValues 参数值
	 * @param isAppend 是否添加。true:是添加,false:删除已存在的数据后再加入
	 * @throws ApplicationException
	 * @since 1.3.0
	 */
	@WSDLDocumentation(value = "机构规则参数管理", placement = Placement.BINDING)
	@WebMethod(operationName = "UpdateRuleParam")
	public void updateRuleParam(
            @WebParam(name = "ruleId") Integer ruleId
            , @WebParam(name = "paramCode") String paramCode
            , @WebParam(name = "paramName") String paramName
            , @WebParam(name = "paramdesc") String paramdesc
            , @WebParam(name = "paramValues") List<String> paramValues
            , @WebParam(name = "isAppend") boolean isAppend
            , @WebParam(name = "context") ApplicationContext context)throws ApplicationException;
	
	/**
	 * 机构规则参数删除
	 * @param id 规则序号
	 * @throws ApplicationException
	 * @since 1.3.0
	 */
	@WSDLDocumentation(value = "机构规则参数删除", placement = Placement.BINDING)
	@WebMethod(operationName = "DeleteRuleParam")
	public void deleteRuleParam(@WebParam(name = "id") List<Long> id
            , @WebParam(name = "context") ApplicationContext context)throws ApplicationException;
	
	/**
	 * 机构规则参数查询
	 * @param query 查询参数
	 * @return 查询结果
	 * @throws ApplicationException
	 * @since 1.3.0
	 */
	@WSDLDocumentation(value = "机构规则参数查询", placement = Placement.BINDING)
	@WebMethod(operationName = "QueryRuleParam")
	public RuleParamsDTO queryRuleParam(@WebParam(name = "query") RuleParamQueryDTO query,
                                        @WebParam(name = "context") ApplicationContext context)throws ApplicationException;
	
	/**
	 * 保存机构规则参数。如果id为空则新建，存在则更新
	 * @param ruleParam 机构规则参数
	 * @param context 上下文
	 * @return 
	 * @throws ApplicationException
	 * @since 1.3.0
	 */
	@WSDLDocumentation(value = "保存机构规则参数。如果id为空则新建，存在则更新", placement = Placement.BINDING)
	@WebMethod(operationName = "SaveRuleParam")
	public SaveRuleParamResponse saveRuleParam(@WebParam(name = "ruleParam") RuleParamDTO ruleParam,
                                               @WebParam(name = "context") ApplicationContext context)throws ApplicationException;
	
	/**
	 * @param cardInfoId 用户卡id
	 * @param mobilePhone 手机号
	 * @param context
	 * @throws ApplicationException
	 * @since 1.3.0
	 */
	@WSDLDocumentation(value = "重置U盾密码", placement = Placement.BINDING)
	@WebMethod(operationName = "ResetUDPasswd")
	public String resetUDPasswd(@WebParam(name = "cardInfoId") Long cardInfoId
            , @WebParam(name = "mobilePhone") String mobilePhone
            , @WebParam(name = "context") ApplicationContext context);
	
	/**
	 * @param cardInfoId 用户卡id
	 * @param mobilePhone 手机号
	 * @param context
	 * @throws ApplicationException
	 * @since 1.3.0
	 */
	@WSDLDocumentation(value = "查询用户信息", placement = Placement.BINDING)
	@WebMethod(operationName = "QueryCustomerInfo")
	public CustomerInfosDTO queryCustomerInfo(@WebParam(name = "query") QueryUserInfoRequest query,
                                              @WebParam(name = "context") ApplicationContext context);
	
	
	/**
	 * 
	 * 根据U盾的PSAM卡号得到绑定的用户的UID
	 * @param psamNo
	 * @param context
	 * @return 
	 * @throws ServiceException
	 * @throws ApplicationException
	 * @since 1.4.0
	 */
	@WebMethod(operationName = "GetUidByPsamNo")
	public GetUidByPsamNoResponse getUidByPsamNo(
            @WebParam(name = "psamNo") String psamNo,
            @WebParam(name = "context") ApplicationContext context)throws ServiceException,ApplicationException;
	
	
}
