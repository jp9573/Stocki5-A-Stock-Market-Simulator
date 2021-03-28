package com.csci5308.stocki5.user;

import com.csci5308.stocki5.user.signup.UserSignUp;
import org.junit.Assert;
import org.junit.Test;

public class UserSignUpTest
{
	UserSignUp userSignUp = new UserSignUp();

	@Test
	public void addUserTest()
	{
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setPassword("12345678");
		user.setConfirmPassword("12345678");
		user.setEmailId("test@best.com");
		user.setContactNo("19876543210");
		user.setProvince("Toronto");
		user.generateUserCode();
		UserDbMock userDbMock = new UserDbMock();
		Assert.assertNotEquals("error",userSignUp.addUser(userDbMock, user, "1994-09-28"));
	}
}
