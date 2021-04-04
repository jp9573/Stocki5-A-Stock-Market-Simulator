package com.csci5308.stocki5.user.authentication;

public class UserAuthenticationMock implements IAuthenticationProviderMock
{
	private static IAuthenticationProviderMock uniqueInstance = null;

	private UserAuthenticationMock()
	{

	}

	public static IAuthenticationProviderMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserAuthenticationMock();
		}
		return uniqueInstance;
	}

	@Override
	public boolean authenticate(String username, String password)
	{
		if (null == username || null == password)
		{
			return false;
		}

		if (username.equals("stocki5") && password.equals("stocki5@123"))
		{
			return true;
		}
		return false;
	}

}
