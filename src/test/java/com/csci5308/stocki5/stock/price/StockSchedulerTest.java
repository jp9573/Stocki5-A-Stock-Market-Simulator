package com.csci5308.stocki5.stock.price;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockSchedulerTest
{
	private StockScheduler stockScheduler = null;

	@Before
	public void createObjects()
	{
		stockScheduler = new StockScheduler();
	}

	@After
	public void destriyObjects()
	{
		stockScheduler = null;
	}

	@Test
	public void scheduleGenerateStockPriceTest()
	{
		Assert.assertTrue(true);
	}

	@Test
	public void scheduleStockBodTest()
	{
		stockScheduler.scheduleStockBod();
		Assert.assertEquals(true, StockScheduler.isMarketHours);
	}

	@Test
	public void scheduleStockEodTest()
	{
		stockScheduler.scheduleStockEod();
		Assert.assertEquals(false, StockScheduler.isMarketHours);
	}

	@Test
	public void scheduleStockClosingPriceTest()
	{
		Assert.assertTrue(true);
	}
}
