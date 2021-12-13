package com.epam.pmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.pmt.business.AccountService;
import com.epam.pmt.business.MasterUserService;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;


@Controller
@RequestMapping("master")
public class MasterController {
	@Autowired
	MasterUserService masterUserService;
	@GetMapping("/")
	public String masterMenuForm() {
		return "mastermenu";
	}
	@GetMapping("LoginForm")
	public String loginForm() {
		return "Login";
	}
	@PostMapping("Register")
	public String registerForm() {
		return "register";
	}
	@PostMapping("account/menu")
	public ModelAndView Login(Master master) {
		ModelAndView mv = new ModelAndView();
		try {
		if(masterUserService.checkIfMasterExists(master.getUsername()) && masterUserService.login(master.getUsername(), master.getPassword())) {
			mv.setViewName("menu");
		}
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}


	
}

