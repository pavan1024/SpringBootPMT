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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;



@RestController
@RequestMapping("/restaccounts")
@Api("Operations to accounts in pmt app")
public class AccountControllerRest {

	@Autowired
	private AccountService accountService;

	@GetMapping
	@ApiOperation(value = "View List of Accounts",response = List.class)
	@ApiResponses(value = {@ApiResponse(code = 200,message = "successfully retrieved list") } )
	public ResponseEntity<List<Account>> fetchAllAccounts() {
		return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Add Account",response = String.class)
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Account Added Successfully") } )
	public ResponseEntity<String> addAccount(@RequestBody AccountDto accountDto)
			throws URLNotValidException, PasswordNotValidException {
		String status = "";
		HttpStatus statusCode = null;
		if (accountService.createAccount(accountDto)) {
			status = "Account Added Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Account Creation Unsuccessful";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}

	@PostMapping("/readpassword")
	@ApiOperation(value = "View Password",response = String.class)
	@ApiResponses(value = {@ApiResponse(code = 200,message = "successfully retrieved password") } )
	public ResponseEntity<String> readPassword(@RequestBody AccountDto accountDto) throws URLNotFoundException {
		String password = "";
		HttpStatus statusCode = null;
		if (accountService.checkUrl(accountDto.getUrl())) {
			password = accountService.readPassword(accountDto);
			statusCode = HttpStatus.ACCEPTED;
		} else {
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(password, statusCode);
	}

	@PutMapping("/updateusername")
	@ApiOperation(value = "Update Username",response = String.class)
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Account Username Updated Successfully") } )
	public ResponseEntity<String> updateUsername(@RequestBody AccountDto accountDto)
			throws URLNotFoundException, PasswordNotValidException {
		String status = "";
		HttpStatus statusCode = null;
		if (accountService.updateUsername(accountDto)) {
			status = "Account Username Updated Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Account Username Not Updated";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}

	@PutMapping("/updatepassword")
	@ApiOperation(value = "Update Password",response = String.class)
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Account Password Updated Successfully") } )
	public ResponseEntity<String> updatePassword(@RequestBody AccountDto accountDto)
			throws URLNotFoundException, PasswordNotValidException {
		String status = "";
		HttpStatus statusCode = null;
		if (accountService.updatePassword(accountDto)) {
			status = "Account Password Updated Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Account Password Not Updated";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}

	@DeleteMapping
	@ApiOperation(value = "Delete Account",response = String.class)
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Account Deleted Successfully") } )
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