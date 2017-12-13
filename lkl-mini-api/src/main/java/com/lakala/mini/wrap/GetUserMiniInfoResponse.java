/**
 * @author ray
 * @filename: GetUserMiniInfoResponse.java
 * @create date: 下午03:24:50
 */
package com.lakala.mini.wrap;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.lakala.core.dto.PageResultDTO;
import com.lakala.core.wrap.BaseResponseType;
import com.lakala.mini.dto.UserMiniInfoDTO;
import com.lakala.mini.dto.UserMiniInfosDTO;

@XmlType(name = "GetUserMiniInfoResponse")
public class GetUserMiniInfoResponse extends BaseResponseType {

	private static final long serialVersionUID = 4919776572181258988L;
	private List<UserMiniInfoDTO> userMiniInfos;
	private PageResultDTO pageResult;
	
	public void setUserMiniInfosDTO(UserMiniInfosDTO userMiniInfosDTO) {
		if (userMiniInfosDTO != null) {
			setUserMiniInfos(userMiniInfosDTO.getUserMiniInfos());
			setPageResult(userMiniInfosDTO.getPage());
		}
	}
	public UserMiniInfosDTO getUserMiniInfosDTO(){
		if(userMiniInfos!=null&&userMiniInfos.size()>0){
			UserMiniInfosDTO dto=new UserMiniInfosDTO();
			dto.setPage(pageResult);
			dto.setUserMiniInfos(userMiniInfos);
			return dto;
		}
		return null;
	}
	public void setPageResult(PageResultDTO pageResult) {
		this.pageResult = pageResult;
	}

	public void setUserMiniInfos(List<UserMiniInfoDTO> userMiniInfos) {
		this.userMiniInfos = userMiniInfos;
	}

	public List<UserMiniInfoDTO> getUserMiniInfos() {
		return userMiniInfos;
	}

	public PageResultDTO getPageResult() {
		return pageResult;
	}

}
