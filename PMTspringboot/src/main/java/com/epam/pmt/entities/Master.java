package com.epam.pmt.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MasterAccounts")
public class Master {

	@Id
	private String username;
	@Column
	private String password;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "master")
	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		accounts.stream().forEach(account -> account.setMaster(this));
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
