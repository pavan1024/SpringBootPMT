package com.epam.pmt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@AutoConfigureMockMvc
@SpringBootTest
class MasterControllerTest {
	

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	void loginTest() throws Exception {
		this.mockMvc.perform(post("/master/login").param("username", "user").param("password", "pass")).andExpect(status().isOk()).andExpect(view().name("master/login"));
	}
	
	@Test
	void masterMenuTest() throws Exception {
		this.mockMvc.perform(get("/master/")).andExpect(status().isOk());
	}
	
	@Test
	void loginFormTest() throws Exception {
		this.mockMvc.perform(get("/master/loginForm")).andExpect(status().isOk());;
	}
	
	@Test
	void registerFormTest() throws Exception {
		this.mockMvc.perform(get("/master/registerForm")).andExpect(status().isOk());
	}
	
	
	
	
}
