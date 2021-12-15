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
	ModelMapper mapper;
	Master master = MasterProvider.getMaster();

	public boolean addAccount(AccountDto accountDto) {
		Account account = mapper.map(accountDto, Account.class);
		account.setUrl(accountDto.getUrl());
		account.setUsername(accountDto.getUsername());
		account.setPassword(accountDto.getPassword());
		account.setGroupname(accountDto.getGroupname());
		account.setMaster(master);
		accountRepository.save(account);
		return true;

	}

	public String readPassword(AccountDto accountDto) {
		String password = "";
		Account retrievedAccount = accountRepository.findByUrlAndMaster(accountDto.getUrl(), master);
		if (retrievedAccount != null) {
			password = retrievedAccount.getPassword();
		}
		return password;
	}

	public boolean checkUrl(AccountDto accountDto) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(accountDto.getUrl(), master);
		if (account != null) {
			status = true;
		}
		return status;
	}

	public boolean deleteAccount(AccountDto accountDto) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(accountDto.getUrl(), master);
		if (account != null) {
			accountRepository.delete(account);
			status = true;
		}
		return status;
	}

	public boolean updateUsername(AccountDto accountDto) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(accountDto.getUrl(), master);
		if (account != null) {
			account.setUsername(accountDto.getUsername());
			accountRepository.save(account);
			status = true;
		}
		return status;

	}

	public boolean updatePassword(AccountDto accountDto) {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(accountDto.getUrl(), master);
		if (account != null) {
			account.setPassword(accountDto.getPassword());
			accountRepository.save(account);
			status = true;
		}
		return status;

	}

	public List<Account> getAll() {
		return accountRepository.findByMaster(master);
	}

}
