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
	private IStockPriceAlgorithm iStockPriceAlgorithm = null;
	private IStockPriceAlgorithmMock iStockPriceAlgorithmMock = null;
	private float currentPrice = 0.00f;
	private float previousPrice = 0.00f;
	private int limit = 0;

	@Before
	public void createStock()
	{
		iStockPriceAlgorithm = stockFactory.createStockPriceAlgorithm();
		iStockPriceAlgorithmMock = stockFactoryMock.createStockPriceAlgorithmMock();
		currentPrice = 125.02f;
		previousPrice = 42.63f;
		limit = 10;
	}

	@After
	public void destroyStock()
	{
		iStockPriceAlgorithm = null;
		iStockPriceAlgorithmMock = null;
		currentPrice = 0.00f;
		previousPrice = 0.00f;
		limit = 0;
	}

	@Test
	public void stockPriceAlgorithmTest()
	{
		Assert.assertNotNull(iStockPriceAlgorithm.stockPriceAlgorithm(currentPrice));
	}

	@Test
	public void stockPriceAlgorithmTestNotMoreThanTenPlusStockPrice()
	{
		float maxStockPrice = currentPrice + limit;
		float newStockPrice = iStockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(maxStockPrice >= newStockPrice);
	}

	@Test
	public void stockPriceAlgorithmTestNotLessThanTenMinusStockPrice()
	{
		float minStockPrice = currentPrice - limit;
		float newStockPrice = iStockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(minStockPrice <= newStockPrice);
	}

	@Test
	public void stockPriceAlgorithmTestNegativeNumber()
	{
		currentPrice = -25.00f;
		float newStockPrice = iStockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(newStockPrice >= 0.00f);
	}

	@Test
	public void stockPriceAlgorithmTestZero()
	{
		currentPrice = 0.00f;
		float newStockPrice = iStockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(newStockPrice >= 0.00f);
	}

	@Test
	public void stockPricePercentIncreaseDecreaseTest()
	{
		currentPrice = 47.89f;
		float percent = iStockPriceAlgorithm.stockPricePercentIncreaseDecrease(currentPrice, previousPrice);
		Assert.assertEquals(percent, 12.34f, 0);
	}

	@Test
	public void generateStockPriceTest()
	{
		boolean isStockPriceGenerated = iStockPriceAlgorithmMock.generateStockPrice();
		Assert.assertEquals(true, isStockPriceGenerated);
	}
}
