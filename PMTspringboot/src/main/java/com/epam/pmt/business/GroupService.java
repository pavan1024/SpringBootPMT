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
public class GroupService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	Security security;
	Master master = MasterProvider.getMaster();

	public boolean checkIfGroupExists(String groupname) {
		boolean status = false;
		List<Account> groupAccounts = ((Collection<Account>) accountRepository.findByMaster(master)).stream()
				.filter(i -> i.getGroupname().equalsIgnoreCase(groupname)).collect(Collectors.toList());
		if (!groupAccounts.isEmpty()) {
			status = true;
		}
		return status;
	}

	public List<Account> getGroupList(String groupname) {
		List<Account> groupAccounts = null;
		groupAccounts = ((Collection<Account>) accountRepository.findByMaster(master)).stream()
				.filter(i -> i.getGroupname().equalsIgnoreCase(groupname)).collect(Collectors.toList());
		groupAccounts.stream().forEach(i->i.setPassword(security.decrypt(i.getPassword())));
		return groupAccounts;
	}

	public boolean deleteGroup(String groupname) {
		boolean status = false;
		List<Account> groupAccounts = ((Collection<Account>) accountRepository.findByMaster(master)).stream()
				.filter(i -> i.getGroupname().equalsIgnoreCase(groupname)).collect(Collectors.toList());
		if (!groupAccounts.isEmpty()) {
			accountRepository.deleteAll(groupAccounts);
			status = true;
		}
		return status;
	}

	public boolean updateGroupname(String currentGroupname, String newGroupname) {
		boolean status = false;
		List<Account> accounts = accountRepository.findByMaster(master).stream()
				.filter(i -> i.getGroupname().equals(currentGroupname)).collect(Collectors.toList());
		if (this.checkIfGroupExists(currentGroupname) && !accounts.isEmpty()) {
			accounts.stream().forEach(i -> i.setGroupname(newGroupname));
			accountRepository.save(accounts.get(0));
			master.setAccounts(accounts);
			status = true;
		}
		return status;
	}
}
