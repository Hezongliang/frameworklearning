package com.ztesoft.frameworklearning.user.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ztesoft.frameworklearning.common.BaseJunit;


public class LoginControllerTest extends BaseJunit {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void login() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/login.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("password", "f");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void logout() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/logout.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("password", "f");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void forceLlogout() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/forceLogout.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("password", "f");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}
}
