package com.csci5308.stocki5.user.funds;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.User;

@Service
public class UserFunds implements IUserFunds
{
	public boolean resetFunds(User user, IUserDb userDb)
	{
		if (user.getFunds() < 10000)
		{
			user.setFunds(10000);
			return userDb.updateUserFunds(user.getUserCode(), 10000);
		} else
		{
			return false;
		}
	}
}
