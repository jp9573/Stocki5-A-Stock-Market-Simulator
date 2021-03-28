package com.csci5308.stocki5.user.profile;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.User;

@Service
public interface IUserProfile
{
	public boolean updateUser(IUserDb dbInterface, User user, String dateOfBirth);
}
