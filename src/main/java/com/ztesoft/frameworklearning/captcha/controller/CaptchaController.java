package com.ztesoft.frameworklearning.captcha.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ztesoft.frameworklearning.base.BaseController;
import com.ztesoft.frameworklearning.redis.constants.RedisConstants;
import com.ztesoft.frameworklearning.redis.mapper.RedisMapper;
import com.ztesoft.frameworklearning.system.service.interfaces.ISystemParamService;

@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {

	@Autowired
	private Producer captchaProducer; // captchaProducer和applicationContext.xml中配置的bean id相同
	
	@Autowired
    private ISystemParamService systemParamService;

	@Autowired
	private RedisMapper redisMapper;

	@RequestMapping(value = "/getCode.do", method = RequestMethod.GET)
	public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setDateHeader("Expires", 0);

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// 生成验证码文本
		String capText = captchaProducer.createText();
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		redisMapper.set(RedisConstants.PREFIX_CAPTCHA + request.getSession().getId(), capText,
		        systemParamService.getRedisCaptchaTimeout());

		// 利用生成的字符串构建图片
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = null;

		try {
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/verifyCode.do", method = RequestMethod.GET)
	public Map<String, Boolean> verifyCode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "identifyCode", required = true) String identifyCode) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();

		String generateCode = (String) redisMapper.get(RedisConstants.PREFIX_CAPTCHA + request.getSession().getId());
		
		if (StringUtils.equalsIgnoreCase(generateCode, identifyCode)) {
			map.put("valid", true);
			redisMapper.delete(RedisConstants.PREFIX_CAPTCHA + request.getSession().getId());
		} else {
			map.put("valid", false);
		}

		return map;
	}
}
