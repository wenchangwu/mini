package com.lakala.mini.server.core.dao;

import com.lakala.mini.common.PSAMCardStatus;
import com.lakala.mini.exception.PSAMCardInfoNotFoundException;

public interface IPSAMInfoGateway {

	void setPsamInfoToHistoryById(String psamNo);

	void setPSAMCardInfoStatus(String PSAMNo, PSAMCardStatus psamCardStatus)
			throws PSAMCardInfoNotFoundException;

	void setOriginalPSAMInfoStatus(String originalPSAMNo);

	void setChangedPSAMInfoStatus(String changedPSAMNo);
}
