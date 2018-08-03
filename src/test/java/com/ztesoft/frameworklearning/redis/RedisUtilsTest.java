package com.ztesoft.frameworklearning.redis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.ztesoft.frameworklearning.common.BaseJunit;
import com.ztesoft.frameworklearning.redis.mapper.RedisMapper;
import com.ztesoft.frameworklearning.user.model.User;
import com.ztesoft.frameworklearning.user.service.interfaces.IUserService;

public class RedisUtilsTest extends BaseJunit {

	@Autowired
	private IUserService userService;

	@Autowired
	private RedisMapper redisMapper;

	@Autowired
	private HttpServletRequest request;

	@Test
	public void exists() {
		if (redisMapper.hasKey("ztesoft")) {
			logger.info("exists");
		} else {
			logger.info("not exists");
		}
	}

	@Test
	@Transactional(readOnly = true)
	public void saveAndGet() throws DataAccessException, Exception {
		// redisService.set("key", "data", 10000L);
		// redisService.get("key");
	}

	@Test
	public void deleteByPrefix() {
		//redisDao.deleteByPrefix("ztesoft");
	}

	@Test
	public void setString() throws DataAccessException, Exception {
		//redisDao.setObject("user:" + user.getUserId(), user);

		//User user2 = (User) redisDao.getObject("user:" + user.getUserId());
		//logger.info("user2:" + user2.getUserPassword());
	}

	@Test
	public void setSession() throws DataAccessException, Exception {
		User user = userService.getUser("ztesoft");
		HttpSession httpSession = request.getSession();

		// redisService.setSession(user.getUserId(), httpSession);

		logger.info("session:{}", request.getSession().getId());

	}

	@Test
	public void expire() {
		long startTime = System.currentTimeMillis();
		
		for (int i = 1; i <= 10; i++) {
			String userId = "ztesoft1" + String.valueOf(i);
		}
		
		long endTime = System.currentTimeMillis();
		
		logger.info("time cost:{}ms", (endTime - startTime));
	}
	
	@Test
	public void setString1() {
		redisMapper.set("f", "f");
	}
}
