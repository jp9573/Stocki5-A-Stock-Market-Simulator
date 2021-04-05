package com.csci5308.stocki5.database;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DbConnectionTest
{
	private IDbConnectionMock iDbConnection = null;

	@Before
	public void createObjects()
	{
		iDbConnection = DbConnectionMock.instance();
	}

	@After
	public void destroyObject()
	{
		iDbConnection = null;
	}

	@Test
	public void createConnectionTest()
	{
		Assert.assertEquals(true, iDbConnection.createConnection());
	}

	@Test
	public void closeConnectionTest()
	{
		Assert.assertEquals(true, iDbConnection.closeConnection());
	}
}
