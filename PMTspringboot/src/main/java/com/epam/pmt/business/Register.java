package com.epam.pmt.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Register {
	@Autowired
	MasterUserService masterUserService;
	@Autowired
	Validation validation;

	public boolean registerAccount(String username, String password) {
		boolean status = false;
		if (validation.isValidPassword(password)) {
			masterUserService.createMaster(username, password);

			status = true;
		}
		return status;
	}

}
