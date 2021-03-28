package com.csci5308.stocki5.user.signup;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.IUserDb;
import org.springframework.stereotype.Service;

@Service
public class UserSignUp implements IUserSignUp
{

	public boolean addUser(IUserDb dbInterface, User user, String dob)
	{
		boolean isDobValid = user.validateDateOfBirth(dob);
		boolean isPasswordValid = user.validatePassword();
		boolean isUserValid = user.validate();

		if (isDobValid && isPasswordValid && isUserValid)
		{
			user.setFunds(50000.0);
			user.setInternationalCommodityExchange(1);
			user.setInternationalDerivativeExchange(1);
			user.setInternationalStockExchange(1);
			user.setForeignExchange(1);
			user.setRole("ROLE_USER");
			user.generateUserCode();
			boolean isUserAdded = dbInterface.insertUser(user);
			if (isUserAdded)
			{
				return true;
			}
			user.setValidityMessage("Error in adding user. Please try again later.");
		}
		return false;

	}

}
