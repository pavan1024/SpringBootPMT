package com.epam.pmt.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.service.GroupService;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	GroupService groupService;

	@MockBean
	GroupNotFoundException ex;

	AccountDto accountDto;
	AccountDto invalidAccountDto;

	@BeforeEach
	void setUp() {
		accountDto = new AccountDto();
		accountDto.setGroupname("group");

		invalidAccountDto = new AccountDto();
		invalidAccountDto.setGroupname("groupname");
	}

	@Test
	void masterMenuTest() throws Exception {
		mockMvc.perform(get("/group/menu")).andExpect(view().name("menu")).andExpect(status().isOk());
	}

	@Test
	void deleteGroupFormTest() throws Exception {
		mockMvc.perform(get("/group/deleteGroupForm")).andExpect(view().name("deleteGroupForm"))
				.andExpect(status().isOk());
	}

	@Test
	void deleteGroupTest() throws Exception {
		when(groupService.deleteGroup(accountDto)).thenReturn(true);
		mockMvc.perform(post("/group/deleteGroup")).andExpect(view().name("group/deleteGroup"))
				.andExpect(status().isOk());
	}

	@Test
	void displayByGroupFormTest() throws Exception {
		mockMvc.perform(get("/group/displayByGroupForm")).andExpect(view().name("displayByGroupForm"))
				.andExpect(status().isOk());
	}

	@Test
	void displayGroupTest() throws Exception {
		List<Account> groupAccounts = new ArrayList<>();
		when(groupService.getGroupList(accountDto)).thenReturn(groupAccounts);
		mockMvc.perform(post("/group/displayByGroup")).andExpect(model().size(2))
				.andExpect(model().attribute("accounts", groupAccounts)).andExpect(view().name("displayByGroup"))
				.andExpect(status().isOk());
	}

	@Test
	void updateGroupnameFormTest() throws Exception {
		mockMvc.perform(get("/group/updateGroupnameForm")).andExpect(view().name("updateGroupnameForm"))
				.andExpect(status().isOk());
	}

	@Test
	void updateGroupnameTest() throws Exception {
		when(groupService.updateGroupname("group", "newgroup")).thenReturn(true);
		mockMvc.perform(post("/group/updateGroupname")).andExpect(view().name("updateGroupname"))
				.andExpect(status().isOk());
	}

//private static final Throwable GroupNotFoundException = new GroupNotFoundException("Group Not Found");

//	@Test
//	void displayGroupErrorTest() throws Exception {
////		List<Account> groupAccounts = new ArrayList<>();
//		when(groupService.getGroupList(accountDto)).thenThrow(GroupNotFoundException);
//			mockMvc.perform(post("/group/displayByGroup"))
//			.andExpect(model().size(1))
//			.andExpect(model().attribute("errorMessage", ex.getMessage()))
//            .andExpect(view().name("error"))
//            .andExpect(status().isOk());
////		GroupNotFoundException ex = new GroupNotFoundException(null);
//		
////		this.mockMvc.perform(post("/group/displayByGroup")).andExpect(status().isOk()).andExpect(view().name("displayByGroup"));
//	}
}
