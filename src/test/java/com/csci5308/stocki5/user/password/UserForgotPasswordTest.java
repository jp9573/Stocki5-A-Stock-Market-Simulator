package com.csci5308.stocki5.user.password;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.email.EmailMock;
import com.csci5308.stocki5.email.IEmail;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class UserForgotPasswordTest
{
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserForgotPassword iUserForgotPassword = null;
	IUserDb iUserDbMock = null;
	IUserOtpDb iUserOtpDbMock = null;
	IEmail iEmailMock = null;
	IUserChangePassword iUserChangePassword = null;
	IUserOtp iUserOtp = null;

	@Before
	public void createObjects()
	{
		iUserForgotPassword = userFactory.createUserForgotPassword();
		iUserDbMock = userFactoryMock.createUserDbMock();
		iUserOtpDbMock = userFactoryMock.createUserOtpDbMock();
		iEmailMock = EmailMock.instance();
		iUserChangePassword = userFactory.createUserChangePassword();
		iUserOtp = userFactory.createUserOtp();
	}

	@After
	public void destroyObjects()
	{
		iUserForgotPassword = null;
		iUserDbMock = null;
		iUserOtpDbMock = null;
		iEmailMock = null;
		iUserChangePassword = null;
		iUserOtp = null;
	}

	@Test
	public void validateUserCodeTestValid()
	{
		String userCode = "AB123456";
		Assert.assertTrue(iUserForgotPassword.validateUserCode(userCode, iUserDbMock));

	}

	@Test
	public void validateUserCodeTestInvalid()
	{
		String userCode = "INVALID";
		Assert.assertFalse(iUserForgotPassword.validateUserCode(userCode, iUserDbMock));
	}

	@Test
	public void generateUserOtpTest()
	{
		String userCode = "AB123456";
		Assert.assertTrue(iUserForgotPassword.generateUserOtp(userCode, iUserOtp, iUserOtpDbMock, iUserDbMock, iEmailMock));
	}

	@Test
	public void verifyOtpTestValid()
	{
		String userCode = "AB123456";
		int otp = 9999;
		Assert.assertTrue(iUserForgotPassword.verifyOtp(userCode, otp, iUserOtpDbMock));
	}

	@Test
	public void verifyOtpTestInvalid()
	{
		String userCode = "AB123456";
		int otp = 1111;
		Assert.assertFalse(iUserForgotPassword.verifyOtp(userCode, otp, iUserOtpDbMock));
	}

	@Test
	public void verifyOtpTestExpired()
	{
		String userCode = "AB123456";
		int otp = 8888;
		Assert.assertFalse(iUserForgotPassword.verifyOtp(userCode, otp, iUserOtpDbMock));
	}

	@Test
	public void verifyOtpTestInvalidUserCode()
	{
		String userCode = "INVALID";
		int otp = 9999;
		Assert.assertFalse(iUserForgotPassword.verifyOtp(userCode, otp, iUserOtpDbMock));
	}

	@Test
	public void verifyOtpTestInvalidUserCodeAndOtp()
	{
		String userCode = "INVALID";
		int otp = 1111;
		Assert.assertFalse(iUserForgotPassword.verifyOtp(userCode, otp, iUserOtpDbMock));
	}

	@Test
	public void resetPassword()
	{
		String userCode = "AB123456";
		String password = "password";
		String confirmPassword = "password";
		Assert.assertTrue(iUserForgotPassword.resetPassword(userCode, password, confirmPassword, iUserDbMock, iUserOtpDbMock, iUserChangePassword));
	}

	@Test
	public void resetPasswordInvalid()
	{
		String userCode = "AB123456";
		String password = "password";
		String confirmPassword = "confirmpassword";
		Assert.assertFalse(iUserForgotPassword.resetPassword(userCode, password, confirmPassword, iUserDbMock, iUserOtpDbMock, iUserChangePassword));
	}

}