package com.epam.pmt.dto;

import java.util.List;

public class MasterDto {
	private String username;

	private String password;
	private List<AccountDto> accounts;

	public List<AccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDto> accounts) {
		accounts.forEach(account -> account.setMasterDto(this));
		this.accounts = accounts;
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
