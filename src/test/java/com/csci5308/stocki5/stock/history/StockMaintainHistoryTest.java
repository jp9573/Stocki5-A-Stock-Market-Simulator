package com.csci5308.stocki5.stock.history;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StockMaintainHistoryTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	private IStockDb stockDbMock = null;
	private IStockHistoryDb stockHistoryDbMock = null;
	private IStockMaintainHistory stockMaintainHistory = null;
	private List<IStock> stocks = null;

	@Before
	public void createObjects()
	{
		stockDbMock = stockFactoryMock.createStockDbMock();
		stockHistoryDbMock = stockFactoryMock.createStockHistoryDbMock();
		stockMaintainHistory = stockFactory.createStockMaintainHistory();
		stocks = stockDbMock.getStocks();
	}

	@After
	public void destroyObjects()
	{
		stockDbMock = null;
		stockHistoryDbMock = null;
		stockMaintainHistory = null;
		stocks = null;
	}

	@Test
	public void maintainStocksHistoryTest()
	{
		boolean isHistoryMaintained = stockMaintainHistory.maintainStocksHistory(stocks, 0, stockHistoryDbMock);
		Assert.assertEquals(true, isHistoryMaintained);
	}
}
