package com.csci5308.stocki5.user;

import org.junit.Assert;
import org.junit.Test;

public class UserCodeTest
{
	private UserCode userCode = new UserCode();

	@Test
	public void generateUserCodeTestNegative()
	{
		Assert.assertNull(userCode.generateUserCode());
	}

	@Test
	public void generateUserCodeTest()
	{
		userCode.setFirstName("John");
		userCode.setLastName("Doe");
		Assert.assertNotNull(userCode.generateUserCode());
	}
}
