/**
 * @author ray
 * @filename: UserMiniInfosDTO.java
 * @create date: 上午11:02:12
 */
package com.lakala.mini.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.lakala.core.dto.PageResultDTO;
@XmlType(name="UserMiniInfos")
public class UserMiniInfosDTO implements Serializable{
	
	private static final long serialVersionUID = 8709883727179587468L;
	
	private PageResultDTO page;
	private List<UserMiniInfoDTO> userMiniInfos;
	public PageResultDTO getPage() {
		return page;
	}
	public void setPage(PageResultDTO page) {
		this.page = page;
	}
	public List<UserMiniInfoDTO> getUserMiniInfos() {
		return userMiniInfos;
	}
	public void setUserMiniInfos(List<UserMiniInfoDTO> userMiniInfos) {
		this.userMiniInfos = userMiniInfos;
	}
	
	
}
