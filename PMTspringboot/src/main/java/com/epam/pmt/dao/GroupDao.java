package com.epam.pmt.dao;

import java.util.List;

import com.epam.pmt.entities.Account;

public interface GroupDao {
	
	public List<Account> displayByGroup(String groupname);
	
	public boolean deleteGroup(String groupname);

	public boolean modifyGroup(String currentGroupname, String newGroupname);
}
