package com.csci5308.stocki5.user.password;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.email.IEmail;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.db.IUserOtpDb;

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
	public String getOtpValidityMessage()
	{
		return otpValidityMessage;
	}

	public void setOtpValidityMessage(String otpValidityMessage)
	{
		this.otpValidityMessage = otpValidityMessage;
	}

	@Override
	public String getPasswordValidityMessage()
	{
		return passwordValidityMessage;
	}

	public void setPasswordValidityMessage(String passwordValidityMessage)
	{
		this.passwordValidityMessage = passwordValidityMessage;
	}

	@Override
	public boolean validateUserCode(String userCode, IUserDb iUserDb)
	{
		IUser iUser = iUserDb.getUser(userCode);
		if (null == iUser.getUserCode())
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean generateUserOtp(String userCode, IUserOtp iUserOtp, IUserOtpDb iUserOtpDb, IUserDb iUserDb, IEmail iEmail)
	{
		IUser iUser = iUserDb.getUser(userCode);

		iUserOtp.generateOtpForUser(userCode);
		iUserOtpDb.insertOtp(iUserOtp);

		String toEmail = iUser.getEmailId();
		String text = EMAIL_OTP_TEXT + String.valueOf(iUserOtp.getOtp());
		iEmail.sendEmail(toEmail, EMAIL_OTP_SUBJECT, text);
		return true;
	}

	@Override
	public boolean verifyOtp(String userCode, int otp, IUserOtpDb iUserOtpDb)
	{
		IUserOtp iUserOtp = iUserOtpDb.getOtp(otp);
		if (null == iUserOtp)
		{
			setOtpValidityMessage(OTP_INVALID_MESSAGE);
			return false;
		} else
		{
			if ((userCode.equals(iUserOtp.getUserCode())) && (otp == iUserOtp.getOtp()))
			{
				try
				{
					Date currentDateTime = new Date();
					Date validityDateTime = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN).parse(iUserOtp.getValidity());
					if (currentDateTime.compareTo(validityDateTime) > 0)
					{
						iUserOtpDb.deleteOtp(otp);
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
	public boolean resetPassword(String userCode, String password, String confirmPassword, IUserDb iUserDb, IUserOtpDb iUserOtpDb, IUserChangePassword iUserChangePassword)
	{
		IUser iUser = iUserDb.getUser(userCode);
		boolean isChanged = iUserChangePassword.changePassword(iUser, password, confirmPassword, iUserDb);
		setPasswordValidityMessage(iUserChangePassword.getPasswordValidityMessage());
		if (isChanged)
		{
			iUserOtpDb.deleteOtpByUserCode(userCode);
			return isChanged;
		}
		return isChanged;
	}

}
