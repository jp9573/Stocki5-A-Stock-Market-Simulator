package com.csci5308.stocki5.user;

import org.junit.Assert;
import org.junit.Test;

public class UserCodeTest
{
	private UserCode userCode = new UserCode();

	@Test
	public void generateUserCodeTestNegative()
	{
		userCode.generateUserCode();
		Assert.assertNull(userCode.getUserCode());
	}

	@Test
	public void generateUserCodeTest()
	{
		userCode.setFirstName("John");
		userCode.setLastName("Doe");
		userCode.generateUserCode();
		Assert.assertNotNull(userCode.getUserCode());
	}
}
