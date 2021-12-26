package com.epam.pmt.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ValidationUtilTest {

	ValidationUtil validationUtil = new ValidationUtil();;
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
		assertEquals(true, validationUtil.isValidURL(validUrl));
		assertEquals(false, validationUtil.isValidURL(inValidUrl));
	}

	@Test
	void inValidUrlTest() {
		assertEquals(true, validationUtil.isValidPassword(validPassword));
		assertEquals(false, validationUtil.isValidPassword(inValidPassword));
	}
}