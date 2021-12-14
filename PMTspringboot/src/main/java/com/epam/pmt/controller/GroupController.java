package com.epam.pmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.epam.pmt.business.GroupService;
import com.epam.pmt.entities.Account;

@Controller
@RequestMapping("account")
public class GroupController {
	@Autowired
	GroupService groupService;

	@GetMapping("deleteGroupForm")
	public String deleteGroupForm() {
		return "deleteGroupForm";
	}

	@PostMapping("deleteGroup")
	public ModelAndView deleteGroup(Account account) {
		ModelAndView mv = new ModelAndView();
		try {
			if (groupService.deleteGroup(account.getGroupname())) {
				mv.setViewName("deleteGroup");
			}
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}

	@GetMapping("displayByGroupForm")
	public String displayByGroupForm() {
		return "displayByGroupForm";
	}

	@PostMapping("displayByGroup")
	public ModelAndView displaybyGroup(Account account) {
		ModelAndView mv = new ModelAndView();
		try {
			List<Account> groupAccounts = groupService.groupDetails(account.getGroupname());
			mv.addObject("accounts", groupAccounts);
			mv.setViewName("displayByGroup");

		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}

	@GetMapping("updateGroupnameForm")
	public String updateGroupnameForm() {
		return "updateGroupnameForm";
	}

	@PostMapping("updateGroupname")
	public ModelAndView displaybyGroup(String currentGroupname, String newGroupname) {
		ModelAndView mv = new ModelAndView();
		try {
			groupService.updateGroupname(currentGroupname, newGroupname);
			mv.setViewName("updateGroupname");

		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}

}
