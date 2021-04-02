package com.csci5308.stocki5.stock.fetch;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.db.UserDbMock;

public class StockFetchTest
{
	
	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockFactoryMock.instance();
	private UserDbMock userDbMock = null;
	private IStockDbGainersLosers gainersLosersMock = null;
	private IStockDb stockDbMock = null;
	private IStockFetch stockFetch = null;
	private User user = null;

	@Before
	public void createObjects()
	{
		userDbMock = new UserDbMock();
		gainersLosersMock = stockFactoryMock.createStockDbGainersLosersMock();
		stockDbMock = stockFactoryMock.createStockDbMock();
		stockFetch = stockFactory.createStockFetch();
		user = userDbMock.getUser("AB123456");
	}

	@After
	public void destroyObjects()
	{
		userDbMock = null;
		gainersLosersMock = null;
		stockDbMock = null;
		stockFetch = null;
		user = null;
	}

	@Test
	public void getUserSegmentsTest()
	{
		Assert.assertEquals("'FOREX','IDE','ICE','ISE'", stockFetch.getUserStockSegments(user));
	}

	@Test
	public void getUserSegmentsTestThreeSegments()
	{
		user.setForeignExchange(0);
		Assert.assertEquals("'IDE','ICE','ISE'", stockFetch.getUserStockSegments(user));
	}

	@Test
	public void getUserSegmentsTestTwoSegments()
	{
		user.setForeignExchange(0);
		user.setInternationalDerivativeExchange(0);
		Assert.assertEquals("'ICE','ISE'", stockFetch.getUserStockSegments(user));
	}

	@Test
	public void getUserSegmentsTestOneSegment()
	{
		user.setForeignExchange(0);
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		Assert.assertEquals("'ISE'", stockFetch.getUserStockSegments(user));
	}

	@Test
	public void getUserSegmentsTestZeroSegment()
	{
		user.setForeignExchange(0);
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		user.setInternationalStockExchange(0);
		Assert.assertEquals("", stockFetch.getUserStockSegments(user));
	}

	@Test
	public void fetchUserStocksTest()
	{
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		user.setInternationalStockExchange(0);
		List<IStock> stocks = stockFetch.fetchUserStocks(stockDbMock, userDbMock, "AB123456");
		Assert.assertEquals("FOREX", stocks.get(0).getSegment());
	}

	@Test
	public void fetchTopGainerStocksTest()
	{
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		user.setInternationalStockExchange(0);
		List<IStock> topGainersStocks = stockFetch.fetchTopGainerStocks(gainersLosersMock, userDbMock, "AB123456");
		Assert.assertEquals(5, topGainersStocks.size());
	}

	@Test
	public void fetchTopLoserStocksTest()
	{
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		user.setInternationalStockExchange(0);
		List<IStock> topLosersStocks = stockFetch.fetchTopLoserStocks(gainersLosersMock, userDbMock, "AB123456");
		Assert.assertEquals(5, topLosersStocks.size());
	}
}
