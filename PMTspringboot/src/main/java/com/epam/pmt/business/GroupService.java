package com.epam.pmt.business;


import java.util.List;
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
		List<Account> groupAccounts = accountRepository.findByGroupnameAndMaster(groupname, master);
		if (!groupAccounts.isEmpty()) {
			status = true;
		}
		return status;
	}

	public List<Account> getGroupList(String groupname) {
		List<Account> groupAccounts = null;
		groupAccounts = accountRepository.findByGroupnameAndMaster(groupname, master);
		groupAccounts.stream().forEach(i->i.setPassword(security.decrypt(i.getPassword())));
		return groupAccounts;
	}

	public boolean deleteGroup(String groupname) {
		boolean status = false;
		List<Account> groupAccounts = accountRepository.findByGroupnameAndMaster(groupname, master);
		if (!groupAccounts.isEmpty()) {
			accountRepository.deleteAll(groupAccounts);
			status = true;
		}
		return status;
	}

	public boolean updateGroupname(String currentGroupname, String newGroupname) {
		boolean status = false;
		List<Account> groupAccounts = accountRepository.findByGroupnameAndMaster(currentGroupname, master);
		if (this.checkIfGroupExists(currentGroupname) && !groupAccounts.isEmpty()) {
			groupAccounts.stream().forEach(i -> i.setGroupname(newGroupname));
			accountRepository.save(groupAccounts.get(0));
			status = true;
		}
		return status;
	}
}
