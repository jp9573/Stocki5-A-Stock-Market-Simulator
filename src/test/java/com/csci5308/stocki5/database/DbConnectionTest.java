package com.csci5308.stocki5.database;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DbConnectionTest
{
	private IDbConnectionMock iDbConnectionMock = null;

	@Before
	public void createObjects()
	{
		iDbConnectionMock = DbConnectionMock.instance();
	}

	@After
	public void destroyObject()
	{
		iDbConnectionMock = null;
	}

	@Test
	public void createConnectionTest()
	{
		Assert.assertEquals(true, iDbConnectionMock.createConnection());
	}

	@Test
	public void closeConnectionTest()
	{
		Assert.assertEquals(true, iDbConnectionMock.closeConnection());
	}
}
