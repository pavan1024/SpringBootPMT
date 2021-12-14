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
	Master master = MasterProvider.getMaster();
	
	public boolean checkIfGroupExists(String groupname) {		
		boolean status=false;
		List<Account> groupAccounts = ((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getGroupname().equals(groupname)).collect(Collectors.toList());
		if(!groupAccounts.isEmpty()) {
			status = true;
		}
		return status;
	}
	
	public List<Account> groupDetails(String groupname) {
		List<Account> groupAccounts = null;
		groupAccounts = ((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getGroupname().equals(groupname)).collect(Collectors.toList());
		return groupAccounts;
	}
	
	public boolean deleteGroup(String groupname) {
		boolean status= false;
		List<Account> groupAccounts = ((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getGroupname().equals(groupname)).collect(Collectors.toList());
		if(!groupAccounts.isEmpty()) {
			accountRepository.deleteAll(groupAccounts);
			status = true;
		}
		return status;
	}
	
	public boolean updateGroupname(String currentGroupname, String newGroupname) {
		boolean status = false;
		List<Account> accounts=((Collection<Account>) accountRepository.findAll()).stream().filter(i->i.getGroupname().equals(currentGroupname)).collect(Collectors.toList());
		if(this.checkIfGroupExists(currentGroupname)) {
			master.setAccounts(accounts);
			if(!accounts.isEmpty()) {
				accounts.stream().forEach(i->i.setGroupname(newGroupname));
				accountRepository.save(accounts.get(0));
				status =true;
			}
		}
		return status;
	}
}
	


