package com.csci5308.stocki5.user.funds;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.IUser;

@Service
public interface IUserFunds
{
	public boolean resetFunds(IUser user, IUserDb userDb);

	public void setResetFundAmount(int resetFundAmount);

}
