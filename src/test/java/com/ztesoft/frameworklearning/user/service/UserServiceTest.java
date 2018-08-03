package com.ztesoft.frameworklearning.user.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.ztesoft.frameworklearning.common.BaseJunit;
import com.ztesoft.frameworklearning.user.model.User;
import com.ztesoft.frameworklearning.user.service.interfaces.IUserService;

public class UserServiceTest extends BaseJunit {
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	@Autowired
	private IUserService userService;
	
	@Before
	public void before() {
		startTime = LocalDateTime.now();
	}

	@After
	public void after() {
		endTime = LocalDateTime.now();
		logger.info("time cost:{}ms", Duration.between(startTime, endTime).toMillis());
	}

	@Test
	public void getUser() {
		try {
			User user = userService.getUser("ztesoft");
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getUser2() {
		try {
			LocalDateTime startTime = LocalDateTime.now();
			User user = userService.getUser("ztesoft", "f");
			LocalDateTime endTime = LocalDateTime.now();

			logger.info("time cost:{}ms", Duration.between(startTime, endTime).toMillis());
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void onlineUser() {
		try {
			LocalDateTime startTime = LocalDateTime.now();
			userService.setUserOnline("ztesoft");
			LocalDateTime endTime = LocalDateTime.now();

			logger.info("time cost:{}ms", Duration.between(startTime, endTime).toMillis());
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void offlineUser() {
		try {
			LocalDateTime startTime = LocalDateTime.now();
			userService.setUserOffline("ztesoft");
			LocalDateTime endTime = LocalDateTime.now();

			logger.info("time cost:{}ms", Duration.between(startTime, endTime).toMillis());
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
