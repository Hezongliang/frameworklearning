package com.ztesoft.frameworklearning.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztesoft.frameworklearning.base.BaseService;
import com.ztesoft.frameworklearning.system.mapper.SystemParamMapper;
import com.ztesoft.frameworklearning.system.model.SystemParam;
import com.ztesoft.frameworklearning.system.service.interfaces.ISystemParamService;

@Service
public class SystemParamServiceImpl extends BaseService implements ISystemParamService {
    private static final String REDIS_SESSION_EXPIRE_TIMEOUT = "REDIS_SESSION_EXPIRE_TIMEOUT"; // redis中session有效期（单位：秒）
    private static final String REDIS_CAPTCHA_EXPIRE_TIMEOUT = "REDIS_CAPTCHA_EXPIRE_TIMEOUT"; // redis中验证码有效期（单位：秒）
    
    @Autowired
    private SystemParamMapper systemParamMapper;
    
    @Override
    public List<SystemParam> getParamList(String paramId, Integer pageNumber, Integer pageSize) {
        if ((pageNumber != null) && (pageSize != null)) {
            return systemParamMapper.getParamList(paramId, (pageNumber - 1) * pageSize, pageSize);
        } else {
            return systemParamMapper.getParamList(paramId, null, null);
        }
    }
    
    @Override
    public long getRedisSessionTimeout() {
        String redisSessionExpireTimeout = systemParamMapper.getValue(REDIS_SESSION_EXPIRE_TIMEOUT);
        
        return StringUtils.isBlank(redisSessionExpireTimeout) ? 1800L : Long.parseLong(redisSessionExpireTimeout);
    }
    
    @Override
    public long getRedisCaptchaTimeout() {
        String redisCaptchaExpireTimeout = systemParamMapper.getValue(REDIS_CAPTCHA_EXPIRE_TIMEOUT);
        
        return StringUtils.isBlank(redisCaptchaExpireTimeout) ? 600L : Long.parseLong(redisCaptchaExpireTimeout);
    }
}