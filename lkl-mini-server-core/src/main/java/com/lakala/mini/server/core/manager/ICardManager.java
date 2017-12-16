/**
 * 
 */
package com.lakala.mini.server.core.manager;

import com.lakala.ca.dto.AuthenticationDTO;
import com.lakala.common.exception.ApplicationException;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.mini.dto.*;
import com.lakala.mini.dto.card.CardQueryDTO;
import com.lakala.mini.dto.card.CardQueryResultDTO;
import com.lakala.mini.dto.card.CardTelNoQueryResultDTO;
import com.lakala.mini.exception.*;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardOrg;
import com.lakala.mini.server.core.domain.ReInitTerminalAudit;
import com.lakala.mini.server.core.domain.SpecialTelNo;

import javax.jws.WebService;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author ray
 * 迷你相关卡服务
 */

@WebService
public interface ICardManager {
	
	
	/**
	 * 卡入库
	 * @param FileUrl 文件存放路径
	 * @param CardType 1用户卡  2PSAM卡
	 * @return
	 * @throws Exception 
	 */
	public String cardInStore(String FileUrl, int CardType, AuthenticationDTO authenication) throws ApplicationException;
	/**
	 * 用户卡原始信息入库
	 * @param cardResource  用户卡原始信息
	 * @param CardType      1用户卡  2PSAM卡
	 * @param authenication
	 * @return
	 * @throws ApplicationException
	 */
	public String cardInStore(Object cardResources, int CardType, AuthenticationDTO authenication) throws ApplicationException;
	/**
	 * PSAM卡信息入库
	 * @param cardResources  PSAM卡信息
	 * @param CardType       0使用用户卡的PSAM卡、1不使用用户卡的PSAM卡、2U盾的PSAM卡
	 * @param authenication
	 * @return
	 * @throws ApplicationException
	 */
	public String psamCardInStore(Object cardResources, int CardType, AuthenticationDTO authenication) throws ApplicationException;


	/**
	 * 人工开通
	 * @param serviceParam
	 * @return
	 */
	public ManuServiceResponse manuOpen(ManuServiceRequest serviceParam, AuthenticationDTO authenication) throws ApplicationException;
	/**
	 * 人工移机
	 * @param serviceParam
	 * @return
	 */
	public ManuServiceResponse manuMove(ManuServiceRequest serviceParam, AuthenticationDTO authenication) throws ApplicationException;

	/**
	 * 退机
	 * @param withdrawRequest
	 * @return
	 * @throws Exception
	 */
	WithdrawResponse withdraw(WithdrawRequest withdrawRequest, AuthenticationDTO authenication)  throws ApplicationException;

	/**
	 * 人工关闭
	 * @param closeWithManualRequest
	 * @return
	 */
	CloseWithManualResponse closeWithManual(CloseWithManualRequest closeWithManualRequest, AuthenticationDTO authenication)  throws ApplicationException;

	/**
	 *
	 * 换机、换用户卡
	 * @deprecated
	 * @param changeRequest
	 * @return
	 */
	ChangeResponse change(ChangeRequest changeRequest, AuthenticationDTO authenication)  throws ApplicationException;

	/**
	 * 数据推送大作业
	 * @param dataPushToJobRequest
	 * @return
	 */
	DataPushToJobResponse dataPushToJob(DataPushToJobRequest dataPushToJobRequest)  throws ApplicationException;

    /**
     * 核审、自动关闭
     * @param auditCloseAutomaticRequest
     * @return
     */
	AuditCloseAutomaticResponse AuditCloseAutomatic(AuditCloseAutomaticRequest auditCloseAutomaticRequest, AuthenticationDTO authenication)  throws ApplicationException;


