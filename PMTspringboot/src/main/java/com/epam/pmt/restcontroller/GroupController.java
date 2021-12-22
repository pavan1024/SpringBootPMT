package com.epam.pmt.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.pmt.entities.Account;
import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/groups")
@Api("Operations to groups in pmt app")
public class GroupController {
	@Autowired
	private GroupService groupService;

	@PostMapping
	@ApiOperation(value = "View List of Group Accounts", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successfully retrieved group list") })
	public ResponseEntity<List<Account>> fetchAllGroupAccounts(String groupname) throws GroupNotFoundException {
		return new ResponseEntity<>(groupService.getGroupList(groupname), HttpStatus.OK);
	}

	@PutMapping
	@ApiOperation(value = "Update Groupname", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Groupname Updated Successfully") })
	public ResponseEntity<String> updateGroupname(String currentGroupname, String newGroupname)
			throws GroupNotFoundException {
		String status = "";
		HttpStatus statusCode = null;
		if (groupService.updateGroupname(currentGroupname, newGroupname)) {
			status = "Groupname Updated Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Groupname Not Updated";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}

	@DeleteMapping
	@ApiOperation(value = "Delete Group", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Groupname Deleted Successfully") })
	public ResponseEntity<String> deleteGroup(String groupname) throws URLNotFoundException {
		String status = "";
		HttpStatus statusCode = null;
		if (groupService.deleteGroup(groupname)) {
			status = "Group Deleted Successfully";
			statusCode = HttpStatus.ACCEPTED;
		} else {
			status = "Group Deletion Unsuccessful";
			statusCode = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status, statusCode);
	}
}
