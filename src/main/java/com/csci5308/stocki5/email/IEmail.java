package com.csci5308.stocki5.email;

public interface IEmail
{
	public boolean sendEmail(String toEmail, String subject, String content);
}
