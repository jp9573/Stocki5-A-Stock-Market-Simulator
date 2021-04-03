package com.csci5308.stocki5.user.profile;

import org.springframework.stereotype.Service;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.IUser;

@Service
public interface IUserProfile {
    boolean updateUser(IUserDb dbInterface, IUser user, String dateOfBirth);
}
