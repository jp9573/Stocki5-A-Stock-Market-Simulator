package com.csci5308.stocki5.stock.price;

import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockSchedulerTest
{
	StockAbstractFactoryMock stockFactoryMock = StockFactoryMock.instance();
	private IStockSchedulerMock stockSchedulerMock = null;

	@Before
	public void createObjects()
	{
		stockSchedulerMock = stockFactoryMock.createStockSchedulerMock();
	}

	@After
	public void destriyObjects()
	{
		stockSchedulerMock = null;
	}

	@Test
	public void scheduleGenerateStockPriceTest()
	{
		Assert.assertEquals(true, stockSchedulerMock.scheduleGenerateStockPrice());
	}

	@Test
	public void scheduleStockBodTest()
	{
		Assert.assertEquals(true, stockSchedulerMock.scheduleStockBod());
	}

	@Test
	public void scheduleStockEodTest()
	{
		Assert.assertEquals(true, stockSchedulerMock.scheduleStockEod());
	}

	@Test
	public void scheduleStockClosingPriceTest()
	{
		Assert.assertEquals(true, stockSchedulerMock.scheduleStockClosingPrice());
	}
}
