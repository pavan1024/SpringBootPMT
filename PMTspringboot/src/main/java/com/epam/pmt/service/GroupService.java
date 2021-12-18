package com.epam.pmt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.repo.AccountRepository;
import com.epam.pmt.util.MasterProvider;
import com.epam.pmt.util.Security;

@Service
public class GroupService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	Security security;
	Master master = MasterProvider.getMaster();

	public boolean checkIfGroupExists(String groupname) throws GroupNotFoundException {
		boolean status = false;
		List<Account> groupAccounts = accountRepository.findByGroupnameAndMaster(groupname, master);
		if (!groupAccounts.isEmpty()) {
			status = true;
		} else {
			throw new GroupNotFoundException(groupname + " Group not Found");
		}
		return status;
	}

	public List<Account> getGroupList(AccountDto accountDto) throws GroupNotFoundException {
		List<Account> groupAccounts = null;
		if (this.checkIfGroupExists(accountDto.getGroupname())) {
			groupAccounts = accountRepository.findByGroupnameAndMaster(accountDto.getGroupname(), master);
			groupAccounts.stream().forEach(i -> i.setPassword(security.decrypt(i.getPassword())));
		}
		return groupAccounts;
	}

	public boolean deleteGroup(AccountDto accountDto) throws GroupNotFoundException {
		boolean status = false;
		List<Account> groupAccounts = accountRepository.findByGroupnameAndMaster(accountDto.getGroupname(), master);
		if (this.checkIfGroupExists(accountDto.getGroupname())) {
			accountRepository.deleteAll(groupAccounts);
			status = true;
		}
		return status;
	}

	public boolean updateGroupname(String currentGroupname, String newGroupname) throws GroupNotFoundException {
		boolean status = false;
		List<Account> groupAccounts = accountRepository.findByGroupnameAndMaster(currentGroupname, master);
		if (this.checkIfGroupExists(currentGroupname)) {
			groupAccounts.stream().forEach(i -> i.setGroupname(newGroupname));
			accountRepository.saveAll(groupAccounts);
			status = true;
		}
		return status;
	}
}
