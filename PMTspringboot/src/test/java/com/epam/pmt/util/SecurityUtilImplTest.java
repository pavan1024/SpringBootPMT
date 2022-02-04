package com.epam.pmt.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SecurityUtilImplTest {

	SecurityUtilImpl securityUtilImpl = new SecurityUtilImpl();
	String plainText;
	String encryptedText;
	String decryptedText;

	@BeforeEach
	void setUp() {
		plainText = "password";
		encryptedText = securityUtilImpl.encrypt(plainText);
		decryptedText = securityUtilImpl.decrypt(encryptedText);
	}

	@Test
	void encryptTest() {
		assertEquals(encryptedText, securityUtilImpl.encrypt(plainText));
	}

	@Test
	void decryptTest() {
		assertEquals(decryptedText, securityUtilImpl.decrypt(encryptedText));
	}
}
