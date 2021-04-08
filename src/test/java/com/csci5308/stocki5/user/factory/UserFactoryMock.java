package com.csci5308.stocki5.user.factory;

import com.csci5308.stocki5.user.authentication.IAuthenticationProviderMock;
import com.csci5308.stocki5.user.authentication.UserAuthenticationMock;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;
import com.csci5308.stocki5.user.db.UserDbMock;
import com.csci5308.stocki5.user.db.UserOtpDbMock;

public class UserFactoryMock extends UserAbstractFactoryMock
{

	@Override
	public IUserDb createUserDbMock()
	{
		return UserDbMock.instance();
	}

	@Override
	public IUserOtpDb createUserOtpDbMock()
	{
		return UserOtpDbMock.instance();
	}

	@Override
	public IAuthenticationProviderMock createUserAuthenticationMock()
	{
		return UserAuthenticationMock.instance();
	}
}
