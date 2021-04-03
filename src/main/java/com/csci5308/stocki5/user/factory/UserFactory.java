package com.csci5308.stocki5.user.factory;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.UserDb;
import com.csci5308.stocki5.user.forgotcode.IUserForgotCode;
import com.csci5308.stocki5.user.forgotcode.UserForgotCode;
import com.csci5308.stocki5.user.funds.IUserFunds;
import com.csci5308.stocki5.user.funds.UserFunds;
import com.csci5308.stocki5.user.password.*;
import com.csci5308.stocki5.user.profile.IUserProfile;
import com.csci5308.stocki5.user.profile.UserProfile;
import com.csci5308.stocki5.user.signup.IUserSignUp;
import com.csci5308.stocki5.user.signup.UserSignUp;

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

    @Override
    public IUserDb createUserDb() {
        return new UserDb();
    }

    @Override
    public IUserOtp createUserOtp() {
        return new UserOtp();
    }

    @Override
    public IUserFunds createUserFunds() {
        return new UserFunds();
    }

    @Override
    public IUserProfile createUserProfile() {
        return new UserProfile();
    }

    @Override
    public IUserSignUp createUserSignUp() {
        return new UserSignUp();
    }

    @Override
    public IUserForgotCode createUserForgotCode() {
        return new UserForgotCode();
    }

    @Override
    public IUserOtpDb createUserOtpDb() {
        return new UserOtpDb();
    }

    @Override
    public IUserForgotPassword createUserForgotPassword() {
        return new UserForgotPassword();
    }

    @Override
    public IUserChangePassword createUserChangePassword() {
        return new UserChangePassword();
    }
}
