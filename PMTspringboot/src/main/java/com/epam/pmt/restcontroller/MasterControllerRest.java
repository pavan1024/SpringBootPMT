package com.epam.pmt.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.exception.UserNotFoundException;
import com.epam.pmt.service.MasterUserService;

@RestController
@RequestMapping("/masterusers")
public class MasterControllerRest {
	@Autowired
	private MasterUserService masterService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody MasterDto masterDto) throws UserNotFoundException {
		String status = "";
		HttpStatus statusCode = null;
		if (masterService.login(masterDto)) {
			status = "Login Successful";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Login Unsuccessful";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);

	}

	@PostMapping("/register")
	public ResponseEntity<String> register(MasterDto masterDto) {
		masterDto.setUsername("master111");
		masterDto.setPassword("Master@123");
		String status = "";
		HttpStatus statusCode = null;
		if(masterService.registerAccount(masterDto)) {
			status = "Account Registered Successfully";
			statusCode = HttpStatus.ACCEPTED;
		}else {
			status = "Account Not Registered";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status,statusCode);
		
		
	}

}