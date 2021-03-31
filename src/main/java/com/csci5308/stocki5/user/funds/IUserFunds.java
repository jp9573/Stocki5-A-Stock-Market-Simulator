package com.csci5308.stocki5.user.funds;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.User;

@Service
public interface IUserFunds
{
	public boolean resetFunds(User user, IUserDb userDb);

	public void setResetFundAmount(int resetFundAmount);

}
