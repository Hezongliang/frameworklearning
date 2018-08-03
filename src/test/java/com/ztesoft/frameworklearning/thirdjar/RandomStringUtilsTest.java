package com.ztesoft.frameworklearning.thirdjar;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.ztesoft.frameworklearning.common.BaseJunit;

public class RandomStringUtilsTest extends BaseJunit {

	@Test
	public void random() {
		System.out.println(RandomStringUtils.randomAlphabetic(10));
		System.out.println(RandomStringUtils.randomAlphanumeric(10));
		System.out.println(RandomStringUtils.randomAscii(10));
		System.out.println(RandomStringUtils.randomNumeric(10));
	}

}