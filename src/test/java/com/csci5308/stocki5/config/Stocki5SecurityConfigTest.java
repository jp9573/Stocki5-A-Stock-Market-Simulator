package com.csci5308.stocki5.config;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Stocki5SecurityConfigTest
{
	private WebSecurityConfigurerAdapterMock configurerAdapterMock = null;

	@Before
	public void createObjects()
	{
		configurerAdapterMock = Stocki5SecurityConfigMock.instance();
	}

	@After
	public void destroyObject()
	{
		configurerAdapterMock = null;
	}

	@Test
	public void configureGlobalTest()
	{
		Assert.assertEquals(true, configurerAdapterMock.configureGlobal());
	}

	@Test
	public void passwordEncoderTest()
	{
		Assert.assertEquals(true, configurerAdapterMock.passwordEncoder());
	}

	@Test
	public void configureTest()
	{
		Assert.assertEquals(true, configurerAdapterMock.configure());
	}
}
