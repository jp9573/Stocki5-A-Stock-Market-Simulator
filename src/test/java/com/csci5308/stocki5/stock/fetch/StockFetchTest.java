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
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class StockFetchTest
{

	StockAbstractFactory stockFactory = StockFactory.instance();
	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	private IUserDb iUserDbMock = null;
	private IStockDbGainersLosers iStockDbGainersLosersMock = null;
	private IStockDb iStockDbMock = null;
	private IStockFetch iStockFetch = null;
	private IUser iUser = null;

	@Before
	public void createObjects()
	{
		iUserDbMock = userFactoryMock.createUserDbMock();
		iStockDbGainersLosersMock = stockFactoryMock.createStockDbGainersLosersMock();
		iStockDbMock = stockFactoryMock.createStockDbMock();
		iStockFetch = stockFactory.createStockFetch();
		iUser = iUserDbMock.getUser("AB123456");
	}

	@After
	public void destroyObjects()
	{
		iUserDbMock = null;
		iStockDbGainersLosersMock = null;
		iStockDbMock = null;
		iStockFetch = null;
		iUser = null;
	}

	@Test
	public void getUserSegmentsTest()
	{
		Assert.assertEquals("'FOREX','IDE','ICE','ISE'", iStockFetch.generateStockSegmentsList(iUser));
	}

	@Test
	public void getUserSegmentsTestThreeSegments()
	{
		iUser.setForeignExchange(0);
		Assert.assertEquals("'IDE','ICE','ISE'", iStockFetch.generateStockSegmentsList(iUser));
	}

	@Test
	public void getUserSegmentsTestTwoSegments()
	{
		iUser.setForeignExchange(0);
		iUser.setInternationalDerivativeExchange(0);
		Assert.assertEquals("'ICE','ISE'", iStockFetch.generateStockSegmentsList(iUser));
	}

	@Test
	public void getUserSegmentsTestOneSegment()
	{
		iUser.setForeignExchange(0);
		iUser.setInternationalDerivativeExchange(0);
		iUser.setInternationalCommodityExchange(0);
		Assert.assertEquals("'ISE'", iStockFetch.generateStockSegmentsList(iUser));
	}

	@Test
	public void getUserSegmentsTestZeroSegment()
	{
		iUser.setForeignExchange(0);
		iUser.setInternationalDerivativeExchange(0);
		iUser.setInternationalCommodityExchange(0);
		iUser.setInternationalStockExchange(0);
		Assert.assertEquals("", iStockFetch.generateStockSegmentsList(iUser));
	}

	@Test
	public void fetchUserStocksTest()
	{
		iUser.setInternationalDerivativeExchange(0);
		iUser.setInternationalCommodityExchange(0);
		iUser.setInternationalStockExchange(0);
		List<IStock> stocks = iStockFetch.fetchUserStocks(iStockDbMock, iUserDbMock, "AB123456");
		Assert.assertEquals("FOREX", stocks.get(0).getSegment());
	}

	@Test
	public void fetchTopGainerStocksTest()
	{
		iUser.setInternationalDerivativeExchange(0);
		iUser.setInternationalCommodityExchange(0);
		iUser.setInternationalStockExchange(0);
		List<IStock> topGainersStocks = iStockFetch.fetchTopGainerStocks(iStockDbGainersLosersMock, iUserDbMock, "AB123456");
		Assert.assertEquals(5, topGainersStocks.size());
	}

	@Test
	public void fetchTopLoserStocksTest()
	{
		iUser.setInternationalDerivativeExchange(0);
		iUser.setInternationalCommodityExchange(0);
		iUser.setInternationalStockExchange(0);
		List<IStock> topLosersStocks = iStockFetch.fetchTopLoserStocks(iStockDbGainersLosersMock, iUserDbMock, "AB123456");
		Assert.assertEquals(5, topLosersStocks.size());
	}
}
