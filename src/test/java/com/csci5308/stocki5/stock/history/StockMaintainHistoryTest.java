package com.csci5308.stocki5.stock.history;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;

public class StockMaintainHistoryTest
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	private IStockDb iStockDbMock = null;
	private IStockHistoryDb iStockHistoryDbMock = null;
	private IStockMaintainHistory iStockMaintainHistory = null;
	private List<IStock> iStocks = null;

	@Before
	public void createObjects()
	{
		iStockDbMock = stockFactoryMock.createStockDbMock();
		iStockHistoryDbMock = stockFactoryMock.createStockHistoryDbMock();
		iStockMaintainHistory = stockFactory.createStockMaintainHistory();
		iStocks = iStockDbMock.getStocks();
	}

	@After
	public void destroyObjects()
	{
		iStockDbMock = null;
		iStockHistoryDbMock = null;
		iStockMaintainHistory = null;
		iStocks = null;
	}

	@Test
	public void maintainStocksHistoryTest()
	{
		boolean isHistoryMaintained = iStockMaintainHistory.maintainStocksHistory(iStocks, 0, iStockHistoryDbMock);
		Assert.assertEquals(true, isHistoryMaintained);
	}
}
