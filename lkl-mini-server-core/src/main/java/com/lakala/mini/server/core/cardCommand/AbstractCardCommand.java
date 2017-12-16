package com.lakala.mini.server.core.cardCommand;

import com.lakala.mini.server.core.dao.*;
import com.lakala.mini.server.core.manager.ICardOrganizationManager;
import com.lakala.mini.server.core.manager.IMiniUserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-21 上午09:55:49
 * 类说明
 */

public abstract class AbstractCardCommand implements ICommand {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected ICardOrganizationManager cardOrganizationManager;
	@Autowired
	protected ICardInfoDAO cardInfoDAO;
	@Autowired
	protected ICardInfoHisDAO cardInfoHisDAO;
	@Autowired
	protected ICardResourceDAO cardResourceDAO;
	@Autowired
	protected ICardUserDAO cardUserDAO;
	@Autowired
	protected ICardUserHisDAO cardUserHisDAO;
	@Autowired
	protected IPsamInfoDAO psamInfoDAO;
	@Autowired
	protected IPsamInfoHisDAO psamInfoHisDAO;
	@Autowired
	protected IMiniUserManager miniUserManager;




}
