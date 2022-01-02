package com.epam.pmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER")
@Getter
@Setter
public class User {

	@Id
	@Column(name="USER_ID")
	private long id;
	
	@Column(name="USERNAME",nullable = false,unique=true)
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	boolean enabled=true;
	
}
