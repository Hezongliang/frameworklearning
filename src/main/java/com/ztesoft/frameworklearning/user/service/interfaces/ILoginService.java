package com.ztesoft.frameworklearning.user.service.interfaces;

public interface ILoginService {

	/** 登录
	 * @param userId
	 * @param password
	 * @throws Exception
	 */
	public void login(String userId, String password) throws Exception;
}
