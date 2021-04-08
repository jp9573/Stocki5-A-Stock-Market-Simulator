package com.csci5308.stocki5.user.password;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class UserOtp implements IUserOtp
{
	private static final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String PROPERTIES_FILE = "config.properties";

	private long expireAfterMS;
	private int otpMinValue;
	private int otpMaxValue;
	private int otp;
	private String validity;
	private String userCode;

	public UserOtp()
	{
		readProperties();
	}

	private void readProperties()
	{
		InputStream inputStream = null;
		try
		{
			Properties prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
			if (inputStream == null)
			{
				throw new FileNotFoundException();
			} else
			{
				prop.load(inputStream);
			}
			this.expireAfterMS = Long.parseLong(prop.getProperty("otp.expireaftermillisecs"));
			this.otpMinValue = Integer.parseInt(prop.getProperty("otp.minvalue"));
			this.otpMaxValue = Integer.parseInt(prop.getProperty("otp.maxvalue"));
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	@Override
	public int getOtp()
	{
		return otp;
	}

	@Override
	public void setOtp(int otp)
	{
		this.otp = otp;
	}

	@Override
	public String getValidity()
	{
		return validity;
	}

	@Override
	public void setValidity(String validity)
	{
		this.validity = validity;
	}

	@Override
	public String getUserCode()
	{
		return userCode;
	}

	@Override
	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	@Override
	public int getOtpMinValue()
	{
		return otpMinValue;
	}

	@Override
	public void setOtpMinValue(int otpMinValue)
	{
		this.otpMinValue = otpMinValue;
	}

	@Override
	public int getOtpMaxValue()
	{
		return otpMaxValue;
	}

	@Override
	public void setOtpMaxValue(int otpMaxValue)
	{
		this.otpMaxValue = otpMaxValue;
	}

	@Override
	public void generateOtpForUser(String userCode)
	{
		this.userCode = userCode;
		SimpleDateFormat dateFormater = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN);
		Date currentDateTime = new Date();
		long currentDateTimeMS = currentDateTime.getTime();
		long newDateTimeMS = currentDateTimeMS + expireAfterMS;

		Date newDateTime = new Date(newDateTimeMS);
		this.validity = dateFormater.format(newDateTime);
		this.otp = (int) (Math.random() * (otpMaxValue - otpMinValue + 1) + otpMinValue);
	}
}
