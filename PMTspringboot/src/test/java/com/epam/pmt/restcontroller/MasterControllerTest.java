package com.epam.pmt.restcontroller;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.service.MasterUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MasterController.class)
@ContextConfiguration(classes = { MasterController.class })
class MasterControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	MasterUserService masterService;

	MasterDto masterDto;
	MasterDto invalidMasterDto;
	ObjectMapper mapper;

	@BeforeEach
	void setUp() {
		mapper = new ObjectMapper();
		masterDto = new MasterDto();
		masterDto.setUsername("masteruser");
		masterDto.setPassword("Master@123");
		invalidMasterDto = new MasterDto();
	}

	@Test
	void masterLoginTest() throws Exception {
		when(masterService.login(any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/masters/login").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Login Successful", response);
	}

	@Test
	void masterLoginErrorTest() throws Exception {
		when(masterService.login(any())).thenReturn(false);
		MvcResult result = mockMvc
				.perform(post("/masters/login").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Login Unsuccessful", response);
	}

	@Test
	void masterRegisterTest() throws Exception {
		when(masterService.registerAccount(any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/masters/register").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Registered Successfully", response);

	}

	@Test
	void masterLRegisterErrorTest() throws Exception {
		when(masterService.registerAccount(any())).thenReturn(false);
		MvcResult result = mockMvc
				.perform(post("/masters/register").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Not Registered", response);
	}

}
