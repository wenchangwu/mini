/**
 * 
 */
package com.lakala.mini.server.core.domain;

import org.codehaus.jackson.annotate.*;

import java.io.Serializable;

/**
 * 
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-12
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
@JsonWriteNullProperties(false)
@JsonAutoDetect(value={JsonMethod.FIELD})
public class CardOrgPublicParam implements Serializable {

	private static final long serialVersionUID = -2433138677396110637L;

	@JsonProperty
	private Integer donationCount ;

	/**
	 * @return the donationCount
	 */
	public Integer getDonationCount() {
		return donationCount;
	}

	/**
	 * @param donationCount
	 *            the donationCount to set
	 */
	public void setDonationCount(Integer donationCount) {
		this.donationCount = donationCount;
	}
	
	public Boolean isDonationCountDefault(){
		return donationCount==null;
	}
}
