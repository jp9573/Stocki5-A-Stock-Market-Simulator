package com.csci5308.stocki5.stock.price;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;

public class StockPriceEodTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	private IStockPriceEod iStockPriceEod = null;
	private IStockDb iStockDbMock = null;

	@Before
	public void createObjects()
	{
		iStockPriceEod = stockFactory.createStockPriceEod();
		iStockDbMock = stockFactoryMock.createStockDbMock();
	}

	@After
	public void destroyObjects()
	{
		iStockPriceEod = null;
		iStockDbMock = null;
	}

	@Test
	public void setStockClosingPriceTest()
	{
		boolean isClosingPriceSet = iStockPriceEod.setStockClosingPrice(iStockDbMock);
		Assert.assertEquals(true, isClosingPriceSet);
	}
}
