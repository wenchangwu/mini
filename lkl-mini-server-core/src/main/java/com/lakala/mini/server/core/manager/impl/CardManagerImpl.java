/**
 * 
 */
package com.lakala.mini.server.core.manager.impl;

import com.lakala.ca.dto.AuthenticationDTO;
import com.lakala.common.exception.ApplicationException;
import com.lakala.common.util.CollectionHelper;
import com.lakala.common.util.Tools;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.dto.DateRangeDTO;
import com.lakala.core.dto.PageResultDTO;
import com.lakala.core.objmapper.IObjectMapper;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.common.*;
import com.lakala.mini.dto.*;
import com.lakala.mini.dto.card.*;
import com.lakala.mini.exception.*;
import com.lakala.mini.server.core.cardCommand.CardCommandInvoker;
import com.lakala.mini.server.core.cardCommand.ICardCommandInvoker;
import com.lakala.mini.server.core.dao.*;
import com.lakala.mini.server.core.domain.*;
import com.lakala.mini.server.core.manager.ICardManager;
import com.lakala.mini.server.core.manager.ICardOrganizationManager;
import com.lakala.mini.server.core.manager.IMiniUserManager;
import com.lakala.mini.server.core.manager.IPsamInfoManager;
import com.lakala.mini.server.core.util.Config;
import com.lakala.mini.server.core.util.FtpConfig;
import com.lakala.mini.server.core.util.FtpUtil;
import com.lakala.mini.server.core.util.ValidatorUtil;
import com.lakala.sms.service.ISmsService;
import net.sf.oval.constraint.NotNull;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.io.*;
import java.net.SocketException;
import java.net.URI;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.*;

import static com.lakala.mini.server.core.util.FtpUtil.FTP_DIRECTORY_ROOT;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author ray 类说明,rpc服务类，可以抛出异常，可以被其他代码或rmi远程调用
 *         对于大多数的针对card的调用，使用command模式封装了调用方法
 *         ，提供该功能的类为CardCommandInvoker，例如开通，关闭，停机，移机，查询单一卡等
 *         对于分页查询等大量数据返回的情况，请在该类中自行完成
 */
@Service(value = "miniCardManger")
@Transactional(readOnly = true)
public class CardManagerImpl implements ICardManager {
	final static private Logger logger = LoggerFactory.getLogger(CardManagerImpl.class);

	public CardManagerImpl() {
	}

	@Autowired
	private ValidatorUtil validatorUtil;
	@Autowired
	private ICardInfoDAO cardInfoDAO;
	@Autowired
	private IMiniUserManager miniUserManager;
	@Resource
	private ISmsService smsService;
	@Autowired
	private ICardCommandInvoker cardCommandInvoker;
	@Autowired
	private ICardServiceGateway cardServiceGateway;
	@Autowired
	private ICardResourceDAO cardResourceDAO;
	@Autowired
	private ICardUserDAO cardUserDAO;
	@Autowired
	private ICardUserHisDAO cardUserHisDAO;
	@Autowired
	private IObjectMapper mapper;
	@Autowired
	private ICardOrganizationManager cardOrganizationManager;
	@Autowired
	private ICardInfoHisDAO cardInfoHisDAO;
	@Autowired
	private IPsamInfoManager psamInfoManager;
	@Autowired
	private ICardTelNoDAO cardTelNoDAO;
	@Autowired
	private ICardTelNoAppendDAO cardTelNoAppendDAO;
	@Autowired
	private ICardTelNoUnAppendDAO cardTelNoUnAppendDAO;
	@Autowired
	private ISpecialTelNoDAO specialTelNoDAO;
	@Autowired
	private ILoginMiniUserHisDao loginMiniUserHisDAO;
	
	/**
	 * 短信模板
	 */
	@Autowired
	private Config config;
	
	@Autowired
	private IReInitTerminalAuditDAO reInitTerminalAuditDAO;

	public static final long ONE_DAY = 24 * 60 * 60 * 1000;

