package com.epam.pmt.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SecurityUtilTest {

	SecurityUtil securityUtil = new SecurityUtil();
	String plainText;
	String encryptedText;
	String decryptedText;

	@BeforeEach
	void setUp() {
		plainText = "password";
		encryptedText = securityUtil.encrypt(plainText);
		decryptedText = securityUtil.decrypt(encryptedText);
	}

	@Test
	void encryptTest() {
		assertEquals(encryptedText, securityUtil.encrypt(plainText));
	}

	@Test
	void decryptTest() {
		assertEquals(decryptedText, securityUtil.decrypt(encryptedText));
	}
}