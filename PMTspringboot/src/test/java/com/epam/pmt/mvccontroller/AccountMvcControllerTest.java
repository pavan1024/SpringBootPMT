package com.epam.pmt.mvccontroller;



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
import org.springframework.test.web.servlet.MockMvc;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.service.AccountService;

@WebMvcTest(AccountMvcController.class)
class AccountMvcControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	AccountService accountService;
	
	AccountDto accountDto;
	
	@BeforeEach
	void setUp() {
		accountDto = new AccountDto();
		accountDto.setUrl("https://www.abcd.com");
		accountDto.setUsername("abcduser");
		accountDto.setPassword("Abcd@123");
		accountDto.setGroupname("abcd");
		
	}
	
	@Test
	void addAccountFormTest() throws Exception {
		mockMvc.perform(get("/account/addAccountForm")).andExpect(view().name("addAccountForm"))
				.andExpect(status().isOk());
	}
	
	@Test
	void addAccountTest() throws Exception {
		when(accountService.createAccount(accountDto)).thenReturn(true);
		mockMvc.perform(post("/account/addAccount")).andExpect(view().name("account/addAccount")).andExpect(status().isOk());
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
//		mockMvc.perform(post("/account/displayPassword")).andExpect(model().size(2))
//		.andExpect(model().attribute("password", "Facebook@123")).andExpect(view().name("account/displayPassword"))
//		.andExpect(status().isOk());
//	}

	@Test
	void deleteAccountFormTest() throws Exception {
		mockMvc.perform(get("/account/deleteAccountForm")).andExpect(view().name("deleteAccountForm"))
				.andExpect(status().isOk());
	}

	@Test
	void deleteAccountTest() throws Exception {
		when(accountService.deleteAccount("https://www.abcd.com")).thenReturn(true);
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
		when(accountService.updateUsername("https://www.abcd.com","abcdusername")).thenReturn(true);
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
		when(accountService.updatePassword("https://www.abcd.com","Abcd@123")).thenReturn(true);
		mockMvc.perform(post("/account/updateAccountPassword")).andExpect(view().name("account/updateAccountPassword"))
				.andExpect(status().isOk());
	}
	
	
	
}
//@Test
//void addAccountTest1() throws Exception {
//	AccountDto accountDto = new AccountDto();
//	accountDto.setUrl("https://wwww.abcd.com");
//	accountDto.setUsername("abcduser");
//	accountDto.setPassword("Abcd@123");
//	accountDto.setGroupname("abcd");
//	ModelMapper mapper = new ModelMapper();
//	when(accountService.createAccount(any())).thenReturn(true);
//	mockMvc.perform(post("/accounts")
//			.accept(MediaType.TEXT_PLAIN_VALUE).content(mapper.writeValueAsString(accountDto)).contentType(MediaType.APPLICATION_JSON_VALUE)).
//			andExpect(status().isCreated()).andExpect(content().bytes("Employee insert successfully.".getBytes()));
//}