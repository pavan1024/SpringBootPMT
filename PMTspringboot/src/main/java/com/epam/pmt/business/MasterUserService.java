package com.epam.pmt.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.entities.Master;
import com.epam.pmt.repo.MasterRepository;

@Service
public class MasterUserService {
	@Autowired
	MasterRepository masterRepository;

	public boolean registerAccount(String username, String password) {
		boolean status = false;
			Master master = new Master();
			master.setUsername(username);
			master.setPassword(password);
			masterRepository.save(master);
			status = true;
		return status;

	}

	public boolean login(String username, String password) {
		boolean status = false;
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
