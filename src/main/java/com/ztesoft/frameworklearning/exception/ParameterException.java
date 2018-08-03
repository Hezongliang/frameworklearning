package com.ztesoft.frameworklearning.exception;

public class ParameterException extends RuntimeException {
	private static final long serialVersionUID = 7808536663295977429L;

	public ParameterException() {
	}

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException(Throwable e) {
		super(e);
	}

	public ParameterException(String message, Throwable e) {
		super(message, e);
	}
}
