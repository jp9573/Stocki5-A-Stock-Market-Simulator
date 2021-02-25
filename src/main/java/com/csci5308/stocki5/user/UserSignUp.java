package com.csci5308.stocki5.user;

import org.springframework.stereotype.Service;

@Service
public class UserSignUp
{
	public boolean addUser(UserDbInterface dbInterface, UserCode user)
	{
		user.generateUserCode();
		return dbInterface.insertUser(user);
	}
}
