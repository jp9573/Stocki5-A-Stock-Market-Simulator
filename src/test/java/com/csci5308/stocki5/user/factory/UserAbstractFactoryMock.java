package com.csci5308.stocki5.user.factory;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.password.IUserOtp;
import org.springframework.stereotype.Service;

@Service
public abstract class UserAbstractFactoryMock {
    public abstract IUser createUser();

    public abstract IUserOtp createUserOtp();

    public abstract IUserDb createUserDbMock();
}
