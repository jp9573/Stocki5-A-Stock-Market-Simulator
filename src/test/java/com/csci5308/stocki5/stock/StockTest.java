package com.csci5308.stocki5.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.db.StockDbMock;

public class StockTest
{
	private StockDbMock stockDbMock;
	private Stock stock;

	@Before
	public void createObjects()
	{
		stockDbMock = new StockDbMock();
		stock = new Stock(1, stockDbMock);
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