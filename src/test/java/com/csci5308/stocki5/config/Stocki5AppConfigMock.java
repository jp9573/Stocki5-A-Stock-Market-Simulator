package com.csci5308.stocki5.config;

public class Stocki5AppConfigMock implements IWebMvcConfigurer
{
	private static IWebMvcConfigurer uniqueInstance = null;

	private Stocki5AppConfigMock()
	{
	}

	public static IWebMvcConfigurer instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new Stocki5AppConfigMock();
		}
		return uniqueInstance;
	}

	@Override
	public boolean propertySourcesPlaceholderConfigurer()
	{
		return true;
	}

	@Override
	public boolean resolver()
	{
		return true;
	}

	@Override
	public boolean addViewControllers()
	{
		return true;
	}

}
