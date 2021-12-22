package com.epam.pmt.restcontrollertest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.restcontroller.GroupController;
import com.epam.pmt.service.GroupService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(GroupController.class)
@ContextConfiguration(classes = { GroupController.class })
class GroupControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	GroupService groupService;

	AccountDto accountDto;
	Master master;
	List<Account> accounts;

	@BeforeEach
	void setUp() {
		accounts = new ArrayList<>();
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
		accountDto = new AccountDto();
		accountDto.setUrl("https://www.abcd.com");
		accountDto.setUsername("abcduser");
		accountDto.setPassword("Abcd@123");
		accountDto.setGroupname("abcd");
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
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
		MvcResult result = mockMvc.perform(post("/groups/?groupname=abcd")).andExpect(status().isOk()).andReturn();
		String urlres = result.getResponse().getContentAsString();
		Account[] accountList = this.mapFromJson(urlres, Account[].class);
		assertTrue(accountList.length == 1);
		assertEquals(accountList[0].getUrl(), "https://www.abcd.com");
	}
	
	@Test
	void updateGroupnameTest() throws Exception {
		when(groupService.updateGroupname("abcd","newabcd")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(put("/groups/?currentGroupname=abcd&newGroupname=newabcd"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();

	}
	
	@Test
	void updateGroupnameErrorTest() throws Exception {
		when(groupService.updateGroupname("abcd","newabcd")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(put("/groups/?currentgroupname=abcd&newgroupname=newabcd"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();

	}
	@Test
	void deleteGroupTest() throws Exception {		
		when(groupService.deleteGroup("abcd")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(delete("/groups?groupname=abcd"))
				.andExpect(status().isAccepted()).andReturn();
		String res = result.getResponse().getContentAsString();

	}
	@Test
	void deleteGroupErrorTest() throws Exception {
		
		when(groupService.deleteGroup("abcd")).thenReturn(true);
		MvcResult result = mockMvc
				.perform(delete("/groups?groupname=newabcd"))
				.andExpect(status().isNotFound()).andReturn();
		String res = result.getResponse().getContentAsString();

	}
	
	
}
