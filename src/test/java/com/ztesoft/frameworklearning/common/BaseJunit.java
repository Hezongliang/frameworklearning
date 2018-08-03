package com.ztesoft.frameworklearning.common;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@Transactional
@Rollback(false) // 不回滚事务，直接提交数据库。如果设置为true，进行事务回滚
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml", 
		"classpath:config/spring-servlet.xml" })

public class BaseJunit extends AbstractTransactionalJUnit4SpringContextTests {
	protected static final Logger logger = LoggerFactory.getLogger(BaseJunit.class.getName());
}