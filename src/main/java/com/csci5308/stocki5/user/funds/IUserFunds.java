package com.csci5308.stocki5.user.funds;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public interface IUserFunds
{
	public boolean resetFunds(IUser iUser, IUserDb iUserDb);

	public void setResetFundAmount(int resetFundAmount);
}
