package com.csci5308.stocki5.user.forgotcode;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.db.IUserDb;

@Service
public interface IUserForgotCode
{
	public String getUserCode(String email, String dob, IUserDb userDb);
}
