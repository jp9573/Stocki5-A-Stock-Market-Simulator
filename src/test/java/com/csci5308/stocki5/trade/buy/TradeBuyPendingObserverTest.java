package com.csci5308.stocki5.trade.buy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.observer.IObserverMock;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;

public class TradeBuyPendingObserverTest
{
	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	IObserverMock tradeBuyPendingObserverMock = null;

	@Before
	public void createObjects()
	{
		tradeBuyPendingObserverMock = tradeFactoryMock.createTradeBuyPendingObserverMock();
	}

	@After
	public void destroyObjects()
	{
		tradeBuyPendingObserverMock = null;
	}

	@Test
	public void updateTest()
	{
		Assert.assertEquals(true, tradeBuyPendingObserverMock.update());
	}
}
