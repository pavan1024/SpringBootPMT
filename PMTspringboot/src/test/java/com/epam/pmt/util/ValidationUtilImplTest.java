package com.epam.pmt.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ValidationUtilImplTest {

	ValidationUtilImpl validationUtilImpl = new ValidationUtilImpl();;
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
		assertEquals(true, validationUtilImpl.isValidURL(validUrl));
		assertEquals(false, validationUtilImpl.isValidURL(inValidUrl));
	}

	@Test
	void inValidUrlTest() {
		assertEquals(true, validationUtilImpl.isValidPassword(validPassword));
		assertEquals(false, validationUtilImpl.isValidPassword(inValidPassword));
	}
}
