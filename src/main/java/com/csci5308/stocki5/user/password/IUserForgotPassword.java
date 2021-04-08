package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.email.IEmail;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;

public interface IUserForgotPassword
{
	public String getOtpValidityMessage();

	public String getPasswordValidityMessage();

	public boolean validateUserCode(String userCode, IUserDb iUserDb);

	public boolean generateUserOtp(String userCode, IUserOtp iUserOtp, IUserOtpDb iUserOtpDb, IUserDb iUserDb, IEmail iEmail);

	public boolean verifyOtp(String userCode, int otp, IUserOtpDb iUserOtpDb);

	public boolean resetPassword(String userCode, String password, String confirmPassword, IUserDb iUserDb, IUserOtpDb iUserOtpDb, IUserChangePassword iUserChangePassword);
}
