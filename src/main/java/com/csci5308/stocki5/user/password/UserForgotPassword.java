package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.*;
import org.springframework.stereotype.Service;
import com.csci5308.stocki5.email.IEmail;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserForgotPassword implements IUserForgotPassword
{
	private static final String EMAIL_OTP_SUBJECT = "Stocki5: OTP TO RESET PASSWORD";
	private static final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private String otpValidityMessage;
	private String passwordValidityMessage;


	@Override
	public String getOtpValidityMessage() {
		return otpValidityMessage;
	}

	public void setOtpValidityMessage(String otpValidityMessage) {
		this.otpValidityMessage = otpValidityMessage;
	}

	@Override
	public String getPasswordValidityMessage() {
		return passwordValidityMessage;
	}

	public void setPasswordValidityMessage(String passwordValidityMessage) {
		this.passwordValidityMessage = passwordValidityMessage;
	}

	@Override
	public boolean validateUserCode(String userCode, IUserDb userDb)
	{
		User user = userDb.getUser(userCode);
		if (null == user.getUserCode())
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean generateUserOtp(String userCode, IUserOtp userOtp, IUserOtpDb userOtpDb, IUserDb userDb, IEmail email)
	{
		User user = userDb.getUser(userCode);

		userOtp.generateOtpForUser(userCode);
		userOtpDb.insertOtp(userOtp);

		String toEmail = user.getEmailId();
		String text = "The OTP to reset you password is - " + String.valueOf(userOtp.getOtp());
		email.sendEmail(toEmail, EMAIL_OTP_SUBJECT, text);
		return true;
	}

	@Override
	public boolean verifyOtp(String userCode, int otp, IUserOtpDb userOtpDb)
	{
		UserOtp userOtp = userOtpDb.getOtp(otp);
		if (null == userOtp)
		{
			setOtpValidityMessage("Invalid OTP");
			return false;
		} else
		{
			if ((userCode.equals(userOtp.getUserCode())) && (otp == userOtp.getOtp()))
			{
				try
				{
					Date currentDateTime = new Date();
					Date validityDateTime = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN).parse(userOtp.getValidity());
					if (currentDateTime.compareTo(validityDateTime) > 0)
					{
						userOtpDb.deleteOtp(otp);
						setOtpValidityMessage("OTP Expired");
						return false;

					} else
					{
						setOtpValidityMessage("Valid");
						return true;
					}
				} catch (ParseException e)
				{
					e.printStackTrace();
					setOtpValidityMessage("Error. Please try again later.");
					return false;
				}
			}
			setOtpValidityMessage("Invalid OTP");
			return false;
		}
	}

	@Override
	public boolean resetPassword(String userCode, String password, String confirmPassword, IUserDb userDb, IUserOtpDb userOtpDb, IUserChangePassword userChangePassword)
	{
		User user = userDb.getUser(userCode);
		boolean isChanged = userChangePassword.changePassword(user, password, confirmPassword, userDb);
		setPasswordValidityMessage(userChangePassword.getPasswordValidityMessage());
		if (isChanged)
		{
			userOtpDb.deleteOtpByUserCode(userCode);
			return true;
		}
		return isChanged;
	}

}
