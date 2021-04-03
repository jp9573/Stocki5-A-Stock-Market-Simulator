package com.csci5308.stocki5.user.signup;

import com.csci5308.stocki5.user.IUser;
import org.springframework.stereotype.Service;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public interface IUserSignUp {
    boolean addUser(IUserDb dbInterface, IUser user, String dob);
}
