package com.epam.pmt.bussiness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.business.AccountService;
import com.epam.pmt.business.MasterProvider;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.repo.AccountRepository;

@SpringBootTest
class AccountServiceTest {
	
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	AccountService accountService;
	List<Account> accounts;
	List<Account> emptyAccounts;
	Account account;
	Account emptyAccount = null;
	Master master;
	Master master1=null;
	@BeforeEach
	public void setUp() {
		MasterProvider.setMaster("masteruser", "Master@123");
		master = MasterProvider.getMaster();
		account = new Account();
		account.setMaster(master);
		account.setUrl("https://www.yahoo.com");
		account.setUsername("yahoouser");
		account.setPassword("yahoo@123");
		account.setGroupname("yahoo");
//		accounts.add(account);
		
	}
	
	@Test
	void updateUsernametest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		assertFalse(accountService.updateUsername("https://www.instagram.com", "mailusername"));
		assertTrue(accountService.updatePassword("https://www.yahoo.com", "yahoousername"));
	}
	
	@Test
	void updatePasswordtest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		assertFalse(accountService.updateUsername("https://www.instagram.com", "mailusername"));
		assertTrue(accountService.updatePassword("https://www.yahoo.com", "yahoousername"));
	}
	@Test
	void getPasswordtest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		assertEquals("yahoo@123",accountService.readPassword("https://www.yahoo.com"));
	}
//	@Test
//	void getAlltest() {
//		when(accountRepository.findByMaster(master)).thenReturn(accounts);
//		when(accountRepository.findByMaster(master1)).thenReturn(emptyAccounts);
//		assertEquals(accounts,accountService.getAll());
//	}
	
	@Test
	void checkUrltest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		assertFalse(accountService.checkUrl("https://www.instagram.com"));
		assertTrue(accountService.checkUrl("https://www.yahoo.com"));
	}

}
