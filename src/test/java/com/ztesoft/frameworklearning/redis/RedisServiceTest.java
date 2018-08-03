package com.ztesoft.frameworklearning.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.Transactional;

import com.ztesoft.frameworklearning.common.BaseJunit;
import com.ztesoft.frameworklearning.user.model.User;
import com.ztesoft.frameworklearning.user.service.interfaces.IUserService;

public class RedisServiceTest extends BaseJunit {

	@Autowired
	private IUserService userService;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource(name="stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	@Transactional(readOnly = true)
	public void saveAndGet() throws DataAccessException, Exception {
		User user = userService.getUser("ztesoft");
		ValueOperations<String , Object> valueOperation = redisTemplate.opsForValue();
		valueOperation.set("user" + user.getUserId(), user);
		valueOperation.set("zza", 1);
		
		User result = (User)valueOperation.get("user" + user.getUserId());
		logger.info("userId:{}", result.toString());
		logger.info("zza:{}", valueOperation.get("zza"));
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		ValueOperations<String , Object> valueOperation2 = redisTemplate.opsForValue();
		valueOperation2.set("sequence", "100");
		logger.info("sequence:{}", valueOperation2.get("sequence"));
		
		long cycleTimes = 10000L;
		long startTime = System.currentTimeMillis();
		for (int i = 1; i <= cycleTimes; i++) {
			valueOperation2.increment("sequence", 1);
			//logger.info("sequence:" + i + ":" + valueOperation2.get("sequence"));
		}
		long endTime = System.currentTimeMillis();
		logger.info("time cost:{}ms" + (endTime - startTime));
	}
}
