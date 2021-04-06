package com.csci5308.stocki5.config;

public class Stocki5SecurityConfigMock extends WebSecurityConfigurerAdapterMock
{
	private static WebSecurityConfigurerAdapterMock uniqueInstance = null;

	private Stocki5SecurityConfigMock()
	{
	}

	public static WebSecurityConfigurerAdapterMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new Stocki5SecurityConfigMock();
		}
		return uniqueInstance;
	}
	
	@Override
	public boolean configureGlobal()
	{
		return true;
	}

	@Override
	public boolean passwordEncoder()
	{
		return true;
	}

	@Override
	public boolean configure()
	{
		return true;
	}

}
