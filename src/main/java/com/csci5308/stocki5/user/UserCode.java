package com.csci5308.stocki5.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserCode extends User
{
	public final void generateUserCode()
	{
		if (null != super.getFirstName() && null != super.getLastName())
		{
			char firstNameFirstChar = super.getFirstName().charAt(0);
			char lastNameFirstChar = super.getLastName().charAt(0);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = simpleDateFormat.format(new Date());
			String userCode = String.valueOf(firstNameFirstChar) + String.valueOf(lastNameFirstChar) + date;
			super.setUserCode(userCode.toUpperCase());
		}
	}
}
