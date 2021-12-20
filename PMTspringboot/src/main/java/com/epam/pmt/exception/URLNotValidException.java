package com.epam.pmt.exception;

public class URLNotValidException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public URLNotValidException(String message) {
		super(message);
	}

}
