package com.csci5308.stocki5.user.factory;

import com.csci5308.stocki5.user.IUser;
import org.springframework.stereotype.Service;

@Service
public abstract class UserAbstractFactoryMock {
    public abstract IUser createUser();
}
