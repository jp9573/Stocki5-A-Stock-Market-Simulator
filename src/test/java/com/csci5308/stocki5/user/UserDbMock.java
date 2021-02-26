package com.csci5308.stocki5.user;

public class UserDbMock implements UserDbInterface
{

	@Override
	public boolean insertUser(User user)
	{
		System.out.println(user.getUserCode());
		return true;
	}

	@Override
	public boolean updateUser(User user)
	{
		System.out.println(user.getUserCode());
		return true;
	}

	@Override
	public User getUser(String userCode)
	{
		System.out.println(userCode);
		return null;
	}

}
