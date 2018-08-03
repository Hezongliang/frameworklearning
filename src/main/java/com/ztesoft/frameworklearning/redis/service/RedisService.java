package com.ztesoft.frameworklearning.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ztesoft.frameworklearning.base.BaseService;
import com.ztesoft.frameworklearning.redis.mapper.RedisMapper;

@Component
public class RedisService extends BaseService {

	@Autowired
	private RedisMapper redisMapper;
	
	public void init() {
		logger.info("clean all redis data");
		redisMapper.flushDb();
	}
}
