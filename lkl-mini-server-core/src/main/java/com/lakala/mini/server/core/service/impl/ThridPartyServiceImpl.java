/**
 * 
 */
package com.lakala.mini.server.core.service.impl;

import com.lakala.ca.dto.BindThirdPartyUserInfoRequest;
import com.lakala.ca.service.IOrganizationService;
import com.lakala.ca.service.IUserService;
import com.lakala.ca.util.StringHelper;
import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.BizException;
import com.lakala.common.util.CollectionHelper;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.objmapper.IObjectMapper;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.dto.*;
import com.lakala.mini.dto.card.UDServiceRequestDTO;
import com.lakala.mini.dto.card.UDServiceRequestExtDTO;
import com.lakala.mini.server.core.dao.IThirdPartyUserMappingDAO;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.ThirdPartyUserMapping;
import com.lakala.mini.server.core.manager.ICardManager;
import com.lakala.mini.server.core.manager.exception.BuzException;
import com.lakala.mini.service.IThridPartyService;
import com.lakala.mini.service.IUDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 第三方机构服务实现类
 * 
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-14
 */
@Service("thridPartyService")
@WebService
@Transactional(readOnly = false)
public class ThridPartyServiceImpl implements IThridPartyService {
	final private Logger logger = LoggerFactory
			.getLogger(ThridPartyServiceImpl.class);

	@Resource
	private IObjectMapper mapper;

	@Resource
	private IThirdPartyUserMappingDAO thirdPartyUserMappingDAO;
	@Autowired(required = false)
	private IOrganizationService organizationService;

	@Resource
	private IUDService uDService;

	@Resource
	private ICardManager cardManager;
	
