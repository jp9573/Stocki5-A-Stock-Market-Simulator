package com.csci5308.stocki5.email;

public class EmailMock implements IEmail
{
	private static IEmail uniqueInstance = null;
	
	private EmailMock()
	{
	}

	public static IEmail instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new EmailMock();
		}
		return uniqueInstance;
	}

	@Override
	public boolean sendEmail(String toEmail, String subject, String content)
	{
		return true;
	}
}