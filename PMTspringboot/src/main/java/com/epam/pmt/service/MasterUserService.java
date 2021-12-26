package com.epam.pmt.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.entities.Master;
import com.epam.pmt.exception.UserNotFoundException;
import com.epam.pmt.repo.MasterRepository;
import com.epam.pmt.util.MasterProvider;

@Service
public class MasterUserService {
	@Autowired
	MasterRepository masterRepository;
	@Autowired
	ModelMapper mapper;

	public boolean registerAccount(MasterDto masterDto) {
		boolean status = false;
		Master master = mapper.map(masterDto, Master.class);
		masterRepository.save(master);
		status = true;
		return status;

	}

	public boolean login(MasterDto masterDto) throws UserNotFoundException {
		boolean status = false;
		List<Master> masterAccounts = ((Collection<Master>) masterRepository.findAll()).stream()
				.filter(i -> i.getUsername().equals(masterDto.getUsername())).collect(Collectors.toList());
		try {
			if (masterAccounts.get(0).getPassword().equals(masterDto.getPassword())) {
				MasterProvider.setMaster(masterDto.getUsername(), masterDto.getPassword());
				status = true;
			} else {
				throw new UserNotFoundException("Invalid Username or Password ");
			}
		} catch (IndexOutOfBoundsException e) {
			e.getStackTrace();
		}
		return status;
	}

}