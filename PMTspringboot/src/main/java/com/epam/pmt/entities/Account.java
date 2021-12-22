package com.epam.pmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Accounts")
@NoArgsConstructor
public class Account {

	@Id
	@Getter
	@Setter
	private String url;
	
	@Column
	@Getter
	@Setter
	private String username;
	
	@Column
	@Getter
	@Setter
	private String password;
	
	@Column
	@Getter
	@Setter
	private String groupname;
	
	@ManyToOne
	@JoinColumn(name = "master_id")
	@Getter
	@Setter
	@JsonIgnore
	private Master master;

}
