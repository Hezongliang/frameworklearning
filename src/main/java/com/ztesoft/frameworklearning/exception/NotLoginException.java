package com.ztesoft.frameworklearning.exception;

public class NotLoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2760035741566195396L;

	public NotLoginException(String errorMsg)
    {
        super(errorMsg);
    }
}
