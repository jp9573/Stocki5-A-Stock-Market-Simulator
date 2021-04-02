package com.csci5308.stocki5.user.factory;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.User;

public class UserFactory extends UserAbstractFactory {
    private static UserFactory uniqueInstance = null;

    public static UserAbstractFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new UserFactory();
        }
        return uniqueInstance;
    }

    @Override
    public IUser createUser() {
        return new User();
    }
}
