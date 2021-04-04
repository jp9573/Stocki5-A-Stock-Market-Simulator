package com.csci5308.stocki5.user.factory;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;
import com.csci5308.stocki5.user.forgotcode.IUserForgotCode;
import com.csci5308.stocki5.user.funds.IUserFunds;
import com.csci5308.stocki5.user.password.IUserChangePassword;
import com.csci5308.stocki5.user.password.IUserForgotPassword;
import com.csci5308.stocki5.user.password.IUserOtp;
import com.csci5308.stocki5.user.profile.IUserProfile;
import com.csci5308.stocki5.user.signup.IUserSignUp;
import org.springframework.stereotype.Service;

@Service
public abstract class UserAbstractFactoryMock {
    private static UserAbstractFactoryMock uniqueInstance = null;

    protected UserAbstractFactoryMock() { }

    public static UserAbstractFactoryMock instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new UserFactoryMock();
        }
        return uniqueInstance;
    }

    public abstract IUser createUser();

    public abstract IUserOtp createUserOtp();

    public abstract IUserProfile createUserProfile();

    public abstract IUserDb createUserDbMock();

    public abstract IUserFunds createUserFunds();

    public abstract IUserSignUp createUserSignUp();

    public abstract IUserForgotCode createUserForgotCode();

    public abstract IUserChangePassword createUserChangePassword();

    public abstract IUserForgotPassword createUserForgotPassword();

    public abstract IUserOtpDb createUserOtpDbMock();
}
