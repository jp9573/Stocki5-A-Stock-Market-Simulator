package com.csci5308.stocki5.user.password;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserOtp implements IUserOtp
{

	private static final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Value("${otp.expireaftermillisecs}")
	private long expireAfterMS;

	@Value("${otp.minvalue}")
	private int otpMinValue;

	@Value("${otp.maxvalue}")
	private int otpMaxValue;

	private int otp;
	private String validity;
	private String userCode;

	@Override
	public int getOtp()
	{
		return otp;
	}

	public void setOtp(int otp)
	{
		this.otp = otp;
	}

	@Override
	public String getValidity()
	{
		return validity;
	}

	public void setValidity(String validity)
	{
		this.validity = validity;
	}

	@Override
	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	@Override
	public int getOtpMinValue() {
		return otpMinValue;
	}

	@Override
	public void setOtpMinValue(int otpMinValue) {
		this.otpMinValue = otpMinValue;
	}

	@Override
	public int getOtpMaxValue() {
		return otpMaxValue;
	}

	@Override
	public void setOtpMaxValue(int otpMaxValue) {
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
