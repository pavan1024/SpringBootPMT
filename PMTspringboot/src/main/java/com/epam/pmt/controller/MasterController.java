package com.epam.pmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.epam.pmt.business.MasterUserService;


@Controller
@RequestMapping("master")
public class MasterController {
	@Autowired
	MasterUserService masterUserService;
	@GetMapping("/")
	public String masterMenuForm() {
		return "mastermenu";
	}
	@GetMapping("loginForm")
	public String loginForm() {
		return "Login";
	}

	@PostMapping("account/menu")
	public ModelAndView Login(String username,String password) {
		ModelAndView mv = new ModelAndView();
		try {
		if(masterUserService.checkIfMasterExists(username) && masterUserService.login(username,password)) {
			mv.setViewName("menu");
		}
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}
	@GetMapping("registerForm")
	public String registerForm() {
		return "registerForm";
	}
	@PostMapping("register")
	public ModelAndView register(String username,String password) {
		ModelAndView mv = new ModelAndView();
		try {
			if(masterUserService.createMaster(username, password)) {
				mv.setViewName("register");
			}
		} catch (Exception ex) {
			mv.addObject("errorMessage", ex.getMessage());
			mv.setViewName("error");
		}
		return mv;
	}

	
}

