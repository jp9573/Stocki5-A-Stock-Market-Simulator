package com.csci5308.stocki5.user.profile;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.IUserDb;
import org.springframework.stereotype.Service;

@Service
public class UserProfile implements IUserProfile
{

	public boolean updateUser(IUserDb dbInterface, User user, String dateOfBirth)
	{
		boolean isDobValid = user.validateDateOfBirth(dateOfBirth);
		boolean isUserValid = user.validate();
		
		if (isDobValid && isUserValid)
		{
			boolean isUserUpdated = dbInterface.updateUser(user);
			if (isUserUpdated)
			{
				return true;
			}
			user.setValidityMessage("Error updating user information. Please try again later.");
		}
		return false;
	}
}
