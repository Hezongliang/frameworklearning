package com.ztesoft.frameworklearning.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ztesoft.frameworklearning.base.BaseController;
import com.ztesoft.frameworklearning.base.ResponseResult;
import com.ztesoft.frameworklearning.exception.ParameterException;
import com.ztesoft.frameworklearning.user.service.interfaces.ILoginService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private ILoginService loginService;
	
	/**
	 * 打开登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/", "/login.html", "/home.html" }, method = RequestMethod.GET)
	public String openHomePage() {
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public ResponseResult loginCheck(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "userPassword", required = true) String userPassword) throws Exception {

		if (StringUtils.isBlank(userId)) {
			throw new ParameterException("传入的用户名不允许为空");
		}

		if (StringUtils.isBlank(userPassword)) {
			throw new ParameterException("传入的密码不允许为空");
		}

		loginService.login(userId, userPassword);

		return ResponseResult.success();
	}

	@RequestMapping(value = "/welcome.html", method = RequestMethod.POST)
	public ModelAndView openWelcomePage(@RequestParam(value = "userId", required = true) String userId) {
	    ModelAndView mav = new ModelAndView();
	    
	    mav.setViewName("welcome");
	    mav.addObject("userId", userId);
		
	    return mav;
	}
}
