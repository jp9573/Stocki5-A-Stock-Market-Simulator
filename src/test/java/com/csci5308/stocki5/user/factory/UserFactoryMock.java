package com.csci5308.stocki5.user.factory;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.User;

public class UserFactoryMock extends UserAbstractFactoryMock {
    private static UserFactoryMock uniqueInstance = null;

    public static UserFactoryMock instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new UserFactoryMock();
        }
        return uniqueInstance;
    }

    @Override
    public IUser createUser() {
        return new User();
    }
}
