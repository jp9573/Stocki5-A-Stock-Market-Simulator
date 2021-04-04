package com.csci5308.stocki5.user.signup;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

@Service
public interface IUserSignUp {
    boolean addUser(IUserDb dbInterface, IUser user, String dob);
}
