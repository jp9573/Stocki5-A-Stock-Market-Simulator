package com.csci5308.stocki5.email;

public class Stocki5EmailConfigMock implements IStocki5EmailConfig
{
	private final String HOST = "HOST";
	private final String USERNAME = "USERNAME";
	private final String PASSWORD = "PASSWORD";
	private final int PORT = 8080;

	private static IStocki5EmailConfig uniqueInstance = null;

	private Stocki5EmailConfigMock()
	{

	}

	public static IStocki5EmailConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new Stocki5EmailConfigMock();
		}
		return uniqueInstance;
	}

	@Override
	public String getHost()
	{
		return HOST;
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

	@Override
	public int getPort()
	{
		return PORT;
	}

}
