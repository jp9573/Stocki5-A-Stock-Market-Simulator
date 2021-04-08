package com.csci5308.stocki5.user.factory;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.authentication.IAuthenticationProviderMock;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;

@Service
public abstract class UserAbstractFactoryMock
{
	private static UserAbstractFactoryMock uniqueInstance = null;

	protected UserAbstractFactoryMock()
	{
	}

	public static UserAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserFactoryMock();
		}
		return uniqueInstance;
	}

	public abstract IUserDb createUserDbMock();

	public abstract IUserOtpDb createUserOtpDbMock();

	public abstract IAuthenticationProviderMock createUserAuthenticationMock();
}
