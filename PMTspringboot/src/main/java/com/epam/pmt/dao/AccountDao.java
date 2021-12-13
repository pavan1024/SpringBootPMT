package com.epam.pmt.dao;

import java.util.List;
import com.epam.pmt.entities.Account;

public interface AccountDao {
	public boolean createAccount(Account account);

	public String readPassword(Account account);
	
	public boolean deleteAccount(Account account);
	
	public boolean updateAccount(Account account);

	public List<Account> getAll();
	


}
