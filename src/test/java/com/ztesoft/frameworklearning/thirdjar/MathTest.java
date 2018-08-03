package com.ztesoft.frameworklearning.thirdjar;

import org.junit.Test;

import com.ztesoft.frameworklearning.common.BaseJunit;

public class MathTest extends BaseJunit {

	@Test
	public void floor() {
		byte percent = 1;
		System.out.println(1 - percent / 100.0);
		System.out.println(10 * (percent / 100.0));
		System.out.println(Math.floor(10 * (percent / 100.0)));
		System.out.println((int)(Math.floor(100 * (percent / 100.0))));
	}
}