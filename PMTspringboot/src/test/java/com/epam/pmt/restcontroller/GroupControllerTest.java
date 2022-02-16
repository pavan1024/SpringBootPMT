package com.epam.pmt.restcontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.service.GroupService;

@WebMvcTest(GroupController.class)
class GroupControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	GroupService groupService;

	@Test
	void updateGroupnameTest() throws Exception {
		when(groupService.updateGroupname("abcd", "newabcd")).thenReturn(true);
		MvcResult result = mockMvc.perform(put("/groups/?currentGroupname=abcd&newGroupname=newabcd"))
				.andExpect(status().isAccepted()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Groupname Updated Successfully", response);

	}

	@Test
	void updateGroupnameErrorTest() throws Exception {
		when(groupService.updateGroupname("abcd", "newabcd")).thenReturn(true);
		MvcResult result = mockMvc.perform(put("/groups/?currentgroupname=abcd&newgroupname=newabcd"))
				.andExpect(status().isNotFound()).andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Groupname Not Updated", response);

	}

	@Test
	void deleteGroupTest() throws Exception {
		when(groupService.deleteGroup("abcd")).thenReturn(true);
		MvcResult result = mockMvc.perform(delete("/groups?groupname=abcd")).andExpect(status().isAccepted())
				.andReturn();
		String response = result.getResponse().getContentAsString();
		assertEquals("Group Deleted Successfully", response);

	}

	@Test
	void deleteGroupErrorTest() throws Exception {

		when(groupService.deleteGroup("abcd")).thenReturn(false);
		MvcResult result = mockMvc.perform(delete("/groups?groupname=abcd")).andExpect(status().isNotFound())
				.andReturn();
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
		mockMvc.perform(get("/groups/?groupname=abcd")).andExpect(status().isOk()).andReturn();
	}

}
