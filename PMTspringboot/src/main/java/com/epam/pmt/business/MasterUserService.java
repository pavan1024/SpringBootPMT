package com.epam.pmt.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.repo.MasterRepository;

@Service
public class MasterUserService {
	@Autowired
	MasterRepository masterRepository;
	@Autowired
	ModelMapper mapper;

	public boolean registerAccount(MasterDto masterDto) {
		Master master = new Master();
		master.setUsername(masterDto.getUsername());
		master.setPassword(masterDto.getPassword());
		masterRepository.save(master);
		return true;

	}

	public boolean login(MasterDto masterDto) {
		boolean status = false;
		String username = masterDto.getUsername();
		String password = masterDto.getPassword();
		List<Master> masterAccounts = ((Collection<Master>) masterRepository.findAll()).stream()
				.filter(i -> i.getUsername().equals(username)).collect(Collectors.toList());
		try {
			if (masterAccounts.get(0).getPassword().equals(password)) {
				MasterProvider.setMaster(username, password);
				status = true;
			}
		} catch (IndexOutOfBoundsException e) {
			e.getStackTrace();
		}
		return status;
	}

}
