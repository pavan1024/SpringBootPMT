package com.epam.pmt.bussiness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.business.AccountService;
import com.epam.pmt.business.MasterProvider;
import com.epam.pmt.business.Security;
import com.epam.pmt.business.Validation;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.repo.AccountRepository;

@SpringBootTest
class AccountServiceTest {

	@Mock
	AccountRepository accountRepository;
	
	@Mock
	Validation validation;
	
	@Mock
	Security security;

	@InjectMocks
	AccountService accountService;

	List<Account> accounts;
	Account account;
	Account emptyAccount = null;
	Master master;
	Master master1 = null;

	@BeforeEach
	public void setUp() {
		MasterProvider.setMaster("masteruser", "Master@123");
		master = MasterProvider.getMaster();
		account = new Account();
		account.setMaster(master);
		account.setUrl("https://www.yahoo.com");
		account.setUsername("yahoouser");
		account.setPassword("Yahoo@123");
		account.setGroupname("yahoo");
		accounts = new ArrayList<>();
		accounts.add(account);
		
	}

	@Test
	void updateUsernameTest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		try {
		assertFalse(accountService.updateUsername("https://www.instagram.com", "mailusername"));
		assertTrue(accountService.updateUsername("https://www.yahoo.com", "yahoousername"));
		}
		catch(URLNotFoundException ex) {
			
		}
	}

	@Test
	void updatePasswordTest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		when(validation.isValidPassword("Yahoo@123")).thenReturn(true);
		try {
		assertFalse(accountService.updatePassword("https://www.instagram.com", "Mail@1"));
		assertTrue(accountService.updatePassword("https://www.yahoo.com", "Yahoo@123"));
		}
		catch(URLNotFoundException ex){
			
		}
	}

//	@Test
//	void createAccountTest() {
//		when(accountRepository.findByMaster(master)).thenReturn(accounts);
//		when(accountRepository.findByMaster(master1)).thenReturn(null);
//		assertTrue(accountService.createAccount("https://www.yahoo.com", "yahoo", "yahoo@123", "yahoo"));
//	}

	@Test
	void getPasswordTest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		assertEquals(security.decrypt(security.encrypt("Yahoo@123")), accountService.readPassword("https://www.yahoo.com"));
	}

	@Test
	void checkUrlTest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		try {
		assertFalse(accountService.checkUrl("https://www.instagram.com"));
		assertTrue(accountService.checkUrl("https://www.yahoo.com"));
		}
		catch(URLNotFoundException ex) {
				
			}
	}

	@Test
	void getAllTest() {
		when(accountRepository.findByMaster(master)).thenReturn(accounts);
		when(accountRepository.findByMaster(master1)).thenReturn(null);
		assertEquals(accounts, accountService.getAll());
	}

	@Test
	void deleteAccountTest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		try {
		assertFalse(accountService.deleteAccount("https://www.instagram.com"));
		assertTrue(accountService.deleteAccount("https://www.yahoo.com"));
		}
		catch(URLNotFoundException ex) {
			
		}
	}

}
