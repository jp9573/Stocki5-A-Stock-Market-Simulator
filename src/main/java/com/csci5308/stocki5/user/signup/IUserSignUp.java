package com.csci5308.stocki5.user.signup;

import com.csci5308.stocki5.user.User;
import org.springframework.stereotype.Service;
import com.csci5308.stocki5.user.IUserDb;

@Service
public interface IUserSignUp {
    boolean addUser(IUserDb dbInterface, User user, String dob);
}
