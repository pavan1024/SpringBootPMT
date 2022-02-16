package com.epam.pmt.exceptionhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.exception.PasswordNotValidException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.exception.URLNotValidException;
import com.epam.pmt.exception.UserNotFoundException;
import com.epam.pmt.service.AccountService;
import com.epam.pmt.service.GroupService;
import com.epam.pmt.service.MasterUserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class RestExceptionHandlerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@Autowired
	WebApplicationContext context;
	@MockBean
	AccountService accountService;
	@MockBean
	GroupService groupService;
	@MockBean
	MasterUserService masterService;

	AccountDto accountDto;
	MasterDto masterDto;

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@BeforeEach
	void setUp() throws Exception {
		mapper = new ObjectMapper();
		accountDto = new AccountDto();
		accountDto.setUrl("abcd.com");
		accountDto.setUsername("abcduser");
		accountDto.setPassword("Abcd@123");
		accountDto.setGroupname("abcd");

		masterDto = new MasterDto();
		masterDto.setUsername("master");
		masterDto.setPassword("Master@123");
	}

	@Test
	void handleURLNotFoundExceptionTest() throws Exception {
		when(accountService.checkUrl("abcd.com")).thenThrow(new URLNotFoundException("abcd.com URL Not Found"));
		when(accountService.readPassword("abcd.com")).thenThrow(new URLNotFoundException("abcd.com URL Not Found"));
		MvcResult result = mockMvc.perform(get("/accounts/readpassword?url=abcd.com")).andExpect(status().isOk())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		HashMap<String, String> data = this.mapFromJson(response, HashMap.class);
		assertEquals("abcd.com URL Not Found", data.get("error"));
	}

	@Test
	void handleGroupNotFoundExceptionTest() throws Exception {
		when(groupService.checkIfGroupExists("sample")).thenThrow(new GroupNotFoundException("sample group Not Found"));
		when(groupService.getGroupList("sample")).thenThrow(new GroupNotFoundException("sample group Not Found"));
		MvcResult result = mockMvc.perform(get("/groups/?groupname=sample")).andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		HashMap<String, String> data = this.mapFromJson(response, HashMap.class);
		assertEquals("sample group Not Found", data.get("error"));
	}

	@Test
	void handleURLNotValidExceptionTest() throws Exception {
		when(accountService.createAccount(any())).thenThrow(new URLNotValidException("URL must start with https://"));
		MvcResult result = mockMvc
				.perform(post("/accounts/").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(accountDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		HashMap<String, String> data = this.mapFromJson(response, HashMap.class);
		assertEquals("URL must start with https://", data.get("error"));
	}

	@Test
	void handlePasswordNotValidExceptionTest() throws Exception {
		when(accountService.createAccount(any()))
				.thenThrow(new PasswordNotValidException("password must contain more than or equal to 8 chars"));
		MvcResult result = mockMvc
				.perform(post("/accounts/").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(accountDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		HashMap<String, String> data = this.mapFromJson(response, HashMap.class);
		assertEquals("password must contain more than or equal to 8 chars", data.get("error"));
	}

	@Test
	void handleUserNotFoundExceptionTest() throws Exception {
		when(masterService.login(any())).thenThrow(new UserNotFoundException("invalid username or password"));
		MvcResult result = mockMvc
				.perform(post("/masters/login").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(masterDto)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		HashMap<String, String> data = this.mapFromJson(response, HashMap.class);
		assertEquals("invalid username or password", data.get("error"));
	}

}
