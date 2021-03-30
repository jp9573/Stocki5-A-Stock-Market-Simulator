package com.csci5308.stocki5.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Stocki5ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[] { Stocki5AppConfig.class };
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[] { "/" };
	}

}
