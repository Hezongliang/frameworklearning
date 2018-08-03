package com.ztesoft.frameworklearning.user.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ztesoft.frameworklearning.common.BaseJunit;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

public class UserControllerTest extends BaseJunit {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private static int THREAD_COUNT = 5;
	private static int seq = 14;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void batchTest() throws Throwable {
		try {
			// Runner数组，相当于并发多少个
			TestRunnable[] trs = new TestRunnable[THREAD_COUNT];

			for (int i = 0; i < THREAD_COUNT; i++) {
				trs[i] = new TestRunnable() {
					@Override
					public void runTest() throws Throwable {
						// addOrg();
						// deleteOrg();
						addOrgMember();
						// deleteOrgMember();
					}
				};
			}

			// 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
			MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);

			long startTime = System.currentTimeMillis();

			// 开发并发执行数组里定义的内容
			mttr.runTestRunnables();

			long endTime = System.currentTimeMillis();

			logger.info("time cost:" + (endTime - startTime));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void register() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/user/register.do");
		 mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		 mockHttpServletRequestBuilder.param("userId", "ztesoft33");
		 mockHttpServletRequestBuilder.param("password", "f");
		 mockHttpServletRequestBuilder.param("email", "724045021@qq.com");
		 mockHttpServletRequestBuilder.param("contactNumber", "724045021");
		
		 mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void register1() throws Exception {
		for (int i = 11040; i <= 20000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}
	
	@Test
	public void register2() throws Exception {
		for (int i = 20001; i <= 30000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}
	
	@Test
	public void register3() throws Exception {
		for (int i = 30001; i <= 40000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}
	
	@Test
	public void register4() throws Exception {
		for (int i = 40001; i <= 50000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}
	
	@Test
	public void register5() throws Exception {
		for (int i = 50001; i <= 60000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}
	
	@Test
	public void register6() throws Exception {
		for (int i = 60001; i <= 70000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}
	
	@Test
	public void register7() throws Exception {
		for (int i = 70001; i <= 80000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}
	
	@Test
	public void register8() throws Exception {
		for (int i = 80001; i <= 90000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}
	
	@Test
	public void register9() throws Exception {
		for (int i = 90001; i < 100000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/register.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"user" + StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "449085052@qq.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}

	@Test
	public void registerBatch() throws Throwable {
		try {
			// Runner数组，相当于并发多少个
			TestRunnable[] trs = new TestRunnable[9];

			trs[0] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register1();
				}
			};
			
			trs[1] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register2();
				}
			};
			
			trs[2] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register3();
				}
			};
			
			trs[3] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register4();
				}
			};
			
			trs[4] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register5();
				}
			};
			
			trs[5] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register6();
				}
			};
			
			trs[6] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register7();
				}
			};
			
			trs[7] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register8();
				}
			};
			
			trs[8] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					register9();
				}
			};

			// 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
			MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);

			long startTime = System.currentTimeMillis();

			// 开发并发执行数组里定义的内容
			mttr.runTestRunnables();

			long endTime = System.currentTimeMillis();

			logger.info("time cost:" + (endTime - startTime));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteIndividual() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/admin/deleteIndividual.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("deleteUserId", "user00008");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void addOrg() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/addOrg.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("orgId", "org000011");
		mockHttpServletRequestBuilder.param("password", "f");
		mockHttpServletRequestBuilder.param("email", "18811050334@139.com");
		mockHttpServletRequestBuilder.param("contactNumber", "18811050334");
		mockHttpServletRequestBuilder.param("officialName",
				"组织00001");
		mockHttpServletRequestBuilder.param("address", "中国北京市");
		mockHttpServletRequestBuilder.param("orgType", "学校");
		mockHttpServletRequestBuilder.param("businessLicenseId",
				"business:org00001");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void deleteOrg() throws Exception {
		// for (int i = 1; i <= 500; i++) {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/user/deleteOrg.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("deleteOrgId", "org000011");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		// }
	}

	@Test
	public void addOrgMember() throws Exception {
		int userSeq = seq++;

		for (int i = 680; i <= 1000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/addOrgMember.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"org" + StringUtils.leftPad(String.valueOf(userSeq), 5, "0"));
			// mockHttpServletRequestBuilder.param("userId", "org00001");
			mockHttpServletRequestBuilder.param("memberName",
					"org" + StringUtils.leftPad(String.valueOf(userSeq), 5, "0") + "-mem"
							+ StringUtils.leftPad(String.valueOf(i), 5, "0"));
			mockHttpServletRequestBuilder.param("password", "f");
			mockHttpServletRequestBuilder.param("email", "18811050334@139.com");
			mockHttpServletRequestBuilder.param("contactNumber", "18811050334");

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}

	@Test
	public void deleteOrgMember() throws Exception {
		int userSeq = seq++;

		for (int i = 1; i <= 1000; i++) {
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
					.post("/user/deleteOrgMember.do");
			mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
			mockHttpServletRequestBuilder.param("userId",
					"org" + StringUtils.leftPad(String.valueOf(userSeq), 5, "0"));
			// mockHttpServletRequestBuilder.param("userId", "org00001");
			mockHttpServletRequestBuilder.param("memberName",
					"org" + StringUtils.leftPad(String.valueOf(userSeq), 5, "0") + "-mem"
							+ StringUtils.leftPad(String.valueOf(i), 5, "0"));

			mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
		}
	}

	@Test
	public void modifyPassword() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/user/modifyPassword.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("password", "tX<K9b?ONf");
		mockHttpServletRequestBuilder.param("newPassword", "f");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void resetPassword() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/user/resetPassword.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("contactNumber", "18811050334");
		mockHttpServletRequestBuilder.param("email", "18811050334@139.com");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void unlockUser() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/user/unlockUser.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("unlockUserId", "ztesoft");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void lockUser() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.post("/user/lockUser.do");
		mockHttpServletRequestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mockHttpServletRequestBuilder.param("userId", "ztesoft");
		mockHttpServletRequestBuilder.param("lockUserId", "ztesoft");

		mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void batchImport() throws Throwable {
		try {
			// Runner数组，相当于并发多少个
			TestRunnable[] trs = new TestRunnable[6];

			trs[0] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					importExcel1();
				}
			};

			trs[1] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					importExcel2();
				}
			};

			trs[2] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					importExcel3();
				}
			};

			trs[3] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					importExcel4();
				}
			};

			trs[4] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					importExcel5();
				}
			};

			trs[5] = new TestRunnable() {
				@Override
				public void runTest() throws Throwable {
					importExcel6();
				}
			};

			// 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
			MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);

			long startTime = System.currentTimeMillis();

			// 开发并发执行数组里定义的内容
			mttr.runTestRunnables();

			long endTime = System.currentTimeMillis();

			logger.info("time cost:{}ms", (endTime - startTime));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void importExcel1() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "G:\\\\tmp\\\\导入成员_org05111.xlsx", "text/plain",
				"content in file".getBytes());
		MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.fileUpload("/user/importExcel.do");
		request.file(file);
		request.param("userId", "org05111");

		mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void importExcel2() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "G:\\\\tmp\\\\导入成员_org05112.xlsx", "text/plain",
				"content in file".getBytes());
		MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.fileUpload("/user/importExcel.do");
		request.file(file);
		request.param("userId", "org05112");

		mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void importExcel3() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "G:\\\\tmp\\\\导入成员_org05114.xlsx", "text/plain",
				"content in file".getBytes());
		MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.fileUpload("/user/importExcel.do");
		request.file(file);
		request.param("userId", "org05114");

		mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void importExcel4() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "G:\\\\tmp\\\\导入成员_org05115.xlsx", "text/plain",
				"content in file".getBytes());
		MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.fileUpload("/user/importExcel.do");
		request.file(file);
		request.param("userId", "org05115");

		mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void importExcel5() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "G:\\\\tmp\\\\导入成员_org05117.xlsx", "text/plain",
				"content in file".getBytes());
		MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.fileUpload("/user/importExcel.do");
		request.file(file);
		request.param("userId", "org05117");

		mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void importExcel6() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "G:\\\\tmp\\\\导入成员_org05118.xlsx", "text/plain",
				"content in file".getBytes());
		MockMultipartHttpServletRequestBuilder request = MockMvcRequestBuilders.fileUpload("/user/importExcel.do");
		request.file(file);
		request.param("userId", "org05118");

		mockMvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
	}
}
