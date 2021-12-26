package com.epam.pmt.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.pmt.service.MasterUserService;

@WebMvcTest(MasterController.class)
class MasterControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	MasterUserService masterService;

	@Test
	void masterLoginTest() throws Exception {
		when(masterService.login(any())).thenReturn(true);
		MvcResult result = mockMvc.perform(post("/masters/login?username=master111&password=Master@123"))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Login Successful", response);
	}

	@Test
	void masterLoginErrorTest() throws Exception {
		when(masterService.login(any())).thenReturn(false);
		MvcResult result = mockMvc.perform(post("/masters/login?username=master111&password=Master@123"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Login Unsuccessful", response);
	}

	@Test
	void masterRegisterTest() throws Exception {
		when(masterService.registerAccount(any())).thenReturn(true);
		MvcResult result = mockMvc.perform(post("/masters/register?username=master111&password=Master@123"))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Registered Successfully", response);
	}

	@Test
	void masterLRegisterErrorTest() throws Exception {
		when(masterService.registerAccount(any())).thenReturn(false);
		MvcResult result = mockMvc.perform(post("/masters/register?username=master111&password=Master@123"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Not Registered", response);
	}

}
