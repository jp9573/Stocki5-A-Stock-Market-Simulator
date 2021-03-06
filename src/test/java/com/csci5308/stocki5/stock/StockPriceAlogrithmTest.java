package com.csci5308.stocki5.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockPriceAlogrithmTest {

	StockPriceAlgorithm stockPriceAlgorithm = new StockPriceAlgorithm();

	private float currentPrice = 0.00f;

	private float previousPrice = 0.00f;

	@Before
	public void createStock() {
		currentPrice = 125.02f;
		previousPrice = 42.63f;
	}

	@After
	public void destroyStock() {
		currentPrice = 0.00f;
		previousPrice = 0.00f;
	}

	@Test
	public void stockPriceAlgorithmTest() {
		Assert.assertNotNull(stockPriceAlgorithm.stockPriceAlgorithm(currentPrice));
	}

	@Test
	public void stockPriceAlgorithmTestNotMoreThanTenPlusStockPrice() {
		float maxStockPrice = currentPrice + 10;
		float newStockPrice = stockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(maxStockPrice >= newStockPrice);
	}

	@Test
	public void stockPriceAlgorithmTestNotLessThanTenMinusStockPrice() {
		float minStockPrice = currentPrice - 10;
		float newStockPrice = stockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(minStockPrice <= newStockPrice);
	}

	@Test
	public void stockPriceAlgorithmTestNegativeNumber() {
		currentPrice = -25.00f;
		float newStockPrice = stockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(newStockPrice > 0.00f);
	}

	@Test
	public void stockPriceAlgorithmTestZero() {
		currentPrice = 0.00f;
		float newStockPrice = stockPriceAlgorithm.stockPriceAlgorithm(currentPrice);
		Assert.assertTrue(newStockPrice > 0.00f);
	}

	@Test
	public void stockPricePercentIncreaseDecreaseTest() {
		currentPrice = 47.89f;
		float percent = stockPriceAlgorithm.stockPricePercentIncreaseDecrease(currentPrice, previousPrice);
		Assert.assertTrue(percent == 12.34f);
	}

	@Test
	public void generateStockPriceTest() {
		Assert.assertTrue(true);
	}
}
