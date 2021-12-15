package com.epam.pmt.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.repo.AccountRepository;
import com.epam.pmt.repo.MasterRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	MasterRepository masterRepository;
	Master master = MasterProvider.getMaster();

	public boolean createAccount(String url, String username, String password, String groupname) {
		Account account = new Account();
		account.setUrl(url);
		account.setUsername(username);
		account.setPassword(password);
		account.setGroupname(groupname);
		account.setMaster(master);
		accountRepository.save(account);
		return true;

	}

	public String readPassword(String url) {
		String password = "";
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (account!=null) {
			password = account.getPassword();
		}
		return password;
	}

	public boolean checkUrl(String url) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (account!=null) {
			status = true;
		}
		return status;
	}

	public boolean deleteAccount(String url) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (account!=null) {
			accountRepository.delete(account);
			status = true;
		}
		return status;
	}

	public boolean updateUsername(String url, String newUsername) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (account != null) {
			account.setUsername(newUsername);
			accountRepository.save(account);
			status = true;
		}
		return status;

	}

	public boolean updatePassword(String url, String newPassword) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (account != null) {
			account.setPassword(newPassword);
			accountRepository.save(account);
			status = true;
		}
		return status;

	}

	public List<Account> getAll() {
		return accountRepository.findByMaster(master);
	}		

}
