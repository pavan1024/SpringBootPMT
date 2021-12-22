package com.epam.pmt.restcontrollertest;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.restcontroller.MasterController;
import com.epam.pmt.service.MasterUserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MasterController.class)
@ContextConfiguration(classes = { MasterController.class })
public class MasterControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	MasterUserService masterService;
	
	MasterDto master;
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@BeforeEach
	void setUp() {
		master = new MasterDto();
		master.setUsername("master111");
		master.setPassword("Master@123");
	}
	
	@Test
	void masterLoginTest() throws Exception {
		when(masterService.login(any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/master/login?username=master111&password=Master@123"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("Login Successful",res);
	}
	
	@Test
	void masterLoginErrorTest() throws Exception {
		when(masterService.login(any())).thenReturn(false);
		MvcResult result = mockMvc
				.perform(post("/master/login?username=master111&password=Master@123"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("Login Unsuccessful",res);
	}
	
	@Test
	void masterRegisterTest() throws Exception {
		when(masterService.registerAccount(any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/master/register?username=master111&password=Master@123"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("Account Registered Successfully",res);
	}
	
	@Test
	void masterLRegisterErrorTest() throws Exception {
		when(masterService.registerAccount(any())).thenReturn(false);
		MvcResult result = mockMvc
				.perform(post("/master/register?username=master111&password=Master@123"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals("Account Not Registered",res);
	}
	
	
	
	
	
}
