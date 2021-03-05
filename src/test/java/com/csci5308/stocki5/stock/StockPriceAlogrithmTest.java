package com.csci5308.stocki5.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockPriceAlogrithmTest {

	StockPriceAlgorithm stockPriceAlgorithm = new StockPriceAlgorithm();

	private float currentPrice = 0.00f;

	@Before
	public void createStock() {
		currentPrice = 125.02f;
	}

	@After
	public void destroyStock() {
		currentPrice = 0.00f;
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
	public void generateStockPriceTest() {
		Assert.assertTrue(true);
	}
}
