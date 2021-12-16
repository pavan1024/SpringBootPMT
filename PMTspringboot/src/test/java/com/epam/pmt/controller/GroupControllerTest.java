package com.epam.pmt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void masterMenuTest() throws Exception {
		this.mockMvc.perform(get("/group/menu")).andExpect(status().isOk());
	}
	
	@Test
	void deleteGroupFormTest() throws Exception {
		this.mockMvc.perform(get("/group/deleteGroupForm")).andExpect(status().isOk());
	}
	
	@Test
	void displayByGroupFormTest() throws Exception {
		this.mockMvc.perform(get("/group/displayByGroupForm")).andExpect(status().isOk());
	}
	
	@Test
	void updateGroupnameFormTest() throws Exception {
		this.mockMvc.perform(get("/group/updateGroupnameForm")).andExpect(status().isOk());
	}
}
