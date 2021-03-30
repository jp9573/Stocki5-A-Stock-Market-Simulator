package com.csci5308.stocki5.stock.price;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.db.StockDbMock;

public class StockPriceEodTest
{
	private StockPriceEod stockPriceEod = null;
	private StockDbMock stockDbMock = null;

	@Before
	public void createObjects()
	{
		stockPriceEod = new StockPriceEod();
		stockDbMock = new StockDbMock();
	}

	@After
	public void destroyObjects()
	{
		stockPriceEod = null;
		stockDbMock = null;
	}

	@Test
	public void setStockClosingPriceTest()
	{
		boolean isClosingPriceSet = stockPriceEod.setStockClosingPrice(stockDbMock);
		Assert.assertEquals(true, isClosingPriceSet);
	}
}
