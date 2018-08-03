package com.ztesoft.frameworklearning.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ztesoft.frameworklearning.base.BaseCache;
import com.ztesoft.frameworklearning.system.mapper.SystemParamMapper;
import com.ztesoft.frameworklearning.system.model.SystemParam;

@Component
public class SystemParamCache extends BaseCache {
	public static Map<String, String> systemParamMap = new HashMap<String, String>();

	@Autowired
	private SystemParamMapper systemParamMapper;

	@Transactional(readOnly = true)
	public void init() {
		logger.info("开始加载系统参数到缓存");

		systemParamMap.clear();

		List<SystemParam> systemParamList = systemParamMapper.getParamList(null, null, null);

		for (SystemParam systemParam : systemParamList) {
			systemParamMap.put(systemParam.getParamId(), systemParam.getParamValue());
		}

		logger.info("加载系统参数到缓存结束，共加载参数个数：{}", systemParamMap.size());
	}

	public String get(String paramId) {
		return systemParamMap.get(paramId);
	}
}
