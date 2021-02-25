package com.csci5308.stocki5.user;

import org.springframework.stereotype.Service;

@Service
public class UserSignUp
{
	public boolean addUser(UserDbInterface dbInterface, UserCode user)
	{
		user.setFunds(50000.0);
		user.setInternationalCommodityExchange(1);
		user.setInternationalDerivativeExchange(1);
		user.setInternationalStockExchange(1);
		user.setForeignExchange(1);
		user.setRole("ROLE_USER");
		user.generateUserCode();
		return dbInterface.insertUser(user);
	}
}
