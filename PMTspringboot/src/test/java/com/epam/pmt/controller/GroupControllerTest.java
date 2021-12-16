package com.epam.pmt.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.pmt.business.GroupService;
import com.epam.pmt.entities.Account;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	GroupService groupService;

	@Test
	void masterMenuTest() throws Exception {
		this.mockMvc.perform(get("/group/menu")).andExpect(status().isOk());
	}
	
	@Test
	void deleteGroupFormTest() throws Exception {
		this.mockMvc.perform(get("/group/deleteGroupForm")).andExpect(status().isOk());
	}
	@Test
	void deleteGroupTest() throws Exception {
		when(groupService.deleteGroup("group")).thenReturn(true);
		this.mockMvc.perform(post("/group/deleteGroup")).andExpect(status().isOk()).andExpect(view().name("group/deleteGroup"));
	}
	
	@Test
	void displayByGroupFormTest() throws Exception {
		this.mockMvc.perform(get("/group/displayByGroupForm")).andExpect(status().isOk());
	}
	
	@Test
	void displayGroupTest() throws Exception {
		List<Account> groupAccounts = null;
		when(groupService.getGroupList("group")).thenReturn(groupAccounts);
		this.mockMvc.perform(post("/group/displayByGroup")).andExpect(status().isOk()).andExpect(view().name("displayByGroup"));
	}
	
	@Test
	void updateGroupnameFormTest() throws Exception {
		this.mockMvc.perform(get("/group/updateGroupnameForm")).andExpect(status().isOk());
	}
	@Test
	void updateGroupnameTest() throws Exception {
		when(groupService.updateGroupname("group","newgroup")).thenReturn(true);
		this.mockMvc.perform(post("/group/updateGroupname")).andExpect(status().isOk()).andExpect(view().name("updateGroupname"));
	}
}
