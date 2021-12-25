package com.epam.pmt.mvccontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.pmt.entities.Account;
import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.service.GroupService;

@Controller
@RequestMapping("group")
public class GroupMvcController {
	@Autowired
	GroupService groupService;
	String errormsg = "errorMessage";
	String error = "error";

	@GetMapping("menu")
	public String gmenu() {
		return "menu";
	}

	@GetMapping("deleteGroupForm")
	public String deleteGroupForm() {
		return "deleteGroupForm";
	}

	@PostMapping("deleteGroup")
	public ModelAndView deleteGroup(String groupname) throws GroupNotFoundException {
		ModelAndView mv = new ModelAndView();
		if (groupService.deleteGroup(groupname)) {
			mv.setViewName("deleteGroup");
		}
		return mv;
	}

	@GetMapping("displayByGroupForm")
	public String displayByGroupForm() {
		return "displayByGroupForm";
	}

	@PostMapping("displayByGroup")
	public ModelAndView displaybyGroup(String groupname) throws GroupNotFoundException {
		ModelAndView mv = new ModelAndView();
		List<Account> groupAccounts = groupService.getGroupList(groupname);
		mv.addObject("accounts", groupAccounts);
		mv.setViewName("displayByGroup");
		return mv;
	}

	@GetMapping("updateGroupnameForm")
	public String updateGroupnameForm() {
		return "updateGroupnameForm";
	}

	@PostMapping("updateGroupname")
	public ModelAndView displaybyGroup(String currentGroupname, String newGroupname) throws GroupNotFoundException {
		ModelAndView mv = new ModelAndView();
		groupService.updateGroupname(currentGroupname, newGroupname);
		mv.setViewName("updateGroupname");
		return mv;
	}

}
