package com.epam.pmt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.entities.Master;
import com.epam.pmt.repo.MasterRepository;
import com.epam.pmt.service.MasterUserService;
import com.epam.pmt.util.MasterProvider;

@SpringBootTest
class MasterUserServiceTest {
	@Mock
	MasterRepository masterRepository;

	@Mock
	ModelMapper mapper;

	@InjectMocks
	MasterUserService masterUserService;

	Master master;
	List<Master> masterAccounts;

	@BeforeEach
	public void setUp() {
		MasterProvider.setMaster("masteruser", "Master@123");
		master = MasterProvider.getMaster();
		masterAccounts = new ArrayList<>();
		masterAccounts.add(master);
	}

	@Test
	void loginTest() {
		MasterDto master = new MasterDto();
		master.setUsername("masteruser");
		master.setPassword("Master@123");
		MasterDto master1 = new MasterDto();
		master1.setUsername("user");
		master1.setPassword("pass");
		when(masterRepository.findAll()).thenReturn(masterAccounts);
		assertTrue(masterUserService.login(master));
		assertFalse(masterUserService.login(master1));
	}

	@Test
	void registerTest() {
		MasterDto master = new MasterDto();
		master.setUsername("user");
		master.setPassword("password");
		assertTrue(masterUserService.registerAccount(master));
	}
}