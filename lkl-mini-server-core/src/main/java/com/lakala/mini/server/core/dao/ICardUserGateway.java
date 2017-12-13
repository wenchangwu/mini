package com.lakala.mini.server.core.dao;

import com.lakala.mini.exception.CardUserNotFoundException;
import com.lakala.mini.server.core.domain.CardUser;
import com.lakala.mini.server.core.domain.CardUserHis;

import java.sql.Timestamp;
import java.util.List;

public interface ICardUserGateway {

	List<CardUserHis> retrieveCardUserHistoryList(Timestamp startTimestamp,
                                                  Timestamp endTimestamp, Timestamp nextDayFromStartTimestamp);

	void setCardUserStatus(String PSAMNo, String operateType)
			throws CardUserNotFoundException;

	void setCardUserToHistory(String PSAMNo) throws CardUserNotFoundException;

	CardUser retrieveCardUser(Long cardUserId);
	
    void setCardUserData(String changedPSAMNo, CardUser cardUser) ;
}
