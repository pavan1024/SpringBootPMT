package com.epam.pmt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.repo.AccountRepository;
import com.epam.pmt.service.GroupService;
import com.epam.pmt.util.MasterProvider;
import com.epam.pmt.util.SecurityUtil;

@SpringBootTest
class GroupServiceTest {

	@Mock
	AccountRepository accountRepo;

	@Mock
	SecurityUtil securityUtil;

	@InjectMocks
	GroupService service;

	Master master;
	Account account1;
	Account account2;
	List<Account> accounts;
	List<Account> emptyAccounts;

	@BeforeEach
	void setUp() {
		MasterProvider.setMaster("masteruser", "Master@123");
		master = MasterProvider.getMaster();
		account1 = new Account();
		account1.setMaster(master);
		account1.setUrl("https://www.gmail.com");
		account1.setGroupname("google");
		account1.setUsername("gmailuser");
		account1.setPassword("Gmail@123");

		account2 = new Account();
		account2.setMaster(master);
		account2.setGroupname("google");
		account2.setUrl("https://www.drive.com");
		account2.setUsername("driveuser");
		account2.setPassword("Drive@123");

		accounts = new ArrayList<>();
		accounts.add(account1);
		accounts.add(account2);

		emptyAccounts = new ArrayList<>();

	}

	@Test
	void checkIfGroupExistsTest() {
		when(accountRepo.findByGroupnameAndMaster("google", master)).thenReturn(accounts);
		assertTrue(service.checkIfGroupExists("google"));
	}

	@Test
	void checkIfGroupExistsErrorTest() {
		when(accountRepo.findByGroupnameAndMaster("yahoo", master)).thenReturn(emptyAccounts);
		Throwable exception = assertThrows(GroupNotFoundException.class, () -> service.checkIfGroupExists("yahoo"));
		assertEquals("yahoo Group not Found", exception.getMessage());
	}

	@Test
	void deleteGroupTest() {
		when(accountRepo.findByGroupnameAndMaster("google", master)).thenReturn(accounts);
		assertTrue(service.deleteGroup("google"));
	}

	@Test
	void deleteGroupErrorTest() {
		when(accountRepo.findByGroupnameAndMaster("yahoo", master)).thenReturn(emptyAccounts);
		Throwable exception = assertThrows(GroupNotFoundException.class, () -> service.deleteGroup("yahoo"));
		assertEquals("yahoo Group not Found", exception.getMessage());
	}

	@Test
	void updateGroupnameTest() {
		when(accountRepo.findByGroupnameAndMaster("google", master)).thenReturn(accounts);
		assertTrue(service.updateGroupname("google", "googlegroup"));
	}

	@Test
	void updateGroupnameErrorTest() {
		when(accountRepo.findByGroupnameAndMaster("yahoo", master)).thenReturn(emptyAccounts);
		Throwable exception = assertThrows(GroupNotFoundException.class,
				() -> service.updateGroupname("yahoo", "yahoogroup"));
		assertEquals("yahoo Group not Found", exception.getMessage());
	}

	@Test
	void getGroupListTest() {
		when(accountRepo.findByGroupnameAndMaster("google", master)).thenReturn(accounts);
		assertEquals(accounts, service.getGroupList("google"));
	}

	@Test
	void getGroupListErrorTest() {
		when(accountRepo.findByGroupnameAndMaster("yahoo", master)).thenReturn(emptyAccounts);
		Throwable exception = assertThrows(GroupNotFoundException.class,
				() -> service.updateGroupname("yahoo", "yahoogroup"));
		assertEquals("yahoo Group not Found", exception.getMessage());
	}

}