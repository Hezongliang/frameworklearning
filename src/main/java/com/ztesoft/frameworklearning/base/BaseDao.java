package com.ztesoft.frameworklearning.base;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public abstract class BaseDao<E> {
	protected SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
