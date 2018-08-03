package com.ztesoft.frameworklearning.user.mapper;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.ztesoft.frameworklearning.common.BaseJunit;
import com.ztesoft.frameworklearning.user.constants.UserStatusConstants;
import com.ztesoft.frameworklearning.user.model.User;

public class UserMapperTest extends BaseJunit {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void getUserById() {
		try {
			User user = userMapper.getUserWithId("ztesoft", null, null, null);
			logger.info("userId:{}", user.getUserId());
			user = userMapper.getUserWithId("ztesoft", "489131ee0804b394", null, null);
			logger.info("userId:{}", user.getUserId());
		} catch (DataAccessException e) {
			logger.info("catch a DataAccessException");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("catch a Exception");
			e.printStackTrace();
		}
	}

	@Test
	public void update() {
		try {
			userMapper.update("ztesoft", null, UserStatusConstants.ONLINE, null, null, LocalDateTime.now());
			userMapper.update("ztesoft", "489131ee0804b394", "LOCKED", null, null, null);
			userMapper.update("ztesoft", "489131ee0804b394", "LOCKED", null, null, null);
			userMapper.update("ztesoft", "489131ee0804b394", "LOCKED", "449085052@qq.com", null, null);
			userMapper.update("ztesoft", "489131ee0804b394", "LOCKED", "449085052@qq.com",
					LocalDateTime.now(), null);
			userMapper.update("ztesoft", "489131ee0804b394", "LOCKED", "449085052@qq.com",
					LocalDateTime.now(), LocalDateTime.now());
		} catch (DataAccessException e) {
			logger.info("catch a DataAccessException");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("catch a Exception");
			e.printStackTrace();
		}
	}

	@Test
	public void updatePassword() {
		try {
			userMapper.update("ztesoft", "489131ee0804b394", null, null, null, null);
		} catch (DataAccessException e) {
			logger.info("catch a DataAccessException");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("catch a Exception");
			e.printStackTrace();
		}
	}
}
