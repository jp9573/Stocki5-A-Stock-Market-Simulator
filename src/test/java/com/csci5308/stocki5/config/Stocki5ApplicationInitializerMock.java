package com.csci5308.stocki5.config;

public class Stocki5ApplicationInitializerMock extends AbstractAnnotationConfigDispatcherServletInitializerMock
{
	private static AbstractAnnotationConfigDispatcherServletInitializerMock uniqueInstance = null;

	private Stocki5ApplicationInitializerMock()
	{
	}

	public static AbstractAnnotationConfigDispatcherServletInitializerMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new Stocki5ApplicationInitializerMock();
		}
		return uniqueInstance;
	}

	@Override
	protected boolean getRootConfigClasses()
	{
		return true;
	}

	@Override
	protected boolean getServletConfigClasses()
	{
		return true;
	}

	@Override
	protected boolean getServletMappings()
	{
		return true;
	}

}
