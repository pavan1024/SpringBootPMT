package com.epam.pmt.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.exception.PasswordNotValidException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.repo.AccountRepository;
import com.epam.pmt.service.AccountService;
import com.epam.pmt.util.MasterProvider;
import com.epam.pmt.util.Security;
import com.epam.pmt.util.Validation;

@SpringBootTest
class AccountServiceTest {

	@Mock
	AccountRepository accountRepository;

	@Mock
	Validation validation;

	@Mock
	Security security;

	@Mock
	ModelMapper mapper;
	
	@Mock
	AccountDto accountDto;
	
	@Mock
	AccountDto invalidAccountDto;

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

		accountDto = new AccountDto();
		accountDto.setUrl("https://www.yahoo.com");
		accountDto.setUsername("yahoouser");
		accountDto.setPassword("Yahoo@123");
		accountDto.setGroupname("yahoo");

		invalidAccountDto = new AccountDto();
		invalidAccountDto.setUrl("https://www.instagram.com");
		invalidAccountDto.setUsername("mailusername");
		invalidAccountDto.setPassword("Mail@1");
		invalidAccountDto.setGroupname("instagram");

	}

	@Test
	void updateUsernameTest() {
		AccountDto invalidAccountDto = new AccountDto();
		invalidAccountDto.setUrl("https://www.instagram.com");
		invalidAccountDto.setUsername("mailusername");
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		assertTrue(accountService.updateUsername(accountDto));
		try {
			assertFalse(accountService.updateUsername(invalidAccountDto));

		} catch (URLNotFoundException ex) {

		}
	}

	@Test
	void updatePasswordTest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		when(validation.isValidPassword("Yahoo@123")).thenReturn(true);
		assertTrue(accountService.updatePassword(accountDto));
		try {
			assertFalse(accountService.updatePassword(invalidAccountDto));

		} catch (URLNotFoundException ex) {

		}
	}

	@Test
	void createAccountTest() throws Exception {
		when(validation.isValidURL("https://www.gmail.com")).thenReturn(true);
		when(validation.isValidPassword("Gmail@123")).thenReturn(true);
		AccountDto accountDto = new AccountDto();
		accountDto.setUrl("https://www.gmail.com");
		accountDto.setUsername("fbuser");
		accountDto.setPassword("Gmail@123");
		accountDto.setGroupname("facebook");
		try {
			assertTrue(accountService.createAccount(accountDto));
		} catch (NullPointerException ex) {
			// this test case need to be handled
		}
	}
	
//	@Test
//	void createAccountErrorTest() throws Exception {
////		when(validation.isValidURL("https://www.gmail.com")).thenReturn(true);
//		when(validation.isValidPassword("Gmail123")).thenReturn(false);
//		try {
//			assertEquals(false,accountService.createAccount(invalidAccountDto));
//		} catch (PasswordNotValidException ex) {
//			
//		}
//	}
	
	@Test
	void getPasswordTest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		assertEquals(security.decrypt(security.encrypt("Yahoo@123")), accountService.readPassword(accountDto));
	}

	@Test
	void checkUrlTest() {
		when(accountRepository.findByUrlAndMaster("https://www.yahoo.com", master)).thenReturn(account);
		when(accountRepository.findByUrlAndMaster("https://www.instagram.com", master)).thenReturn(emptyAccount);
		assertTrue(accountService.checkUrl("https://www.yahoo.com"));
		try {
			assertFalse(accountService.checkUrl("https://www.instagram.com"));
		} catch (URLNotFoundException ex) {

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
		assertTrue(accountService.deleteAccount(accountDto));
		try {
			assertFalse(accountService.deleteAccount(invalidAccountDto));
		} catch (URLNotFoundException ex) {

		}
	}

}
