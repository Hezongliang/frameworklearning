package com.ztesoft.frameworklearning.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beust.jcommander.ParameterException;
import com.ztesoft.frameworklearning.base.BaseController;
import com.ztesoft.frameworklearning.base.ResponseResult;
import com.ztesoft.frameworklearning.user.model.User;
import com.ztesoft.frameworklearning.user.service.interfaces.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    
    @Autowired
    private IUserService userService;
    
    @ResponseBody
    @RequestMapping(value = "/getInfo.do", method = RequestMethod.POST)
    public ResponseResult getUserInfo(@RequestParam(value = "userId", required = true) String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new ParameterException("传入的用户名不允许为空");
        }
        
        User user = userService.getUser(userId);
        if (null == user) {
            return ResponseResult.failure("查询不到对应的用户信息");
        } else {
            user.setUserPassword("******");
            return ResponseResult.success(user);
        }
    }
}
