package com.ztesoft.frameworklearning.user.service.interfaces;

import com.ztesoft.frameworklearning.user.model.User;

public interface IUserService {

	public User getUser(String userId);

	public User getUser(String userId, String password);

	public void setUserOnline(String userId);

	public void setUserOffline(String userId);
}
