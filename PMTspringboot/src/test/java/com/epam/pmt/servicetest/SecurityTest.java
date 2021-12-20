package com.epam.pmt.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.pmt.util.Security;

class SecurityTest {

	Security security = new Security();
	String plainText;
	String encryptedText;
	String decryptedText;

	@BeforeEach
	void setUp() {
		plainText = "password";
		encryptedText = security.encrypt(plainText);
		decryptedText = security.decrypt(encryptedText);
	}

	@Test
	void encryptTest() {
		assertEquals(encryptedText, security.encrypt(plainText));
	}

	@Test
	void decryptTest() {
		assertEquals(decryptedText, security.decrypt(encryptedText));
	}
}