	@Override
	@Transactional(readOnly = false)
	public String cardInStore(String fileUrl, int CardType,
			AuthenticationDTO authenication) throws ApplicationException {
		String responseCode = null;
		BufferedReader br = null;
		try {
			URI uri = new URI(fileUrl);
			File file = new File(uri);
			br = new BufferedReader(new FileReader(file));
			if (CardType == 1)
				responseCode = cardCommandInvoker.execute(br,
						CardCommandInvoker.CARDINFOINSTORECOMMAND);// 用户卡入库
			else if (CardType == 2)
				responseCode = cardCommandInvoker.execute(br,
						CardCommandInvoker.PSAMINFOINSTORECOMMAND);// PSAM卡入库

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				br = null;
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

		return responseCode;
	}

	@Override
	public ManuServiceResponse manuOpen(ManuServiceRequest serviceParam,
                                        AuthenticationDTO authenication) throws ApplicationException {
		String responseCode;
		try {
			responseCode = cardCommandInvoker.execute(serviceParam,
					CardCommandInvoker.MANUOPENCOMMAND);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			responseCode = CodeDefine.SYS_ERR;
			if(e instanceof ApplicationException)
				responseCode = ((ApplicationException) e).getErrorCode();
		}

		ManuServiceResponse manuServiceResponse = new ManuServiceResponse();
		manuServiceResponse.setDeviceNo(serviceParam.getDeviceNo());
		manuServiceResponse.setServiceResult(responseCode);
		return manuServiceResponse;
	}

	@Override
	public ManuServiceResponse manuMove(ManuServiceRequest serviceParam,
                                        AuthenticationDTO authenication) throws ApplicationException {
		String responseCode;
		try {
			responseCode = cardCommandInvoker.execute(serviceParam,
					CardCommandInvoker.MANUMOVECOMMAND);
		} catch (Exception e) {
			responseCode = "";
		}
		ManuServiceResponse manuServiceResponse = new ManuServiceResponse();
		manuServiceResponse.setDeviceNo(serviceParam.getDeviceNo());
		manuServiceResponse.setServiceResult(responseCode);
		return manuServiceResponse;
	}

	@Override
	public WithdrawResponse withdraw(@NotNull WithdrawRequest withdrawRequest,
                                     AuthenticationDTO authenication) throws ApplicationException {
		String deviceNo = withdrawRequest.getDeviceNo();
		String PSAMNo = withdrawRequest.getPSAMNo();

		cardServiceGateway.setCardInfoStatus(PSAMNo, UserCardStatus.ABANDON); // 标记用户卡为废卡
		cardServiceGateway
				.setPSAMCardInfoStatus(PSAMNo, PSAMCardStatus.UNBOUND); // 标记PSAM为解绑状态
		cardServiceGateway.setCardUserStatus(PSAMNo, OperateType.BACK_MINI); // 记录卡用户表操作类型、变更时间

		cardServiceGateway.setCardUserToHistory(PSAMNo); // 记录用户用户卡信息数据到历史表
		cardServiceGateway.setCardInfoToHistory(PSAMNo); // 记录用户卡用户表数据到历史表

		return new WithdrawResponse();
	}

	@Override
	public CloseWithManualResponse closeWithManual(
			CloseWithManualRequest closeWithManualRequest,
			AuthenticationDTO authenication) throws ApplicationException {

		String deviceNo = closeWithManualRequest.getDeviceNo();
		String PSAMNo = closeWithManualRequest.getPSAMNo();

		cardServiceGateway.setCardInfoStatus(PSAMNo, UserCardStatus.CLOSED); // 标记用户卡为停用状态
		cardServiceGateway.setPSAMCardInfoStatus(PSAMNo, PSAMCardStatus.CLOSED); // 标记PSAM为停用状态
		cardServiceGateway.setCardUserStatus(PSAMNo, OperateType.HAND_CLOSE); // 记录卡用户表操作类型、变更时间

		cardServiceGateway.setCardUserToHistory(PSAMNo); // 记录用户卡信息数据到历史表
		cardServiceGateway.setCardInfoToHistory(PSAMNo); // 记录卡用户表数据到历史表

		return new CloseWithManualResponse();
	}

	@Override
	public ChangeResponse change(ChangeRequest changeRequest,
                                 AuthenticationDTO authenication) throws ApplicationException {
		Long cardUserId = changeRequest.getCardUserId(); // 卡用户表主键
		String cardId = changeRequest.getCardId(); // 用户卡ID
		String changedCardId = changeRequest.getChangedCardId(); // 新用户卡（可为null）
		String changedPSAMNo = changeRequest.getChangedPSAMNo(); // 新psam卡号（可为null）

		CardUser cardUser = cardServiceGateway.retrieveCardUser(cardUserId);
		String originalPSAMNo = cardUser.getPsamNo();

		cardServiceGateway.setCardUserToHistory(originalPSAMNo); // 记录卡用户表历史
		cardServiceGateway.setCardInfoToHistoryById(cardId); // 记录用户卡信息历史
		cardServiceGateway.setPsamInfoToHistoryById(originalPSAMNo); // 记录PSAM信息历史

		if (isNotBlank(changedCardId)) {
			cardServiceGateway.setOriginalCardStatus(cardId); // 标记原用户卡为废卡
			cardServiceGateway.setOriginalCardData(cardId, changedCardId); // 用新用户卡信息覆盖用户卡信息表中相关数据
		}

		if (isNotBlank(changedPSAMNo)) {
			cardServiceGateway.setOriginalPSAMInfoStatus(originalPSAMNo); // 标记原PSAM为未绑定
			cardServiceGateway.setChangedPSAMInfoStatus(changedPSAMNo); // 新PSAM为绑定
			cardServiceGateway.setCardUserData(changedPSAMNo, cardUser); // 更新卡用户表中PSAM卡号，操作类型，变更时间
		}

		return null;// new ChangeResponse();
	}

	@Override
	public DataPushToJobResponse dataPushToJob(
			DataPushToJobRequest dataPushToJobRequest)
			throws ApplicationException {

		DateRangeDTO dateRange = dataPushToJobRequest.getDateRange();
		Date start = dateRange.getStart();
		Date end = dateRange.getEnd();

		try {
			getCardUserHisWithDateRange(start, end);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		return new DataPushToJobResponse();
	}

	private void getCardUserHisWithDateRange(Date start, Date end)
			throws IOException {

		Timestamp startTimestamp = new Timestamp(start.getTime());
		Timestamp endTimestamp = new Timestamp(end.getTime());

		int day = 1;
		long nextDayFromStart;
		do {
			nextDayFromStart = start.getTime() + ONE_DAY * day;
			Timestamp nextDayFromStartTimestamp = new Timestamp(
					nextDayFromStart);

			List<CardUserHis> cardUserHisList = cardServiceGateway
					.retrieveCardUserHistoryList(startTimestamp, endTimestamp,
							nextDayFromStartTimestamp);

			day++;

			ftpPushedDate(cardUserHisList);
		} while (nextDayFromStart < end.getTime());
	}

	private void ftpPushedDate(List<?> cardUserHisList) throws IOException {
		FtpUtil ftpUtil = new FtpUtil();
		ftpUtil.connectServer(new FtpConfig());

		String fileName = "";
		String remoteFilePath = FTP_DIRECTORY_ROOT + fileName;
		OutputStream outputStream = ftpUtil
				.uploadFileWithOutputStream(remoteFilePath);
		IOUtils.writeLines(cardUserHisList, null, outputStream);

		ftpUtil.closeServer();
	}

	private List ftpPullDate() throws SocketException, IOException {
		FtpUtil ftpUtil = new FtpUtil();
		ftpUtil.connectServer(new FtpConfig());

		String fileName = "";
		String sourceFileName = FTP_DIRECTORY_ROOT + fileName;
		InputStream inputStream = ftpUtil.downFile(sourceFileName);

		return IOUtils.readLines(inputStream);
	}

	// 文件数据：用户卡号、终端号码、PSAM号、psam卡状态为暂停
	// 文件数据: 生成文件 ftp推送大作业
	// 回执文件数据：用户卡号、终端号码、PSAM号、psam卡状态为暂停
	// 回执文件数据 取从大作业生成的文件
	@Override
	public AuditCloseAutomaticResponse AuditCloseAutomatic(
			AuditCloseAutomaticRequest auditCloseAutomaticRequest,
			AuthenticationDTO authenication) throws ApplicationException {
		File requestFile = new File("");
		File respondFile = new File("");

		List<CardUser> cardUserList = new ArrayList<CardUser>();
		try {
			ftpPushedDate(cardUserList);
		} catch (IOException e) {

		}

		try {
			List cardUserListResponse = (List<CardUser>) ftpPullDate();
			// TODO 根据回执文件更新数据库状态
		} catch (SocketException e) {

		} catch (IOException e) {

		}

		return new AuditCloseAutomaticResponse();
	}

	/**
	 * public void setCardInfoDAO(ICardInfoDAO cardInfoDAO) { this.cardInfoDAO =
	 * cardInfoDAO; }
	 */

	@Override
	public void resetUserCardPasswd(String cardNo,
			AuthenticationDTO authenication) throws ApplicationException {
		CardInfo cardInfo = cardInfoDAO.getByCardNo(cardNo);
		if (null != cardInfo) {
			CardUser cardUser = this.miniUserManager.findCardUserByCardInfoId(
					cardInfo.getId(), authenication);
			if (null != cardUser) {
				String newPass = Tools.getRandomNumStr(6);
				cardInfo.setPasswd(newPass);
				this.updateCardInfo(cardInfo, null);
				String userMobile = cardUser.getUserMobile();
				String msg = getRestPasswdMsg(newPass);
				String sendSmsACK = smsService.sendSms(userMobile, msg);
				if (!CodeDefine.SMS_SEND_SUCCESS.equals(sendSmsACK)) {
					logger.warn("用户卡{}密码重置:密码重置短信发送失败，短信返回码:{}", cardNo,
							sendSmsACK);
					throw new SMSSendException(sendSmsACK);
				}
				logger.warn("用户卡{}密码重置:成功:{}", cardNo, sendSmsACK);
			} else {
				logger.warn("用户卡{}密码重置:用户不存在", cardNo);
				throw CardUserNotFoundException.ERROR;
			}
		} else {
			logger.warn("用户卡{}密码重置:用户卡不存在", cardNo);
			throw UserCardNotFoundException.ERROR;
		}
	}
	@Override
	public void resertUserCardPasswd(String cardNo, String mobilePhone,
			ApplicationContext context) throws ApplicationException {
		CardInfo cardInfo = cardInfoDAO.getByCardNo(cardNo);
		resetUserPasswd("CardNo:"+cardNo, mobilePhone, cardInfo);
	
	}
	/**
	 * 
	 * @param newPass
	 * @return
	 */
	@Transactional(readOnly=false)
	private String getRestPasswdMsg(String newPass) {
		MessageFormat messageFormat = new MessageFormat(
				"您的拉卡拉刷卡机用户服务卡重置后的密码为：{0}，请妥善保管您的密码！");
		
		String msg = messageFormat.format(new String[]{newPass});
		return msg;
	}

	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED)
	public void changeUserCardPasswd(long uid, String cardNo, String oldPasswd,
			String newPasswd, long operatorId)
			throws UserCardUnbindException, UserCardNotFoundException,
            UserCardPasswordException, NotUserCardOwnerException {

		CardInfo cardInfo = cardInfoDAO.getByCardNo(cardNo);
		if (cardInfo != null) {
//			CardUser cardUser = cardUserDAO.getByCardInfoId(cardInfo.getId());
//			if (cardUser == null)
//				throw UserCardUnbindException.ERROR;
//			if( cardUser.getId().longValue() != uid) {
//				throw NotUserCardOwnerException.ERROR;
//			}
			LoginMiniUser loginMiniUser = cardInfo.getMiniUser();
			if(loginMiniUser == null)
				throw UserCardUnbindException.ERROR;
			if(loginMiniUser.getUid() != uid)
				throw NotUserCardOwnerException.ERROR;
			if (cardInfo.getPasswd().equals(oldPasswd)) {
				cardInfo.setPasswd(newPasswd);
				try {
					this.updateCardInfo(cardInfo, null);
				} catch (ApplicationException e) {
					logger.error(e.getMessage());
				}

			} else {
				logger.warn("用户卡{}密码重设:密码错误", cardNo);
				throw UserCardPasswordException.ERROR;
			}
		} else {
			logger.warn("用户卡{}密码重设:用户卡不存在", cardNo);
			throw UserCardNotFoundException.ERROR;
		}
	}

	@Override
	public CardInfo getCardInfo(String cardNo, AuthenticationDTO authenication)
			throws ApplicationException {
		return cardInfoDAO.getByCardNo(cardNo);
	}
    


	@Override
	public CardInfo getCardInfo(long cardInfoId, AuthenticationDTO authenication)
			throws ApplicationException {

		return cardInfoDAO.get(cardInfoId);
	}
	@Override
	public Collection<CardInfo> getCardInfos(Long[] cardInfoIds, AuthenticationDTO authenication)
			throws ApplicationException {
		if(CollectionHelper.isNotEmpty(cardInfoIds)){
			return cardInfoDAO.get(cardInfoIds);
		}
		return new ArrayList<CardInfo>();
	}
	@Override
	@Transactional(readOnly=false)
	public CardInfo updateCardInfo(CardInfo cardInfo,
                                   AuthenticationDTO authenication) throws ApplicationException {
//		logger.info("step:3............................");
//		CardInfoHis cardInfoHis = new CardInfoHis();
//		Long cardInfoId = cardInfo.getId();
//		CardInfo load = this.cardInfoDAO.getByCardNo(cardInfo.getCardNo());
//		String[] createHisCopyIgnore = { "id", "cardInfoId" };
//		BeanUtils.copyProperties(load, cardInfoHis,createHisCopyIgnore);
//		logger.info("更新前的对象为：{}",load.toString());
//		cardInfoHis.setId(null);
//		cardInfoHis.setCardInfoId(cardInfoId);
//		cardInfoHis.setModifyDate(new Timestamp(System.currentTimeMillis()));
//		cardInfoHisDAO.save(cardInfoHis);
		this.createCardInfoHis(cardInfo, null);
		if(logger.isDebugEnabled()){
			logger.debug("cardInfo.getID():{},cardInfo:{}",cardInfo.getId(),cardInfo.toString());
			logger.debug("cardInfo.getHis().getStatus():{},cardInfo.getStatus():{}",cardInfo.getHis().getStatus(),cardInfo.getStatus());
		}
		if (cardInfo.getHis().getStatus() != cardInfo.getStatus()) {
			if (cardInfo.getType() != CardType.USER_CARD) {
				//CardUser cardUser = miniUserManager.findCardUserByCardInfoId(cardInfo.getId(), null);
				CardUser cardUser = cardInfo.getCardUser();
				String psamNo = cardUser.getPsamNo();
				if (psamNo == null || "".equals(psamNo))
					throw new ApplicationException(CodeDefine.NO_PSAM);
				PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);
				psamInfo.setPasmState(cardInfo.getStatus());
				if(CardState.RELEASE == cardInfo.getStatus())
					psamInfo.setReleaseDate(new Timestamp(System.currentTimeMillis()));
				psamInfoManager.updatePsamInfo(psamInfo);
			}
			if (cardInfo.getType() == CardType.USER_CARD){
				//CardUser cardUser = miniUserManager.findCardUserByCardInfoId(cardInfo.getId(), null);
				CardUser cardUser = cardInfo.getCardUser();
				String psamNo = cardUser.getPsamNo();
				if (psamNo != null && !"".equals(psamNo)){
					PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);
					psamInfo.setPasmState(cardInfo.getStatus());
					if(CardState.RELEASE == cardInfo.getStatus())
						psamInfo.setReleaseDate(new Timestamp(System.currentTimeMillis()));
					psamInfoManager.updatePsamInfo(psamInfo);
				}
			}
		}
//		logger.info("............................");
		return cardInfoDAO.save(cardInfo);
	}
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED)
	private void createCardInfoHis(CardInfo cardInfo, AuthenticationDTO authenication){
		CardInfoHis cardInfoHis = cardInfo.getHis();
		Long cardInfoId = cardInfo.getId();
		//CardInfo load = this.cardInfoDAO.load(cardInfoId);
//		logger.info("更新前的对象为：{}",load.toString());
//		logger.info("更新前的旧对象为：{}",load.getHis().toString());
		cardInfoHis.setId(null);
		cardInfoHis.setCardInfoId(cardInfoId);
		cardInfoHis.setModifyDate(new Timestamp(System.currentTimeMillis()));
		cardInfoHisDAO.save(cardInfoHis);
	}
	private void deleteCardInfo(CardInfo cardInfo , AuthenticationDTO authenication) throws ApplicationException{
		createCardInfoHis(cardInfo,authenication);
		cardInfoDAO.remove(cardInfo.getId());
	}

	@Override
	public String cardInStore(Object cardResources, int cardType,
			AuthenticationDTO authenication) throws ApplicationException {
		String responseCode = null;		
		try {
			switch(cardType){
			case 0:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.CARDINFOINSTORECOMMAND);// 用户卡入库
				break;
			case 1:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.PSAMINFOINSTORECOMMAND);// PSAM卡入库
				break;
			case 2:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.UD_PSAMINFOINSTORECOMMAND);// U盾用户PSAM卡入库
				break;
		}
			
