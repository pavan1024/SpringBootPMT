package com.epam.pmt.dto;

import com.epam.pmt.entities.Master;

public class AccountDto {

	private String url;

	private String username;

	private String password;

	private String groupname;

	private Master master;

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupName) {
		this.groupname = groupName;
	}

	public Master getmaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
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
