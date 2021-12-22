package com.epam.pmt.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MasterAccounts")
public class Master {

	@Id
	@Getter
	@Setter
	private String username;

	@Column
	@Getter
	@Setter
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "master")
	@JsonIgnore
	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		accounts.stream().forEach(account -> account.setMaster(this));
		this.accounts = accounts;
	}

}
