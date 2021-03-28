package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.*;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.email.IEmail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserForgotPassword extends UserChangePassword
{

	public boolean validateUserCode(String userCode, IUserDb userDb)
	{
		User user = userDb.getUser(userCode);
		if (user.getUserCode() == null)
		{
			return false;
		}
		return true;
	}

	public boolean generateUserOtp(String userCode, IUserOtpDb userOtpDb, IUserDb userDb, IEmail email)
	{
		UserOtp userOtp = new UserOtp();
		User user = userDb.getUser(userCode);

		userOtp.generateOtpForUser(userCode);
		userOtpDb.insertOtp(userOtp);

		String toEmail = user.getEmailId();
		String subject = "Stocki5: OTP TO RESET PASSWORD";
		String text = "The OTP to reset you password is - " + String.valueOf(userOtp.getOtp());
		email.sendEmail(toEmail, subject, text);
		return true;
	}

	public String verifyOtp(String userCode, int otp, IUserOtpDb userOtpDb)
	{
		UserOtp userOtp = userOtpDb.getOtp(otp);
		if (userOtp == null)
		{
			return "Invalid OTP";
		} else
		{
			if ((userCode.equals(userOtp.getUserCode())) && (otp == userOtp.getOtp()))
			{
				try
				{
					Date currentDateTime = new Date();
					Date validityDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userOtp.getValidity());
					if (currentDateTime.compareTo(validityDateTime) > 0)
					{
						userOtpDb.deleteOtp(otp);
						return "OTP Expired";
					} else
					{
						return "Valid";
					}
				} catch (ParseException e)
				{
					e.printStackTrace();
					return "Error. Please try again later.";
				}
			}
			return "Invalid OTP";
		}
	}

	public String resetPassword(String userCode, String password, String confirmPassword, IUserDb userDb,
			IUserOtpDb userOtpDb)
	{
		User user = userDb.getUser(userCode);
		String result = super.changePassword(user, password, confirmPassword, userDb);
		if (result.equals("Valid"))
		{
			userDb.updateUserPassword(user);
			userOtpDb.deleteOtpByUserCode(userCode);
			return "Success";
		}
		return result;
	}

}
