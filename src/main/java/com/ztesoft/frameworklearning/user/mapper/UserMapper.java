package com.ztesoft.frameworklearning.user.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ztesoft.frameworklearning.user.model.User;

@Repository
public interface UserMapper {
	public User getUserWithId(@Param("userId") String userId, @Param("userPassword") String userPassword,
			@Param("status") String status, @Param("email") String email);

	public int update(@Param("userId") String userId, @Param("userPassword") String userPassword,
			@Param("status") String status, @Param("email") String email, @Param("expireTime") LocalDateTime expireTime,
			@Param("lastLoginTime") LocalDateTime lastLoginTime);
}