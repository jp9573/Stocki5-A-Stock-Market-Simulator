package com.csci5308.stocki5.trade.eod;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;

public class TradeEodTest
{
	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	private ITradeScheduler iTradeScheduler = null;

	@Before
	public void createObjects()
	{
		iTradeScheduler = tradeFactoryMock.createTradeSchedulerMock();
	}

	@After
	public void destriyObjects()
	{
		iTradeScheduler = null;
	}
	
	@Test
	public void scheduleFailedBuyOrderTest()
	{
		Assert.assertEquals(true, iTradeScheduler.scheduleFailedBuyOrder());
	}

	@Test
	public void scheduleFailedSellOrderTest()
	{
		Assert.assertEquals(true, iTradeScheduler.scheduleFailedSellOrder());
	}
}
