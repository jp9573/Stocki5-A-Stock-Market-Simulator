package com.csci5308.stocki5.stock;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	private IStockDb iStockDbMock = null;
	private IStock iStock = null;

	@Before
	public void createObjects()
	{
		iStockDbMock = stockFactoryMock.createStockDbMock();
		iStock = stockFactory.createStockById(1, iStockDbMock);
	}

	@After
	public void destroyObjects()
	{
		iStockDbMock = null;
		iStock = null;
	}

	@Test
	public void testCalculateHighAndLow()
	{
		iStock.calculateHighAndLow(16);
		iStock.calculateHighAndLow(40);
		Assert.assertEquals(40, iStock.getHigh(), 0);
	}

	@Test
	public void testCalculateHighAndLowNegative()
	{
		iStock.calculateHighAndLow(30);
		iStock.calculateHighAndLow(-40);
		Assert.assertEquals(-40, iStock.getLow(), 0);
	}

	@Test
	public void testCalculateHighAndLowZero()
	{
		iStock.calculateHighAndLow(30);
		iStock.calculateHighAndLow(0);
		Assert.assertEquals(0, iStock.getLow(), 0);
	}
}