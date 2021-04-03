package com.csci5308.stocki5.user.factory;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.UserDbMock;
import com.csci5308.stocki5.user.password.IUserOtp;
import com.csci5308.stocki5.user.password.UserOtp;

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

    @Override
    public IUserOtp createUserOtp() {
        return new UserOtp();
    }

    @Override
    public IUserDb createUserDbMock() {
        return new UserDbMock();
    }
}
