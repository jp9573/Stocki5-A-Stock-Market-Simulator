package com.csci5308.stocki5.user;

import org.springframework.stereotype.Service;

@Service
public class UserProfile {

    public boolean updateUser(UserDbInterface dbInterface, User user) {
        return dbInterface.updateUser(user);
    }
}
