package com.lakala.mini.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.lakala.core.dto.DateRangeDTO;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataPushToJobRequest")
public class DataPushToJobRequest implements Serializable {
	private static final long serialVersionUID = -8223529267082817345L;
	
	private DateRangeDTO dateRange;

	public void setDateRange(DateRangeDTO dateRange) {
		this.dateRange = dateRange;
	}

	public DateRangeDTO getDateRange() {
		return dateRange;
	}
}  
