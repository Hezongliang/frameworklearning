package com.ztesoft.frameworklearning.user.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ztesoft.frameworklearning.base.BaseService;
import com.ztesoft.frameworklearning.exception.BusinessException;
import com.ztesoft.frameworklearning.redis.constants.RedisConstants;
import com.ztesoft.frameworklearning.redis.mapper.RedisMapper;
import com.ztesoft.frameworklearning.system.service.interfaces.ISystemParamService;
import com.ztesoft.frameworklearning.user.model.User;
import com.ztesoft.frameworklearning.user.service.interfaces.ILoginService;
import com.ztesoft.frameworklearning.user.service.interfaces.IUserService;

@Service
public class LoginServiceImpl extends BaseService implements ILoginService {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISystemParamService systemParamService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RedisMapper redisMapper;

	/*
	 * 备注：密码输入错误时，抛出BusinessException异常，由于要写数据库记录，所以不回滚BusinessException异常
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = { BusinessException.class })
	public void login(String userId, String userPassword) throws Exception {
		User user = userService.getUser(userId);
		if (null == user) {
			throw new BusinessException("用户名或密码不正确");
		}


		if (redisMapper.hasKey(RedisConstants.PREFIX_USER + userId)) {
			// throw new BusinessException("用户已在线，请不要重复登录");
		}

		userService.setUserOnline(userId);
		redisMapper.set(RedisConstants.PREFIX_USER + userId, request.getSession().getId(),
		        systemParamService.getRedisSessionTimeout());
	}
}