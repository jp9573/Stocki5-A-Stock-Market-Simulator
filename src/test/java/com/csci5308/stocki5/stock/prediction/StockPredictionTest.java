package com.csci5308.stocki5.stock.prediction;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import com.csci5308.stocki5.stock.history.IStockHistoryDb;

public class StockPredictionTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockFactoryMock.instance();
	private IStockHistoryDb stockHistoryDbMock = null;
	private IStockPrediction stockPrediction = null;
	private List<IStock> stocks = null;

	@Before
	public void createObjects()
	{
		stockHistoryDbMock = stockFactoryMock.createStockHistoryDbMock();
		stockPrediction = stockFactory.createStockPrediction();
	}

	@After
	public void destroyObjects()
	{
		stockHistoryDbMock = null;
		stockPrediction = null;
		stocks = null;
	}

	@Test
	public void predictStockValueTest()
	{
		stocks = stockPrediction.predictStockValue(stockHistoryDbMock, "ABC");
		Assert.assertEquals(13, stocks.get(0).getPrice(), 0);
	}

	@Test
	public void predictStockValueTestNull()
	{
		stocks = stockPrediction.predictStockValue(stockHistoryDbMock, "DEF");
		Assert.assertNull(stocks);
	}

	@Test
	public void predictStockValueTestNegative()
	{
		stocks = stockPrediction.predictStockValue(stockHistoryDbMock, "ABC");
		stocks.get(0).setPrice(-13);
		Assert.assertEquals(-13, stocks.get(0).getPrice(), 0);
	}

	@Test
	public void predictStockValueTestZero()
	{
		stocks = stockPrediction.predictStockValue(stockHistoryDbMock, "ABC");
		stocks.get(0).setPrice(0);
		Assert.assertEquals(0, stocks.get(0).getPrice(), 0);
	}
}
