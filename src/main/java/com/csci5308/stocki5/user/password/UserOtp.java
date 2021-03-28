package com.csci5308.stocki5.user.password;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserOtp
{
	private int otp;
	private String validity;
	private String userCode;

	public int getOtp()
	{
		return otp;
	}

	public void setOtp(int otp)
	{
		this.otp = otp;
	}

	public String getValidity()
	{
		return validity;
	}

	public void setValidity(String validity)
	{
		this.validity = validity;
	}

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	public void generateOtpForUser(String userCode)
	{
		this.userCode = userCode;
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormater = new SimpleDateFormat(format);
		Date currentDateTime = new Date();
		long currentDateTimeMS = currentDateTime.getTime();
		long newDateTimeMS = currentDateTimeMS + 3600000;

		Date newDateTime = new Date(newDateTimeMS);
		this.validity = dateFormater.format(newDateTime);
		this.otp = (int) (Math.random() * (10000 - 999 + 1) + 999);
	}

}
