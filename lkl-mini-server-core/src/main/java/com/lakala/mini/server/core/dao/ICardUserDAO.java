package com.lakala.mini.server.core.dao;

import com.lakala.core.dao.GenericDAO;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.server.core.domain.CardInfo;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.PsamInfo;

import java.util.Date;
import java.util.List;

public interface ICardUserDAO extends GenericDAO<CardUser> {

	Pagination<CardUser> search(String psamNo, String userCardNo,
                                String userName, String mobile, Page page);

	public void init(PsamInfo psamInfo, CardInfo cardInfo);

	CardUser getByCardInfoId(long cardInfoId);

	CardUser getByPsamNo(String psamNo);

	CardUser getByIdAndPsamNoAndUserCardNo(long id, String psamNo,
                                           String cardInfoNo);

	/**
	 * 根据审核参数查找
	 * @param auditingWaiting 审核状态
	 * @param page 分页信息,null不分页
	 * @return 结果集
	 */
	Pagination<CardUser> getByAuditingStatus(int auditingWaiting, Page page);

	/**
	 * 根据id和审核参数查找
	 * @param ids id
	 * @param auditingWaiting 审核状态
	 * @param page 分页信息,null不分页
	 * @return 结果集
	 */
	Pagination<CardUser> getByAuditingStatus(long[] ids, int auditingWaiting, Page page);
	/**
	 * 根据查询条件得到查询结果和分页信息
	 * @param userMobilePhones  用户手机号
	 * @param psamNos           PSAM卡号
	 * @param telNos            开通时绑定的电话号码
	 * @param page              分页信息
	 * @return
	 */
	Pagination<CardUser> queryCardUser(String[] userMobilePhones, String[] psamNos, String[] telNos, String[] telAresNos, Page page);
	/**
	 * 根据操作类型和时间得到用户绑定信息
	 * @param operateType
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	Pagination<CardUser> queryCardUserByOperateType(String[] operateType, Date startTime, Date endTime, Page page);

	/**
	 * @param bindingTelNo
	 * @param openMobileNum
	 * @param userId
	 * @return
	 */
	List<CardUser> findCardUsers(String bindingTelNo, String openMobileNum,
                                 Long userId);
	
}
