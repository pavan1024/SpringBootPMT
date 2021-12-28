package com.epam.pmt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.epam.pmt.dto.AuthenticationRequest;
import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.service.MasterUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class MasterControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@Autowired
	WebApplicationContext context;
	@MockBean
	MasterUserService masterService;

	private String token;
	MasterDto masterDto;
	MasterDto invalidMasterDto;

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

		masterDto = new MasterDto();
		masterDto.setUsername("master111");
		masterDto.setPassword("Master@123");
		invalidMasterDto = new MasterDto();
	}

	@Test
	void masterLoginTest() throws Exception {
		when(masterService.login(any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/master/login").contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Login Successful", response);
	}

	@Test
	void masterLoginErrorTest() throws Exception {
		when(masterService.login(any())).thenReturn(false);
		MvcResult result = mockMvc.perform(
				post("/master/login").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + token)
						.content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Login Unsuccessful", response);
	}

	@Test
	void masterRegisterTest() throws Exception {
		when(masterService.registerAccount(any())).thenReturn(true);
		MvcResult result = mockMvc.perform(post("/master/register").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token).content(mapper.writeValueAsString(masterDto))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Registered Successfully", response);

	}

	@Test
	void masterLRegisterErrorTest() throws Exception {
		when(masterService.registerAccount(any())).thenReturn(false);
		MvcResult result = mockMvc.perform(post("/master/register").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + token).content(mapper.writeValueAsString(masterDto))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Account Not Registered", response);
	}

}
