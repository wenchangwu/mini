/**
 * 
 */
package com.lakala.mini.server.core.manager;

import com.lakala.ca.dto.AuthenticationDTO;
import com.lakala.common.exception.ApplicationException;
import com.lakala.common.exception.IllegalStateException;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.core.support.query.Page;
import com.lakala.core.support.query.Pagination;
import com.lakala.mini.dto.CardUserSubmitRequest;
import com.lakala.mini.dto.CardUsersFindRequest;
import com.lakala.mini.exception.*;
import com.lakala.mini.server.core.domain.*;

import javax.jws.WebService;
import java.util.List;

/**
 * @author ray 迷你用户信息服务
 */
@WebService
public interface IMiniUserManager {
	/**
	 * 卡用户信息查询
	 * 
	 * @param loginId
	 *            用户网站注册id
	 * @return 迷你用户信息
	 */
	List<CardUser> findCardUserByUid(long uid);

	/**
	 * 按psamNo查找用户
	 * 
	 * @param psamNo
	 * @return
	 */
	CardUser findCardUserByPsamNo(String psamNo, AuthenticationDTO authenication);

	/**
	 * 按用户卡id查询用户
	 * 
	 * @param cardInfoId
	 * @return
	 */
	CardUser findCardUserByCardInfoId(long cardInfoId,
                                      AuthenticationDTO authenication);

	/**
	 * 按cardUserId查找用户历史记录
	 *
	 * @param cardUserId
	 * @return
	 */
	Pagination<CardUserHis> findHisByCardUserId(long cardUserId, Page page,
                                                AuthenticationDTO authenication);

	/**
	 * 按用户全局id查找用户历史记录
	 *
	 * @param uid
	 *            用户网站注册id
	 * @param page
	 * @return
	 */
	Pagination<CardUserHis> findHis(long uid, Page page,
                                    AuthenticationDTO authenication);

	/**
	 * 按psam卡号查找用户历史记录
	 *
	 * @param psamNo
	 * @param page
	 * @return
	 */
	Pagination<CardUserHis> findHisByPsamNo(String psamNo, Page page,
                                            AuthenticationDTO authenication);

	/**
	 * 按用户卡号查找用户历史记录
	 *
	 * @param cardInfoId
	 * @param page
	 * @return
	 */
	Pagination<CardUserHis> findHisByCardInfoId(long cardInfoId, Page page,
                                                AuthenticationDTO authenication);

	/**
	 *
	 * @param cardUser
	 */
	void updateCardUser(CardUser cardUser, AuthenticationDTO authenication);

	/**
	 * 新建用户
	 *
	 * @param cardUser
	 * @return card user id
	 */
	long createUser(CardUser cardUser, AuthenticationDTO authenication);

	/**
	 * 用户信息查询
	 *
	 * @return
	 */
	Pagination<CardUser> findCardUser(CardUsersFindRequest user,
                                      AuthenticationDTO authenication);

	/**
	 *
	 * 用户关联用户卡
	 *
	 * @param uid
	 * @param userCardNo
	 * @return
	 * @throws UserCardNotFoundException
	 * @throws UserCardAllreadyBindException
	 * @throws UserCardBindOtherUserException
	 * @throws CardUserNotFoundException
	 * @throws PasswordNotMatchException
	 * @throws UserCardNotFoundException
	 */
	void bindUserToUserCard(long uid, String userCardNo, String password,
                            long operatorId) throws PasswordNotMatchException,
            CardUserNotFoundException, UserCardBindOtherUserException,
            UserCardAllreadyBindException, UserCardNotFoundException;

	/**
	 * 查找用户的用户卡
	 *
	 * @param uid
	 * @return
	 */
	List<CardInfo> findUserBindUserCard(long uid,
                                        AuthenticationDTO authenication);

	/**
	 * 提交用户审核资料
	 *
	 * @param cardUser
	 * @return 用户卡Id
	 * @throws CardUserNotFoundException
	 */
	long submitCardUser(CardUserSubmitRequest request,
                        AuthenticationDTO authenication) throws CardUserNotFoundException;

	/**
	 * 获取待审核用户资料
	 *
	 * @param ids
	 * @param page
	 * @return 待审核用户资料
	 */
	Pagination<CardUser> checkCardUser(long[] ids, Page page,
                                       AuthenticationDTO authenication);

	/**
	 * 提交审核结果
	 *
	 * @param id
	 * @param auditingStatus
	 *            ,0:拒绝,2:通过
	 * @return 用户卡Id
	 * @throws UserCardNotFoundException
	 * @throws SMSSendException
	 * @throws IllegalStateException
	 * @throws CardUserNotFoundException
	 */
	long changeCardUserAuditingStatus(long id, int auditingStatus,
                                      AuthenticationDTO authenication) throws UserCardNotFoundException,
            SMSSendException, IllegalStateException, CardUserNotFoundException;

	LoginMiniUser getLoginMiniUser(long uid);

	/**
	 * 換用户卡、換机
	 * @param userCardId
	 * @param newCardNo
	 * @param newPsamNo
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	String changeMiniInfo(String userCardId, String newCardNo, String newPsamNo, ApplicationContext context) throws ApplicationException;
	String deliverConfirm(Long[] cardInfoIds, ApplicationContext context) throws ApplicationException;
	String revokeDeliverConfirm(Long[] cardInfoIds, ApplicationContext context) throws ApplicationException;
	/**
	 * 更新mini用户的绑定管理系
	 * @param cardUser
	 * @param isNotify  是否通知作业系统
	 * @return
	 * @throws ApplicationException
	 */
	String updateBindMini(CardUser cardUser, boolean isNotify) throws ApplicationException;

	PsamInfo getPsamInfoByPsamNo(String psamNo) throws ApplicationException;
	/**
	 * 查询某个用户对应使用过的所有电话号码
	 * @param cardInfoId
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	String[] getTelNos(long cardInfoId, ApplicationContext context) throws ApplicationException;
	/**
	 * 通过电话号码查找是否有符合的CardUser记录
	 * @param telNo   电话号码
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public List<CardUser> findCardUsersUseTelNo(String telNo, ApplicationContext context)throws ApplicationException;


	/**
	 * @param bindingTelNo
	 * @param openMobileNum
	 * @param userId
	 * @return
	 */
	List<CardUser> findCardUsers(String bindingTelNo, String openMobileNum,
                                 Long userId);
}
