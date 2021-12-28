package com.epam.pmt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.dto.AuthenticationRequest;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@Autowired
	WebApplicationContext context;
	@MockBean
	AccountService accountService;

	AccountDto accountDto;
	String token;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		AuthenticationRequest request = new AuthenticationRequest();
		request.setUsername("pavan");
		request.setPassword("password");
		
		MvcResult result = mockMvc
				.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		token = response.substring(8, response.length() - 2);
		
		mapper = new ObjectMapper();
		accountDto = new AccountDto();
		accountDto.setUrl("https://www.abcd.com");
		accountDto.setUsername("abcduser");
		accountDto.setPassword("Abcd@123");
		accountDto.setGroupname("abcd");
	}

	@Test
	void addAccountTest() throws Exception {
		when(accountService.createAccount(any())).thenReturn(true);		
		MvcResult result = mockMvc.perform(post("/accounts/").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token)
						.content(mapper.writeValueAsString(accountDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Added Successfully", response);
	}

	@Test
	void addAccountErrorTest() throws Exception {
		when(accountService.createAccount(any())).thenReturn(false);
		MvcResult result = mockMvc
				.perform(post("/accounts/").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token)
						.content(mapper.writeValueAsString(accountDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Creation Unsuccessful", response);
	}

	@Test
	void updateUsernameTest() throws Exception {
		when(accountService.updateUsername("https://www.abcd.com", "abcdusername")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(put("/accounts/updateusername?newUsername=abcdusername&url=https://www.abcd.com").header("Authorization", "Bearer " + token))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Username Updated Successfully", response);
	}

	@Test
	void updateUsernameErrorTest() throws Exception {
		when(accountService.updateUsername("https://www.abcd.com", "abcdusername")).thenReturn(false);
		MvcResult result = mockMvc
				.perform(put("/accounts/updateusername?newUsername=abcdusername&url=https://www.abcd.com").header("Authorization", "Bearer " + token))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Username Not Updated", response);
	}

	@Test
	void updatePasswordTest() throws Exception {
		when(accountService.updatePassword("https://www.abcd.com", "Abcd@12345")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(put("/accounts/updatepassword?newPassword=Abcd@12345&url=https://www.abcd.com").header("Authorization", "Bearer " + token))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Password Updated Successfully", response);
	}

	@Test
	void updatePasswordErrorTest() throws Exception {
		when(accountService.updatePassword("https://www.abcd.com", "Abcdef")).thenReturn(false);
		MvcResult result = mockMvc.perform(put("/accounts/updatepassword?newPassword=Abcdef&url=https://www.abcd.com").header("Authorization", "Bearer " + token))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Password Not Updated", response);
	}

	@Test
	void deleteTest() throws Exception {
		when(accountService.deleteAccount("https://www.abcd.com")).thenReturn(true);
		MvcResult result = mockMvc.perform(delete("/accounts?url=https://www.abcd.com").header("Authorization", "Bearer " + token))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Deleted Successfully", response);
	}

	@Test
	void deleteErrorTest() throws Exception {
		when(accountService.deleteAccount("https://www.abcd.com")).thenReturn(false);
		MvcResult result = mockMvc.perform(delete("/accounts?url=https://www.abcd.com").header("Authorization", "Bearer " + token))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Deletion Unsuccessful", response);
	}

	@Test
	void readPasswordErrorTest() throws Exception {
		when(accountService.readPassword("https://www.abcd.com")).thenReturn("");
		MvcResult result = mockMvc.perform(get("/accounts/readpassword?url=https://www.abcd.com").header("Authorization", "Bearer " + token))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("", response);
	}

	@Test
	void readPasswordTest() throws Exception {
		when(accountService.checkUrl("https://www.abcd.com")).thenReturn(true);
		when(accountService.readPassword("https://www.abcd.com")).thenReturn("Abcd@12345");

		MvcResult result = mockMvc.perform(get("/accounts/readpassword?url=https://www.abcd.com").header("Authorization", "Bearer " + token))
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
		mockMvc.perform(get("/accounts").header("Authorization", "Bearer " + token)).andExpect(status().isOk()).andReturn();
	}
}
