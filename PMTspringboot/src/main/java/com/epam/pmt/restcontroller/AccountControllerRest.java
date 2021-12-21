package com.epam.pmt.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.exception.PasswordNotValidException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.exception.URLNotValidException;
import com.epam.pmt.service.AccountService;

@RestController
@RequestMapping("/restaccounts")
public class AccountControllerRest {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public ResponseEntity<List<Account>> fetchAllAccounts() {
		return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addAccount(@RequestBody AccountDto accountDto)
			throws URLNotValidException, PasswordNotValidException {
		String status = "";
		HttpStatus statusCode = null;
		if (accountService.createAccount(accountDto)) {
			status = "Account Created Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Account Creation Unsuccessful";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}

	@PutMapping
	public ResponseEntity<String> update(@RequestBody AccountDto accountDto)
			throws URLNotFoundException, PasswordNotValidException {
		String status = "";
		HttpStatus statusCode = HttpStatus.NOT_FOUND;

		if (accountDto.getUsername() != null) {
			if (accountService.updateUsername(accountDto)) {
				status = "Account Username Updated Successfully";
				statusCode = HttpStatus.ACCEPTED;
			} else {
				status = "Account Username Not Updated";
				statusCode = HttpStatus.NOT_FOUND;
			}

		} else if (accountDto.getPassword() != null) {
			if (accountService.updatePassword(accountDto)) {
				status = "Account Password Updated Successfully";
				statusCode = HttpStatus.ACCEPTED;
			} else {
				status = "Account Password Not Updated";
				statusCode = HttpStatus.NOT_FOUND;
			}
		}

		return new ResponseEntity<>(status, statusCode);
	}

	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody AccountDto accountDto) throws URLNotFoundException {
		String status = "";
		HttpStatus statusCode = null;
		if (accountService.deleteAccount(accountDto)) {
			status = "Account Deleted Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Account Deletion Unsuccessful";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}

}