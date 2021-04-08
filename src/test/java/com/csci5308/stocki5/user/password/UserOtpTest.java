package com.csci5308.stocki5.user.password;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.factory.UserAbstractFactory;

public class UserOtpTest
{
	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserOtp iUserOtp;

	@Before
	public void createObjects()
	{
		iUserOtp = userFactory.createUserOtp();
		iUserOtp.setOtpMinValue(999);
		iUserOtp.setOtpMaxValue(10000);
	}

	@After
	public void destroyObjects()
	{
		iUserOtp = null;
	}

	@Test
	public void generateOtpForUserTest()
	{
		String userCode = "TEST1234";
		iUserOtp.generateOtpForUser(userCode);
		Assert.assertTrue(iUserOtp.getOtp() > iUserOtp.getOtpMinValue() && iUserOtp.getOtp() < iUserOtp.getOtpMaxValue());
	}
}