package com.epam.pmt.exception;

public class GroupNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GroupNotFoundException(String message) {
		super(message);
	}

}