/*			if (CardType == 1)
				responseCode = cardCommandInvoker.execute(cardResources,
						CardCommandInvoker.CARDINFOINSTORECOMMAND);// 用户卡入库
			else if (CardType == 2)
				responseCode = cardCommandInvoker.execute(cardResources,
						CardCommandInvoker.PSAMINFOINSTORECOMMAND);// PSAM卡入库
*/

		} catch (Exception e) {
			e.printStackTrace();
			responseCode = CodeDefine.SYS_ERR;
		} 		
		return responseCode;
	}
	@Override
	public String psamCardInStore(Object cardResources, int cardType,
			AuthenticationDTO authenication) throws ApplicationException {
		String responseCode = null;		
		try {
			switch(cardType){
			case CardType.USER_CARD:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.MINI_USECARD_PSAMINFOINSTORECOMMAND);// 
				break;
			case CardType.UESR_MPHONE:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.MINI_NOUSECARD_PSAMINFOINSTORECOMMAND);// 
				break;
			case CardType.USER_UD:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.UD_PSAMINFOINSTORECOMMAND);// U盾用户PSAM卡入库
				break;
			case CardType.UESR_SJ:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.SJ_PSAMINFOINSTORECOMMAND);// 手机刷卡器用户PSAM卡入库
				break;
			case CardType.USER_POS_PLUS:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.POS_PLUS_PSAMINFOINSTORECOMMAND);// 手机刷卡器用户PSAM卡入库
				break;
			case CardType.USER_MANUAL:responseCode = cardCommandInvoker.execute(cardResources,
					CardCommandInvoker.MANUAL_PSAMINFOINSTORECOMMAND);// 人工开通的个人终端用户PSAM卡入库 
				break;
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			responseCode = CodeDefine.SYS_ERR;
		} 		
		return responseCode;
	}
	@Override
	public CardQueryResultDTO queryCard(CardQueryDTO cardQueryDTO,
                                        String resultType, ApplicationContext context)
			throws ApplicationException {
		String[] cardNos = cardQueryDTO.getCardNos();
		String[] cardOrgCodes = cardQueryDTO.getCardOrgCodes();
		String[] cardOrgNames = cardQueryDTO.getCardOrgNames();
		String[] userMobilePhones = cardQueryDTO.getMobilePhones();
		String[] psamNos = cardQueryDTO.getPsamNos();
		String[] telNos = cardQueryDTO.getTelNos();
		String[] telAreaNos = cardQueryDTO.getTelAreaNos();
		Integer[] status = cardQueryDTO.getStatus();
		Integer[] types = cardQueryDTO.getTypes();
		int pageNo = cardQueryDTO.getPageStart();
		int pageSize = cardQueryDTO.getPageSize();
		Page page = new Page();
		if(pageSize > 0){
			page.setPageSize(pageSize);
			page.setNeedPage(true);
			page.setPageStart(pageNo);
		}else{
			page = null;
		}
		CardQueryResultDTO result = new CardQueryResultDTO();
		CardQueryResultDTO emptyResult = new CardQueryResultDTO();
		emptyResult.setPageResultDTO(new PageResultDTO(0, 0, 0, 0, pageSize));
		Pagination<CardOrg> allCardOrgPages = cardOrganizationManager.get(null, cardOrgCodes, cardOrgNames, false, 0, 0, 0l);
		Map<Long,CardOrg> allCardOrgMaps = new HashMap<Long,CardOrg>();//解决性能问题，先缓存所有卡机构信息
		if(allCardOrgPages != null){
			List<CardOrg> allCardOrgs = allCardOrgPages.getData();
			if(allCardOrgs != null && allCardOrgs.size() > 0){
				Iterator<CardOrg> iterator = allCardOrgs.iterator();
				while(iterator.hasNext()){
					CardOrg cardOrg = iterator.next();
					allCardOrgMaps.put(cardOrg.getId(), cardOrg);					
				}
			}				
		}
		if(cardNos == null && cardOrgCodes == null && cardOrgNames == null && userMobilePhones == null && psamNos == null && telNos == null && telAreaNos ==null && status == null && types == null){
			if(resultType != null && !"".equals(resultType)){
				
/*				if(resultType.length() != 3){
					logger.info("查询用户卡信息时参数resultType错误，传输的值为：{0}", resultType);
					throw new ApplicationException(CodeDefine.PARAM_ERROR);
				}
				if("1".equals(resultType.substring(0, 1))){
					logger.debug("查询用户卡信息需返回对象CardResourceDTO");
					Pagination<CardResource>  cardResourcePages =  cardResourceDAO.findAllByPage(page);
					result.setPageResultDTO(mapper.map(cardResourcePages.getPage(), PageResultDTO.class));
					List<CardResource> cardResources = cardResourcePages.getData();
					if(cardResources != null){
						List<CardResultDTO> cardResultDTOs = new ArrayList<CardResultDTO>();
						Iterator<CardResource> i = cardResources.iterator();
						while(i.hasNext()){
							CardResultDTO cardResultDTO = new CardResultDTO();
							cardResultDTO.setCardResourceDTO(mapper.map(i.next(), CardResourceDTO.class));
							cardResultDTOs.add(cardResultDTO);
						}
						result.setCardResultDTOs(cardResultDTOs);
					}					
				}
*/
//				if("1".equals(resultType.substring(1, 2))){
					logger.debug("查询用户卡信息返回基础对象CardInfoDTO");
					Pagination<CardInfo> cardInfoPages = cardInfoDAO.findAllByPage(page);
					result.setPageResultDTO(mapper.map(cardInfoPages.getPage(), PageResultDTO.class));
					List<CardInfo> cardInfos = cardInfoPages.getData();
					if(cardInfos != null){
						List<CardResultDTO> cardResultDTOs = new ArrayList<CardResultDTO>();
						Iterator<CardInfo> i = cardInfos.iterator();
						while(i.hasNext()){
							CardResultDTO cardResultDTO = new CardResultDTO();
							CardInfo cardInfo = i.next();
							cardResultDTO.setCardInfoDTO(mapper.map(cardInfo, CardInfoDTO.class));
							CardUser cardUser = cardUserDAO.getByCardInfoId(cardInfo.getId());
							cardResultDTO.setCardUserDTO(mapper.map(cardUser, CardUserDTO.class));
							if(cardInfo.getCopFlag() != null){
								cardResultDTO.setCardOrgDTO(mapper.map(allCardOrgMaps.get(cardInfo.getCopFlag()), CardOrgDTO.class));
							}
							cardResultDTOs.add(cardResultDTO);
							
						}
						result.setCardResultDTOs(cardResultDTOs);
					}					

//				}
/*				if("1".equals(resultType.substring(2, 3))){
					logger.debug("查询用户卡信息需返回对象CardUserDTO");
					Pagination<CardUser> cardUserPages = cardUserDAO.findAllByPage(page);
					result.setPageResultDTO(mapper.map(cardUserPages.getPage(), PageResultDTO.class));
					List<CardUser> cardUsers = cardUserPages.getData();
					if(cardUsers != null){
						List<CardResultDTO> cardResultDTOs = new ArrayList<CardResultDTO>();
						Iterator<CardUser> i = cardUsers.iterator();
						while(i.hasNext()){
							CardResultDTO cardResultDTO = new CardResultDTO();
							cardResultDTO.setCardUserDTO(mapper.map(i.next(), CardUserDTO.class));
							cardResultDTOs.add(cardResultDTO);
						}
						result.setCardResultDTOs(cardResultDTOs);
					}					

				}
*/
				}
			return result;
		}else{
			Long[] cardOrgIds = null;
			if((cardOrgCodes != null && cardOrgCodes.length > 0) || (cardOrgNames !=null && cardOrgNames.length > 0)){
				Pagination<CardOrg> cardOrgPages = cardOrganizationManager.get(null, cardOrgCodes, cardOrgNames, false, 0, 0, 0l);
				if(cardOrgPages != null){
					List<CardOrg> cardOrgs = cardOrgPages.getData();
					if(cardOrgs != null && cardOrgs.size() > 0){
						Iterator<CardOrg> iterator = cardOrgs.iterator();
						int i = 0;
						cardOrgIds = new Long[cardOrgs.size()];
						while(iterator.hasNext()){
							CardOrg cardOrg = iterator.next();
							cardOrgIds[i] = cardOrg.getId();
							i++;
						}
					}else
						return emptyResult;
				}
			}
			Long[] cardInfoIds = null;
			if((userMobilePhones != null && userMobilePhones.length > 0) || (psamNos != null && psamNos.length > 0) || (telNos != null && telNos.length > 0) || (telAreaNos != null && telAreaNos.length > 0)){
				logger.debug("查询条件中含有手机号，Psam卡号，电话号码等参数，须先查询CardUser信息");
//				Page cardInfoPage = new Page();
//				cardInfoPage.setNeedPage(false);
//				cardInfoPage.setPageStart(0);
//				cardInfoPage.setPageSize(0);
				Pagination<CardUser> cardUserPages =  cardUserDAO.queryCardUser(userMobilePhones, psamNos, telNos, telAreaNos,null);
				List<CardUser> cardUsers = cardUserPages.getData();
				if(cardUsers != null && cardUsers.size() > 0){
					Iterator<CardUser> iterator = cardUsers.iterator();
					int j = 0;
					cardInfoIds = new Long[cardUsers.size()];
					while(iterator.hasNext()){
						CardUser cardUser = iterator.next();
						cardInfoIds[j] = cardUser.getCardInfoId();
						j++;
					}
				}else
					return emptyResult;
			}
				
			Pagination<CardInfo> cardInfoPages =  cardInfoDAO.queryCardInfo(cardNos,cardOrgIds,cardInfoIds, status, types,page);
			List<CardInfo> cardInfos = cardInfoPages.getData();
			if(cardInfos != null && cardInfos.size() > 0){
				Iterator<CardInfo> iterator = cardInfos.iterator();
				List<CardResultDTO> cardResultDTOs = new ArrayList<CardResultDTO>();
				while(iterator.hasNext()){
					CardInfo cardInfo = iterator.next();
					CardResultDTO cardResultDTO = new CardResultDTO();
					cardResultDTO.setCardInfoDTO(mapper.map(cardInfo, CardInfoDTO.class));
					CardUser cardUser = cardUserDAO.getByCardInfoId(cardInfo.getId());
					cardResultDTO.setCardUserDTO(mapper.map(cardUser, CardUserDTO.class));
					if(cardInfo.getCopFlag() != null){
						cardResultDTO.setCardOrgDTO(mapper.map(allCardOrgMaps.get(cardInfo.getCopFlag()), CardOrgDTO.class));
					}
					cardResultDTOs.add(cardResultDTO);
				}
				result.setCardResultDTOs(cardResultDTOs);
			}
			result.setPageResultDTO(mapper.map(cardInfoPages.getPage(), PageResultDTO.class));
		}
	
		return result;
	}

	
	@Override
	public boolean replaceCardNo(Long oldCardInfoId, String newCardNo) throws ApplicationException {
		if(oldCardInfoId != null && newCardNo != null && !"".equals(newCardNo)){
			CardInfo cardinfo = cardInfoDAO.load(oldCardInfoId);
			
			CardInfo newCardInfo = cardInfoDAO.getByCardNo(newCardNo);
			
			if(newCardInfo == null)
				throw new UserCardNotFoundException();
			//只有新CardNo的状态是未初始化的卡才能用于更换卡号
			if(newCardInfo.getStatus() == CardState.UNINITIALIZATION){

				CardInfo tempOldCardInfo = cardInfoDAO.load(oldCardInfoId);
				CardInfo tempNewCardInfo = cardInfoDAO.getByCardNo(newCardNo);
				
				String tmpNewTrack2 = newCardInfo.getTrack2();
				String tmpNewPasswd = newCardInfo.getPasswd();
				String tmpNewCVN = newCardInfo.getCvn();
				String tmpNewPVN = newCardInfo.getPvn();
				
				tempNewCardInfo.setCardNo(tempOldCardInfo.getCardNo());
				tempNewCardInfo.setCvn(tempOldCardInfo.getCvn());
				tempNewCardInfo.setTrack2(tempOldCardInfo.getTrack2());
				tempNewCardInfo.setPasswd(tempOldCardInfo.getPasswd());
				tempNewCardInfo.setPvn(tempOldCardInfo.getPvn());
			
				cardinfo.setCardNo(newCardNo);
				cardinfo.setTrack2(tmpNewTrack2);
				cardinfo.setPasswd(tmpNewPasswd);
				cardinfo.setCvn(tmpNewCVN);
				cardinfo.setPvn(tmpNewPVN);
				

				//1.先drop掉新卡的原有信息
				dropCardInfo(tempNewCardInfo);
				updateCardInfo(cardinfo, null);
				return true;
			}else{
				throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
			}
		}		
		return false;
	}
	private boolean dropCardInfo(CardInfo cardInfo) throws ApplicationException{
		cardInfo.setStatus(CardState.REPLACE);
		cardInfo.setCopFlag(null);
		this.updateCardInfo(cardInfo, null);
//		this.deleteCardInfo(cardInfo, null);
		return true;
	}


	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=false)
	public String initCardOrg(Long[] cardInfoIds, String[] cardNos, CardOrg cardOrg, ApplicationContext context) throws ApplicationException {
		if((cardNos != null && cardNos.length > 0) || (cardInfoIds != null && cardInfoIds.length > 0) ){
			Pagination<CardInfo> cardInfoPages =  cardInfoDAO.queryCardInfo(cardInfoIds,cardNos, CardState.UNINITIALIZATION, null);
			if(cardInfoPages != null && cardInfoPages.getData() != null && cardInfoPages.getData().size() > 0)
				return CodeDefine.USER_CARD_STATUS_ERROR;
			if(cardOrg == null)
				return CodeDefine.NO_CARD_ORG;
			//根据cardInfoIds，cardNos获取所用cardInfo
			Pagination<CardInfo> oldCardInfoPages = cardInfoDAO.queryCardInfo(cardInfoIds, cardNos, -1, null);
			if( oldCardInfoPages != null && oldCardInfoPages.getData() != null && oldCardInfoPages.getData().size() > 0){
				List<CardInfo> cardinfos = oldCardInfoPages.getData();
				initCardOrg(cardinfos,cardOrg,context);
			}
			return CodeDefine.SUCCESS;
		}
		return CodeDefine.OTHER_ERR;
	}
	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=false)
	public String initCardOrg(Collection<CardInfo> cardinfos, CardOrg cardOrg, ApplicationContext context) throws ApplicationException {
		if(cardOrg == null)
			return CodeDefine.NO_CARD_ORG;
		if(CollectionHelper.isNotEmpty(cardinfos)){
			for (CardInfo cardInfo : cardinfos) {
				initCardOrg(cardInfo,cardOrg,context);
			}
			return CodeDefine.SUCCESS;
		}
		return CodeDefine.OTHER_ERR;
	}
	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=false)
	public String initCardOrg(CardInfo cardinfo, CardOrg cardOrg, ApplicationContext context) throws ApplicationException {
		if(cardOrg == null)
			return CodeDefine.NO_CARD_ORG;
		if(cardinfo!=null){
			if(logger.isDebugEnabled()){
				logger.debug("初始化Mini用户卡联名商户标识 start. cardInfoId:"+cardinfo.getId()+" cardOrg:"+cardOrg.getId());
			}
			try{
				cardinfo.initCardOrg(cardOrg);
				updateCardInfo(cardinfo, null);
				miniUserManager.updateCardUser(cardinfo.getCardUser(), null);
			}catch(IllegalStateException e){
				return e.getMessage();
			}catch(IllegalArgumentException e){
				return e.getMessage();
			}
			if(logger.isDebugEnabled()){
				logger.debug("初始化Mini用户卡联名商户标识 end. cardInfoId:"+cardinfo.getId()+" cardOrg:"+cardOrg.getId());
			}
			return CodeDefine.SUCCESS;
		}
		return CodeDefine.OTHER_ERR;
	}
	@Override
	@Transactional(rollbackFor = Exception.class,readOnly=false)
	public String revokeInitCardOrg(Long[] cardInfoIds, String[] cardNos, ApplicationContext context) throws ApplicationException {
		if((cardNos != null && cardNos.length > 0) || (cardInfoIds != null && cardInfoIds.length > 0) ){
			Pagination<CardInfo> cardInfoPages =  cardInfoDAO.queryCardInfo(cardInfoIds,cardNos, CardState.INITIALIZATION, null);
			if(cardInfoPages != null && cardInfoPages.getData() != null && cardInfoPages.getData().size() > 0)
				return CodeDefine.USER_CARD_STATUS_ERROR;
		
			Pagination<CardInfo> oldCardInfoPages = cardInfoDAO.queryCardInfo(cardInfoIds, cardNos, -1, null);
			if( oldCardInfoPages != null && oldCardInfoPages.getData() != null && oldCardInfoPages.getData().size() > 0){
				Iterator<CardInfo> cardInfos = oldCardInfoPages.getData().iterator();
				while(cardInfos.hasNext()){
					CardInfo newCardInfo = cardInfos.next();
					newCardInfo.setCopFlag(null);
					newCardInfo.setMovingRule(0);
					newCardInfo.setStatus(CardState.UNINITIALIZATION);
					this.updateCardInfo(newCardInfo, null);
					CardUser cardUser = newCardInfo.getCardUser();
					cardUser.setOperatType(OperateType.REVOKE_INITIALIZATION);
					cardUser.setChangeDate(new Date(System.currentTimeMillis()));
					miniUserManager.updateCardUser(cardUser, null);
				}
			}
			return CodeDefine.SUCCESS;
		}
		return CodeDefine.PARAM_ERROR;
	}


	@Override
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	public String changeCardOrg(Long[] cardInfoIds, String[] cardNos, Long cardOrgId, ApplicationContext context) throws ApplicationException {
		if((cardInfoIds == null || cardInfoIds.length <=0 ) && (cardNos == null || cardNos.length <=0))
			return CodeDefine.PARAM_ERROR;
		CardOrg cardOrg = cardOrganizationManager.getById(cardOrgId,0l);
		if(cardOrg == null)
			return CodeDefine.NO_CARD_ORG;

		Pagination<CardInfo> oldCardInfoPages = cardInfoDAO.queryCardInfo(cardInfoIds, cardNos, -1, null);
		if( oldCardInfoPages != null && oldCardInfoPages.getData() != null && oldCardInfoPages.getData().size() > 0){
			Iterator<CardInfo> cardInfos = oldCardInfoPages.getData().iterator();
			while(cardInfos.hasNext()){
				CardInfo newCardInfo = cardInfos.next();
				if(newCardInfo.getStatus() != CardState.INITIALIZATION && newCardInfo.getStatus() != CardState.BINGDING)
					throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
				newCardInfo.setCopFlag(cardOrg.getId());
				newCardInfo.setMovingRule(cardOrg.getMovingRule());
				this.updateCardInfo(newCardInfo, null);
			}
			return CodeDefine.SUCCESS;
		}
		return CodeDefine.OTHER_ERR;
	}
	@Override
	public CardQueryResultDTO getUserCardOperate(String[] operateType, Date startTime, Date endTime, ApplicationContext context) throws ApplicationException {
		/**
		 * 如果传入的操作类型为空，默认为查询操作为：OperateType.AUTO_OPEN,OperateType.AUTO_MOVING的用户卡绑定信息
		 */
		if(operateType == null || operateType.length <= 0){
			operateType = new String[]{OperateType.AUTO_OPEN, OperateType.AUTO_MOVING};
		}
		if(startTime == null && endTime == null){
			Calendar cal = Calendar.getInstance();
			Calendar endCal = Calendar.getInstance();
			endCal.clear();
			endCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			endCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			endCal.set(Calendar.HOUR_OF_DAY, 0);
			cal.clear();
			cal.setTimeInMillis(System.currentTimeMillis()-24*60*60*1000);
			Calendar startCal = Calendar.getInstance();
			startCal.clear();
			startCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			startCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			startCal.set(Calendar.HOUR_OF_DAY, 0);
			startTime = startCal.getTime();
			endTime = endCal.getTime();
		}
		if(startTime == null){
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.setTimeInMillis(System.currentTimeMillis()-24*60*60*1000);
			Calendar startCal = Calendar.getInstance();
			startCal.clear();
			startCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			startCal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			startCal.set(Calendar.HOUR_OF_DAY, 0);
			startTime = startCal.getTime();
		}
			
		if(endTime == null)
			endTime = new Date(System.currentTimeMillis());
	
		Pagination<CardUser> cardUserPages = cardUserDAO.queryCardUserByOperateType(operateType, startTime, endTime, null);
		CardQueryResultDTO result = new CardQueryResultDTO();
		if(cardUserPages == null){
			result.setRetCode(CodeDefine.PARAM_ERROR);
			return result;
		}
		result.setPageResultDTO(mapper.map(cardUserPages.getPage(), PageResultDTO.class));
		if(cardUserPages.getData() != null && cardUserPages.getData().size() >0){
			Iterator<CardUser> cardUsers = cardUserPages.getData().iterator();
			List<CardResultDTO> cardResultDTOs = new ArrayList<CardResultDTO>();
			while(cardUsers.hasNext()){
				CardUser cardUser = cardUsers.next();
				CardInfo cardInfo = cardInfoDAO.get(cardUser.getCardInfoId());
				CardResultDTO cardResultDTO = new CardResultDTO();
				cardResultDTO.setCardInfoDTO(mapper.map(cardInfo, CardInfoDTO.class));
				cardResultDTO.setCardUserDTO(mapper.map(cardUser, CardUserDTO.class));
				cardResultDTOs.add(cardResultDTO);
			}
			result.setCardResultDTOs(cardResultDTOs);
			result.setRetCode(CodeDefine.SUCCESS);
		}
		return result;
	}

	@Override
	public CardInfo getCardInfoByPsamNo(String psamNo, ApplicationContext context) throws ApplicationException {
		CardUser cardUser = miniUserManager.findCardUserByPsamNo(psamNo, null);
		if(cardUser == null)
			throw new CardUserNotFoundException();
		CardInfo cardInfo = cardInfoDAO.get(cardUser.getCardInfoId());
		return cardInfo;
	}
