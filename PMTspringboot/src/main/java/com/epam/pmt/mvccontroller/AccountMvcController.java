package com.epam.pmt.mvccontroller;

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
public class AccountMvcController {
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
	public ModelAndView addAccount(AccountDto accountDto) throws URLNotValidException, PasswordNotValidException {
		ModelAndView mv = new ModelAndView();
		if (accountService.createAccount(accountDto)) {
			mv.setViewName("addAccount");
		}
		return mv;
	}

	@GetMapping("displayPasswordForm")
	public String displayPasswordForm() {
		return "displayPasswordForm";
	}

	@PostMapping("displayPassword")
	public ModelAndView displayPassword(String url) throws URLNotFoundException {
		ModelAndView mv = new ModelAndView();
		String password = accountService.readPassword(url);
		if (!password.equals("")) {
			mv.addObject("password", password);
			mv.setViewName("displayPassword");
		}
		return mv;
	}

	@GetMapping("deleteAccountForm")
	public String deleteAccountForm() {
		return "deleteAccountForm";
	}

	@PostMapping("deleteAccount")
	public ModelAndView deleteAccount(String url) throws URLNotFoundException {
		ModelAndView mv = new ModelAndView();
		if (accountService.deleteAccount(url)) {
			mv.setViewName("deleteAccount");
		}
		return mv;
	}

	@GetMapping("viewAll")
	public ModelAndView viewAll() {
		ModelAndView mv = new ModelAndView();
			mv.setViewName("viewAll");
			mv.addObject("accounts", accountService.getAll());
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
	public ModelAndView updateAccountUsername(String url, String newUsername) throws URLNotFoundException {
		ModelAndView mv = new ModelAndView();
		if (accountService.updateUsername(url, newUsername)) {
			mv.setViewName("updateAccountUsername");
		}
		return mv;
	}

	@GetMapping("updateAccountPasswordForm")
	public String updateAccountPasswordForm() {
		return "updateAccountPasswordForm";
	}

	@PostMapping("updateAccountPassword")
	public ModelAndView updateAccountPassword(String url, String newPassword)
			throws URLNotFoundException, PasswordNotValidException {
		ModelAndView mv = new ModelAndView();
		if (accountService.updatePassword(url, newPassword)) {
			mv.setViewName("updateAccountPassword");
		}
		return mv;
	}

} 