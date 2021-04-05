package com.csci5308.stocki5.config;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Stocki5ApplicationInitializerTest
{
	private AbstractAnnotationConfigDispatcherServletInitializerMock initializerMock = null;
	
	@Before
	public void createObjects()
	{
		initializerMock = Stocki5ApplicationInitializerMock.instance();
	}

	@After
	public void destroyObject()
	{
		initializerMock = null;
	}
	
	@Test
	public void getRootConfigClassesTest()
	{
		Assert.assertEquals(true, initializerMock.getRootConfigClasses());
	}

	@Test
	public void getServletConfigClassesTest()
	{
		Assert.assertEquals(true, initializerMock.getServletConfigClasses());
	}

	@Test
	public void getServletMappingsTest()
	{
		Assert.assertEquals(true, initializerMock.getServletMappings());
	}
}
