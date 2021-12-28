package com.epam.pmt.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.pmt.dto.MasterDto;
import com.epam.pmt.exception.UserNotFoundException;
import com.epam.pmt.service.MasterUserService;

@Controller
@RequestMapping("master")
public class MasterMvcController {
	@Autowired
	MasterUserService masterUserService;

	@GetMapping("/")
	public String masterMenuForm() {
		return "mastermenu";
	}

	@GetMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@PostMapping("login")
	public ModelAndView login(MasterDto masterDto) throws UserNotFoundException {
		ModelAndView mv = new ModelAndView();
		if (masterUserService.login(masterDto)) {
			mv.setViewName("menu");
		}
		return mv;
	}

	@GetMapping("registerForm")
	public String registerForm() {
		return "registerForm";
	}

	@PostMapping("register")
	public ModelAndView register(MasterDto masterDto) {
		ModelAndView mv = new ModelAndView();
		if (masterUserService.registerAccount(masterDto)) {
			mv.setViewName("register");
		}
		return mv;
	}

} 