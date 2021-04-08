package com.csci5308.stocki5.user.password;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public class UserChangePassword implements IUserChangePassword
{
	private String passwordValidityMessage;

	@Override
	public String getPasswordValidityMessage()
	{
		return passwordValidityMessage;
	}

	public void setPasswordValidityMessage(String passwordValidityMessage)
	{
		this.passwordValidityMessage = passwordValidityMessage;
	}

	@Override
	public boolean validateCurrentPassword(IUser iUser, String currentPassword)
	{
		if (currentPassword.equals(iUser.getPassword()))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean changePassword(IUser iUser, String newPassword, String confirmNewPassword, IUserDb iUserDb)
	{
		iUser.setPassword(newPassword);
		iUser.setConfirmPassword(confirmNewPassword);
		boolean isValid = iUser.validatePassword();
		setPasswordValidityMessage(iUser.getValidityMessage());
		if (isValid)
		{
			boolean isChanged = iUserDb.updateUserPassword(iUser);
			return isChanged;
		}
		return isValid;
	}
}
