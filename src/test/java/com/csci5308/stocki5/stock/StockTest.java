package com.csci5308.stocki5.stock;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockFactoryMock.instance();
	private IStockDb stockDbMock = null;
	private IStock stock = null;

	@Before
	public void createObjects()
	{
		stockDbMock = stockFactoryMock.createStockDbMock();
		stock = stockFactory.createStockById(1, stockDbMock);
	}

	@After
	public void destroyObjects()
	{
		stockDbMock = null;
		stock = null;
	}

	@Test
	public void testCalculateHighAndLow()
	{
		stock.calculateHighAndLow(16);
		stock.calculateHighAndLow(40);
		Assert.assertEquals(40, stock.getHigh(), 0);
	}

	@Test
	public void testCalculateHighAndLowNegative()
	{
		stock.calculateHighAndLow(30);
		stock.calculateHighAndLow(-40);
		Assert.assertEquals(-40, stock.getLow(), 0);
	}

	@Test
	public void testCalculateHighAndLowZero()
	{
		stock.calculateHighAndLow(30);
		stock.calculateHighAndLow(0);
		Assert.assertEquals(0, stock.getLow(), 0);
	}
}