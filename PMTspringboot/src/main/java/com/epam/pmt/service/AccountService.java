package com.epam.pmt.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.exception.PasswordNotValidException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.exception.URLNotValidException;
import com.epam.pmt.repo.AccountRepository;
import com.epam.pmt.repo.MasterRepository;
import com.epam.pmt.util.MasterProvider;
import com.epam.pmt.util.SecurityUtil;
import com.epam.pmt.util.ValidationUtil;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	MasterRepository masterRepository;
	@Autowired
	ValidationUtil validationUtil;
	@Autowired
	SecurityUtil securityUtil;
	@Autowired
	ModelMapper mapper;

	Master master = MasterProvider.getMaster();

	public boolean createAccount(AccountDto accountDto) throws URLNotValidException, PasswordNotValidException {
		boolean status = false;
		if (validationUtil.isValidURL(accountDto.getUrl())) {
			if (validationUtil.isValidPassword(accountDto.getPassword())) {
				Account account = mapper.map(accountDto, Account.class);
				account.setPassword(securityUtil.encrypt(accountDto.getPassword()));
				account.setMaster(master);
				accountRepository.save(account);
				status = true;
			} else {
				throw new PasswordNotValidException("Password must contain 1 upppercase,lowercase,"
						+ "special character and it must be more than 8 characters");
			}
		} else {
			throw new URLNotValidException("Url must start with https://");
		}
		return status;
	}

	public String readPassword(String url) throws URLNotFoundException {
		String password = "";
		Account account = accountRepository.findByUrlAndMaster(url, master);
		Optional<Account> optionalAccount = Optional.ofNullable(account);
		if (checkUrl(url) && optionalAccount.isPresent()) {
			password = securityUtil.decrypt(account.getPassword());
		}
		return password;
	}

	public boolean checkUrl(String url) throws URLNotFoundException {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		Optional<Account> optionalAccount = Optional.ofNullable(account);
		if (optionalAccount.isPresent()) {
			status = true;
		} else {
			throw new URLNotFoundException(url + " URL not Found");
		}
		return status;
	}

	public boolean deleteAccount(String url) throws URLNotFoundException {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (checkUrl(url)) {
			accountRepository.delete(account);
			status = true;
		}
		return status;
	}

	public boolean updateUsername(String url, String newUsername) throws URLNotFoundException {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (checkUrl(url)) {
			account.setUsername(newUsername);
			accountRepository.save(account);
			status = true;
		}
		return status;

	}

	public boolean updatePassword(String url, String newPassword)
			throws URLNotFoundException, PasswordNotValidException {
		boolean status = false;
		Account account = accountRepository.findByUrlAndMaster(url, master);
		if (checkUrl(url)) {
			if (validationUtil.isValidPassword(newPassword)) {
				account.setPassword(securityUtil.encrypt(newPassword));
				accountRepository.save(account);
				status = true;
			} else {
				throw new PasswordNotValidException(
						"Password must contain 1 upppercase,lowercase,special character and it must be more than 8 characters");

			}
		}
		return status;

	}

	public List<Account> getAll() {
		List<Account> accounts = accountRepository.findByMaster(master);
		accounts.stream().forEach(i -> i.setPassword(securityUtil.decrypt(i.getPassword())));
		return accounts;
	}

}
