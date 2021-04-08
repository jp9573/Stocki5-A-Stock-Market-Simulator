package com.csci5308.stocki5.user.profile;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public interface IUserProfile
{
	boolean updateUser(IUserDb iUserDb, IUser iUser, String dateOfBirth);
}
