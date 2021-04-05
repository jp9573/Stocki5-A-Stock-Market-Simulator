package com.csci5308.stocki5.user.profile;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

@Service
public interface IUserProfile {
    boolean updateUser(IUserDb dbInterface, IUser user, String dateOfBirth);
}
