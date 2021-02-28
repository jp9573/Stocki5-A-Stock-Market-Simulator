package com.csci5308.stocki5.user;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class UserCode extends User
{
	public final void generateUserCode()
	{
		if (null != super.getFirstName() && null != super.getLastName())
		{
			char firstNameFirstChar = super.getFirstName().charAt(0);
			char lastNameFirstChar = super.getLastName().charAt(0);
			SimpleDateFormat simpleDateFormatYearMonth = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("dd");
			SimpleDateFormat simpleDateFormatHourMinuteSecs = new SimpleDateFormat("HHmmss");
			long dateYearMonth = Long.parseLong(simpleDateFormatYearMonth.format(new Date()));
			long dateDay = Long.parseLong(simpleDateFormatDay.format(new Date()));
			long dateHourMinuteSecs = Long.parseLong(simpleDateFormatHourMinuteSecs.format(new Date()));
			long code = dateYearMonth + dateDay + dateHourMinuteSecs;
			String userCode = String.valueOf(firstNameFirstChar) + String.valueOf(lastNameFirstChar) + code;
			super.setUserCode(userCode.toUpperCase());
		}
	}
}
