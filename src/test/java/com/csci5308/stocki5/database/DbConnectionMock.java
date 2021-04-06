package com.csci5308.stocki5.database;

public class DbConnectionMock implements IDbConnectionMock
{
	private static IDbConnectionMock uniqueInstance = null;

	private DbConnectionMock()
	{
	}

	public static IDbConnectionMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new DbConnectionMock();
		}
		return uniqueInstance;
	}

	@Override
	public boolean createConnection()
	{
		return true;
	}

	@Override
	public boolean closeConnection()
	{
		return true;
	}

}
