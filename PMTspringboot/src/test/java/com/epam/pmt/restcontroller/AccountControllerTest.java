package com.epam.pmt.restcontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.service.AccountService;

@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = { AccountController.class })
class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	AccountService accountService;

	@Test
	void addAccountTest() throws Exception {
		when(accountService.createAccount(any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/accounts?groupname=abcd&username=abcduser&password=Abcd@123&url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Added Successfully", response);
	}

	@Test
	void addAccountErrorTest() throws Exception {
		when(accountService.createAccount(any())).thenReturn(false);
		MvcResult result = mockMvc
				.perform(post("/accounts?groupname=abcd&username=abcduser&password=Abcd@123&url=www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Creation Unsuccessful", response);
	}

	@Test
	void updateUsernameTest() throws Exception {
		when(accountService.updateUsername("https://www.abcd.com", "abcdusername")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(put("/accounts/updateusername?newUsername=abcdusername&url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Username Updated Successfully", response);
	}

	@Test
	void updateUsernameErrorTest() throws Exception {
		when(accountService.updateUsername("https://www.abcd.com", "abcdusername")).thenReturn(false);
		MvcResult result = mockMvc
				.perform(put("/accounts/updateusername?newUsername=abcdusername&url=https://www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Username Not Updated", response);
	}

	@Test
	void updatePasswordTest() throws Exception {
		when(accountService.updatePassword("https://www.abcd.com", "Abcd@12345")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(put("/accounts/updatepassword?newPassword=Abcd@12345&url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Password Updated Successfully", response);
	}

	@Test
	void updatePasswordErrorTest() throws Exception {
		when(accountService.updatePassword("https://www.abcd.com", "Abcdef")).thenReturn(false);
		MvcResult result = mockMvc.perform(put("/accounts/updatepassword?newPassword=Abcdef&url=https://www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Password Not Updated", response);
	}

	@Test
	void deleteTest() throws Exception {
		when(accountService.deleteAccount("https://www.abcd.com")).thenReturn(true);
		MvcResult result = mockMvc.perform(delete("/accounts?url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Deleted Successfully", response);
	}

	@Test
	void deleteErrorTest() throws Exception {
		when(accountService.deleteAccount("https://www.abcd.com")).thenReturn(false);
		MvcResult result = mockMvc.perform(delete("/accounts?url=https://www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Deletion Unsuccessful", response);
	}

	@Test
	void readPasswordErrorTest() throws Exception {
		when(accountService.readPassword("https://www.abcd.com")).thenReturn("");
		MvcResult result = mockMvc.perform(get("/accounts/readpassword?url=https://www.abcd.com"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void readPasswordTest() throws Exception {
		when(accountService.checkUrl("https://www.abcd.com")).thenReturn(true);
		when(accountService.readPassword("https://www.abcd.com")).thenReturn("Abcd@12345");

		MvcResult result = mockMvc.perform(get("/accounts/readpassword?url=https://www.abcd.com"))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Abcd@12345", response);
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
		mockMvc.perform(get("/accounts")).andExpect(status().isOk()).andReturn();
	}
}
