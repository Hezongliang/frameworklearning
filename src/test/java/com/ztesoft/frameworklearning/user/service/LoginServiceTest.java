package com.ztesoft.frameworklearning.user.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.ztesoft.frameworklearning.common.BaseJunit;
import com.ztesoft.frameworklearning.user.service.interfaces.ILoginService;
import com.ztesoft.frameworklearning.user.service.interfaces.IUserService;

public class LoginServiceTest extends BaseJunit {
	// private MockHttpServletRequest request;
	// private MockHttpServletResponse response;

	@Autowired
	private ILoginService loginService;

	@Autowired
	private IUserService userService;

	@Before
	public void setUp() {
		// request = new MockHttpServletRequest();
		// request.setCharacterEncoding("UTF-8");
		// response = new MockHttpServletResponse();
	}

	@Test
	// @Rollback(false) //Rollback(false)将
	public void testLogin() {
		try {
			//loginService.loginPasswordError("f");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRegister() {
	}
}
