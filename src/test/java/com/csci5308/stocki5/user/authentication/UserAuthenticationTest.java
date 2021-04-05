package com.csci5308.stocki5.user.authentication;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class UserAuthenticationTest
{
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	private IAuthenticationProviderMock iAuthenticationProviderMock = null;
	private String username;
	private String password;

	@Before
	public void createObjects()
	{
		iAuthenticationProviderMock = userFactoryMock.createUserAuthenticationMock();
	}

	@After
	public void destroyObjects()
	{
		iAuthenticationProviderMock = null;
		username = null;
		password = null;
	}

	@Test
	public void authenticateTest()
	{
		username = "stocki5";
		password = "stocki5@123";
		Assert.assertEquals(true, iAuthenticationProviderMock.authenticate(username, password));
	}

	@Test
	public void authenticateIncorretUsernameTest()
	{
		username = "Stocki5";
		password = "stocki5@123";
		Assert.assertEquals(false, iAuthenticationProviderMock.authenticate(username, password));
	}

	@Test
	public void authenticateNullUsernameTest()
	{
		username = null;
		password = "stocki5@123";
		Assert.assertEquals(false, iAuthenticationProviderMock.authenticate(username, password));
	}

	@Test
	public void authenticateIncorretPasswordTest()
	{
		username = "stocki5";
		password = "stocki5@1234";
		Assert.assertEquals(false, iAuthenticationProviderMock.authenticate(username, password));
	}

	@Test
	public void authenticateNullPasswordTest()
	{
		username = "stocki5";
		password = null;
		Assert.assertEquals(false, iAuthenticationProviderMock.authenticate(username, password));
	}

	@Test
	public void authenticateIncorretUsernamePasswordTest()
	{
		username = "Stocki5";
		password = "stocki5@1234";
		Assert.assertEquals(false, iAuthenticationProviderMock.authenticate(username, password));
	}

	@Test
	public void authenticateNullUsernamePasswordTest()
	{
		username = null;
		password = null;
		Assert.assertEquals(false, iAuthenticationProviderMock.authenticate(username, password));
	}
}
