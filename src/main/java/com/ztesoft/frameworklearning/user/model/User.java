package com.ztesoft.frameworklearning.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztesoft.frameworklearning.constants.SystemConstants;

public class User implements Serializable {
	private static final long serialVersionUID = 4602104234800421895L;
	private String userId;
    private String userPassword;
    private String roleId;
    private String status;
    private String email;
    private LocalDateTime validTime;
    private LocalDateTime expireTime;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @JsonFormat(pattern = SystemConstants.DATETIME_FORMAT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getValidTime() {
        return validTime;
    }

    public void setValidTime(LocalDateTime validTime) {
        this.validTime = validTime;
    }

    @JsonFormat(pattern = SystemConstants.DATETIME_FORMAT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @JsonFormat(pattern = SystemConstants.DATETIME_FORMAT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @JsonFormat(pattern = SystemConstants.DATETIME_FORMAT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}