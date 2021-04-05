package com.csci5308.stocki5.stock.price;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockPriceEodTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	private IStockPriceEod stockPriceEod = null;
	private IStockDb stockDbMock = null;

	@Before
	public void createObjects()
	{
		stockPriceEod = stockFactory.createStockPriceEod();
		stockDbMock = stockFactoryMock.createStockDbMock();
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
