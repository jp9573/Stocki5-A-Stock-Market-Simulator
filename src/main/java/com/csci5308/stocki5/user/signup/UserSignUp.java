package com.csci5308.stocki5.user.signup;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public class UserSignUp implements IUserSignUp
{
	private static final double INITIAL_FUNDS = 50000.0;
	private static final int ONE_VALUE = 1;
	private static final String USER_ROLE = "ROLE_USER";
	private static final String ADD_USER_ERROR_MESSAGE = "Error in adding user. Please try again later.";

	private static IUserSignUp uniqueInstance = null;

	private UserSignUp()
	{
	}

	public static IUserSignUp instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserSignUp();
		}
		return uniqueInstance;
	}

	public boolean addUser(IUserDb iUserDb, IUser iUser, String dob)
	{
		boolean isDobValid = iUser.validateDateOfBirth(dob);
		boolean isPasswordValid = iUser.validatePassword();
		boolean isUserValid = iUser.validate();

		if (isDobValid && isPasswordValid && isUserValid)
		{
			iUser.setFunds(INITIAL_FUNDS);
			iUser.setInternationalCommodityExchange(ONE_VALUE);
			iUser.setInternationalDerivativeExchange(ONE_VALUE);
			iUser.setInternationalStockExchange(ONE_VALUE);
			iUser.setForeignExchange(ONE_VALUE);
			iUser.setRole(USER_ROLE);
			iUser.generateUserCode();
			boolean isUserAdded = iUserDb.insertUser(iUser);
			if (isUserAdded)
			{
				return true;
			} else
			{
				iUser.setValidityMessage(ADD_USER_ERROR_MESSAGE);
			}
		}
		return false;
	}
}
