package com.epam.pmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.exception.UserNotFoundException;
import com.epam.pmt.service.MasterUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/master")
@Api("Operations to Master in pmt app")
public class MasterController {
	@Autowired
	private MasterUserService masterService;

	@PostMapping("/login")
	@ApiOperation(value = "Login", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Login Successful") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@ApiOperation(value = "Register", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Account Registered Successfully") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> register(@RequestBody MasterDto masterDto) {
		String status = "";
		HttpStatus statusCode = null;
		if (masterService.registerAccount(masterDto)) {
			status = "Account Registered Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Account Not Registered";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);

	}

}