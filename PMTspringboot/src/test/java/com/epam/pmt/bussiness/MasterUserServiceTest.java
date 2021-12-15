package com.epam.pmt.bussiness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.business.MasterProvider;
import com.epam.pmt.business.MasterUserService;
import com.epam.pmt.entities.Master;
import com.epam.pmt.repo.MasterRepository;

@SpringBootTest
class MasterUserServiceTest {
	@Mock
	MasterRepository masterRepository;

	@InjectMocks
	MasterUserService masterUserService;

	Master master;
	Master master1 = null;
	List<Master> masterAccounts;
	List<Master> emptyAccounts = null;

	@BeforeEach
	public void setUp() {
		MasterProvider.setMaster("masteruser", "Master@123");
		master = MasterProvider.getMaster();
		masterAccounts = new ArrayList<>();
		masterAccounts.add(master);
	}

	@Test
	void loginTest() {
		when(masterRepository.findAll()).thenReturn(masterAccounts);
		assertTrue(masterUserService.login("masteruser", "Master@123"));
		assertFalse(masterUserService.login("masteruser", "Master3"));
	}

	@Test
	void registerTest() {
		assertTrue(masterUserService.registerAccount("masteruser", "Master@123"));
	}
}
