package com.csci5308.stocki5.trade.sell;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.observer.IObserverMock;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;

public class TradeSellPendingObserverTest
{
	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	IObserverMock tradeSellPendingObserverMock = null;

	@Before
	public void createObjects()
	{
		tradeSellPendingObserverMock = tradeFactoryMock.createTradeSellPendingObserverMock();
	}

	@After
	public void destroyObjects()
	{
		tradeSellPendingObserverMock = null;
	}

	@Test
	public void updateTest()
	{
		Assert.assertEquals(true, tradeSellPendingObserverMock.update());
	}
}
