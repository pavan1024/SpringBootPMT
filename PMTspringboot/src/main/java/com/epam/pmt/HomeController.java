package com.epam.pmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Value("${message}")
	String msg;
	
	@RequestMapping("homepage")
	public String goHome() {
		logger.debug(msg);
		return "home";
		
	}
}
