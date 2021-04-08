package com.csci5308.stocki5.user.funds;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class UserFundsTest
{

	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserDb iUserDbMock = null;
	IUserFunds iUserFunds = null;

	@Before
	public void createObjects()
	{
		iUserDbMock = userFactoryMock.createUserDbMock();
		iUserFunds = userFactory.createUserFunds();
		iUserFunds.setResetFundAmount(10000);
	}

	@After
	public void destroyObjects()
	{
		iUserDbMock = null;
		iUserFunds = null;
	}

	@Test
	public void resetFundsTest()
	{
		IUser user = iUserDbMock.getUser("AB1234567");
		boolean resetStatus = iUserFunds.resetFunds(user, iUserDbMock);
		Assert.assertTrue(resetStatus);
	}

	@Test
	public void resetFundsNegativeTest()
	{
		IUser user = iUserDbMock.getUser("AB123456");
		boolean resetStatus = iUserFunds.resetFunds(user, iUserDbMock);
		Assert.assertFalse(resetStatus);
	}
}