package com.epam.pmt.exception;

public class PasswordNotValidException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PasswordNotValidException(String message) {
		super(message);
	}
}
