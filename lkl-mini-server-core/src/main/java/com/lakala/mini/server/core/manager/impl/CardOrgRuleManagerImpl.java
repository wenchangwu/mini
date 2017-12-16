/**
 * 
 */
package com.lakala.mini.server.core.manager.impl;

import com.lakala.common.exception.ApplicationException;
import com.lakala.common.util.CollectionHelper;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.dao.ICardOrgRuleParamDAO;
import com.lakala.mini.server.core.domain.CardOrgPublicParam;
import com.lakala.mini.server.core.domain.CardOrgRule;
import com.lakala.mini.server.core.domain.CardOrgRuleParam;
import com.lakala.mini.server.core.manager.ICardOrgRuleManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author leijiajian@lakala.com
 * @since 1.0.0
 * @crate_date 2012-12-26
 */
@Service
@Transactional(readOnly = false)
public class CardOrgRuleManagerImpl implements ICardOrgRuleManager {
	
	private Logger logger= LoggerFactory.getLogger(CardOrgRuleManagerImpl.class);
	
	@Resource
	private ICardOrgRuleParamDAO cardOrgRuleParamDAO;
	
	@Resource
	private ObjectMapper jsonObjectMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.server.core.manager.ICardOrgRuleManager#queryCardRuleParam
	 * ()
	 */
	@Override
	@Transactional(readOnly = true)
	public Pagination<CardOrgRuleParam> queryCardRuleParam(List<Long> id,
                                                           List<Integer> ruleId, List<String> paramCode,
                                                           List<String> paramName, List<String> paramValue, Page page) {
		return cardOrgRuleParamDAO.query(id, ruleId, paramCode, paramName,
				paramValue, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.server.core.manager.ICardOrgRuleManager#batchUpdateParam
	 * (java.lang.Long, java.lang.String, java.lang.String, java.lang.String,
	 * java.util.List, boolean)
	 */
	@Override
	public void batchUpdateParam(Integer ruleId, String paramCode,
			String paramName, String paramDesc, List<String> paramValue,
			boolean isAppend) {
		if (isAppend) {
			//TODO 需要优化
			if(CollectionHelper.isNotEmpty(paramValue)){
				cardOrgRuleParamDAO.batchUpdateNameAndDesc(ruleId, paramCode,
						paramValue, paramName, paramDesc);
				List<CardOrgRuleParam> cardOrgRuleParams = cardOrgRuleParamDAO.getByRuleIdAndCodeAndValues(ruleId,
						paramCode, paramValue);
				Set<String> paramValueSet=new HashSet<String>();
				paramValueSet.addAll(paramValue);
				for (CardOrgRuleParam cardOrgRuleParam : cardOrgRuleParams) {
					paramValueSet.remove(cardOrgRuleParam.getParamValue());
				}
				for (String value : paramValueSet) {
					cardOrgRuleParamDAO.save(new CardOrgRuleParam(ruleId,
							paramCode, paramName, value, paramDesc));
				}
			}
		} else {
			cardOrgRuleParamDAO.batchRemove(paramCode, ruleId);
			if(CollectionHelper.isNotEmpty(paramValue)){
				for (String value : paramValue) {
					cardOrgRuleParamDAO.save(new CardOrgRuleParam(ruleId,
							paramCode, paramName, value, paramDesc));
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.server.core.manager.ICardOrgRuleManager#deleteParamById
	 * (java.util.List)
	 */
	@Override
	public void deleteParamById(List<Long> id) {
		cardOrgRuleParamDAO.batchRemove(id);
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.ICardOrgRuleManager#saveRuleParam(com.lakala.mini.server.core.domain.CardOrgRuleParam)
	 */
	@Override
	public Long saveRuleParam(CardOrgRuleParam cardOrgRuleParam) {
		CardOrgRuleParam ruleParam = cardOrgRuleParamDAO.save(cardOrgRuleParam);
		return ruleParam.getId();
	}
	
	@Override
	public CardOrgPublicParam getCardOrgPublicParam(Long orgId){
		List<CardOrgRuleParam> cardOrgRuleParams = cardOrgRuleParamDAO.getsByRuleIdAndParamCode(CardOrgRule.RULE_ID_ORG_PUBLIC,orgId+"");
		CardOrgPublicParam orgParam=new CardOrgPublicParam();
		if(CollectionHelper.isNotEmpty(cardOrgRuleParams)){
			CardOrgRuleParam cardOrgRuleParam = cardOrgRuleParams.get(0);
			try {
				orgParam=jsonObjectMapper.readValue(cardOrgRuleParam.getParamValue(),CardOrgPublicParam.class);
			} catch (Exception e) {
				logger.warn("转换机构通用参数错误! 使用默认参数",e);
			} 
		}
		return orgParam;
	}
	
	
	@Override
	public void saveCardOrgPublicParam(final Long orgId,final CardOrgPublicParam param) throws ApplicationException{
		List<CardOrgRuleParam> cardOrgRuleParams = cardOrgRuleParamDAO.getsByRuleIdAndParamCode(CardOrgRule.RULE_ID_ORG_PUBLIC,orgId+"");
		if(CollectionHelper.isNotEmpty(cardOrgRuleParams)){
			for (CardOrgRuleParam cardOrgRuleParam : cardOrgRuleParams) {
				cardOrgRuleParamDAO.remove(cardOrgRuleParam.getId());
			}
		}
		try {
			CardOrgRuleParam ruleParam=new CardOrgRuleParam();
			String paramValue=jsonObjectMapper.writeValueAsString(param);
			ruleParam.setParamValue(paramValue);
			if(logger.isDebugEnabled()){
				logger.debug("转换机构通用参数,机构id:"+orgId+" 参数:",paramValue);
			}
			ruleParam.setParamCode(orgId+"");
			ruleParam.setRuleId(CardOrgRule.RULE_ID_ORG_PUBLIC);
			ruleParam = cardOrgRuleParamDAO.save(ruleParam);
		} catch (Exception e) {
			logger.warn("转换机构通用参数错误!",e);
			throw new ApplicationException(e);
		} 
		
	}
}
