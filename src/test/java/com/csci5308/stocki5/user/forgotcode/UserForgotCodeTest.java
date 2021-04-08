package com.csci5308.stocki5.user.forgotcode;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class UserForgotCodeTest
{
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserForgotCode iUserForgotCode = null;
	IUserDb iUserDb = null;

	@Before
	public void createObjects()
	{
		iUserDb = userFactoryMock.createUserDbMock();
		iUserForgotCode = userFactory.createUserForgotCode();
	}

	@After
	public void destroyObjects()
	{
		iUserDb = null;
		iUserForgotCode = null;
	}

	@Test
	public void getUserCodeTestBothEmpty()
	{
		String email = "";
		String dob = "";
		Assert.assertNull(iUserForgotCode.getUserCode(email, dob, iUserDb));
	}

	@Test
	public void getUserCodeTestOnlyEmail()
	{
		String email = "test@example.com";
		String dob = "";
		Assert.assertNull(iUserForgotCode.getUserCode(email, dob, iUserDb));
	}

	@Test
	public void getUserCodeTestOnlyDob()
	{
		String email = "";
		String dob = "2000-10-12";
		Assert.assertNull(iUserForgotCode.getUserCode(email, dob, iUserDb));
	}

	@Test
	public void getUserCodeTestBothCorrect()
	{
		String email = "test@example.com";
		String dob = "2000-10-12";
		Assert.assertEquals("TEST12456789", iUserForgotCode.getUserCode(email, dob, iUserDb));
	}

	@Test
	public void getUserCodeTestWrongDob()
	{
		String email = "test@example.com";
		String dob = "2001-10-12";
		Assert.assertNull(iUserForgotCode.getUserCode(email, dob, iUserDb));
	}
}