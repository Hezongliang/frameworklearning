package com.ztesoft.frameworklearning.base;

import java.io.Serializable;

//所有的ajax请求的返回类型封装JSON结果
public class ResponseResult implements Serializable {
	private static final long serialVersionUID = -487416541944578635L;

	private Object data;
	private String errorMessage;
	private boolean hasError;

	public static ResponseResult success(Object data) {
		ResponseResult result = new ResponseResult();
		result.data = data;
		result.errorMessage = null;
		result.hasError = false;
		return result;
	}

	public static ResponseResult success() {
		ResponseResult result = new ResponseResult();
		result.data = null;
		result.errorMessage = null;
		result.hasError = false;
		return result;
	}

	public static ResponseResult failure(String errorMessage) {
		ResponseResult result = new ResponseResult();
		result.data = null;
		result.errorMessage = errorMessage;
		result.hasError = true;
		return result;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean getHasError() {
		return this.hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
}
