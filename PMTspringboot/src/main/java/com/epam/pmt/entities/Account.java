package com.epam.pmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
public class Account {

	@Id
	private String url;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String groupname;

	@ManyToOne
	@JoinColumn(name = "master_id")
	@JsonIgnore
	private Master master;

}
