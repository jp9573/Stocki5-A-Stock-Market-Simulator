package com.csci5308.stocki5.user.profile;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class UserProfileTest
{
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	private IUserDb iUserDbMock = null;
	private IUserProfile iUserProfile = null;
	private IUser iUser = null;

	@Before
	public void createObjects()
	{
		iUserProfile = userFactory.createUserProfile();
		iUser = userFactory.createUser();
		iUser.setFirstName("Jack");
		iUser.setLastName("Sparrow");
		iUser.setProvince("Toronto");
		iUserDbMock = userFactoryMock.createUserDbMock();
	}

	@After
	public void destroyObjects()
	{
		iUserProfile = null;
		iUser = null;
		iUserDbMock = null;
	}

	@Test
	public void updateUserTest()
	{
		iUser.setEmailId("test@best.com");
		iUser.setContactNo("19876543210");
		iUser.generateUserCode();
		Assert.assertTrue(iUserProfile.updateUser(iUserDbMock, iUser, "1994-09-28"));
	}

	@Test
	public void updateUserWithInvalidDOBTest()
	{
		iUser.setEmailId("test@best.com");
		iUser.setContactNo("19876543210");
		Assert.assertFalse(iUserProfile.updateUser(iUserDbMock, iUser, "2020-09-28"));
	}

	@Test
	public void updateUserWithInvalidEmailTest()
	{
		iUser.setEmailId("test@best");
		iUser.setContactNo("19876543210");
		Assert.assertFalse(iUserProfile.updateUser(iUserDbMock, iUser, "1996-09-28"));
		Assert.assertEquals("Invalid Email Id", iUser.getValidityMessage());
	}

	@Test
	public void updateUserWithInvalidContactNoTest()
	{
		iUser.setEmailId("test@best.ca");
		iUser.setContactNo("198765acb");
		Assert.assertFalse(iUserProfile.updateUser(iUserDbMock, iUser, "1996-09-28"));
		Assert.assertEquals("Invalid Contact Number", iUser.getValidityMessage());
	}

	@Test
	public void updateUserWithInvalidDBOperationResultTest()
	{
		iUser.setEmailId("test@best.ca");
		iUser.setContactNo("19876543210");
		iUser.setFirstName("Tony");
		Assert.assertFalse(iUserProfile.updateUser(iUserDbMock, iUser, "1996-09-28"));
		Assert.assertEquals("Error updating user information. Please try again later.", iUser.getValidityMessage());
	}
}