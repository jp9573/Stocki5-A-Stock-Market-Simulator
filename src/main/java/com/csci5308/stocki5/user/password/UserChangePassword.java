package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.IUserDb;
import org.springframework.stereotype.Service;

@Service
public class UserChangePassword
{

	public boolean validateCurrentPassword(User user, String currentPassword)
	{
		if (currentPassword.equals(user.getPassword()))
		{
			return true;
		}
		return false;
	}

	public String changePassword(User user, String newPassword, String confirmNewPassword, IUserDb userDb)
	{
		user.setPassword(newPassword);
		user.setConfirmPassword(confirmNewPassword);
		boolean isValid = user.validatePassword();
		if (isValid)
		{
			userDb.updateUserPassword(user);
		}
		return user.getValidityMessage();
	}
}
