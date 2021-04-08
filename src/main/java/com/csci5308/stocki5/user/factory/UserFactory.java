package com.csci5308.stocki5.user.factory;

import org.springframework.security.authentication.AuthenticationProvider;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.authentication.UserAuthentication;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;
import com.csci5308.stocki5.user.db.UserDb;
import com.csci5308.stocki5.user.db.UserOtpDb;
import com.csci5308.stocki5.user.forgotcode.IUserForgotCode;
import com.csci5308.stocki5.user.forgotcode.UserForgotCode;
import com.csci5308.stocki5.user.funds.IUserFunds;
import com.csci5308.stocki5.user.funds.UserFunds;
import com.csci5308.stocki5.user.password.IUserChangePassword;
import com.csci5308.stocki5.user.password.IUserForgotPassword;
import com.csci5308.stocki5.user.password.IUserOtp;
import com.csci5308.stocki5.user.password.UserChangePassword;
import com.csci5308.stocki5.user.password.UserForgotPassword;
import com.csci5308.stocki5.user.password.UserOtp;
import com.csci5308.stocki5.user.profile.IUserProfile;
import com.csci5308.stocki5.user.profile.UserProfile;
import com.csci5308.stocki5.user.signup.IUserSignUp;
import com.csci5308.stocki5.user.signup.UserSignUp;

public class UserFactory extends UserAbstractFactory
{

	@Override
	public IUser createUser()
	{
		return new User();
	}

	@Override
	public IUserDb createUserDb()
	{
		return UserDb.instance();
	}

	@Override
	public IUserOtp createUserOtp()
	{
		return new UserOtp();
	}

	@Override
	public IUserFunds createUserFunds()
	{
		return new UserFunds();
	}

	@Override
	public IUserProfile createUserProfile()
	{
		return UserProfile.instance();
	}

	@Override
	public IUserSignUp createUserSignUp()
	{
		return UserSignUp.instance();
	}

	@Override
	public IUserForgotCode createUserForgotCode()
	{
		return UserForgotCode.instance();
	}

	@Override
	public IUserOtpDb createUserOtpDb()
	{
		return UserOtpDb.instance();
	}

	@Override
	public IUserForgotPassword createUserForgotPassword()
	{
		return new UserForgotPassword();
	}

	@Override
	public IUserChangePassword createUserChangePassword()
	{
		return new UserChangePassword();
	}

	@Override
	public AuthenticationProvider createUserAuthentication()
	{
		return UserAuthentication.instance();
	}
}
