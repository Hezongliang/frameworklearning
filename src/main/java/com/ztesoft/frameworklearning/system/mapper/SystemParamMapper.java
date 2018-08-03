package com.ztesoft.frameworklearning.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ztesoft.frameworklearning.system.model.SystemParam;

@Repository
public interface SystemParamMapper {
    public List<SystemParam> getParamList(@Param("paramId") String paramId, @Param("offset") Integer offset,
            @Param("limit") Integer limit);
    
	public String getValue(@Param("paramId") String paramId);
}