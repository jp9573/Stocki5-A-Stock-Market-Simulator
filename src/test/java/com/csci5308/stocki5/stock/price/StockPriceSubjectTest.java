package com.csci5308.stocki5.stock.price;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import com.csci5308.stocki5.stock.observer.Subject;

public class StockPriceSubjectTest
{
	StockAbstractFactoryMock stockFactoryMock = StockFactoryMock.instance();
	Subject stockPriceSubjectMock = null;

	@Before
	public void createObject()
	{
		stockPriceSubjectMock = stockFactoryMock.createStockPriceSubjectMock();
	}

	@After
	public void destroyObject()
	{
		stockPriceSubjectMock = null;
	}

	@Test
	public void stockPriceSubjectTest()
	{
		Assert.assertNotNull(stockPriceSubjectMock);
	}
}
