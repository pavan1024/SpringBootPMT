package com.epam.pmt.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.service.GroupService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class GroupControllerTest {
	@MockBean
	GroupService groupService;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@Autowired
	WebApplicationContext context;

	private String token;

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
	}

	@Test
	void updateGroupnameTest() throws Exception {
		when(groupService.updateGroupname("abcd", "newabcd")).thenReturn(true);
		MvcResult result = mockMvc.perform(
				put("/groups/?currentGroupname=abcd&newGroupname=newabcd").header("Authorization", "Bearer " + token))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Groupname Updated Successfully", response);

	}

	@Test
	void updateGroupnameErrorTest() throws Exception {
		when(groupService.updateGroupname("abcd", "newabcd")).thenReturn(true);
		MvcResult result = mockMvc.perform(
				put("/groups/?currentgroupname=abcd&newgroupname=newabcd").header("Authorization", "Bearer " + token))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Groupname Not Updated", response);

	}

	@Test
	void deleteGroupTest() throws Exception {
		when(groupService.deleteGroup("abcd")).thenReturn(true);
		MvcResult result = mockMvc.perform(delete("/groups?groupname=abcd").header("Authorization", "Bearer " + token))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Group Deleted Successfully", response);

	}

	@Test
	void deleteGroupErrorTest() throws Exception {

		when(groupService.deleteGroup("abcd")).thenReturn(false);
		MvcResult result = mockMvc.perform(delete("/groups?groupname=abcd").header("Authorization", "Bearer " + token))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Group Deletion Unsuccessful", response);

	}

	@Test
	void fetchAllGroupAccountsTest() throws Exception {
		List<Account> accounts = new ArrayList<>();
		Master master = new Master();
		master.setUsername("master111");
		master.setPassword("Master@123");
		Account account = new Account();
		account.setUrl("https://www.abcd.com");
		account.setUsername("abcduser");
		account.setPassword("Abcd@123");
		account.setGroupname("abcd");
		account.setMaster(master);
		accounts.add(account);
		when(groupService.getGroupList("abcd")).thenReturn(accounts);
		mockMvc.perform(post("/groups/?groupname=abcd").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();
	}

}
