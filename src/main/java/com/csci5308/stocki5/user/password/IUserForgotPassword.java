package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.email.IEmail;
import com.csci5308.stocki5.user.db.IUserDb;

public interface IUserForgotPassword {
    public String getOtpValidityMessage();

    public String getPasswordValidityMessage();

    public boolean validateUserCode(String userCode, IUserDb userDb);

    public boolean generateUserOtp(String userCode, IUserOtp userOtp, IUserOtpDb userOtpDb, IUserDb userDb, IEmail email);

    public boolean verifyOtp(String userCode, int otp, IUserOtpDb userOtpDb);

    public boolean resetPassword(String userCode, String password, String confirmPassword, IUserDb userDb, IUserOtpDb userOtpDb, IUserChangePassword userChangePassword);
}
