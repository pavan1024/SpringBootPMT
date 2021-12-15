package com.epam.pmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.pmt.business.AccountService;
import com.epam.pmt.entities.Account;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	AccountService accountService;
	String errormsg = "errorMessage";
	String error = "error";

	@GetMapping("createAccountForm")
	public String createAccountForm() {
		return "createAccountForm";
	}

	@GetMapping("menu")
	public String menu() {
		return "menu";
	}

	@PostMapping("createAccount")
	public ModelAndView accountCreation(Account account) {
		ModelAndView mv = new ModelAndView();
		try {
			boolean status = accountService.createAccount(account.getUrl(), account.getUsername(),
					account.getPassword(), account.getGroupname());
			if (status) {
				mv.setViewName("createAccount");
			}
		} catch (Exception ex) {
			mv.addObject(errormsg, ex.getMessage());
			mv.setViewName(error);
		}
		return mv;
	}

	@GetMapping("displayPasswordForm")
	public String displayPasswordForm() {
		return "displayPasswordForm";
	}

	@PostMapping("displayPassword")
	public ModelAndView displayPassword(Account account) {
		ModelAndView mv = new ModelAndView();
		try {
			String password = accountService.readPassword(account.getUrl());
			if (password != null) {
				mv.addObject("password", password);
				mv.setViewName("displayPassword");
			}
		} catch (Exception ex) {
			mv.addObject(errormsg, ex.getMessage());
			mv.setViewName(error);
		}
		return mv;
	}

	@GetMapping("deleteAccountForm")
	public String deleteAccountForm() {
		return "deleteAccountForm";
	}

	@PostMapping("deleteAccount")
	public ModelAndView deleteAccount(Account account) {
		ModelAndView mv = new ModelAndView();
		try {
			if (accountService.deleteAccount(account.getUrl())) {
				mv.setViewName("deleteAccount");
			}
		} catch (Exception ex) {
			mv.addObject(errormsg, ex.getMessage());
			mv.setViewName(error);
		}
		return mv;
	}

	@GetMapping("viewAll")
	public ModelAndView viewAll() {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("viewAll");
			mv.addObject("accounts", accountService.getAll());

		} catch (Exception ex) {
			mv.addObject(errormsg, ex.getMessage());
			mv.setViewName(error);
		}
		return mv;
	}

	@GetMapping("submenu")
	public String subMenum() {
		return "submenu";
	}

	@GetMapping("updateAccountUsernameForm")
	public String updateAccountUsernameForm() {
		return "updateAccountUsernameForm";
	}

	@PostMapping("updateAccountUsername")
	public ModelAndView updateAccountUsername(Account account) {
		ModelAndView mv = new ModelAndView();
		try {
			if (accountService.checkUrl(account.getUrl())
					&& accountService.updateUsername(account.getUrl(), account.getUsername())) {
				mv.setViewName("updateAccountUsername");
			}
		} catch (Exception ex) {
			mv.addObject(errormsg, ex.getMessage());
			mv.setViewName(error);
		}
		return mv;
	}

	@GetMapping("updateAccountPasswordForm")
	public String updateAccountPasswordForm() {
		return "updateAccountPasswordForm";
	}

	@PostMapping("updateAccountPassword")
	public ModelAndView updateAccountPassword(Account account) {
		ModelAndView mv = new ModelAndView();
		try {
			if (accountService.checkUrl(account.getUrl())
					&& accountService.updatePassword(account.getUrl(), account.getPassword())) {
				mv.setViewName("updateAccountPassword");
			}
		} catch (Exception ex) {
			mv.addObject(errormsg, ex.getMessage());
			mv.setViewName(error);
		}
		return mv;
	}

}
