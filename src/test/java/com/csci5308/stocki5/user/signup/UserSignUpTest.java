package com.csci5308.stocki5.user.signup;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class UserSignUpTest
{
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	private IUserDb iUserDbMock = null;
	private IUserSignUp iUserSignUp = null;
	private IUser iUser = null;

	@Before
	public void createObjects()
	{
		iUserSignUp = userFactory.createUserSignUp();
		iUser = userFactory.createUser();
		iUser.setFirstName("John");
		iUser.setLastName("Doe");
		iUser.setProvince("Toronto");
		iUserDbMock = userFactoryMock.createUserDbMock();
	}

	@After
	public void destroyObjects()
	{
		iUserSignUp = null;
		iUser = null;
		iUserDbMock = null;
	}

	@Test
	public void addUserTest()
	{
		iUser.setPassword("12345678");
		iUser.setConfirmPassword("12345678");
		iUser.setEmailId("test@best.com");
		iUser.setContactNo("19876543210");
		iUser.generateUserCode();
		Assert.assertNotEquals("error", iUserSignUp.addUser(iUserDbMock, iUser, "1994-09-28"));
	}

	@Test
	public void addUserWithInvalidDOBTest()
	{
		iUser.setPassword("12345678");
		iUser.setConfirmPassword("12345678");
		iUser.setEmailId("test@best.com");
		iUser.setContactNo("19876543210");
		Assert.assertFalse(iUserSignUp.addUser(iUserDbMock, iUser, "2020-09-28"));
	}

	@Test
	public void addUserWithInvalidEmailTest()
	{
		iUser.setPassword("12345678");
		iUser.setConfirmPassword("12345678");
		iUser.setEmailId("test@best");
		iUser.setContactNo("19876543210");
		Assert.assertFalse(iUserSignUp.addUser(iUserDbMock, iUser, "1996-09-28"));
		Assert.assertEquals("Invalid Email Id", iUser.getValidityMessage());
	}

	@Test
	public void addUserWithInvalidPasswordTest()
	{
		iUser.setPassword("123");
		iUser.setConfirmPassword("123");
		iUser.setEmailId("test@best");
		iUser.setContactNo("19876543210");
		Assert.assertFalse(iUserSignUp.addUser(iUserDbMock, iUser, "1996-09-28"));
	}

	@Test
	public void updateUserWithInvalidContactNoTest()
	{
		iUser.setPassword("12345678");
		iUser.setConfirmPassword("12345678");
		iUser.setEmailId("test@best.ca");
		iUser.setContactNo("198765acb");
		Assert.assertFalse(iUserSignUp.addUser(iUserDbMock, iUser, "1996-09-28"));
		Assert.assertEquals("Invalid Contact Number", iUser.getValidityMessage());
	}

	@Test
	public void addUserWithInvalidDBOperationResultTest()
	{
		iUser.setPassword("12345678");
		iUser.setConfirmPassword("12345678");
		iUser.setEmailId("test@best.ca");
		iUser.setContactNo("19876543210");
		iUser.setFirstName("Tony");
		Assert.assertFalse(iUserSignUp.addUser(iUserDbMock, iUser, "1996-09-28"));
		Assert.assertEquals("Error in adding user. Please try again later.", iUser.getValidityMessage());
	}
}
