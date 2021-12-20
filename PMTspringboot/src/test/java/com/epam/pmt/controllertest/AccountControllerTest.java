package com.epam.pmt.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.pmt.controller.AccountController;
import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.service.AccountService;

@WebMvcTest(AccountController.class)
@ContextConfiguration(classes = { AccountController.class })
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	AccountService accountService;
	AccountDto accountDto;

	@BeforeEach
	void setUp() {
		accountDto = new AccountDto();
		accountDto.setUrl("https://www.yahoo.com");
		accountDto.setUsername("username");
		accountDto.setPassword("Password@123");
	}

	@Test
	void addAccountFormTest() throws Exception {
		mockMvc.perform(get("/account/addAccountForm")).andExpect(view().name("addAccountForm"))
				.andExpect(status().isOk());
	}

	@Test
	void addAccountTest() throws Exception {
		AccountDto accountDto = new AccountDto();
		accountDto.setUrl("https://www.facebook.com");
		accountDto.setUsername("fbuser");
		accountDto.setPassword("Facebook@123");
		accountDto.setGroupname("facebook");
		when(accountService.createAccount(accountDto)).thenReturn(true);
		this.mockMvc.perform(post("/account/addAccount")).andExpect(status().isOk())
				.andExpect(view().name("account/addAccount"));
	}

	@Test
	void menuTest() throws Exception {
		mockMvc.perform(get("/account/menu")).andExpect(view().name("menu")).andExpect(status().isOk());
	}

	@Test
	void displayPasswordFormTest() throws Exception {
		mockMvc.perform(get("/account/displayPasswordForm")).andExpect(view().name("displayPasswordForm"))
				.andExpect(status().isOk());
	}

//	@Test
//	void displayPasswordTest() throws Exception {
//		when(accountService.readPassword(accountDto)).thenReturn("Facebook@123");
//		this.mockMvc.perform(post("/account/displayPassword")).andExpect(status().isOk()).andExpect(view().name("account/displayPassword"));
//	}

	@Test
	void deleteAccountFormTest() throws Exception {
		mockMvc.perform(get("/account/deleteAccountForm")).andExpect(view().name("deleteAccountForm"))
				.andExpect(status().isOk());
	}

	@Test
	void deleteAccountTest() throws Exception {
		when(accountService.deleteAccount(accountDto)).thenReturn(true);
		mockMvc.perform(post("/account/deleteAccount")).andExpect(view().name("account/deleteAccount"))
				.andExpect(status().isOk());
	}

	@Test
	void viewAllTest() throws Exception {
		List<Account> accounts = new ArrayList<>();
		when(accountService.getAll()).thenReturn(accounts);
		mockMvc.perform(get("/account/viewAll")).andExpect(model().size(1))
				.andExpect(model().attribute("accounts", accounts)).andExpect(view().name("viewAll"))
				.andExpect(status().isOk());
	}

	@Test
	void submenuTest() throws Exception {
		mockMvc.perform(get("/account/submenu")).andExpect(view().name("submenu")).andExpect(status().isOk());
	}

	@Test
	void updateAccountUsernameFormTest() throws Exception {
		mockMvc.perform(get("/account/updateAccountUsernameForm")).andExpect(view().name("updateAccountUsernameForm"))
				.andExpect(status().isOk());
	}

	@Test
	void updateAccountUsernameTest() throws Exception {
		when(accountService.updateUsername(accountDto)).thenReturn(true);
		mockMvc.perform(post("/account/updateAccountUsername")).andExpect(view().name("account/updateAccountUsername"))
				.andExpect(status().isOk());
	}

	@Test
	void updateAccountPasswordeFormTest() throws Exception {
		mockMvc.perform(get("/account/updateAccountPasswordForm")).andExpect(view().name("updateAccountPasswordForm"))
				.andExpect(status().isOk());
	}

	@Test
	void updateAccountPasswordTest() throws Exception {
		when(accountService.updatePassword(accountDto)).thenReturn(true);
		when(accountService.updatePassword(accountDto)).thenReturn(true);
		mockMvc.perform(post("/account/updateAccountPassword")).andExpect(view().name("account/updateAccountPassword"))
				.andExpect(status().isOk());
	}

}
