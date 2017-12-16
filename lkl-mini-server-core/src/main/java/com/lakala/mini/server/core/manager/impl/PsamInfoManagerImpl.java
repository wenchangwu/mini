/**
 * com.lakala.mini.server.core.service.impl.PsamInfoServiceImpl.java
 */
package com.lakala.mini.server.core.manager.impl;

import com.lakala.common.exception.ApplicationException;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.common.CardState;
import com.lakala.mini.common.CodeDefine;
import com.lakala.mini.server.core.dao.IPsamInfoDAO;
import com.lakala.mini.server.core.dao.IPsamInfoHisDAO;
import com.lakala.mini.server.core.dao.IViewPsamNoDAO;
import com.lakala.mini.server.core.domain.PsamInfo;
import com.lakala.mini.server.core.domain.PsamInfoHis;
import com.lakala.mini.server.core.domain.ViewPsamNo;
import com.lakala.mini.server.core.manager.ICardManager;
import com.lakala.mini.server.core.manager.IMiniUserManager;
import com.lakala.mini.server.core.manager.IPsamInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * IPsamInfoService的一种实现
 * @author QW
 * 2010-12-2 下午10:47:06
 */
@Service(value="psamInfoManager")
public class PsamInfoManagerImpl implements IPsamInfoManager {
	final static private Logger logger = LoggerFactory.getLogger(PsamInfoHisManagerImpl.class);
	@Autowired
	private IPsamInfoDAO psamInfoDao;
	@Autowired
	private IPsamInfoHisDAO psamInfoHisDao;
	@Autowired
	private ICardManager cardManger;
	@Autowired()
	private IMiniUserManager miniUserManager;
	@Autowired()
	private IViewPsamNoDAO viewPsamNoDAO;

	/* (non-Javadoc)
	 * @see com.lakala.mini.service.IPsamInfoService#getPsamInfo(java.lang.Long)
	 */
	@Override
	public PsamInfo getPsamInfo(Long id) {
		return psamInfoDao.get(id);
	}

	public void setPsamInfoDao(IPsamInfoDAO psamInfoDao) {
		this.psamInfoDao = psamInfoDao;
	}

	@Override
	public PsamInfo getPsamInfoByPsamNo(String psamNo) {
		
		return psamInfoDao.getBy("pasmNo", psamNo);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean replacePsamNo(String oldPsamNo, String newPsamNo) throws ApplicationException{
		if(oldPsamNo != null && !"".equals(oldPsamNo) && newPsamNo != null && !"".equals(newPsamNo)){
			PsamInfo oldPsamInfo = this.getPsamInfoByPsamNo(oldPsamNo);
			PsamInfo newPsamInfo = this.getPsamInfoByPsamNo(newPsamNo);
			if(newPsamInfo == null)
				throw new ApplicationException(CodeDefine.NO_PSAM);
			oldPsamInfo.setPasmState(CardState.REPLACE);//更换PSAM卡的旧卡标识为被換卡
			newPsamInfo.setPasmState(CardState.BINGDING);//更换PSAM卡的新卡标识为绑定状态
			this.updatePsamInfo(oldPsamInfo);
			this.updatePsamInfo(newPsamInfo);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public PsamInfo updatePsamInfo(PsamInfo psamInfo) {
		createPsamInfHis(psamInfo);		
		return psamInfoDao.update(psamInfo);
	}
	private void createPsamInfHis(PsamInfo psamInfo){
//		PsamInfo load = psamInfoDao.load(psamInfo.getId());
		PsamInfoHis his = psamInfo.getHis();
		his.setId(null);
		his.setModifyDate(new Timestamp(System.currentTimeMillis()));
		psamInfoHisDao.save(his);
	}

	@Override
	public Pagination<ViewPsamNo> queryViewPsamInfo(String[] psamNos,
                                                    Integer[] status, Integer[] types, Page page) {
		
		return viewPsamNoDAO.queryViewPsamInfo(psamNos, status, types, page);
	}

	@Override
	public ViewPsamNo getViewPsamNofoByPsamNo(String psamNo) {
		
		return viewPsamNoDAO.getBy("psamNo", psamNo);
	}

	@Override
	public Pagination<ViewPsamNo> queryViewPsamInfo(String[] psamNos, Integer[] status, Integer[] types, String[] cardNos, String[] userMobilePhones,
                                                    String[] telNos, String[] telAreaNos, Long[] cardOrgIds, Page page) {
		
		return viewPsamNoDAO.queryViewPsamInfo(psamNos, status, types, cardNos, userMobilePhones, telNos, telAreaNos, cardOrgIds, page);
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.IPsamInfoManager#queryViewPsamInfo(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public List<ViewPsamNo> queryViewPsamInfo(String userMobilePhone, String telNo, String psam, Long userId) {
		
		return viewPsamNoDAO.queryViewPsamInfo(userMobilePhone, telNo, psam,userId);
	}

	/* (non-Javadoc)
	 * @see com.lakala.mini.server.core.manager.IPsamInfoManager#getByPsams(java.util.Collection)
	 */
	@Override
	public List<ViewPsamNo> getByPsams(Collection<String> psamsCollection) {
		return viewPsamNoDAO.getPsamsBy(psamsCollection);
	}

	

}