	/**
	 * UserCard密码重置
	 * @param userCardId
	 * @param mobileNum
	 * @throws CardUserNotFoundException
	 * @throws UserCardNotFoundException
	 * @throws SMSSendException
	 */
	void resetUserCardPasswd(String userCardNum, AuthenticationDTO authenication) throws ApplicationException;
	/**
	 * UserCard密码重置
	 * @param cardNo  用户卡卡号
	 * @param mobilePhone  接受随机密码的手机号
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	void resertUserCardPasswd(String cardNo, String mobilePhone, ApplicationContext context) throws ApplicationException;
	/**
	 * UserCard密码重设
	 *
	 * @param uid
	 * @param userCardNum
	 * @param oldPasswd
	 *            旧密码
	 * @param newPasswd
	 *            新密码
	 * @throws UserCardPasswordException
	 * @throws UserCardUnbindException
	 * @throws UserCardNotFoundException
	 * @throws NotUserCardOwnerException
	 * @throws CardUserNotFoundException
	 */
	void changeUserCardPasswd(long uid, String userCardNum, String oldPasswd, String newPasswd, long operatorId) throws UserCardUnbindException, UserCardNotFoundException, UserCardPasswordException, NotUserCardOwnerException;
	/**
	 * 根据用户卡卡号获得用户卡信息
	 * @param cardNo 用户卡卡号
	 * @return
	 */
	public CardInfo getCardInfo(String cardNo, AuthenticationDTO authenication) throws ApplicationException;
	/**
	 * 根据用户卡ID获得用户卡信息
	 * @param cardInfoId 用户卡ID
	 * @param authenication
	 * @return
	 * @throws ApplicationException
	 */
	public CardInfo getCardInfo(long cardInfoId, AuthenticationDTO authenication)throws ApplicationException;
	/**
	 * 保存用户卡信息
	 * @param cardInfo
	 * @param authenication
	 * @return
	 * @throws ApplicationException
	 */
	public CardInfo updateCardInfo(CardInfo cardInfo, AuthenticationDTO authenication)throws ApplicationException;
	/**
	 * 通过查询参数查询用户卡相关信息
	 * @param cardQueryDTO
	 * @param resultType
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public CardQueryResultDTO queryCard(CardQueryDTO cardQueryDTO, String resultType, ApplicationContext context)throws ApplicationException;
	/**
	 * 用户更换用户卡信息
	 * @param oldCardInfoId
	 * @param newCardNo
	 * @return
	 * @throws ApplicationException
	 */
	public boolean replaceCardNo(Long oldCardInfoId, String newCardNo) throws ApplicationException;
	/**
	 * 批量初始化Mini用户卡联名商户标识
	 * @param cardNos
	 * @param cardOrg
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public String initCardOrg(Long[] cardInfoIds, String[] cardNos, CardOrg cardOrg, ApplicationContext context) throws ApplicationException;
	/**
	 * 批量撤销初始化Mini用户卡联名商户标识
	 * @param cardNos
	 * @param cardOrg
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public String revokeInitCardOrg(Long[] cardInfoIds, String[] cardNos, ApplicationContext context) throws ApplicationException;
	/**
	 * 变更用户卡联名商户标识
	 * @param cardInfoIds     用户卡信息ID
	 * @param cardNos         用户卡卡号
	 * @param cardOrgId       机构号ID
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public String changeCardOrg(Long[] cardInfoIds, String[] cardNos, Long cardOrgId, ApplicationContext context) throws ApplicationException;
	/**
	 * 取得用户绑定关系变更的列表
	 * @param operateType     操作类型列表，如果为空,默认为：M00002，M00003
	 * @param startTime       开始时间
	 * @param endTime         结束时间
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public CardQueryResultDTO getUserCardOperate(String[] operateType, Date startTime, Date endTime, ApplicationContext context) throws ApplicationException;
	/**
	 * 根据psam卡号得到用户卡相关信息
	 * @param psamNo
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public CardInfo getCardInfoByPsamNo(String psamNo, ApplicationContext context) throws ApplicationException;
	/**
	 * PSAM卡回库处理
	 * @param psamNos
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public String recyclePsams(String[] psamNos, ApplicationContext context) throws ApplicationException;
	/**
	 * 退机
	 * @param cardInfo
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public String recedeTerminal(CardInfo cardInfo, ApplicationContext context) throws ApplicationException ;
	/**
	 * 通过查询参数查询PSAM卡相关信息
	 * @param cardQueryDTO
	 * @param resultType
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public CardQueryResultDTO queryPsamCard(CardQueryDTO cardQueryDTO,
                                            String resultType, ApplicationContext context)
			throws ApplicationException;
	/**
	 * 根据cardinfoID或者用户服务卡号查询绑定的电话号码信息列表
	 * @param cardInfoId
	 * @param cardNo
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public CardTelNoQueryResultDTO queryCardTelNos(String cardInfoId, String cardNo, ApplicationContext context) throws ApplicationException;
	/**
	 * 使用新号码替换曾经绑定的号码
	 * @param cardInfoId
	 * @param oldTelNo
	 * @param newTelNo
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public CardTelNoQueryResultDTO replaceCardTelNo(String cardInfoId, String oldTelNo, String newTelNo, ApplicationContext context) throws ApplicationException;
	/**
	 * 通过电话号码查询是否是总机或者特服号码
	 * @param telNo 电话号码
	 * @return
	 * @throws ApplicationException
	 */
	public SpecialTelNo querySpecialTelNo(String telNo) throws ApplicationException;

