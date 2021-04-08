package com.csci5308.stocki5.trade.holding;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;
import com.csci5308.stocki5.trade.factory.TradeFactory;

public class TradeHoldingTest
{

	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	private ITradeHolding iTradeHolding = null;
	private ITradeDb iTradeDb = null;
	private IStockDb iStockDb = null;

	@Before
	public void createObjects()
	{
		iStockDb = stockFactoryMock.createStockDbMock();
		iTradeDb = tradeFactoryMock.createTradeDbMock();
		iTradeHolding = tradeFactory.createTradeHolding();
	}

	@After
	public void destroyObjects()
	{
		iStockDb = null;
		iTradeDb = null;
		iTradeHolding = null;
	}

	@Test
	public void fetchUserHoldingsTest()
	{
		List<IHolding> iHoldings = iTradeHolding.fetchUserHoldings("AB123456", iTradeDb, iStockDb);
		Iterator<IHolding> holdingIterator = iHoldings.iterator();

		while (holdingIterator.hasNext())
		{
			IHolding holding = holdingIterator.next();
			Assert.assertNotEquals(0, holding.getStockId());
			Assert.assertNotNull(holding.getBuySell());
			Assert.assertNotEquals(0, holding.getQuantity());
			Assert.assertNotNull(holding.getStatus());
			Assert.assertNotNull(holding.getStockDbInterface());
			Assert.assertNotNull(holding.getUserDbInterface());
		}
	}

	@Test
	public void fetchUserHoldingsInvalidUserTest()
	{
		List<IHolding> iHoldings = iTradeHolding.fetchUserHoldings("AB1234", iTradeDb, iStockDb);
		int holdingListSize = iHoldings.size();
		Assert.assertEquals(0, holdingListSize);
	}
}