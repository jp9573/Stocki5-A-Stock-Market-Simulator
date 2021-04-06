package com.csci5308.stocki5.stock.price;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;

public class StockPriceAlogrithmTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	private IStockPriceAlgorithm stockPriceAlgorithm = null;
	private IStockPriceAlgorithmMock stockPriceAlgorithmMock = null;
	private float currentPrice = 0.00f;
	private float previousPrice = 0.00f;
	private int limit = 0;

	@Before
	public void createStock()
	{
		stockPriceAlgorithm = stockFactory.createStockPriceAlgorithm();
		stockPriceAlgorithmMock = stockFactoryMock.createStockPriceAlgorithmMock();
		currentPrice = 125.02f;
		previousPrice = 42.63f;
		limit = 10;
	}

	@After
	public void destroyStock()
	{
		stockPriceAlgorithm = null;
		stockPriceAlgorithmMock = null;
		currentPrice = 0.00f;
		previousPrice = 0.00f;
		limit = 0;
	}

	@Test
	public void stockPriceAlgorithmTest()
	{
		Assert.assertNotNull(stockPriceAlgorithm.stockPriceAlgorithm(currentPrice));
	}

	@Test
	public void stockPriceAlgorithmTestNotMoreThanTenPlusStockPrice()
	{
		float maxStockPrice = currentPrice + limit;
		float newStockPrice = stockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(maxStockPrice >= newStockPrice);
	}

	@Test
	public void stockPriceAlgorithmTestNotLessThanTenMinusStockPrice()
	{
		float minStockPrice = currentPrice - limit;
		float newStockPrice = stockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(minStockPrice <= newStockPrice);
	}

	@Test
	public void stockPriceAlgorithmTestNegativeNumber()
	{
		currentPrice = -25.00f;
		float newStockPrice = stockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(newStockPrice >= 0.00f);
	}

	@Test
	public void stockPriceAlgorithmTestZero()
	{
		currentPrice = 0.00f;
		float newStockPrice = stockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(newStockPrice >= 0.00f);
	}

	@Test
	public void stockPricePercentIncreaseDecreaseTest()
	{
		currentPrice = 47.89f;
		float percent = stockPriceAlgorithm.stockPricePercentIncreaseDecrease(currentPrice, previousPrice);
		Assert.assertEquals(percent, 12.34f, 0);
	}

	@Test
	public void generateStockPriceTest()
	{
		boolean isStockPriceGenerated = stockPriceAlgorithmMock.generateStockPrice();
		Assert.assertEquals(true, isStockPriceGenerated);
	}
}
