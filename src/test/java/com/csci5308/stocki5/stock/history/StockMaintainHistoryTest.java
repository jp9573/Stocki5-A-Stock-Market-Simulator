package com.csci5308.stocki5.stock.history;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.StockDbMock;

public class StockMaintainHistoryTest
{
	private StockDbMock stockDbMock = null;
	private StockHistoryDbMock stockHistoryDbMock = null;
	private StockMaintainHistory stockMaintainHistory = null;
	private List<Stock> stocks = null;

	@Before
	public void createObjects()
	{
		stockDbMock = new StockDbMock();
		stockHistoryDbMock = new StockHistoryDbMock();
		stockMaintainHistory = new StockMaintainHistory();
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
