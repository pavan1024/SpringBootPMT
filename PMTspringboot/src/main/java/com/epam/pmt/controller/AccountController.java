package com.epam.pmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.exception.PasswordNotValidException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.exception.URLNotValidException;
import com.epam.pmt.service.AccountService;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	AccountService accountService;
	String errormsg = "errorMessage";
	String error = "error";

	@GetMapping("addAccountForm")
	public String addAccountForm() {
		return "addAccountForm";
	}

	@GetMapping("menu")
	public String menu() {
		return "menu";
	}

	@PostMapping("addAccount")
	public ModelAndView addAccount(AccountDto accountDto) {
		ModelAndView mv = new ModelAndView();
		try {
			boolean status = accountService.createAccount(accountDto);
			if (status) {
				mv.setViewName("addAccount");
			}
		} catch (URLNotValidException | PasswordNotValidException ex) {
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
	public ModelAndView displayPassword(AccountDto accountDto) {
		ModelAndView mv = new ModelAndView();
		try {
			String password = accountService.readPassword(accountDto);
			if (!password.equals("")) {
				mv.addObject("password", password);
				mv.setViewName("displayPassword");
			}
		} catch (URLNotFoundException ex) {
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
	public ModelAndView deleteAccount(AccountDto accountDto) {
		ModelAndView mv = new ModelAndView();
		try {
			if (accountService.deleteAccount(accountDto)) {
				mv.setViewName("deleteAccount");
			}
		} catch (URLNotFoundException ex) {
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
	public ModelAndView updateAccountUsername(AccountDto accountDto) {
		ModelAndView mv = new ModelAndView();
		try {
			if (accountService.updateUsername(accountDto)) {
				mv.setViewName("updateAccountUsername");
			}
		} catch (URLNotFoundException ex) {
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
	public ModelAndView updateAccountPassword(AccountDto accountDto) {
		ModelAndView mv = new ModelAndView();
		try {
			if (accountService.updatePassword(accountDto) ) {
				mv.setViewName("updateAccountPassword");
			}
		} catch (URLNotFoundException | PasswordNotValidException ex) {
			mv.addObject(errormsg, ex.getMessage());
			mv.setViewName(error);
		}
		return mv;
	}

}
