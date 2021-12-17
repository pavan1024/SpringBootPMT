package com.epam.pmt.util;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class Security {
	public String encrypt(String plainPwd) {
		String b64encoded = Base64.getEncoder().encodeToString(plainPwd.getBytes());
		String reverse = new StringBuilder(b64encoded).reverse().toString();
		StringBuilder temp = new StringBuilder();
		final int OFFSET = 4;
		for (int i = 0; i < reverse.length(); i++) {
			temp.append((char) (reverse.charAt(i) + OFFSET));
		}
		return temp.toString();
	}

	public String decrypt(String encryptedPwd) {
		StringBuilder temp = new StringBuilder();
		final int OFFSET = 4;
		for (int i = 0; i < encryptedPwd.length(); i++) {
			temp.append((char) (encryptedPwd.charAt(i) - OFFSET));
		}
		String reversed = new StringBuilder(temp.toString()).reverse().toString();
		return new String(Base64.getDecoder().decode(reversed));
	}
}
