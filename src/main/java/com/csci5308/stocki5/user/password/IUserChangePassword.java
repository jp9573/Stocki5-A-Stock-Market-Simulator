package com.csci5308.stocki5.user.password;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public interface IUserChangePassword
{
	public String getPasswordValidityMessage();

	public boolean validateCurrentPassword(IUser iUser, String currentPassword);

	public boolean changePassword(IUser iUser, String newPassword, String confirmNewPassword, IUserDb iUserDb);
}
