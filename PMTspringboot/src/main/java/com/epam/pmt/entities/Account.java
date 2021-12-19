package com.epam.pmt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Accounts")
@NoArgsConstructor
public class Account {

	@Id
	@Getter @Setter private String url;
	@Column
	@Getter @Setter private String username;
	@Column
	@Getter @Setter private String password;
	@Column
	@Getter @Setter private String groupname;
	@ManyToOne
	@JoinColumn(name = "master_id")
	@Getter @Setter private Master master;
	
	

//	public String getGroupname() {
//		return groupname;
//	}
//
//	public void setGroupname(String groupname) {
//		this.groupname = groupname;
//	}
//
//	public Master getMaster() {
//		return master;
//	}
//
//	public void setMaster(Master master) {
//		this.master = master;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

}
