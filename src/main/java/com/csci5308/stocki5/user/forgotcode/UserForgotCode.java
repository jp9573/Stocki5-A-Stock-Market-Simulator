package com.csci5308.stocki5.user.forgotcode;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.IUserDb;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserForgotCode implements IUserForgotCode
{
	private static final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	public String getUserCode(String email, String dob, IUserDb userDb) {
		String result = null;
		User user = userDb.getUserByEmail(email);
		if (null == user) {
			return result;
		} else {
			try {
				Date dobDate = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN).parse(dob);
				if (user.getEmailId().equals(email) && user.getDateOfBirth().compareTo(dobDate) == 0) {
					result = user.getUserCode();
					return result;
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return result;
			}
		}
		return result;
	}
}
