/**
 * 
 */
package com.lakala.mini.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.cxf.annotations.WSDLDocumentation;

/**
 * 第三方合作商户解绑响应
 * @author leijiajian@lakala.com
 * @since 1.4.0
 * @crate_date 2013-3-13
 */
@WSDLDocumentation(value="第三方合作商户解绑")
@XmlRootElement(name="ThirdPartyUnbindResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThirdPartyUnbindResponse extends ResponseDTO {

	private static final long serialVersionUID = 1L;

}
