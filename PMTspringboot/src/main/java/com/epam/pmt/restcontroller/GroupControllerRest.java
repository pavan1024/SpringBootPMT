package com.epam.pmt.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entities.Account;
import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.service.GroupService;

@RestController
@RequestMapping("/restgroups")
public class GroupControllerRest {
	@Autowired
	private GroupService groupService;

	@GetMapping
	public ResponseEntity<List<Account>> fetchAllAccounts(@RequestBody AccountDto accountDto)
			throws GroupNotFoundException {
		return new ResponseEntity<>(groupService.getGroupList(accountDto), HttpStatus.OK);
	}

	@PutMapping("/{newGroupname}")
	public ResponseEntity<String> updateGroupname(@RequestBody AccountDto accountDto, @PathVariable String newGroupname)
			throws GroupNotFoundException {
		String status = "";
		HttpStatus statusCode = null;
		if (groupService.updateGroupname(accountDto.getGroupname(), newGroupname)) {
			status = "Groupname Updated Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Groupname Not Updated";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteGroup(@RequestBody AccountDto accountDto) throws URLNotFoundException {
		String status = "";
		HttpStatus statusCode = null;
		if (groupService.deleteGroup(accountDto)) {
			status = "Group Deleted Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Group Deletion Unsuccessful";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}
}
