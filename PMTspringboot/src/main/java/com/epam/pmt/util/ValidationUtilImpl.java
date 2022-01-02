package com.epam.pmt.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtilImpl implements ValidationUtil {
	@Override
	public boolean isValidPassword(String password) {
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	@Override
	public boolean isValidURL(String url) {
		try {
			new URL(url).toURI();
			return true;

		} catch (MalformedURLException | URISyntaxException e) {
			return false;
		}
	}
}
