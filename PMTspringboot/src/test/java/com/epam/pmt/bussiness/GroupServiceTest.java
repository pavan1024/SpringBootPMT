package com.epam.pmt.bussiness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.business.AccountService;
import com.epam.pmt.business.GroupService;
import com.epam.pmt.business.MasterProvider;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.repo.AccountRepository;
@SpringBootTest
class GroupServiceTest {
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	GroupService groupService;
	
	List<Account> groupAccounts;
	List<Account> emptyAccounts;
	
	Account account1;
	Account account2;
	Account emptyAccount = null;
	
	Master master;
	Master master1=null;
	
	@BeforeEach
	public void setUp() {
		MasterProvider.setMaster("masteruser", "Master@123");
		master = MasterProvider.getMaster();
		groupAccounts = new ArrayList<>();
		emptyAccounts = new ArrayList<>();
		account1 = new Account();
		account1.setMaster(master);
		account1.setUrl("https://www.gmail.com");
		account1.setUsername("gmailuser");
		account1.setPassword("Gmail@123");
		account1.setGroupname("google");
		
		account2 = new Account();
		account2.setMaster(master);
		account2.setUrl("https://www.drive.com");
		account2.setUsername("driveuser");
		account2.setPassword("Drive@123");
		account2.setGroupname("google");
		groupAccounts.add(account1);
		groupAccounts.add(account2);	
	}
	
	@Test
	void getGroupListTest() {
		when(accountRepository.findByMaster(master)).thenReturn(groupAccounts);
		when(accountRepository.findByMaster(master1)).thenReturn(emptyAccounts);
		assertEquals(groupAccounts,groupService.getGroupList("google"));
		assertEquals(emptyAccounts,groupService.getGroupList("yahoo"));
	}
	
	@Test
	void updateGroupnameTest() {
		when(accountRepository.findByMaster(master)).thenReturn(groupAccounts);
		when(accountRepository.findByMaster(master1)).thenReturn(emptyAccounts);
		assertEquals(true,groupService.updateGroupname("google","googlegroup"));
		assertEquals(false,groupService.updateGroupname("yahoo","yahoogroup"));
	}
	
	@Test
	void checkIfGroupExistsTest() {
		when(accountRepository.findByMaster(master)).thenReturn(groupAccounts);
		when(accountRepository.findByMaster(master1)).thenReturn(emptyAccounts);
		assertEquals(true,groupService.checkIfGroupExists("google"));
		assertEquals(false,groupService.checkIfGroupExists("yahoo"));
	}
	
	
	
	@Test
	void deleteGroupTest() {
		when(accountRepository.findByMaster(master)).thenReturn(groupAccounts);
		when(accountRepository.findByMaster(master1)).thenReturn(emptyAccounts);
		assertEquals(true,groupService.deleteGroup("google"));
		assertEquals(false,groupService.deleteGroup("yahoo"));
	}
	
	
	
	
	
}