	@Resource
	private IUserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IUDService#thirdPartyRediectRegister(com.lakala
	 * .mini.dto.ThirdPartyRediectRegisterRequest)
	 */
	@Override
	public ThirdPartyRediectRegisterResponse thirdPartyRediectRegister(
			ThirdPartyRediectRegisterRequest request) {
		ThirdPartyRegisterRequest thirdPartyRegisterRequest = this.mapper.map(
				request, ThirdPartyRegisterRequest.class);
		ThirdPartyRegisterResponse thirdPartyRegister = this
				.thirdPartyRegister(thirdPartyRegisterRequest);
		return this.mapper.map(thirdPartyRegister,
				ThirdPartyRediectRegisterResponse.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IUDService#thirdPartyLogin(com.lakala.mini.dto
	 * .ThirdPartyLoginRequest)
	 */
	@Override
	public ThirdPartyLoginResponse thirdPartyLogin(
			ThirdPartyLoginRequest request) {
		ThirdPartyLoginResponse result = new ThirdPartyLoginResponse();
		mapper.map(request, result);
		try {
			assertNotNull(request);
			String orgIdStr = request.getOrgId();
			String orgId = getOrgId(orgIdStr);
			ThirdPartyUserMapping m = thirdPartyUserMappingDAO
					.getByOrgIdAndOrgUserIdAndPsamNo(orgId,
							request.getOrgUserId(), request.getPsamNo());
			if (m != null && m.verifyLogin(request.getPsamNo())) {
				mapper.map(m, result);
			} else {
				throw new BuzException(CodeDefine.CARD_USER_NOT_FOUND_MSG, CodeDefine.CARD_USER_NOT_FOUND);
			}
		} catch (Exception e) {

			handleMessage(result, e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IUDService#thirdPartyVerifyBind(com.lakala.mini
	 * .dto.ThirdPartyVerifyBindRequest)
	 */
	@Override
	@Transactional(readOnly = true)
	public ThirdPartyVerifyBindResponse thirdPartyVerifyBind(
			ThirdPartyVerifyBindRequest request) {
		ThirdPartyVerifyBindResponse result = new ThirdPartyVerifyBindResponse(
				false);
		mapper.map(request, result);
		result.setUserId(null);
		try {
			assertNotNull(request);
			String orgIdStr = request.getOrgId();
			
			String orgId = getOrgId(orgIdStr);
			ThirdPartyUserMapping m = null;

			Long userId = request.getUserId();
			if (userId != null) {
				// 存在lakala用户id
				if (StringHelper.hasNoText(request.getPsamNo())) {
					// 存在psamNo
					m = thirdPartyUserMappingDAO
							.getByOrgIdAndOrgUserIdAndPsamNo(orgId,
									request.getOrgUserId(), request.getPsamNo());
					if (m != null && m.verifyBind(userId)) {
						result.setBind(true);
						mapper.map(m, result);
						result.setUserId(userId);
					}
				} else {
					m = thirdPartyUserMappingDAO
							.getByOrgIdAndOrgUserIdAndUserId(orgId,
									request.getOrgUserId(), request.getUserId());
					if (m != null) {
						result.setBind(true);
						mapper.map(m, result);
						result.setUserId(userId);
					}
				}
			} else {
				m = thirdPartyUserMappingDAO.getByOrgIdAndOrgUserId(orgId,
						request.getOrgUserId());
				if (m != null) {
					result.setBind(true);
					result.setUserId(m.getUserId());
				}
			}
		} catch (Exception e) {
			handleMessage(result, e);
		}
		return result;
	}

	private String getOrgId(String orgIdStr) {
		return orgIdStr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IUDService#thirdPartyUnbind(com.lakala.mini.dto
	 * .ThirdPartyUnbindRequest)
	 */
	@Override
	public ThirdPartyUnbindResponse thirdPartyUnbind(
			ThirdPartyUnbindRequest request) {
		ThirdPartyUnbindResponse result = new ThirdPartyUnbindResponse();
		try {
			String orgIdStr = request.getOrgId();
			String orgId = getOrgId(orgIdStr);
			//Long userId = request.getUserId();
			String psamNo = request.getPsamNo();
			ThirdPartyUserMapping m = null;
			if(StringHelper.hasText(psamNo)){

				ApplicationContext context = mapper.map(request,
						ApplicationContext.class);
				CardInfo cardInfo = this.cardManager.getCardInfoByPsamNo(
						psamNo, context);
				if (cardInfo != null
						&& cardInfo.getThirdPartyUserMapping() != null) {
					ThirdPartyUserMapping thirdPartyUserMapping = cardInfo
							.getThirdPartyUserMapping();
					if (thirdPartyUserMapping.getOrgId().equals(orgId)
							&& thirdPartyUserMapping.getOrgUserId().equals(
									request.getOrgUserId())) {
						cardInfo.setThirdPartyUserMapping(null);
					} else {
						throw new BuzException(CodeDefine.CARD_USER_NOT_FOUND_MSG,
								CodeDefine.CARD_USER_NOT_FOUND);
					}

				} else {
					throw new BuzException(CodeDefine.CARD_USER_NOT_FOUND_MSG,
							CodeDefine.CARD_USER_NOT_FOUND);
				}
			}
			else {
				m = thirdPartyUserMappingDAO.getByOrgIdAndOrgUserId(
						orgId, request.getOrgUserId());
				if (m == null) {
					throw new BuzException(CodeDefine.CARD_USER_NOT_FOUND_MSG,
							CodeDefine.CARD_USER_NOT_FOUND);
				} else {
					if (CollectionHelper.isNotEmpty(m.getCardInfos())) {
						m.setUserId(null);
					} else {
						thirdPartyUserMappingDAO.remove(m.getId());
					}
				}
			} 
		} catch (Exception e) {
			handleMessage(result, e);
		}
		return result;
	}

	/**
	 * 异常转换
	 * 
	 * @param result
	 * @param e
	 */
	private void handleMessage(ResponseDTO result, Exception e) {
		if (result != null && e != null) {
			logger.error(e.getMessage(), e);
			if (e instanceof BuzException) {
				result.setSystemError(false);
				result.setServiceResult(((BuzException) e)
						.getErrorCode());
				result.setMsg(e.getMessage());
			} else {
				if (e instanceof ApplicationException) {
					result.setSystemError(false);
					result.setServiceResult(((ApplicationException) e)
							.getErrorCode());
					result.setMsg(e.getMessage());
				} else {
					result.setSystemError(true);
					result.setServiceResult(CodeDefine.SYS_ERR);
					result.setMsg(e.getMessage());
				}
			}
			// rollback
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
	}

	/**
	 * @param obj
	 * @throws BuzException
	 */
	private void assertNotNull(Object obj) throws BuzException {
		if (obj == null) {
			throw new BuzException(CodeDefine.PARAM_ERROR_MSG, CodeDefine.PARAM_ERROR);
		}
	}

	/**
	 * @param obj
	 * @throws BuzException
	 */
	private void assertNotEmpty(String obj) throws BuzException {
		if (StringHelper.hasNoText(obj)) {
			throw new BuzException(CodeDefine.PARAM_ERROR_MSG, CodeDefine.PARAM_ERROR);
		}
	}
	/**
	 * @param obj
	 * @throws BuzException
	 */
	private void assertNotEmpty(Object obj) throws BuzException {
		if (obj!=null) {
			throw new BuzException(CodeDefine.PARAM_ERROR_MSG, CodeDefine.PARAM_ERROR);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IThridPartyService#thirdPartyBindInfoQuery(com
	 * .lakala.mini.dto.ThirdPartyBindInfoQueryRequest)
	 */
	@Override
	@Transactional(readOnly = true)
	public ThirdPartyBindInfoQueryResponse thirdPartyBindInfoQuery(
			ThirdPartyBindInfoQueryRequest request) {
		ThirdPartyBindInfoQueryResponse result = new ThirdPartyBindInfoQueryResponse();
		String orgIdStr = request.getOrgId();
		String orgId = getOrgId(orgIdStr);
		try {
			ThirdPartyUserMapping tdMapping = thirdPartyUserMappingDAO
					.getByOrgIdAndOrgUserId(orgId, request.getOrgUserId());
			Set<CardInfo> cardInfos = new HashSet<CardInfo>();
			if (tdMapping != null) {
				cardInfos.addAll(tdMapping.getCardInfos());
				if (tdMapping.getUserId() != null) {
					cardInfos.addAll(cardManager.getCardInfoByUserId(tdMapping
							.getUserId()));
				}
			}

			List<UserMiniInfoDTO> dtos = new ArrayList<UserMiniInfoDTO>(
					cardInfos.size());
			for (CardInfo cardInfo : cardInfos) {
				dtos.add(this.mapper.map(cardInfo, UserMiniInfoDTO.class));
			}
			result.setUserMiniInfos(dtos);

		} catch (Exception e) {
			handleMessage(result, e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IThridPartyService#thirdPartyRegister(com.lakala
	 * .mini.dto.ThirdPartyRegisterRequest)
	 */
	@Override
	public ThirdPartyRegisterResponse thirdPartyRegister(
			ThirdPartyRegisterRequest request) {
		ThirdPartyRegisterResponse result = new ThirdPartyRegisterResponse();
		try {
			assertNotNull(request);
			assertNotNull(request.getOrgId());
			assertNotEmpty(request.getOrgUserId());
			Long userId = request.getUserId();
			String psamNo = request.getPsamNo();
			if (userId == null && StringHelper.hasNoText(psamNo)) {
				throw new BuzException(CodeDefine.PARAM_ERROR_MSG, CodeDefine.PARAM_ERROR);
			}
			String orgIdStr = request.getOrgId();
			String orgId = getOrgId(orgIdStr);
			// 查找第三方用户对照
			ThirdPartyUserMapping m = thirdPartyUserMappingDAO
					.getByOrgIdAndOrgUserId(orgId, request.getOrgUserId());
			if (userId != null) {
				// 根据lakala用户id绑定第三方用户
				ThirdPartyUserMapping userMapping = mapper.map(request,
						ThirdPartyUserMapping.class);
				if (m != null) {
					if (m.getUserId() != null && !m.getUserId().equals(userId)) {
						throw new BuzException(CodeDefine.USER_CARD_BINDING_OTHER_MSG,
								CodeDefine.USER_CARD_BINDING_OTHER);
					} else {
						m.setUserId(userId);
					}
				} else {
					userMapping.setUserId(request.getUserId());
					userMapping = thirdPartyUserMappingDAO.save(userMapping);
				}
				mapper.map(userMapping, result);
			} else {
				// 根据PSAM绑定第三方用户
				assertNotEmpty(request.getPsamNo());
				// 查找PSAM对应的cardInfo
				ApplicationContext context = mapper.map(request,
						ApplicationContext.class);
				CardInfo cardInfo = this.cardManager.getCardInfoByPsamNo(
						psamNo, context);
				if (cardInfo != null) {
					if (m == null) {
						m = mapper.map(request, ThirdPartyUserMapping.class);
						m = thirdPartyUserMappingDAO.save(m);
					} else {
						List<CardInfo> cardInfos = m.getCardInfos();
						Set<CardInfo> cardInfoSet = new HashSet<CardInfo>(
								cardInfos);
						// 判断是否已经绑定该用户
						if (cardInfoSet.contains(cardInfo)) {
							mapper.map(m, result);
							return result;
						}
					}
					if(CardState.INITIALIZATION!=cardInfo.getStatus()){
						if(CardState.BINGDING!=cardInfo.getStatus()){
							throw new BuzException(CodeDefine.USER_CARD_STATUS_ERROR_MSG,
									CodeDefine.USER_CARD_STATUS_ERROR);
						}
					}else{
						// 初始化终端
						UDServiceRequestDTO udServiceRequestDTO = mapper.map(
								request, UDServiceRequestDTO.class);
						udServiceRequestDTO.setPasswd("1q2w3e");
						udServiceRequestDTO.setAreaNo("010");
						UDServiceRequestExtDTO udServiceRequestExtDTO = mapper.map(
								request, UDServiceRequestExtDTO.class);
						String initSJTerminal = uDService.initTDTerminal(
								udServiceRequestDTO, udServiceRequestExtDTO,orgId,
								context);
						if (!CodeDefine.SUCCESS.equals(initSJTerminal))
							throw new BuzException("开通失败",initSJTerminal);
					}
					
					cardInfo.setThirdPartyUserMapping(m);
					mapper.map(m, result);
				} else {
					throw new BuzException(CodeDefine.CARD_HAS_NOT_DELIVER_MSG,
							CodeDefine.CARD_HAS_NOT_DELIVER);
				}

			}
		} catch (Exception e) {
			handleMessage(result, e);
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.service.IThridPartyService#thirdPartyRegister(com.lakala
	 * .mini.dto.ThirdPartyRegisterRequest)
	 */
	@Override
	public ThirdPartyRegisterResponse thirdPartyRegisterAndNoticToUserCenter(
			ThirdPartyRegisterRequest request) {
		ThirdPartyRegisterResponse result = new ThirdPartyRegisterResponse();
		try {
			assertNotNull(request);
			assertNotNull(request.getOrgId());
			assertNotEmpty(request.getOrgUserId());
			Long userId = request.getUserId();
			String psamNo = request.getPsamNo();
			if (userId == null || StringHelper.hasNoText(psamNo)) {
				throw new BuzException(CodeDefine.PARAM_ERROR_MSG, CodeDefine.PARAM_ERROR);
			}
			String orgIdStr = request.getOrgId();
			String orgId = getOrgId(orgIdStr);
			// 查找第三方用户对照
			ThirdPartyUserMapping m = thirdPartyUserMappingDAO
					.getByOrgIdAndOrgUserId(orgId, request.getOrgUserId());
			
			// 根据PSAM绑定第三方用户
			// 查找PSAM对应的cardInfo
			ApplicationContext context = mapper.map(request,
					ApplicationContext.class);
			CardInfo cardInfo = this.cardManager.getCardInfoByPsamNo(
					psamNo, context);
			if (cardInfo != null) {
				if (m == null) {
					m = mapper.map(request, ThirdPartyUserMapping.class);
					m.setUserId(userId);
					m = thirdPartyUserMappingDAO.save(m);
					
					BindThirdPartyUserInfoRequest bReq=new BindThirdPartyUserInfoRequest();
					bReq.setChannelNo(m.getOrgId());
					bReq.setChannelUserId(m.getOrgUserId());
					bReq.setUserId(m.getUserId());
					bReq.setOperatorId(m.getUserId());
					bReq.setOperationDate(request.getOperationDate());
					bReq.setOperatorId(request.getOperatorId());
					try{
						userService.bindThirdPartyUserInfo(bReq);
					}catch(BizException e){
						throw new BuzException(CodeDefine.PARAM_ERROR_MSG,
								CodeDefine.PARAM_ERROR);
					}
				} else {
					List<CardInfo> cardInfos = m.getCardInfos();
					Set<CardInfo> cardInfoSet = new HashSet<CardInfo>(
							cardInfos);
					// 判断是否已经绑定该用户
					if (cardInfoSet.contains(cardInfo)) {
						mapper.map(m, result);
						return result;
					}
				}
				if(CardState.INITIALIZATION!=cardInfo.getStatus()){
					if(CardState.BINGDING!=cardInfo.getStatus()){
						throw new BuzException(CodeDefine.USER_CARD_STATUS_ERROR_MSG,
								CodeDefine.USER_CARD_STATUS_ERROR);
					}
				}else{
					// 初始化终端
					UDServiceRequestDTO udServiceRequestDTO = mapper.map(
							request, UDServiceRequestDTO.class);
					udServiceRequestDTO.setPasswd("1q2w3e");
					udServiceRequestDTO.setAreaNo("010");
					UDServiceRequestExtDTO udServiceRequestExtDTO = mapper.map(
							request, UDServiceRequestExtDTO.class);
					String initSJTerminal = uDService.initTDTerminal(
							udServiceRequestDTO, udServiceRequestExtDTO,orgId,
							context);
					if (!CodeDefine.SUCCESS.equals(initSJTerminal))
						throw new BuzException("开通失败",initSJTerminal);
				}
				
				cardInfo.setThirdPartyUserMapping(m);
				mapper.map(m, result);
			} else {
				throw new BuzException(CodeDefine.CARD_HAS_NOT_DELIVER_MSG,
						CodeDefine.CARD_HAS_NOT_DELIVER);
			}

			
		} catch (Exception e) {
			handleMessage(result, e);
		}
		return result;
	}
}
