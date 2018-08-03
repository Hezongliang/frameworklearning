package com.ztesoft.frameworklearning.user.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ztesoft.frameworklearning.base.BaseService;
import com.ztesoft.frameworklearning.user.constants.UserStatusConstants;
import com.ztesoft.frameworklearning.user.mapper.UserMapper;
import com.ztesoft.frameworklearning.user.model.User;
import com.ztesoft.frameworklearning.user.service.interfaces.IUserService;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(readOnly = true)
	public User getUser(String userId) {
		return userMapper.getUserWithId(userId, null, null, null);
	}

	@Override
	@Transactional(readOnly = true)
	public User getUser(String userId, String userPassword) {
		return userMapper.getUserWithId(userId, userPassword, null, null);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void setUserOnline(String userId) {
		userMapper.update(userId, null, UserStatusConstants.ONLINE, null, null, LocalDateTime.now());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void setUserOffline(String userId) {
		userMapper.update(userId, null, UserStatusConstants.NORMAL, null, null, null);
	}
}