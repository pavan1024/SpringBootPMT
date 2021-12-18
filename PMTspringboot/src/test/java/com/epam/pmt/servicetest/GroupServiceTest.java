package com.epam.pmt.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.repo.AccountRepository;
import com.epam.pmt.service.GroupService;
import com.epam.pmt.util.MasterProvider;
import com.epam.pmt.util.Security;

@SpringBootTest
class GroupServiceTest {
	@Mock
	AccountRepository accountRepository;

	@Mock
	Security security;

	@InjectMocks
	GroupService groupService;

	List<Account> groupAccounts;
	List<Account> emptyAccounts;

	Account account1;
	Account account2;
	Account emptyAccount = null;

	Master master;
	Master master1 = null;
	
	AccountDto accountDto;
	AccountDto invalidAccountDto;

	
	@BeforeEach
	public void setUp() {
		MasterProvider.setMaster("masteruser", "Master@123");
		master = MasterProvider.getMaster();
		groupAccounts = new ArrayList<>();
		emptyAccounts = new ArrayList<>();

		account1 = new Account();
		account1.setMaster(master);
		account1.setUrl("https://www.gmail.com");
		account1.setGroupname("google");

		account2 = new Account();
		account2.setMaster(master);
		account2.setGroupname("google");

		groupAccounts.add(account1);
		groupAccounts.add(account2);
		
		accountDto = new AccountDto();
		accountDto.setGroupname("google");
		
		invalidAccountDto = new AccountDto();
		invalidAccountDto.setGroupname("yahoo");
	}

	@Test
	void getGroupListTest() {
		when(accountRepository.findByGroupnameAndMaster("google", master)).thenReturn(groupAccounts);
		when(accountRepository.findByGroupnameAndMaster("google", master1)).thenReturn(emptyAccounts);
		try {
		assertEquals(groupAccounts, groupService.getGroupList(accountDto));
		assertEquals(emptyAccounts, groupService.getGroupList(invalidAccountDto));
		}
		catch(GroupNotFoundException ex) {
			
		}
	}

	@Test
	void updateGroupnameTest() {
		when(accountRepository.findByGroupnameAndMaster("google", master)).thenReturn(groupAccounts);
		when(accountRepository.findByGroupnameAndMaster("google", master1)).thenReturn(emptyAccounts);
		try {
		assertEquals(true, groupService.updateGroupname("google", "googlegroup"));
		assertEquals(false, groupService.updateGroupname("yahoo", "yahoogroup"));
		}
		catch(GroupNotFoundException ex) {
					
		}
	}

	@Test
	void checkIfGroupExistsTest() {
		when(accountRepository.findByGroupnameAndMaster("google", master)).thenReturn(groupAccounts);
		when(accountRepository.findByGroupnameAndMaster("google", master1)).thenReturn(emptyAccounts);
		try {
		assertEquals(true, groupService.checkIfGroupExists("google"));
		assertEquals(false, groupService.checkIfGroupExists("yahoo"));
		}
		catch(GroupNotFoundException ex) {
					
		}
	}

	@Test
	void deleteGroupTest() {
		when(accountRepository.findByGroupnameAndMaster("google", master)).thenReturn(groupAccounts);
		when(accountRepository.findByGroupnameAndMaster("google", master1)).thenReturn(emptyAccounts);
		try {
		assertEquals(true, groupService.deleteGroup(accountDto));
		assertEquals(false, groupService.deleteGroup(invalidAccountDto));
		}
		catch(GroupNotFoundException ex) {
			
		}
	}

}
