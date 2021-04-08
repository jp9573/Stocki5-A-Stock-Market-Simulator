package com.csci5308.stocki5.stock.price;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;

public class StockSchedulerTest
{
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	private IStockSchedulerMock iStockSchedulerMock = null;

	@Before
	public void createObjects()
	{
		iStockSchedulerMock = stockFactoryMock.createStockSchedulerMock();
	}

	@After
	public void destriyObjects()
	{
		iStockSchedulerMock = null;
	}

	@Test
	public void scheduleGenerateStockPriceTest()
	{
		Assert.assertEquals(true, iStockSchedulerMock.scheduleGenerateStockPrice());
	}

	@Test
	public void scheduleStockBodTest()
	{
		Assert.assertEquals(true, iStockSchedulerMock.scheduleStockBod());
	}

	@Test
	public void scheduleStockEodTest()
	{
		Assert.assertEquals(true, iStockSchedulerMock.scheduleStockEod());
	}

	@Test
	public void scheduleStockClosingPriceTest()
	{
		Assert.assertEquals(true, iStockSchedulerMock.scheduleStockClosingPrice());
	}
}
