package com.csci5308.stocki5.user.signup;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public interface IUserSignUp
{
	boolean addUser(IUserDb iUserDb, IUser iUser, String dob);
}
