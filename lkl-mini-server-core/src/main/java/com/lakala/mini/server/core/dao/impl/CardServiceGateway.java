package com.lakala.mini.server.core.dao.impl;

import com.lakala.core.dao.jpa.impl.GenericDAOImpl;
import com.lakala.mini.common.OperateType;
import com.lakala.mini.common.PSAMCardStatus;
import com.lakala.mini.common.UserCardStatus;
import com.lakala.mini.exception.CardUserNotFoundException;
import com.lakala.mini.exception.PSAMCardInfoNotFoundException;
import com.lakala.mini.server.core.dao.ICardServiceGateway;
import com.lakala.mini.server.core.domain.*;
import com.lakala.mini.server.core.util.ValidatorUtil;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.Pre;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

@Guarded
@Repository(value = "cardServiceGateway")
public class CardServiceGateway  implements ICardServiceGateway {

    @Autowired
    private ValidatorUtil validatorUtil;
    @PersistenceContext
    private EntityManager em;

    /* start time is less than the end time.
     * start time is less than the nextDay time.
     *  */
    @Pre(expr = "_args[0] <= _args[1] && _args[0] < _args[2]", lang = "groovy")
    @Override
    public List<CardUserHis> retrieveCardUserHistoryList(
            @NotNull Timestamp startTimestamp,
            @NotNull Timestamp endTimestamp,
            @NotNull Timestamp nextDayFromStartTimestamp) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CardUserHis> criteria = builder.createQuery(CardUserHis.class);
        Root<CardUserHis> cardUserHisRoot = criteria.from(CardUserHis.class);
        criteria.select(cardUserHisRoot);

        Predicate cardUserHisRestriction = builder.and(
                builder.greaterThan(cardUserHisRoot.get(CardUserHis_.modifyDate), startTimestamp),
                builder.lessThan(cardUserHisRoot.get(CardUserHis_.modifyDate), endTimestamp),
                builder.lessThan(cardUserHisRoot.get(CardUserHis_.modifyDate), nextDayFromStartTimestamp)
        );

