package com.csci5308.stocki5.config;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Stocki5AppConfigTest
{
	private IWebMvcConfigurer iWebMvcConfigurer = null;

	@Before
	public void createObjects()
	{
		iWebMvcConfigurer = Stocki5AppConfigMock.instance();
	}

	@After
	public void destroyObject()
	{
		iWebMvcConfigurer = null;
	}

	@Test
	public void propertySourcesPlaceholderConfigurerTest()
	{
		Assert.assertEquals(true, iWebMvcConfigurer.propertySourcesPlaceholderConfigurer());
	}

	@Test
	public void resolverTest()
	{
		Assert.assertEquals(true, iWebMvcConfigurer.propertySourcesPlaceholderConfigurer());
	}

	@Test
	public void addViewControllersTest()
	{
		Assert.assertEquals(true, iWebMvcConfigurer.propertySourcesPlaceholderConfigurer());
	}
}
