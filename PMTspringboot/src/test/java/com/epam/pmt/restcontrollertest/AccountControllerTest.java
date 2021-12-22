package com.epam.pmt.restcontrollertest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.restcontroller.AccountController;
import com.epam.pmt.service.AccountService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = { AccountController.class })
class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	AccountService accountService;

	AccountDto accountDto;
	Master master;
	List<Account> accounts;

	@BeforeEach
	void setUp() {
		accounts = new ArrayList<>();
		Master master = new Master();
		master.setUsername("master111");
		master.setPassword("Master@123");
		Account account = new Account();
		account.setUrl("https://www.abcd.com");
		account.setUsername("abcduser");
		account.setPassword("Abcd@123");
		account.setGroupname("abcd");
		account.setMaster(master);
		accounts.add(account);
		accountDto = new AccountDto();
		accountDto.setUrl("https://www.abcd.com");
		accountDto.setUsername("abcduser");
		accountDto.setPassword("Abcd@123");
		accountDto.setGroupname("abcd");
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	void fetchAllAccountsTest() throws Exception {
		List<Account> accounts = new ArrayList<>();
		Master master = new Master();
		master.setUsername("master111");
		master.setPassword("Master@123");
		Account account = new Account();
		account.setUrl("https://www.abcd.com");
		account.setUsername("abcduser");
		account.setPassword("Abcd@123");
		account.setGroupname("abcd");
		account.setMaster(master);
		accounts.add(account);
		when(accountService.getAll()).thenReturn(accounts);
		MvcResult result = mockMvc.perform(get("/accounts")).andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		Account[] accountList = this.mapFromJson(urlres, Account[].class);
		assertTrue(accountList.length == 1);
		assertEquals(accountList[0].getUrl(), "https://www.abcd.com");
	}

	@Test
	void addAccountTest() throws Exception {
		Master master = new Master();
		master.setUsername("master111");
		master.setPassword("Master@123");
		AccountDto account = new AccountDto();
		account.setUrl("https://www.abcd.com");
		account.setUsername("abcduser");
		account.setPassword("Abcd@123");
		account.setGroupname("abcd");
		when(accountService.createAccount(any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/accounts?groupname=abcd&username=abcduser&password=Abcd@123&url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("Account Added Successfully", res);
	}

	@Test
	void addAccountErrorTest() throws Exception {
		Master master = new Master();
		master.setUsername("master111");
		master.setPassword("Master@123");
		AccountDto account = new AccountDto();
		account.setUrl("https://www.abcd.com");
		account.setUsername("abcduser");
		account.setPassword("Abcd@123");
		account.setGroupname("abcd");
		when(accountService.createAccount(any())).thenReturn(false);
		MvcResult result = mockMvc
				.perform(post("/accounts?groupname=abcd&username=abcduser&password=Abcd@123&url=www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("Account Creation Unsuccessful", res);
	}

	@Test
	void updateUsernameTest() throws Exception {
		when(accountService.updateUsername("https://www.abcd.com", "abcdusername")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(put("/accounts/updateusername?newUsername=abcdusername&url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();
//		      assertEquals("Username Updated Successfully",res);
	}

	@Test
	void updateUsernameErrorTest() throws Exception {
		when(accountService.updateUsername("https://www.abcd.com", "abcdusername")).thenReturn(false);
		MvcResult result = mockMvc
				.perform(put("/accounts/updateusername?newUsername=abcdusername&url=https://www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();
//		      assertEquals("Username Updated Successfully",res);
	}

	@Test
	void updatePasswordTest() throws Exception {
		when(accountService.updatePassword("https://www.abcd.com", "Abcd@12345")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(put("/accounts/updatepassword?newPassword=Abcd@12345&url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();
//		      assertEquals("Password Updated Successfully",res);
	}

	@Test
	void updatePasswordErrorTest() throws Exception {
		when(accountService.updatePassword("https://www.abcd.com", "Abcdef")).thenReturn(false);
		MvcResult result = mockMvc.perform(put("/accounts/updatepassword?newPassword=Abcdef&url=https://www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();
//		      assertEquals("Account Password Not Updated ",res);
	}

	@Test
	void deleteTest() throws Exception {
		when(accountService.deleteAccount("https://www.abcd.com")).thenReturn(true);
		MvcResult result = mockMvc.perform(delete("/accounts?url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();
//		      assertEquals("Deleted Successfully",res);
	}

	@Test
	void deleteErrorTest() throws Exception {
		when(accountService.deleteAccount("https://www.abcd.com")).thenReturn(false);
		MvcResult result = mockMvc.perform(delete("/accounts?url=https://www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();
//		      assertEquals("Deleted Successfully",res);
	}

	@Test
	void readPasswordErrorTest() throws Exception {
		when(accountService.readPassword("https://www.abcd.com")).thenReturn("");
		MvcResult result = mockMvc.perform(get("/accounts/readpassword?url=https://www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("", res);
	}

	@Test
	void readPasswordTest() throws Exception {
		when(accountService.checkUrl("https://www.abcd.com")).thenReturn(true);
		when(accountService.readPassword("https://www.abcd.com")).thenReturn("Abcd@12345");

		MvcResult result = mockMvc.perform(get("/accounts/readpassword?url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("Abcd@12345", res);
	}

}
