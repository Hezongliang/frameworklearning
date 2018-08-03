package com.ztesoft.frameworklearning.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/**
 * <p>
 * 实现HandlerExceptionResolver接口自定义异常处理器
 * </p>
 *
 * @see org.springframework.web.servlet.HandlerExceptionResolver
 */

/**
 * @author microsoft
 *
 */
public class SystemExceptionHandler implements HandlerExceptionResolver {
	protected static final Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class.getName());


	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		if (isAjax(request)) {
			response.setContentType("application/json;charset=UTF-8");
			String errorMessage = null;
			Gson gson = new Gson();

			if (exception instanceof ParameterException) {
				logger.error("SystemExceptionHandler catch ParameterException", exception);
				errorMessage = exception.getMessage();
			} else if (exception instanceof BusinessException) {
				logger.error("SystemExceptionHandler catch BusinessException", exception);
				errorMessage = exception.getMessage();
			} else if (exception instanceof NotLoginException) {
				logger.error("SystemExceptionHandler catch NotLoginException", exception);
				errorMessage = exception.getMessage();
			} else if (exception instanceof DataAccessException) {
				logger.error("SystemExceptionHandler catch DataAccessException", exception);
				errorMessage = "系统错误，请联系管理员解决。"; // 对于DataAccessException异常进行转义
			} else {
				logger.error("SystemExceptionHandler catch Exception", exception);
				errorMessage = "未知错误，请联系管理员解决。"; // 对于DataAccessException异常进行转义
			}

			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.write(gson.toJson(errorMessage));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != writer) {
					writer.flush();
					writer.close();
				}
			}

			return null;
		} else {
			String viewName = null;
			Map<String, Object> model = new HashMap<String, Object>();

			// 根据不同错误转向不同页面
			// 如果捕获到异常，将异常放入session，供OperateLogInterceptor拦截器记录操作结果使用
			if (exception instanceof NotLoginException) {
				logger.error("SystemExceptionHandler catch NotLoginException", exception);
				viewName = "redirect:/"; // 跳转到首页是GET操作，不设置model，要不然地址栏的url会显示model里面的数据
			} else if (exception instanceof BusinessException) {
				logger.error("SystemExceptionHandler catch BusinessException", exception);
				viewName = "error/ErrorPage";
			} else if (exception instanceof DataAccessException) {
				logger.error("SystemExceptionHandler catch DataAccessException", exception);
				viewName = "error/ErrorPage";
			} else {
				logger.error("SystemExceptionHandler catch Exception", exception);
				viewName = "error/ErrorPage";
			}

			return new ModelAndView(viewName, model);
		}
	}

	/**
	 * 判断是否是ajax请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjax(HttpServletRequest request) {
		// if ((request.getHeader("accept").indexOf("application/json") > -1)
		// || ((request.getHeader("X-Requested-With") != null)
		// && (request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
		// return true;
		// } else {
		// return false;
		// }
		return true;
	}
}