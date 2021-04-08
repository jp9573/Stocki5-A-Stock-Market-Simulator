package com.csci5308.stocki5.user.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

public class UserDbMock implements IUserDb
{

	private static IUserDb uniqueInstance = null;

	UserAbstractFactory userFactory = UserAbstractFactory.instance();

	private UserDbMock()
	{
	}

	public static IUserDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserDbMock();
		}
		return uniqueInstance;
	}

	@Override
	public boolean insertUser(IUser iUser)
	{
		if (iUser.getFirstName().equals("Tony"))
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUser(IUser iUser)
	{
		if (iUser.getFirstName().equals("Tony"))
		{
			return false;
		}
		return true;
	}

	@Override
	public IUser getUser(String userCode)
	{
		IUser iUser = userFactory.createUser();
		switch (userCode)
		{
		case "AB123456":
		{
			iUser.setEmailId("test@test.com");
			iUser.setUserCode("AB123456");
			iUser.setContactNo("19324678786");
			iUser.setFirstName("John");
			iUser.setLastName("Doe");
			iUser.setProvince("halifax");
			iUser.setPassword("password");
			iUser.setConfirmPassword("password");
			iUser.setInternationalDerivativeExchange(1);
			iUser.setInternationalCommodityExchange(1);
			iUser.setInternationalStockExchange(1);
			iUser.setForeignExchange(1);
			iUser.setFunds(50000);
			Date dob = null;
			try
			{
				dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			iUser.setDateOfBirth(dob);
			return iUser;
		}
		case "AB1234567":
		{
			iUser.setEmailId("test@test.com");
			iUser.setUserCode("AB123456");
			iUser.setInternationalDerivativeExchange(1);
			iUser.setInternationalCommodityExchange(1);
			iUser.setInternationalStockExchange(1);
			iUser.setForeignExchange(1);
			iUser.setFunds(5000);
			Date dob = null;
			try
			{
				dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			iUser.setDateOfBirth(dob);
			return iUser;
		}
		case "AB12345678":
		{
			iUser.setEmailId("test@test.com");
			iUser.setUserCode("AB123456");
			iUser.setInternationalDerivativeExchange(1);
			iUser.setInternationalCommodityExchange(1);
			iUser.setInternationalStockExchange(1);
			iUser.setForeignExchange(1);
			iUser.setFunds(0);
			Date dob = null;
			try
			{
				dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			iUser.setDateOfBirth(dob);
			return iUser;
		}
		default:
			return iUser;
		}
	}

	@Override
	public IUser getUserByEmail(String email)
	{
		if (email.equals("test@example.com"))
		{
			IUser iUser = userFactory.createUser();
			iUser.setEmailId(email);
			iUser.setUserCode("TEST12456789");
			Date dob = null;
			try
			{
				dob = new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-12");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			iUser.setDateOfBirth(dob);
			return iUser;
		} else
		{
			return null;
		}
	}

	@Override
	public boolean updateUserPassword(IUser iUser)
	{
		return true;
	}

	@Override
	public double getUserFunds(String userCode)
	{
		if (userCode.equals("AB123456"))
		{
			return 5000;
		} else
		{
			return 50000;
		}
	}

	@Override
	public boolean updateUserFunds(String userCode, double amount)
	{
		return true;
	}

}
