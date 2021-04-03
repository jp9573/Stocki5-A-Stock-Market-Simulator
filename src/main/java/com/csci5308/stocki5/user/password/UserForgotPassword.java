package com.csci5308.stocki5.user.password;

import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.IUser;
import org.springframework.stereotype.Service;
import com.csci5308.stocki5.email.IEmail;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserForgotPassword implements IUserForgotPassword
{
	private static final String EMAIL_OTP_SUBJECT = "Stocki5: OTP TO RESET PASSWORD";
	private static final String EMAIL_OTP_TEXT = "The OTP to reset you password is - ";
	private static final String OTP_INVALID_MESSAGE = "Invalid OTP";
	private static final String OTP_EXPIRED_MESSAGE = "OTP Expired";
	private static final String OTP_ERROR_MESSAGE = "Error. Please try again later.";
	private static final String OTP_VALID_MESSAGE = "Valid";
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
		IUser user = userDb.getUser(userCode);
		if (null == user.getUserCode())
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean generateUserOtp(String userCode, IUserOtp userOtp, IUserOtpDb userOtpDb, IUserDb userDb, IEmail email)
	{
		IUser user = userDb.getUser(userCode);

		userOtp.generateOtpForUser(userCode);
		userOtpDb.insertOtp(userOtp);

		String toEmail = user.getEmailId();
		String text = EMAIL_OTP_TEXT + String.valueOf(userOtp.getOtp());
		email.sendEmail(toEmail, EMAIL_OTP_SUBJECT, text);
		return true;
	}

	@Override
	public boolean verifyOtp(String userCode, int otp, IUserOtpDb userOtpDb)
	{
		IUserOtp userOtp = userOtpDb.getOtp(otp);
		if (null == userOtp)
		{
			setOtpValidityMessage(OTP_INVALID_MESSAGE);
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
						setOtpValidityMessage(OTP_EXPIRED_MESSAGE);
						return false;

					} else
					{
						setOtpValidityMessage(OTP_VALID_MESSAGE);
						return true;
					}
				} catch (ParseException e)
				{
					e.printStackTrace();
					setOtpValidityMessage(OTP_ERROR_MESSAGE);
					return false;
				}
			}
			setOtpValidityMessage(OTP_INVALID_MESSAGE);
			return false;
		}
	}

	@Override
	public boolean resetPassword(String userCode, String password, String confirmPassword, IUserDb userDb, IUserOtpDb userOtpDb, IUserChangePassword userChangePassword)
	{
		IUser user = userDb.getUser(userCode);
		boolean isChanged = userChangePassword.changePassword(user, password, confirmPassword, userDb);
		setPasswordValidityMessage(userChangePassword.getPasswordValidityMessage());
		if (isChanged)
		{
			userOtpDb.deleteOtpByUserCode(userCode);
			return isChanged;
		}
		return isChanged;
	}

}
