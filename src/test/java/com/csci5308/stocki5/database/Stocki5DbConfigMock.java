package com.csci5308.stocki5.database;

public class Stocki5DbConfigMock implements IStocki5DbConfig
{
	private final String DRIVER = "DRIVER";
	private final String DATABASE = "DATABASE";
	private final String USERNAME = "USERNAME";
	private final String PASSWORD = "PASSWORD";

	private static IStocki5DbConfig uniqueInstance = null;

	private Stocki5DbConfigMock()
	{
	}

	public static IStocki5DbConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new Stocki5DbConfigMock();
		}
		return uniqueInstance;
	}

	@Override
	public String getDriver()
	{
		return DRIVER;
	}

	@Override
	public String getDatabase()
	{
		return DATABASE;
	}

	@Override
	public String getUsername()
	{
		return USERNAME;
	}

	@Override
	public String getPassword()
	{
		return PASSWORD;
	}

}
