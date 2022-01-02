package com.epam.pmt.exceptionhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.exception.PasswordNotValidException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.exception.URLNotValidException;
import com.epam.pmt.exception.UserNotFoundException;

@RestControllerAdvice(value = "")
public class RestExceptionHandler {
	String accountService = "accountService";
	String accounts = "accounts";
	String timestamp = "timestamp";
	String error = "error";
	String status = "status";

	@ExceptionHandler(value = URLNotValidException.class)
	public Map<String, String> handleURLNotValidException(URLNotValidException ex) {
		Map<String, String> response = new HashMap<>();
		response.put(accountService, accountService);
		response.put(timestamp, new Date().toString());
		response.put(error, ex.getMessage());
		response.put(status, HttpStatus.NOT_FOUND.name());
		return response;
	}

	@ExceptionHandler(value = PasswordNotValidException.class)
	public Map<String, String> handlePasswordNotValidException(PasswordNotValidException ex) {
		Map<String, String> response = new HashMap<>();
		response.put(accountService, accounts);
		response.put(timestamp, new Date().toString());
		response.put(error, ex.getMessage());
		response.put(status, HttpStatus.NOT_FOUND.name());
		return response;
	}

	@ExceptionHandler(value = URLNotFoundException.class)
	public Map<String, String> handleURLNotFoundException(URLNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
		response.put(accountService, accounts);
		response.put(timestamp, new Date().toString());
		response.put(error, ex.getMessage());
		response.put(status, HttpStatus.NOT_FOUND.name());
		return response;
	}

	@ExceptionHandler(value = GroupNotFoundException.class)
	public Map<String, String> handleGroupNotFoundException(GroupNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
		response.put(accountService, accounts);
		response.put(timestamp, new Date().toString());
		response.put(error, ex.getMessage());
		response.put(status, HttpStatus.NOT_FOUND.name());
		return response;
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("masterService", "masteraccounts");
		response.put(timestamp, new Date().toString());
		response.put(error, ex.getMessage());
		response.put(status, HttpStatus.NOT_FOUND.name());
		return response;
	}

}
