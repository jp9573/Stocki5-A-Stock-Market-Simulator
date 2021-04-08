package com.csci5308.stocki5.user.forgotcode;

import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

@Service
public interface IUserForgotCode
{
	public String getUserCode(String email, String dob, IUserDb iUserDb);
}