/*
	@Override
	@Transactional(rollbackFor=Exception.class,readOnly=false)
	public String recyclePsams(String[] psamNos, ApplicationContext context) throws ApplicationException {
		if(psamNos == null || psamNos.length <=0)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		String psamNo = null;
		for(int i = 0 ; i  < psamNos.length ; i++){
			psamNo = psamNos[i];
			CardInfo cardInfo = this.getCardInfoByPsamNo(psamNo, context);
			if( cardInfo == null || cardInfo.getId() == null){
				PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);				
				if(psamInfo == null)
					throw new ApplicationException(CodeDefine.NO_PSAM);
				if(psamInfo.getPasmState() != CardState.REPLACE && psamInfo.getPasmState() != CardState.RELEASE)
					throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
				psamInfo.setPasmState(CardState.UNINITIALIZATION);
				psamInfoManager.updatePsamInfo(psamInfo);
			}
			else{
				if(cardInfo.getStatus() != CardState.REPLACE && cardInfo.getStatus() != CardState.RELEASE)
					throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
				cardInfo.setCopFlag(null);
				cardInfo.setStatus(CardState.UNINITIALIZATION);
				cardInfo.setPasswd(null);
				cardInfo.setMovingRule(0);
				this.updateCardInfo(cardInfo, null);
				CardUser cardUser = cardInfo.getCardUser();
				cardUser.setOperatType(OperateType.RECYCLE_PSAM);
				cardUser.setChangeDate(new Date(System.currentTimeMillis()));
				miniUserService.updateCardUser(cardUser, null);
			}	
		}
		return CodeDefine.SUCCESS;
	}

	@Override
	public String recedeTerminal(CardInfo cardInfo, ApplicationContext context) throws ApplicationException {
		CardUser cardUser = cardInfo.getCardUser();
		String psamNo = cardUser.getPsamNo();
		cardInfo.setStatus(CardState.RELEASE);
		cardInfo.setCopFlag(null);
		cardInfo.setIdentifyingCode(null);
		cardInfo.setIdentifyingCodeValidTime(null);
		cardInfo.setMovingRule(0);
		cardInfo.setPasswd(null);
		this.updateCardInfo(cardInfo, null);
		cardUser.setOperatType(OperateType.BACK_MINI);
		cardUser.setChangeDate(new Date(System.currentTimeMillis()));
		cardUser.setAddress(null);
		cardUser.setArea(null);
		cardUser.setAuditingDate(null);
		cardUser.setAuditingState(-1);
		cardUser.setBindingDate(null);
		cardUser.setBindingTelNo(null);
		cardUser.setCity(null);
		cardUser.setEmail(null);
		cardUser.setIdentityCard(null);
		cardUser.setIdentityCardPic(null);
		cardUser.setNodeNo(null);
		cardUser.setPost(null);
		cardUser.setProvoice(null);
		cardUser.setSelfOpenDate(null);
		cardUser.setTelAreaNo(null);
		cardUser.setTelModifyDate(null);
		cardUser.setTelMovingCount(0);
		cardUser.setTelNo(null);
		cardUser.setUserMobile(null);
		cardUser.setUserName(null);
		cardUser.setUserPic(null);
		cardUser.setPsamNo(null);
		miniUserService.updateCardUser(cardUser, null);
		cardInfoDAO.flush();
		cardUserDAO.flush();
		if(cardInfo.getType() == CardType.USER_UD){
			CardInfo newCardInfo = new CardInfo();
			newCardInfo.setType(CardType.USER_UD);
			newCardInfo.setStatus(CardState.RELEASE);
			newCardInfo = cardInfoDAO.save(newCardInfo);
			CardUser newCardUser = new CardUser();
			newCardUser.setPsamNo(psamNo);
			newCardUser.setCardInfoId(newCardInfo.getId());
			newCardUser.setTelMovingCount(0);
			newCardUser.setOperatType(OperateType.BACK_MINI);
			cardUserDAO.save(newCardUser);
		}
		return CodeDefine.SUCCESS;	
	}
*/
	
	/**
	 * @author QW  2011-11-25 变更：退机时保留用户信息，回库时再清空用户信息
	 */
	@Override
	public String recedeTerminal(CardInfo cardInfo, ApplicationContext context) throws ApplicationException {
		CardUser cardUser = cardInfo.getCardUser();
		cardInfo.setStatus(CardState.RELEASE);
		this.updateCardInfo(cardInfo, null);
		cardUser.setOperatType(OperateType.BACK_MINI);
		cardUser.setChangeDate(new Date(System.currentTimeMillis()));
		miniUserManager.updateCardUser(cardUser, null);
		//cardInfoDAO.flush();
		//cardUserDAO.flush();
		return CodeDefine.SUCCESS;
	}
	/**
	 * @author QW  2011-11-25 变更：退机保留用户信息，回库时清空用户信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String recyclePsams(String[] psamNos, ApplicationContext context) throws ApplicationException {
		if (psamNos == null || psamNos.length <= 0)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		String psamNo = null;
		for (int i = 0; i < psamNos.length; i++) {
			psamNo = psamNos[i];
			ViewPsamNo viewPsamNo = psamInfoManager.getViewPsamNofoByPsamNo(psamNo);
			CardInfo cardInfo=viewPsamNo.getCardInfo();
			recyclePsam(cardInfo,context);
		}
		return CodeDefine.SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.ICardManager#recyclePsam(com.lakala.mini.server.core.domain.CardInfo, com.lakala.core.dto.ApplicationContext)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public CardInfo recyclePsam(final CardInfo cardInfo, ApplicationContext context) throws ApplicationException {
		if (cardInfo==null)
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		if(logger.isDebugEnabled()){
			logger.debug("PSAM卡回库处理 start. cardInfoId:"+ cardInfo.getId());
		}
		CardInfo returnCardInfo=cardInfo;
		CardUser cardUser = cardInfo.getCardUser();
		String psamNo = cardUser.getPsamNo();
		PsamInfo psamInfo = psamInfoManager.getPsamInfoByPsamNo(psamNo);
		if (psamInfo == null)
			throw new ApplicationException(CodeDefine.NO_PSAM);
		if (CardType.USER_CARD == cardInfo.getType()) {
			switch (psamInfo.getPasmState()) {
			case CardState.RELEASE: {
				//用户卡用户关联信息清除
				cardUser.setPsamNo(null);
				cardUser.setOperatType(OperateType.RECYCLE_PSAM);
				cardUser.setChangeDate(new Date(System.currentTimeMillis()));
				break;
			}
			case CardState.REPLACE: {
				break;
			}

			default:
				throw new ApplicationException(CodeDefine.USER_CARD_STATUS_ERROR);
			}
		} else {
			//新建用户卡，用户卡用户关联信息
			cardInfo.setCopFlag(null);
			cardInfo.setIdentifyingCode(null);
			cardInfo.setIdentifyingCodeValidTime(null);
			cardInfo.setMovingRule(0);
			cardInfo.setPasswd(null);
			updateCardInfo(cardInfo, null);
			cardUser.setPsamNo(null);
			cardUser.setOperatType(OperateType.RECYCLE_PSAM);
			cardUser.setChangeDate(new Date(System.currentTimeMillis()));				
			//需要把新的cardInfo返回
			returnCardInfo = new CardInfo();
			returnCardInfo.setType(cardInfo.getType());
			returnCardInfo.setStatus(CardState.UNINITIALIZATION);
			returnCardInfo = cardInfoDAO.save(returnCardInfo);
			returnCardInfo.initHis();
			
			CardUser newCardUser = new CardUser();
			newCardUser.setPsamNo(psamNo);
			newCardUser.setCardInfoId(returnCardInfo.getId());
			newCardUser.setTelMovingCount(0);
			newCardUser.setOperatType(OperateType.RECYCLE_PSAM);
			newCardUser=cardUserDAO.save(newCardUser);
			newCardUser.initHis();
			returnCardInfo.setCardUser(newCardUser);
			
		}
		//设置psam状态未初始化
		psamInfo.recyclePsam();
		miniUserManager.updateCardUser(cardUser, null);
		psamInfoManager.updatePsamInfo(psamInfo);
		if(logger.isDebugEnabled()){
			logger.debug("PSAM卡回库处理 end. cardInfoId:"+ cardInfo.getId());
		}
		return returnCardInfo;
	}
	public CardQueryResultDTO queryPsamCard(CardQueryDTO cardQueryDTO,
                                            String resultType, ApplicationContext context)
			throws ApplicationException {
		String[] psamNos = cardQueryDTO.getPsamNos();
		Integer[] status = cardQueryDTO.getStatus();
		Integer[] types = cardQueryDTO.getTypes();
		String[] cardNos = cardQueryDTO.getCardNos();
		String[] cardOrgCodes = cardQueryDTO.getCardOrgCodes();
		String[] cardOrgNames = cardQueryDTO.getCardOrgNames();
		String[] userMobilePhones = cardQueryDTO.getMobilePhones();
		String[] telNos = cardQueryDTO.getTelNos();
		String[] telAreaNos = cardQueryDTO.getTelAreaNos();
		int pageNo = cardQueryDTO.getPageStart();
		int pageSize = cardQueryDTO.getPageSize();
		Page page = new Page();
		if(pageSize > 0){
			page.setPageSize(pageSize);
			page.setNeedPage(true);
			page.setPageStart(pageNo);
		}else{
			page = null;
		}
		Long[] cardOrgIds = null;
		
		CardQueryResultDTO result = new CardQueryResultDTO();
		Pagination<CardOrg> allCardOrgPages = cardOrganizationManager.get(null, cardOrgCodes, cardOrgNames, false, 0, 0, 0l);
		Map<Long,CardOrg> allCardOrgMaps = new HashMap<Long,CardOrg>();//解决性能问题，先缓存所有卡机构信息
		if(allCardOrgPages != null){
			List<CardOrg> allCardOrgs = allCardOrgPages.getData();
			if(allCardOrgs != null && allCardOrgs.size() > 0){
				Iterator<CardOrg> iterator = allCardOrgs.iterator();
				while(iterator.hasNext()){
					CardOrg cardOrg = iterator.next();
					allCardOrgMaps.put(cardOrg.getId(), cardOrg);
				}
				if((cardOrgCodes != null && cardOrgCodes.length >0) || (cardOrgNames != null && cardOrgNames.length > 0)){
					cardOrgIds = new Long[allCardOrgs.size()];
					allCardOrgMaps.keySet().toArray(cardOrgIds);
				}
			}				
		}

//		Pagination<ViewPsamNo>  viewPsamNoPages = psamInfoManager.queryViewPsamInfo(psamNos, status, types, page);
		Pagination<ViewPsamNo>  viewPsamNoPages = psamInfoManager.queryViewPsamInfo(psamNos, status, types, cardNos, userMobilePhones, telNos, telAreaNos, cardOrgIds, page);
		List<ViewPsamNo> viewPsamNos = viewPsamNoPages.getData();
		if(viewPsamNos != null && viewPsamNos.size() > 0){
			Iterator<ViewPsamNo> iterator = viewPsamNos.iterator();
			List<CardResultDTO> cardResultDTOs = new ArrayList<CardResultDTO>();
			while(iterator.hasNext()){
				ViewPsamNo viewPsamNo = iterator.next();
				CardResultDTO cardResultDTO = new CardResultDTO();
				cardResultDTO.setCardInfoDTO(mapper.map(viewPsamNo, CardInfoDTO.class));
				cardResultDTO.setCardUserDTO(mapper.map(viewPsamNo, CardUserDTO.class));
				if(viewPsamNo.getCopFlag() != null){
					cardResultDTO.setCardOrgDTO(mapper.map(allCardOrgMaps.get(viewPsamNo.getCopFlag()), CardOrgDTO.class));
				}
				cardResultDTOs.add(cardResultDTO);
			}
			result.setCardResultDTOs(cardResultDTOs);
		}
		result.setPageResultDTO(mapper.map(viewPsamNoPages.getPage(), PageResultDTO.class));

		return result;
	}

	@Override
	public CardTelNoQueryResultDTO queryCardTelNos(String cardInfoId, String cardNo, ApplicationContext context) throws ApplicationException {
		if((cardInfoId == null || "".equals(cardInfoId)) && (cardNo == null || "".equals(cardNo)))
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		CardInfo cardInfo = null;
		if(cardInfoId != null && !"".equals(cardInfoId))
			cardInfo = this.cardInfoDAO.get(Long.parseLong(cardInfoId));
		else
			cardInfo = this.cardInfoDAO.getByCardNo(cardNo);
		if(cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_USER_CARD);
		CardUser cardUser = cardInfo.getCardUser();
		CardTelNoQueryResultDTO resultDTO = new CardTelNoQueryResultDTO();
		List<CardTelNo> cardTelNos = cardTelNoDAO.getCardTelNoByCardInfoID(cardInfo.getId().longValue());
		List<CardTelNoDTO> cardTelNoDTOs = new ArrayList<CardTelNoDTO>();
		for(Iterator<CardTelNo> i = cardTelNos.iterator(); i.hasNext();){
			CardTelNo cardTelNo = i.next();
			CardTelNoDTO cardTelNoDTO = new CardTelNoDTO();
			cardTelNoDTO = mapper.map(cardTelNo, CardTelNoDTO.class);
			cardTelNoDTO.setCardNo(cardInfo.getCardNo());
			if(cardTelNo.getTelNo().equals(cardUser.getTelNo())){
				cardTelNoDTO.setUsing(true);
				cardTelNoDTO.setDate(cardUser.getChangeDate());
			}
				
			cardTelNoDTOs.add(cardTelNoDTO);
		}
		resultDTO.setCardTelNos(cardTelNoDTOs);
		List<CardTelNoAppend> cardTelNoAppends =  cardTelNoAppendDAO.getsBy("cardInfoId", cardInfo.getId().longValue());
		List<CardTelNoUnAppend> cardTelNoUnAppends =  cardTelNoUnAppendDAO.getsBy("cardInfoId", cardInfo.getId().longValue());
		List<CardTelNoAppendDTO> cardTelNoAppendDTOs = new ArrayList<CardTelNoAppendDTO>();
		List<CardTelNoUnAppendDTO> cardTelNoUnAppendDTOs = new ArrayList<CardTelNoUnAppendDTO>();
		if(cardTelNoAppends != null && cardTelNoAppends.size() > 0){			
			for(Iterator<CardTelNoAppend> i = cardTelNoAppends.iterator(); i.hasNext();){
				CardTelNoAppend tempCardTelNoAppend = i.next();
				logger.debug("tempCardTelNoAppend:{}",tempCardTelNoAppend);
				long cardTelNoUnAppendId = tempCardTelNoAppend.getCardTelNoUnAppendId();
				CardTelNoUnAppend tempCardTelNoUnAppend = null;
				if(cardTelNoUnAppendId > 0){					
					tempCardTelNoUnAppend = cardTelNoUnAppendDAO.get(cardTelNoUnAppendId);
				}
					
				CardTelNoAppendDTO tempcardTelNoAppendDTO = mapper.map(tempCardTelNoAppend, CardTelNoAppendDTO.class);
				if(tempCardTelNoUnAppend != null){
					tempcardTelNoAppendDTO.setCardTelNoUnAppendDTO(mapper.map(tempCardTelNoUnAppend, CardTelNoUnAppendDTO.class));
				}
				cardTelNoAppendDTOs.add(tempcardTelNoAppendDTO);
			}
		}
		if(cardTelNoUnAppends != null && cardTelNoUnAppends.size() > 0){			
			for(Iterator<CardTelNoUnAppend> i = cardTelNoUnAppends.iterator(); i.hasNext();){
				cardTelNoUnAppendDTOs.add(mapper.map(i.next(), CardTelNoUnAppendDTO.class));
			}
		}
		resultDTO.setCardTelNoAppends(cardTelNoAppendDTOs);
		resultDTO.setCardTelNoUnAppends(cardTelNoUnAppendDTOs);
		return resultDTO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public CardTelNoQueryResultDTO replaceCardTelNo(String cardInfoId, String oldTelNo, String newTelNo, ApplicationContext context)
			throws ApplicationException {
		if(cardInfoId == null || "".equals(cardInfoId) || oldTelNo == null || "".equals(oldTelNo) || newTelNo== null || "".equals(newTelNo))
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		CardInfo cardInfo = cardInfoDAO.get(Long.valueOf(cardInfoId));
		if(cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_USER_CARD);
		if(cardInfo.getCardUser() == null)
			throw new ApplicationException(CodeDefine.CARD_USER_NOT_FOUND);
		/*if(oldTelNo.equals(cardInfo.getCardUser().getTelNo()))
			throw new ApplicationException(CodeDefine.USER_CARD_BINDING_ALLREADY);*/
		List<CardTelNo> cardTelNos = cardTelNoDAO.getCardTelNoByCardInfoID(cardInfo.getId().longValue());
		if(cardTelNos != null && cardTelNos.size() > 0){
			for(Iterator<CardTelNo> i = cardTelNos.iterator(); i.hasNext();){
				CardTelNo cardTelNo = i.next();
				if(newTelNo.equals(cardTelNo.getTelNo()))
					throw new ApplicationException(CodeDefine.USER_CARD_BINDING_ALLREADY);
			}
		}
		
		List<CardTelNoUnAppend> tempCardTelNoUnAppends =  cardTelNoUnAppendDAO.getCardTelNoUnAppends(cardInfo.getId().longValue(), newTelNo, 0);
		if(tempCardTelNoUnAppends != null && tempCardTelNoUnAppends.size() > 0){
			for(Iterator<CardTelNoUnAppend> i = tempCardTelNoUnAppends.iterator(); i.hasNext(); ){
				CardTelNoUnAppend tempCardTelNoUnAppend = i.next();
				tempCardTelNoUnAppend.setStatus(1);
				cardTelNoUnAppendDAO.save(tempCardTelNoUnAppend);
			}
		}
		
		List<CardTelNoAppend> tempCardTelNoAppends = cardTelNoAppendDAO.getCardTelNoAppends(cardInfo.getId().longValue(), oldTelNo, 0);
		if(tempCardTelNoAppends != null && tempCardTelNoAppends.size() > 0){
			for(Iterator<CardTelNoAppend> i = tempCardTelNoAppends.iterator(); i.hasNext(); ){
				CardTelNoAppend tempCardTelNoAppend = i.next();
				tempCardTelNoAppend.setStatus(1);
				cardTelNoAppendDAO.save(tempCardTelNoAppend);
			}
		}		
		
		CardTelNoUnAppend cardTelNoUnAppend = new CardTelNoUnAppend();
		cardTelNoUnAppend.setBindingTelNo(oldTelNo);
		cardTelNoUnAppend.setCardInfoId(cardInfo.getId().longValue());
		cardTelNoUnAppend.setDate(new Date(System.currentTimeMillis()));
		cardTelNoUnAppend.setStatus(0);
		cardTelNoUnAppend = cardTelNoUnAppendDAO.save(cardTelNoUnAppend);
		logger.debug("cardTelNoUnAppend:{}",cardTelNoUnAppend);
		
		
		CardTelNoAppend cardTelNoAppend = new CardTelNoAppend();
		cardTelNoAppend.setBindingTelNo(newTelNo);
		cardTelNoAppend.setCardInfoId(cardInfo.getId().longValue());
		cardTelNoAppend.setDate(new Date(System.currentTimeMillis()));
		cardTelNoAppend.setCardTelNoUnAppendId(cardTelNoUnAppend.getId());
		cardTelNoAppendDAO.save(cardTelNoAppend);
		return this.queryCardTelNos(cardInfoId, cardInfo.getCardNo(), context);
