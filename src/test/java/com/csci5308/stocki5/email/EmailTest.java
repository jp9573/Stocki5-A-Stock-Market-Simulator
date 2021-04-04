package com.csci5308.stocki5.email;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmailTest
{
	private IEmail iEmail = null;
	private String toEmail = null;
	private String subject = null;
	private String content = null;

	@Before
	public void createObjects()
	{
		iEmail = EmailMock.instance();
		toEmail = "test@test.com";
		subject = "subject";
		content = "content";
	}

	@After
	public void destroyObject()
	{
		iEmail = null;
		toEmail = null;
		subject = null;
		content = null;
	}

	@Test
	public void sendMailTest()
	{
		Assert.assertEquals(true, iEmail.sendEmail(toEmail, subject, content));
	}
}
