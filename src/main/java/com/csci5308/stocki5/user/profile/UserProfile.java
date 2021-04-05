package com.csci5308.stocki5.user.profile;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public class UserProfile implements IUserProfile
{
	private static final String UPDATE_USER_ERROR_MESSAGE = "Error updating user information. Please try again later.";
	private static IUserProfile uniqueInstance = null;

	private UserProfile()
	{
	}

	public static IUserProfile instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserProfile();
		}
		return uniqueInstance;
	}

	public boolean updateUser(IUserDb dbInterface, IUser user, String dateOfBirth)
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
			user.setValidityMessage(UPDATE_USER_ERROR_MESSAGE);
		}
		return false;
	}
}
