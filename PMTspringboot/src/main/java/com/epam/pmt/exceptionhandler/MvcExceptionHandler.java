package com.epam.pmt.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.epam.pmt.exception.GroupNotFoundException;
import com.epam.pmt.exception.PasswordNotValidException;
import com.epam.pmt.exception.URLNotFoundException;
import com.epam.pmt.exception.URLNotValidException;
import com.epam.pmt.exception.UserNotFoundException;

@ControllerAdvice("com.epam.pmt.mvccontroller")
public class MvcExceptionHandler {
	String errorMessage = "errorMessage";
	String error = "error";

	@ExceptionHandler
	public ModelAndView defaultErrorHandler(URLNotFoundException ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(errorMessage, ex.getMessage());
		mv.setViewName(error);
		return mv;
	}
	@ExceptionHandler
	public ModelAndView defaultErrorHandler(PasswordNotValidException ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(errorMessage, ex.getMessage());
		mv.setViewName(error);
		return mv;
	}
	@ExceptionHandler
	public ModelAndView defaultErrorHandler(URLNotValidException ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(errorMessage, ex.getMessage());
		mv.setViewName(error);
		return mv;
	}
	@ExceptionHandler
	public ModelAndView defaultErrorHandler(UserNotFoundException ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(errorMessage, ex.getMessage());
		mv.setViewName(error);
		return mv;
	}
	@ExceptionHandler
	public ModelAndView defaultErrorHandler(GroupNotFoundException ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(errorMessage, ex.getMessage());
		mv.setViewName(error);
		return mv;
	}

}