        criteria.where(cardUserHisRestriction);
        List<CardUserHis> cardUserHisList = em.createQuery(criteria).getResultList();
        return cardUserHisList;
    }


    @Override
    public void setCardInfoToHistory(@NotNull String PSAMNo) throws CardUserNotFoundException {
        CardInfo cardInfo = retrieveCardInfo(PSAMNo);
        CardInfoHis cardInfoHis = new CardInfoHis();
        BeanUtils.copyProperties(cardInfo, cardInfoHis);  //detached

        validatorUtil.validateJPAObject(cardInfoHis);
        em.merge(cardInfoHis);
        em.flush();
		/*getJpaTemplate().merge(cardInfoHis);
		getJpaTemplate().flush();*/
    }

    private CardInfo retrieveCardInfo(String PSAMNo) throws CardUserNotFoundException {
        CardUser cardUser = retrieveCardUser(PSAMNo);
        Long cardInfoId = cardUser.getCardInfoId();

        CardInfo cardInfo = em.find(CardInfo.class, cardInfoId);  // Managed

        return cardInfo;
    }

    private CardUser retrieveCardUser(String PSAMNo) throws CardUserNotFoundException {
        /*List<CardUser> cardUserList = this.getEntityManager().find("select cardUser from CardUser cardUser where cardUser.psamNo = ?1", PSAMNo);*/

        Query q = em.createQuery("select cardUser from CardUser cardUser where cardUser.psamNo=?1");
        q.setParameter(1, PSAMNo);
        List<CardUser> cardUserList = (List<CardUser>) q.getResultList();

        if (cardUserList.size() == 0) {
            throw new CardUserNotFoundException();
        }
        return cardUserList.get(0);
    }

    @Override
    public void setCardUserToHistory(@NotNull String PSAMNo) throws CardUserNotFoundException {
        CardUser cardUser = retrieveCardUser(PSAMNo);
        CardUserHis cardUserHis = new CardUserHis();
        BeanUtils.copyProperties(cardUser, cardUserHis);  //detached

        validatorUtil.validateJPAObject(cardUserHis);
        //getJpaTemplate().persist(cardUserHis);
        em.merge(cardUserHis);
        em.flush();
    }

    @Override
    public void setCardUserStatus(@NotNull String PSAMNo, @NotNull String operateType) throws CardUserNotFoundException {

        CardUser cardUser = retrieveCardUser(PSAMNo);

        cardUser.setOperatType(operateType);
        cardUser.setChangeDate(new Timestamp(0));  // Managed

        em.flush();
    }

    @Override
    public void setPSAMCardInfoStatus(@NotNull String PSAMNo,
                                      @NotNull PSAMCardStatus psamCardStatus) throws PSAMCardInfoNotFoundException {
        //List<PsamInfo> psamInfoList = this.getEntityManager().find("select psamInfo from PsamInfo psamInfo where psamInfo.pasmNo = ?1", PSAMNo);

        Query q = em.createQuery("select psamInfo from PsamInfo psamInfo where psamInfo.pasmNo = ?1");
        q.setParameter(1, PSAMNo);
        List<PsamInfo> psamInfoList =(List<PsamInfo>) q.getResultList();

        if (psamInfoList.size() == 0) {
            throw new PSAMCardInfoNotFoundException();
        }

        PsamInfo psamInfo = psamInfoList.get(0);
        psamInfo.setPasmState(psamCardStatus.getStatus());  // Managed

        em.flush();
    }


    @Override
    public void setCardInfoStatus(@NotNull String PSAMNo, @NotNull UserCardStatus cardStatus) throws CardUserNotFoundException {
        CardInfo cardInfo = retrieveCardInfo(PSAMNo);
        cardInfo.setStatus(cardStatus.getStatus());  // Managed

        em.flush();
    }

    @Override
    public void setPsamInfoToHistoryById(@NotNull String psamNo) {
        PsamInfo psamInfo = em.find(PsamInfo.class, psamNo);
        PsamInfoHis psamInfoHis = new PsamInfoHis();
        BeanUtils.copyProperties(psamInfo, psamInfoHis);  // transient
        em.persist(psamInfoHis);
    }

    @Override
    public void setCardInfoToHistoryById(@NotNull String cardId) {
        CardInfo cardInfo = retrieveCardInfoById(cardId);
        CardInfoHis cardInfoHis = new CardInfoHis();
        BeanUtils.copyProperties(cardInfo, cardInfoHis);    // transient
        em.persist(cardInfoHis);
    }

    @Override
    public CardUser retrieveCardUser(@NotNull Long cardUserId) {
        CardUser cardUser = em.find(CardUser.class, cardUserId);
        return cardUser;
    }

    @Override
    public void setOriginalCardStatus(@NotNull String cardId) {
        CardInfo originalCard = retrieveCardInfoById(cardId);
        originalCard.setStatus(UserCardStatus.ABANDON.getStatus());

        em.flush();
    }

    @Override
    public CardInfo retrieveCardInfoById(@NotNull String cardId) {
        CardInfo originalCard = em.find(CardInfo.class, cardId);
        return originalCard;
    }

    @Override
    public void setOriginalCardData(@NotNull String cardId, @NotNull String changedCardId) {
        CardInfo originalCard = retrieveCardInfoById(cardId);

        CardResource changedCard = em.find(CardResource.class, changedCardId);
        BeanUtils.copyProperties(changedCard, originalCard);

        validatorUtil.validateJPAObject(originalCard);
        // originalCard Managed

        em.flush();
    }

    @Override
    public void setOriginalPSAMInfoStatus(@NotNull String originalPSAMNo) {
        PsamInfo originalPSAMInfo = em.find(PsamInfo.class, originalPSAMNo);
        originalPSAMInfo.setPasmState(PSAMCardStatus.UNBOUND.getStatus());
    }

    @Override
    public void setChangedPSAMInfoStatus(@NotNull String changedPSAMNo) {
        PsamInfo changedPSAMInfo = em.find(PsamInfo.class, changedPSAMNo);
        changedPSAMInfo.setPasmState(PSAMCardStatus.BOUND.getStatus());
    }

    @Override
    public void setCardUserData(@NotNull String changedPSAMNo, CardUser cardUser) {
        cardUser.setPsamNo(changedPSAMNo);
        cardUser.setOperatType(OperateType.CHANGE_PSAM);
        cardUser.setChangeDate(new Timestamp(0));

        // originalPSAMInfo, changedPSAMInfo, cardUser Managed
        validatorUtil.validateJPAObject(cardUser);
        em.flush();
    }

}
