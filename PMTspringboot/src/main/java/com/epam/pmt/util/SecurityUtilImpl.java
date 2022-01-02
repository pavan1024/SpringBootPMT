package com.epam.pmt.util;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtilImpl implements SecurityUtil {
	@Override
	public String encrypt(String plainText) {
		String b64encoded = Base64.getEncoder().encodeToString(plainText.getBytes());
		String reverse = new StringBuilder(b64encoded).reverse().toString();
		StringBuilder temp = new StringBuilder();
		final int OFFSET = 4;
		for (int i = 0; i < reverse.length(); i++) {
			temp.append((char) (reverse.charAt(i) + OFFSET));
		}
		return temp.toString();
	}

	@Override
	public String decrypt(String encryptedText) {
		StringBuilder temp = new StringBuilder();
		final int OFFSET = 4;
		for (int i = 0; i < encryptedText.length(); i++) {
			temp.append((char) (encryptedText.charAt(i) - OFFSET));
		}
		String reversed = new StringBuilder(temp.toString()).reverse().toString();
		return new String(Base64.getDecoder().decode(reversed));
	}
}