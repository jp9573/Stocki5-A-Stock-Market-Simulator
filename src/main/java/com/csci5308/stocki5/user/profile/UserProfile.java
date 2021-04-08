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

	public boolean updateUser(IUserDb iUserDb, IUser iUser, String dateOfBirth)
	{
		boolean isDobValid = iUser.validateDateOfBirth(dateOfBirth);
		boolean isUserValid = iUser.validate();
		if (isDobValid && isUserValid)
		{
			boolean isUserUpdated = iUserDb.updateUser(iUser);
			if (isUserUpdated)
			{
				return true;
			}
			iUser.setValidityMessage(UPDATE_USER_ERROR_MESSAGE);
		}
		return false;
	}
}
