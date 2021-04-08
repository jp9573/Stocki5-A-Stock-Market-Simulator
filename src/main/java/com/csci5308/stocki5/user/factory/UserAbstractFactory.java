package com.csci5308.stocki5.user.factory;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;
import com.csci5308.stocki5.user.forgotcode.IUserForgotCode;
import com.csci5308.stocki5.user.funds.IUserFunds;
import com.csci5308.stocki5.user.password.IUserChangePassword;
import com.csci5308.stocki5.user.password.IUserForgotPassword;
import com.csci5308.stocki5.user.password.IUserOtp;
import com.csci5308.stocki5.user.profile.IUserProfile;
import com.csci5308.stocki5.user.signup.IUserSignUp;

@Service
public abstract class UserAbstractFactory
{
	private static UserAbstractFactory uniqueInstance = null;

	protected UserAbstractFactory()
	{
	}

	public static UserAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserFactory();
		}
		return uniqueInstance;
	}

	public abstract IUser createUser();

	public abstract IUserDb createUserDb();

	public abstract IUserOtp createUserOtp();

	public abstract IUserFunds createUserFunds();

	public abstract IUserProfile createUserProfile();

	public abstract IUserSignUp createUserSignUp();

	public abstract IUserForgotCode createUserForgotCode();

	public abstract IUserOtpDb createUserOtpDb();

	public abstract IUserForgotPassword createUserForgotPassword();

	public abstract IUserChangePassword createUserChangePassword();

	public abstract AuthenticationProvider createUserAuthentication();
}
