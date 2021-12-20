package com.epam.pmt.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.pmt.controller.MasterController;
import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.service.MasterUserService;

@WebMvcTest(MasterController.class)
@ContextConfiguration(classes = { MasterController.class })
class MasterControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	MasterUserService masterService;

	@Test
	void loginTest() throws Exception {
		MasterDto master = new MasterDto();
		master.setUsername("user");
		master.setPassword("password");
		when(masterService.login(master)).thenReturn(true);
		mockMvc.perform(post("/master/login")).andExpect(view().name("master/login")).andExpect(status().isOk());
	}

	@Test
	void masterMenuTest() throws Exception {
		mockMvc.perform(get("/master/")).andExpect(view().name("mastermenu")).andExpect(status().isOk());
	}

	@Test
	void loginFormTest() throws Exception {
		mockMvc.perform(get("/master/loginForm")).andExpect(view().name("loginForm")).andExpect(status().isOk());
	}

	@Test
	void registerFormTest() throws Exception {
		mockMvc.perform(get("/master/registerForm")).andExpect(view().name("registerForm")).andExpect(status().isOk());
	}

	@Test
	void registerTest() throws Exception {
		MasterDto master = new MasterDto();
		master.setUsername("user");
		master.setPassword("password");
		when(masterService.registerAccount(master)).thenReturn(true);
		mockMvc.perform(post("/master/register")).andExpect(view().name("master/register")).andExpect(status().isOk());
	}

}
