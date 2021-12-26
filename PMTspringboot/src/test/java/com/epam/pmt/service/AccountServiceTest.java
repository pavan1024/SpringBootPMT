package com.epam.pmt.service;

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
import com.epam.pmt.exception.URLNotValidException;
import com.epam.pmt.repo.AccountRepository;
import com.epam.pmt.util.MasterProvider;
import com.epam.pmt.util.SecurityUtil;
import com.epam.pmt.util.ValidationUtil;

@SpringBootTest
class AccountServiceTest {

	@Mock
	AccountRepository accountRepo;

	@Mock
	ValidationUtil validationUtil;

	@Mock
	SecurityUtil securityUtil;

	@Mock
	ModelMapper mapper;

	@InjectMocks
	AccountService service;

	List<Account> accounts;
	Account account;
	Master master;

	String plainText;
	String encryptedText;

	@BeforeEach
	public void setUp() {
		MasterProvider.setMaster("masteruser", "Master@123");
		master = MasterProvider.getMaster();
		account = new Account();
		account.setMaster(master);
		account.setUrl("https://www.abcd.com");
		account.setUsername("abcduser");
		account.setPassword("Abcd@123");
		account.setGroupname("abcd");
		accounts = new ArrayList<>();
		accounts.add(account);
		plainText = "Abcd@123";
		encryptedText = securityUtil.encrypt(plainText);
	}

	@Test
	void checkUrlTest() {
		when(accountRepo.findByUrlAndMaster(account.getUrl(), master)).thenReturn(account);
		assertTrue(service.checkUrl(account.getUrl()));

	}

	@Test
	void checkUrlThrowsURLNotFoundException() {
		Throwable exception = assertThrows(URLNotFoundException.class, () -> service.checkUrl("www.asdf.com"));
		assertEquals("www.asdf.com URL not Found", exception.getMessage());
	}

	@Test
	void deleteTest() {
		when(accountRepo.findByUrlAndMaster(account.getUrl(), master)).thenReturn(account);
		assertTrue(service.deleteAccount(account.getUrl()));
	}

	@Test
	void deleteAccountExceptionTest() {
		Throwable exception = assertThrows(URLNotFoundException.class, () -> service.deleteAccount("www.asdf.com"));
		assertEquals("www.asdf.com URL not Found", exception.getMessage());
	}

	@Test
	void updateUsernameTest() {
		when(accountRepo.findByUrlAndMaster(account.getUrl(), master)).thenReturn(account);
		assertTrue(service.updateUsername(account.getUrl(), "abcdusername"));
	}

	@Test
	void updateUsernameExceptionTest() {
		Throwable exception = assertThrows(URLNotFoundException.class,
				() -> service.updateUsername("www.asdf.com", "asdf"));
		assertEquals("www.asdf.com URL not Found", exception.getMessage());
	}

	@Test
	void updatePasswordTest() {
		when(accountRepo.findByUrlAndMaster(account.getUrl(), master)).thenReturn(account);
		when(validationUtil.isValidPassword("Abcd@12345")).thenReturn(true);
		assertTrue(service.updatePassword(account.getUrl(), "Abcd@12345"));
	}

	@Test
	void updatePasswordExceptionTest() {
		Throwable exception = assertThrows(URLNotFoundException.class,
				() -> service.updatePassword("www.asdf.com", "asdf"));
		assertEquals("www.asdf.com URL not Found", exception.getMessage());

	}

	@Test
	void updatePasswordException() {
		when(accountRepo.findByUrlAndMaster(account.getUrl(), master)).thenReturn(account);
		when(validationUtil.isValidPassword("abcdpass")).thenReturn(false);
		Throwable passwordException = assertThrows(PasswordNotValidException.class,
				() -> service.updatePassword("https://www.abcd.com", "abcdpass"));
		assertEquals(
				"Password must contain 1 upppercase,lowercase,special character and it must be more than 8 characters",
				passwordException.getMessage());
	}

	@Test
	void createAccount() {
		AccountDto accountDto = new AccountDto();
		accountDto.setUsername("abcduser");
		accountDto.setPassword("Abcd@123");
		accountDto.setGroupname("abcd");
		accountDto.setUrl("https://www.abcd.com");
		when(validationUtil.isValidPassword("Abcd@123")).thenReturn(true);
		when(validationUtil.isValidURL("https://www.abcd.com")).thenReturn(true);
		when(securityUtil.encrypt(plainText)).thenReturn(encryptedText);
		when(mapper.map(accountDto, Account.class)).thenReturn(account);
		assertTrue(service.createAccount(accountDto));
	}

	@Test
	void createAccountErrorTest() {
		AccountDto accountDto = new AccountDto();
		accountDto.setUsername("abcduser");
		accountDto.setPassword("Abcd@123");
		accountDto.setGroupname("abcd");
		accountDto.setUrl("www.abcd.com");
		when(validationUtil.isValidURL("https://www.abcd.com")).thenReturn(false);
		Throwable exception = assertThrows(URLNotValidException.class, () -> service.createAccount(accountDto));
		assertEquals("Url must start with https://", exception.getMessage());
	}

	@Test
	void createAccountErrorTest1() {
		AccountDto accountDto = new AccountDto();
		accountDto.setUsername("abcduser");
		accountDto.setPassword("Abcd23");
		accountDto.setGroupname("abcd");
		accountDto.setUrl("https://www.abcd.com");
		when(validationUtil.isValidURL("https://www.abcd.com")).thenReturn(true);
		Throwable exception = assertThrows(PasswordNotValidException.class, () -> service.createAccount(accountDto));
		assertEquals(
				"Password must contain 1 upppercase,lowercase,special character and it must be more than 8 characters",
				exception.getMessage());
	}

	@Test
	void readPasswordTest() {
		when(accountRepo.findByUrlAndMaster("https://www.abcd.com", master)).thenReturn(account);
		assertEquals(securityUtil.decrypt(securityUtil.encrypt("Abcd@123")), service.readPassword(account.getUrl()));
	}

	@Test
	void readPasswordErrorTest() {
		Throwable exception = assertThrows(URLNotFoundException.class, () -> service.readPassword("www.asdf.com"));
		assertEquals("www.asdf.com URL not Found", exception.getMessage());
	}

	@Test
	void getAllAccountsTest() {
		when(accountRepo.findByMaster(master)).thenReturn(accounts);
		assertEquals(accounts, service.getAll());
	}

}