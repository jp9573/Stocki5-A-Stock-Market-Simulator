package com.csci5308.stocki5.user.password;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class UserChangePasswordTest
{
	IUserChangePassword userChangePassword = null;
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserDb iUserDbMock = null;
	IUser iUser = null;

	@Before
	public void createObjects()
	{
		userChangePassword = userFactory.createUserChangePassword();
		iUserDbMock = userFactoryMock.createUserDbMock();
		iUser = iUserDbMock.getUser("AB123456");
	}

	@After
	public void destroyObjects()
	{
		userChangePassword = null;
		iUserDbMock = null;
		iUser = null;
	}

	@Test
	public void validateCurrentPasswordTestValid()
	{
		String currentPassword = "password";
		Assert.assertTrue(userChangePassword.validateCurrentPassword(iUser, currentPassword));
	}

	@Test
	public void validateCurrentPasswordTestInvalid()
	{
		String currentPassword = "pass";
		Assert.assertFalse(userChangePassword.validateCurrentPassword(iUser, currentPassword));
	}

	@Test
	public void changePasswordTestValid()
	{
		String newpassword = "newpassword";
		String confirmNewPassword = "newpassword";
		Assert.assertTrue(userChangePassword.changePassword(iUser, newpassword, confirmNewPassword, iUserDbMock));

	}

	@Test
	public void changePasswordTestInvalid()
	{
		String newpassword = "new";
		String confirmNewPassword = "new1";
		Assert.assertFalse(userChangePassword.changePassword(iUser, newpassword, confirmNewPassword, iUserDbMock));

	}
}