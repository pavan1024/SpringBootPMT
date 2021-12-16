package com.epam.pmt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void createAccountFormTest() throws Exception {
		this.mockMvc.perform(get("/account/createAccountForm")).andExpect(status().isOk());
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
	void deleteAccountFormTest() throws Exception {
		this.mockMvc.perform(get("/account/deleteAccountForm")).andExpect(status().isOk());
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
	void updateAccountPasswordeFormTest() throws Exception {
		this.mockMvc.perform(get("/account/updateAccountPasswordForm")).andExpect(status().isOk());
	}
	

	
}
