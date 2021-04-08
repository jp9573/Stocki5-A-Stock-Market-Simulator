package com.csci5308.stocki5.stock.prediction;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;

public class StockPredictionTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	private IStockHistoryDb iStockHistoryDbMock = null;
	private IStockPrediction iStockPrediction = null;
	private List<IStock> iStocks = null;

	@Before
	public void createObjects()
	{
		iStockHistoryDbMock = stockFactoryMock.createStockHistoryDbMock();
		iStockPrediction = stockFactory.createStockPrediction();
	}

	@After
	public void destroyObjects()
	{
		iStockHistoryDbMock = null;
		iStockPrediction = null;
		iStocks = null;
	}

	@Test
	public void predictStockValueTest()
	{
		iStocks = iStockPrediction.predictStockValue(iStockHistoryDbMock, "ABC");
		Assert.assertEquals(13, iStocks.get(0).getPrice(), 0);
	}

	@Test
	public void predictStockValueTestNull()
	{
		iStocks = iStockPrediction.predictStockValue(iStockHistoryDbMock, "DEF");
		Assert.assertNull(iStocks);
	}

	@Test
	public void predictStockValueTestNegative()
	{
		iStocks = iStockPrediction.predictStockValue(iStockHistoryDbMock, "ABC");
		iStocks.get(0).setPrice(-13);
		Assert.assertEquals(-13, iStocks.get(0).getPrice(), 0);
	}

	@Test
	public void predictStockValueTestZero()
	{
		iStocks = iStockPrediction.predictStockValue(iStockHistoryDbMock, "ABC");
		iStocks.get(0).setPrice(0);
		Assert.assertEquals(0, iStocks.get(0).getPrice(), 0);
	}
}
