package com.epam.pmt.business;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dto.AccountDto;
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
	@Autowired
	Validation validation;
	@Autowired
	Security security;

	Master master = MasterProvider.getMaster();
	ModelMapper mapper = new ModelMapper();

	public boolean createAccount(AccountDto accountDto) {
		boolean status = false;
		if (validation.isValidURL(accountDto.getUrl()) && validation.isValidPassword(accountDto.getPassword())) {
			Account account = new Account();
			account.setUrl(accountDto.getUrl());
			account.setUsername(accountDto.getUsername());
			account.setPassword(security.encrypt(accountDto.getPassword()));
			account.setGroupname(accountDto.getGroupname());
			account.setMaster(master);
			accountRepository.save(account);
			status = true;
		}
		return status;
	}

	public String readPassword(String url) {
		String password = "";
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (account != null) {
			password = security.decrypt(account.getPassword());
		}
		return password;
	}

	public boolean checkUrl(String url) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (account != null) {
			status = true;
		}
		return status;
	}

	public boolean deleteAccount(String url) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (account != null) {
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
		if (account != null && validation.isValidPassword(newPassword)) {
			account.setPassword(security.encrypt(newPassword));
			accountRepository.save(account);
			status = true;
		}
		return status;

	}

	public List<Account> getAll() {
		List<Account> accounts = accountRepository.findByMaster(master);
		accounts.stream().forEach(i->i.setPassword(security.decrypt(i.getPassword())));
		return accounts;
	}

}
