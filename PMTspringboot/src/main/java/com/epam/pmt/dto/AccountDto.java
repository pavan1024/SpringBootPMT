package com.epam.pmt.dto;

public class AccountDto {

	private String url;

	private String username;

	private String password;

	private String groupname;

	private MasterDto masterDto;

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupName) {
		this.groupname = groupName;
	}

	public MasterDto getMasterDto() {
		return masterDto;
	}

	public void setMasterDto(MasterDto masterDto) {
		this.masterDto = masterDto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
