package com.ifscsearch.exception;

public class IfscException extends Exception {
	private final int status;
	private final String message;

	public IfscException(int status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
