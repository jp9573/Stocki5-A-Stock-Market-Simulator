package com.csci5308.stocki5.user;

import org.junit.Assert;
import org.junit.Test;

public class UserSignUpTest
{
	UserSignUp userSignUp = new UserSignUp();

	@Test
	public void addUserTest()
	{
		UserCode user = new UserCode();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.generateUserCode();
		UserDbMock userDbMock = new UserDbMock();
		Assert.assertNotEquals("error",userSignUp.addUser(userDbMock, user));
	}
}
