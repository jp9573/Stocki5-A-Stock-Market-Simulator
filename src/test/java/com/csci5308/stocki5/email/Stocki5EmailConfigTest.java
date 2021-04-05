package com.csci5308.stocki5.email;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Stocki5EmailConfigTest
{
	private IStocki5EmailConfig iStocki5EmailConfig = null;

	@Before
	public void createObjects()
	{
		iStocki5EmailConfig = Stocki5EmailConfigMock.instance();
	}

	@After
	public void destroyObject()
	{
		iStocki5EmailConfig = null;
	}

	@Test
	public void getHostTest()
	{
		Assert.assertEquals("HOST", iStocki5EmailConfig.getHost());
	}

	@Test
	public void getUsernameTest()
	{
		Assert.assertEquals("USERNAME", iStocki5EmailConfig.getUsername());
	}

	@Test
	public void getPasswordTest()
	{
		Assert.assertEquals("PASSWORD", iStocki5EmailConfig.getPassword());
	}

	@Test
	public void getPortTest()
	{
		Assert.assertEquals(8080, iStocki5EmailConfig.getPort());
	}
}
