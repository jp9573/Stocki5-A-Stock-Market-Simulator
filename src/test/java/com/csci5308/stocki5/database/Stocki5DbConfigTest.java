package com.csci5308.stocki5.database;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Stocki5DbConfigTest
{
	private IStocki5DbConfig iStocki5DbConfig = null;

	@Before
	public void createObjects()
	{
		iStocki5DbConfig = Stocki5DbConfigMock.instance();
	}

	@After
	public void destroyObject()
	{
		iStocki5DbConfig = null;
	}

	@Test
	public void getDriverTest()
	{
		Assert.assertEquals("DRIVER", iStocki5DbConfig.getDriver());
	}

	@Test
	public void getDatabaseTest()
	{
		Assert.assertEquals("DATABASE", iStocki5DbConfig.getDatabase());
	}

	@Test
	public void getUsernameTest()
	{
		Assert.assertEquals("USERNAME", iStocki5DbConfig.getUsername());
	}

	@Test
	public void getPasswordTest()
	{
		Assert.assertEquals("PASSWORD", iStocki5DbConfig.getPassword());
	}
}