//		return new CardTelNoQueryResultDTO();
	}

	@Override
	public SpecialTelNo querySpecialTelNo(String telNo) throws ApplicationException {
		if(telNo == null || "".equals(telNo))
			throw new ApplicationException(CodeDefine.PARAM_ERROR);
		return specialTelNoDAO.getBy("telNo", telNo);
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.ICardManager#resetUDPasswdByCardInfoId(java.lang.Long)
	 */
	@Override
	
	public void resetUDPasswdByCardInfoId(Long cardInfoId,String mobilePhone,ApplicationContext context) throws ApplicationException {
		CardInfo cardInfo = this.getCardInfo(cardInfoId, null);
		resetUserPasswd("CardInfoId:"+cardInfoId, mobilePhone, cardInfo);
	}

	/**
	 * @param cardInfoId
	 * @param mobilePhone
	 * @param cardInfo
	 * @throws ApplicationException
	 * @throws SMSSendException
	 * @throws CardUserNotFoundException
	 * @throws UserCardNotFoundException
	 */
	private void resetUserPasswd(String logKey, String mobilePhone,
			CardInfo cardInfo) throws ApplicationException, SMSSendException,
            CardUserNotFoundException, UserCardNotFoundException {
		
		if (null != cardInfo) {
			CardUser cardUser = this.miniUserManager.findCardUserByCardInfoId(
					cardInfo.getId(), null);
			if (null != cardUser) {
				logger.info("step:1.........................");
				String newPass = cardInfo.resetPasswd();
				logger.info("step:2..........................");
				this.updateCardInfo(cardInfo, null);
				String userMobile = cardUser.getUserMobile();
				if(mobilePhone != null && !"".equals(mobilePhone))
					userMobile= mobilePhone;
				String msg = getRestPasswdMsg(newPass);
				String sendSmsACK = smsService.sendSms(userMobile, msg);
				if (!CodeDefine.SMS_SEND_SUCCESS.equals(sendSmsACK)) {
					logger.warn("用户卡{}密码重置:密码重置短信发送失败，短信返回码:{}", logKey,
							sendSmsACK);
					throw new SMSSendException(sendSmsACK);
				}
				logger.info("用户卡{}密码重置:成功:{}", logKey, sendSmsACK);
			} else {
				logger.info("用户卡{}密码重置:用户不存在", logKey);
				throw CardUserNotFoundException.ERROR;
			}
		} else {
			logger.info("用户卡{}密码重置:用户卡不存在", logKey);
			throw UserCardNotFoundException.ERROR;
		}
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.ICardManager#unBindToUser(com.lakala.mini.server.core.domain.CardInfo)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public String unBindToUser(CardInfo cardInfo)  {
		if (cardInfo == null)
			return CodeDefine.NO_USER_CARD;
		String retStr=cardInfo.unBindToUser();
		if(CodeDefine.SUCCESS.equals(retStr)){
			LoginMiniUserHis loginMiniUserHis = new LoginMiniUserHis();
			loginMiniUserHis.setCardInfoId(cardInfo.getId());
			loginMiniUserHis.setUid(cardInfo.getMiniUser().getUid());
			loginMiniUserHis.setReleaseDate(new Date(System.currentTimeMillis()));
			loginMiniUserHisDAO.save(loginMiniUserHis);
		}
		return retStr;
		
	}

	
	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.ICardManager#recedeTerminal(java.util.Collection)
	 */
	@Override
	public String recedeTerminalAndUnBindToUser(Collection<CardInfo> cardInfos, ApplicationContext context) throws ApplicationException{
		if(CollectionHelper.isNotEmpty(cardInfos)){
			for (CardInfo cardInfo : cardInfos) {
				recedeTerminalAndUnBindToUser(cardInfo,context);
			}
		}
		return CodeDefine.SUCCESS;
	}
	@Override
	public String recedeTerminalAndUnBindToUser(CardInfo cardInfo, ApplicationContext context)throws ApplicationException{
		if (cardInfo == null)
			throw new ApplicationException(CodeDefine.NO_USER_CARD);
		String retStr= CodeDefine.SUCCESS;
		if(logger.isDebugEnabled()){
			logger.debug("解邦并退机 start. cardInfoId:"+cardInfo.getId());
		}
		if (cardInfo.getMiniUser() != null) {
			retStr=unBindToUser(cardInfo);
		}
		if(CodeDefine.SUCCESS.equals(retStr)){
			retStr = recedeTerminal(cardInfo,context);
		}
		if(logger.isDebugEnabled()){
			logger.debug("解邦并退机 end. cardInfoId:"+cardInfo.getId());
		}
		return retStr;
		
	}
	
	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRES_NEW)
	public ReInitTerminalAudit saveReInitTerminalAudit(ReInitTerminalAudit entity){
		return reInitTerminalAuditDAO.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.ICardManager#getCardInfoByUserId(java.lang.Long)
	 */
	@Override
	public List<CardInfo> getCardInfoByUserId(Long userId) {
		return this.cardInfoDAO.getByUserId(userId);
	}
}
