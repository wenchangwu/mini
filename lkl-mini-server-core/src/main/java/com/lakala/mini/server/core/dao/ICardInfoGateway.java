package com.lakala.mini.server.core.dao;

import com.lakala.mini.common.UserCardStatus;
import com.lakala.mini.exception.CardUserNotFoundException;
import com.lakala.mini.server.core.domain.CardInfo;

public interface ICardInfoGateway {
	void setCardInfoToHistory(String PSAMNo) throws CardUserNotFoundException;

	void setCardInfoToHistoryById(String cardId);

	void setCardInfoStatus(String PSAMNo, UserCardStatus cardStatus)
			throws CardUserNotFoundException;

	void setOriginalCardStatus(String cardId);

	CardInfo retrieveCardInfoById(String cardId);

	void setOriginalCardData(String cardId, String changedCardId);
}
