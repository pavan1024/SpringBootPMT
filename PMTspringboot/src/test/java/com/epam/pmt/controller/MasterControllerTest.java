package com.epam.pmt.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.pmt.entities.Master;

@SpringBootTest
@AutoConfigureMockMvc
class MasterControllerTest {
	

	@Autowired
	private MockMvc mockMvc;

	@Test
	void masterMenuTest() throws Exception {
		this.mockMvc.perform(get("/master/")).andExpect(status().isOk());
	}
	
	@Test
	void loginFormTest() throws Exception {
		this.mockMvc.perform(get("/master/loginForm")).andExpect(status().isOk());
	}
	
	@Test
	void registerFormTest() throws Exception {
		this.mockMvc.perform(get("/master/registerForm")).andExpect(status().isOk());
	}
	
	
	
	
}
