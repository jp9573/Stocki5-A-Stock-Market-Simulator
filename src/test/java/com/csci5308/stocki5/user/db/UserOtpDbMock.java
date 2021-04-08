package com.csci5308.stocki5.user.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.password.IUserOtp;

public class UserOtpDbMock implements IUserOtpDb
{

	private static IUserOtpDb uniqueInstance = null;

	UserAbstractFactory userFactory = UserAbstractFactory.instance();

	private UserOtpDbMock()
	{
	}

	public static IUserOtpDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserOtpDbMock();
		}
		return uniqueInstance;
	}

	@Override
	public boolean insertOtp(IUserOtp iUserOtp)
	{
		return true;
	}

	@Override
	public IUserOtp getOtp(int otp)
	{
		if (otp == 9999)
		{
			String format = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat dateFormater = new SimpleDateFormat(format);
			Date currentDateTime = new Date();
			long currentDateTimeMS = currentDateTime.getTime();
			long newDateTimeMS = currentDateTimeMS + 3600000;
			Date newDateTime = new Date(newDateTimeMS);

			IUserOtp iUserOtp = userFactory.createUserOtp();
			iUserOtp.setOtp(9999);
			iUserOtp.setUserCode("AB123456");
			iUserOtp.setValidity(dateFormater.format(newDateTime));
			return iUserOtp;
		} else if (otp == 8888)
		{
			IUserOtp iUserOtp = userFactory.createUserOtp();
			iUserOtp.setOtp(8888);
			iUserOtp.setUserCode("AB123456");
			iUserOtp.setValidity("2000-10-10 12:12:12");
			return iUserOtp;
		} else
		{
			return null;
		}
	}

	@Override
	public boolean deleteOtp(int otp)
	{
		return true;
	}

	@Override
	public boolean deleteOtpByUserCode(String userCode)
	{
		return true;
	}

}