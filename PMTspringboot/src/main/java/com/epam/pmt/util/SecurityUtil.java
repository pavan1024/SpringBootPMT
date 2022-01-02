package com.epam.pmt.util;

public interface SecurityUtil {
	public String encrypt(String plainText);

	public String decrypt(String encryptedText);
}
