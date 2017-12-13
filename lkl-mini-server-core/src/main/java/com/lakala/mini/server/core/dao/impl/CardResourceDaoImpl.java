package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.server.core.dao.ICardResourceDAO;
import com.lakala.mini.server.core.domain.CardResource;
import org.springframework.stereotype.Repository;

/**
 * @author 刘文成 
 * @version 创建时间：2010-12-8 上午10:10:30
 * 类说明
 */
@Repository(value="cardResourceDao")
public class CardResourceDaoImpl extends GenericDAOImpl<CardResource> implements ICardResourceDAO {
	
}