	/**
	 * @param cardInfoId
	 * @param mobilePhone
	 * @param context
	 * @throws ApplicationException
	 * @since 1.3.0
	 */
	public void resetUDPasswdByCardInfoId(Long cardInfoId, String mobilePhone, ApplicationContext context) throws ApplicationException;

	/**
	 * @param cardInfo
	 * @return
	 * @throws ApplicationException
	 * @since 1.3.1
	 */
	public String unBindToUser(CardInfo cardInfo) ;

	/**
	 * 批量解邦并退机
	 * @param cardInfos 用户卡信息列表
	 * @param context
	 * @return
	 * @throws ApplicationException
	 * @since 1.3.1
	 */
	public String recedeTerminalAndUnBindToUser(Collection<CardInfo> cardInfos, ApplicationContext context)throws ApplicationException;
	/**
	 * 解邦并退机
	 * @param cardInfo 用户卡信息
	 * @param context
	 * @return
	 * @throws ApplicationException
	 * @since 1.3.1
	 */
	public String recedeTerminalAndUnBindToUser(CardInfo cardInfos, ApplicationContext context)throws ApplicationException;
	/**
	 * @param cardInfoId
	 * @param authenication
	 * @return
	 * @throws ApplicationException
	 * @since 1.3.1
	 */
	Collection<CardInfo> getCardInfos(Long[] cardInfoIds, AuthenticationDTO authenication)
			throws ApplicationException;
	/**
	 * 批量初始化Mini用户卡联名商户标识
	 * @param cardinfos
	 * @param cardOrg
	 * @param context
	 * @return
	 * @throws ApplicationException
	 * @since 1.3.1
	 */
	String initCardOrg(Collection<CardInfo> cardinfos, CardOrg cardOrg,
                       ApplicationContext context) throws ApplicationException;

	/**
	 * 初始化Mini用户卡联名商户标识
	 * @param cardinfo
	 * @param cardOrg
	 * @param context
	 * @return
	 * @throws ApplicationException
	 * @since 1.3.1
	 */
	String initCardOrg(CardInfo cardinfo, CardOrg cardOrg,
                       ApplicationContext context) throws ApplicationException;
	/**
	 * PSAM卡回库处理,如果是手机拉卡拉返回一个新的实体，其他返回原来的实体
	 * @param psam
	 * @param context
	 * @return
	 * @throws ApplicationException
	 * @since 1.3.1
	 */
	CardInfo recyclePsam(CardInfo cardInfo, ApplicationContext context)
			throws ApplicationException;
	
	
	
	/**
	 * @param entity
	 * @return
	 * @since 1.3.1
	 */
	ReInitTerminalAudit saveReInitTerminalAudit(ReInitTerminalAudit entity);
	/**
	 * @param userId
	 * @return
	 * @since 1.4.0
	 */
	public List<CardInfo> getCardInfoByUserId(Long userId);
	
}
