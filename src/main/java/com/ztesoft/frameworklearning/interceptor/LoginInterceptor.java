package com.ztesoft.frameworklearning.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ztesoft.frameworklearning.base.BaseInterceptor;
import com.ztesoft.frameworklearning.redis.constants.RedisConstants;
import com.ztesoft.frameworklearning.redis.mapper.RedisMapper;

public class LoginInterceptor extends BaseInterceptor implements HandlerInterceptor {
	private String userId = null;

	@Autowired
	private RedisMapper redisMapper;
	
	// 该方法将在请求处理之前进行调用。该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor和Controller
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("+++++++++++++++++++++++++++++LoginInterceptor.preHandle:{}", request.getRequestURI());
		userId = StringUtils.trim(request.getParameter("userId"));
		String cacheSessionId = StringUtils.trim((String) redisMapper.get(RedisConstants.PREFIX_USER + userId));
		String currentSessionId = StringUtils.trim(request.getSession().getId());

		if (!StringUtils.equalsIgnoreCase(cacheSessionId, currentSessionId)) {
			logger.error("您尚未登录，请登录后再操作：{}", userId);
			//throw new NotLoginException("您尚未登录，请登录后再操作");
		}

		return true;
	}

	// 在当前请求进行处理之后，也就是Controller 方法调用之后执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modleAndView) throws Exception {
		logger.info("+++++++++++++++++++++++++++++LoginInterceptor.postHandle:{}", request.getRequestURI());

		redisMapper.expire(RedisConstants.PREFIX_USER + userId, 1800L);

	}

	// 该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		logger.info("+++++++++++++++++++++++++++++LoginInterceptor.afterCompletion:{}", request.getRequestURI());
	}

}
