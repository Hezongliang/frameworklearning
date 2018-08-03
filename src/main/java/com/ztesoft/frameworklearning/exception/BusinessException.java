package com.ztesoft.frameworklearning.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 7808536663295977429L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable e) {
		super(e);
	}

	public BusinessException(String message, Throwable e) {
		super(message, e);
	}
}
