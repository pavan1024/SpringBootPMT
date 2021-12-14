package com.epam.pmt.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.repo.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	Master master = MasterProvider.getMaster();
	public boolean createAccount(String url, String username, String password, String groupname) {
		boolean status = false;
		Account account = new Account();
		account.setUrl(url);
		account.setUsername(username);
		account.setPassword(password);
		account.setGroupname(groupname);
		account.setMaster(master);
		Account savedAccount = accountRepository.save(account);
		if(savedAccount!=null){
			status = true;
		}
		return status;

	}
	public String readPassword(String url) {
		String password="";
		List<Account> accounts=((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getUrl().equals(url)).collect(Collectors.toList());
		if(!accounts.isEmpty())
			password = accounts.get(0).getPassword();
		return password;
	}
	
	public boolean checkUrl(String url) {
		boolean status = false;
		List<Account> accounts=((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getUrl().equals(url)).collect(Collectors.toList());
		if(!accounts.isEmpty()) {
				status =true;
		}
		return status;
	}
	public boolean deleteAccount(String url) {
		boolean status=false;
		List<Account> accounts=((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getUrl().equals(url)).collect(Collectors.toList());
		if(!accounts.isEmpty()) {
			accountRepository.delete(accounts.get(0));
			status =true;
	}
		return status;
	}
	public boolean updateUsername(String url, String newUsername) {
		boolean status=false;
		List<Account> accounts=((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getUrl().equals(url)).collect(Collectors.toList());
		
		master.setAccounts(accounts);
		if(!accounts.isEmpty()) {
			accounts.stream().forEach(i->i.setUsername(newUsername));
			accountRepository.save(accounts.get(0));
			status =true;
	}
		return status;

	}
	
	public boolean updatePassword(String url, String newPassword) {
		boolean status=false;
		List<Account> accounts=((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getUrl().equals(url)).collect(Collectors.toList());
		
		master.setAccounts(accounts);
		if(!accounts.isEmpty()) {
			accounts.stream().forEach(i->i.setPassword(newPassword));
			accountRepository.save(accounts.get(0));
			status =true;
	}
		return status;

	}
	
	public List<Account> getAll() {
		return (List<Account>) accountRepository.findAll();
	}
	
}




