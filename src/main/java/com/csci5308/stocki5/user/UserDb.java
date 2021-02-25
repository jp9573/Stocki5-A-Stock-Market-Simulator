package com.csci5308.stocki5.user;

import org.springframework.stereotype.Repository;

@Repository
public class UserDb implements UserDbInterface
{

	@Override
	public boolean insertUser(User user)
	{
		System.out.println(user.getUserCode());
		return false;
	}

	@Override
	public boolean updateUser(User user)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String userCode)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
