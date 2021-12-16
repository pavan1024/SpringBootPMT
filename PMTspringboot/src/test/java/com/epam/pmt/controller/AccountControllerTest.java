package com.epam.pmt.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.pmt.business.AccountService;
import com.epam.pmt.dto.AccountDto;

@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = {AccountController.class})
class AccountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	AccountService accountService;
	

	@Test
	void addAccountFormTest() throws Exception {
		this.mockMvc.perform(get("/account/addAccountForm")).andExpect(status().isOk());
	}
	
	@Test
	void addAccountTest() throws Exception {
		AccountDto accountDto = new AccountDto();
		accountDto.setUrl("https://www.facebook.com");
		accountDto.setUsername("fbuser");
		accountDto.setPassword("Facebook@123");
		accountDto.setGroupname("facebook");
		when(accountService.createAccount(accountDto)).thenReturn(true);
		this.mockMvc.perform(post("/account/addAccount")).andExpect(status().isOk()).andExpect(view().name("account/addAccount"));
	}
	
	@Test
	void menuTest() throws Exception {
		this.mockMvc.perform(get("/account/menu")).andExpect(status().isOk());
	}
	
	
	@Test
	void displayPasswordFormTest() throws Exception {
		this.mockMvc.perform(get("/account/displayPasswordForm")).andExpect(status().isOk());
	}
	
	@Test
	void displayPasswordTest() throws Exception {
		when(accountService.readPassword("https://www.gmail.com")).thenReturn("Gmail@123");
		this.mockMvc.perform(post("/account/displayPassword")).andExpect(status().isOk()).andExpect(view().name("account/displayPassword"));
	}
	
	
	
	@Test
	void deleteAccountFormTest() throws Exception {
		this.mockMvc.perform(get("/account/deleteAccountForm")).andExpect(status().isOk());
	}
	
	@Test
	void deleteAccountTest() throws Exception {
		when(accountService.deleteAccount("https://www.gmail.com")).thenReturn(true);
		this.mockMvc.perform(post("/account/deleteAccount")).andExpect(status().isOk()).andExpect(view().name("account/deleteAccount"));
	}
	
	
	@Test
	void viewAllTest() throws Exception {
		this.mockMvc.perform(get("/account/viewAll")).andExpect(status().isOk());
	}
	
	@Test
	void submenuTest() throws Exception {
		this.mockMvc.perform(get("/account/submenu")).andExpect(status().isOk());
	}
	
	@Test
	void updateAccountUsernameFormTest() throws Exception {
		this.mockMvc.perform(get("/account/updateAccountUsernameForm")).andExpect(status().isOk());
	}
	@Test
	void updateAccountUsernameTest() throws Exception {
		when(accountService.updateUsername("https://www.gmail.com","user")).thenReturn(true);
		this.mockMvc.perform(post("/account/updateAccountUsername")).andExpect(status().isOk()).andExpect(view().name("account/updateAccountUsername"));
	}
	@Test
	void updateAccountPasswordeFormTest() throws Exception {
		this.mockMvc.perform(get("/account/updateAccountPasswordForm")).andExpect(status().isOk());
	}
	@Test
	void updateAccountPasswordTest() throws Exception {
		when(accountService.updatePassword("https://www.gmail.com","Password@123")).thenReturn(true);
		this.mockMvc.perform(post("/account/updateAccountPassword")).andExpect(status().isOk()).andExpect(view().name("account/updateAccountPassword"));
	}
	

	
}
