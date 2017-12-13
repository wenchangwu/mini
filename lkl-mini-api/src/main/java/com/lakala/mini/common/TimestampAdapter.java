/**
 * 
 */
package com.lakala.mini.common;

import java.util.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 使用XmlType时解决java.sql.Timestamp没有默认构造函数的问题
 * @author QW
 * @TimestampAdapter.java
 * @2011-4-8 上午11:07:33
 */
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {

	@Override
	public Date marshal(Timestamp t) throws Exception {
		return new Date(t.getTime());

	}

	@Override
	public Timestamp unmarshal(Date d) throws Exception {
		return new Timestamp (d.getTime());

	}

}
