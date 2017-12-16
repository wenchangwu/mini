/**
 * 
 */
package com.lakala.mini.server.core.manager.impl;

import com.lakala.ca.dto.organization.CardOrganizationDTO;
import com.lakala.ca.dto.organization.CardOrganizationQueryDTO;
import com.lakala.ca.dto.organization.CardOrganizationsDTO;
import com.lakala.ca.service.IOrganizationService;
import com.lakala.ca.util.StringHelper;
import com.lakala.common.exception.ApplicationException;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.factory.ApplicationContextFactory;
import com.lakala.core.objmapper.IObjectMapper;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.CardOrg;
import com.lakala.mini.server.core.manager.ICardOrganizationManager;
import com.lakala.mini.server.core.manager.exception.CardOrgManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ray
 * 
 */
@Service
public class CardOrgManagerImpl implements ICardOrganizationManager {

	@Autowired(required = false)
	private IOrganizationService organizationService;
	@Resource
	private IObjectMapper mapper;
	@Resource
	private ApplicationContextFactory applicationContextfactory;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lakala.mini.server.core.manager.ICardManager#cardInStore(java.lang
	 * .String, int, com.lakala.ca.dto.AuthenticationDTO)
	 */

	@Override
	public Pagination<CardOrg> get(Long[] id, String[] code, String[] name,
                                   boolean needPage, int pageSize, int pageNo, Long operatorId) {
		return get(id, code,  name,null,
				 needPage,  pageSize, pageNo, operatorId);
	}
	@Override
	public Pagination<CardOrg> get(Long[] id, String[] code, String[] name, List<Integer> movingRule,
                                   boolean needPage, int pageSize, int pageNo, Long operatorId) {
		CardOrganizationQueryDTO cardOrganizationQueryDTO = new CardOrganizationQueryDTO();
		cardOrganizationQueryDTO.setCodes(code);
		cardOrganizationQueryDTO.setIds(id);
		cardOrganizationQueryDTO.setNames(name);
		cardOrganizationQueryDTO.setNeedPage(needPage);
		cardOrganizationQueryDTO.setPageSize(pageSize);
		cardOrganizationQueryDTO.setPageStart(pageNo);
		cardOrganizationQueryDTO.setMovingRule(movingRule);
		ApplicationContext context = applicationContextfactory.getContext();
		context.setOperatorId(operatorId);
		Pagination<CardOrg> result = new Pagination<CardOrg>();
		CardOrganizationsDTO cardOrganizations = organizationService
				.getCardOrganizations(cardOrganizationQueryDTO, context);
		mapper.map(cardOrganizations, result);

		return result;
	}
	@Override
	public CardOrg getById(Long id, Long operatorId) {
		CardOrg cardOrg = null;
		if (id > 0) {
			Long[] ids = { id };
			String[] codes = {};
			String[] names = {};
			Pagination<CardOrg> result = get(ids, codes, names, false, 0, 0,
					operatorId);
			if (result != null && result.getData() != null) {
				List<CardOrg> cardOrgs = result.getData();
				Assert.isTrue(cardOrgs.size() <= 1, "CardOrg id:" + id
						+ ", repeat!");
				if (cardOrgs.size() == 1) {
					cardOrg = cardOrgs.get(0);
				}
			}
		}
		return cardOrg;
	}

	@Override
	public CardOrg getByCode(String code, Long operatorId) {
		CardOrg cardOrg = null;
		if (StringHelper.hasLength(code)) {
			Long[] ids = {};
			String[] codes = { code };
			String[] names = {};
			Pagination<CardOrg> result = get(ids, codes, names, false, 0, 0,
					operatorId);
			if (result != null && result.getData() != null) {
				List<CardOrg> cardOrgs = result.getData();
				Assert.isTrue(cardOrgs.size() <= 1, "CardOrg code:" + code
						+ ", repeat!");
				if (cardOrgs.size() == 1) {
					cardOrg = cardOrgs.get(0);
				}
			}
		}
		return cardOrg;
	}

	@Override
	public void update(CardOrg cardOrg, Long operatorId) throws CardOrgManagerException {
		CardOrganizationDTO updateDTO = mapper.map(cardOrg,
				CardOrganizationDTO.class);
		ApplicationContext context = applicationContextfactory.getContext();
		context.setOperatorId(operatorId);
		try {
			organizationService.updateCardOrganizations(updateDTO, context);
		} catch (ApplicationException e) {
			throw new CardOrgManagerException(e.getErrorCode(),e);
		}
	}

	@Override
	public Long create(CardOrg cardOrg, Long operatorId) throws CardOrgManagerException {
		CardOrganizationDTO updateDTO = mapper.map(cardOrg,
				CardOrganizationDTO.class);
		ApplicationContext context = applicationContextfactory.getContext();
		context.setOperatorId(operatorId);
		try {
			return organizationService
					.createCardOrganizations(updateDTO, context);
		} catch (ApplicationException e) {
			throw new CardOrgManagerException(e.getErrorCode(),e);
		}
	}

}
