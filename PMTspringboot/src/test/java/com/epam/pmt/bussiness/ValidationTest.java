package com.epam.pmt.bussiness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.pmt.business.Validation;

public class ValidationTest {
	
	
	Validation validation = new Validation();;
	String validUrl;
	String inValidUrl;
	String validPassword;
	String inValidPassword;
	
	
	@BeforeEach
	void setUp() {
		validPassword = "Password@123";
		inValidPassword = "password122";
		
		validUrl = "https://www.gmail.com";
		inValidUrl = "gmail.com";
	}
	
	@Test
	void validUrlTest() {
		assertEquals(true,validation.isValidURL(validUrl));
		assertEquals(false,validation.isValidURL(inValidUrl));
	}
	@Test
	void inValidUrlTest() {
		assertEquals(true,validation.isValidPassword(validPassword));
		assertEquals(false,validation.isValidPassword(inValidPassword));
	}
}
