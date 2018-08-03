package com.ztesoft.frameworklearning.system.service.interfaces;

import java.util.List;
import com.ztesoft.frameworklearning.system.model.SystemParam;

public interface ISystemParamService {
    
    public List<SystemParam> getParamList(String paramId, Integer pageNumber, Integer pageSize);
    
    public long getRedisSessionTimeout();
    
    public long getRedisCaptchaTimeout();
}
