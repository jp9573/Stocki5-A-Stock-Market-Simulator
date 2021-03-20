package com.csci5308.stocki5.stock;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDbMock;

public class StockFetchTest {

	private UserDbMock userDbMock = null;

	private StockDbMock stockDbMock = null;

	private StockFetch stockFetch = null;

	private User user = null;

	@Before
	public void createObjects() {
		userDbMock = new UserDbMock();
		stockDbMock = new StockDbMock();
		stockFetch = new StockFetch();
		user = userDbMock.getUser("AB123456");
	}

	@After
	public void destroyObjects() {
		userDbMock = null;
		stockDbMock = null;
		stockFetch = null;
		user = null;
	}

	@Test
	public void getUserSegmentsTest() {
		Assert.assertEquals("'FOREX','IDE','ICE','ISE'", stockFetch.getUserSegments(user));
	}

	@Test
	public void getUserSegmentsTestThreeSegments() {
		user.setForeignExchange(0);
		Assert.assertEquals("'IDE','ICE','ISE'", stockFetch.getUserSegments(user));
	}

	@Test
	public void getUserSegmentsTestTwoSegments() {
		user.setForeignExchange(0);
		user.setInternationalDerivativeExchange(0);
		Assert.assertEquals("'ICE','ISE'", stockFetch.getUserSegments(user));
	}

	@Test
	public void getUserSegmentsTestOneSegment() {
		user.setForeignExchange(0);
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		Assert.assertEquals("'ISE'", stockFetch.getUserSegments(user));
	}

	@Test
	public void getUserSegmentsTestZeroSegment() {
		user.setForeignExchange(0);
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		user.setInternationalStockExchange(0);
		Assert.assertEquals("", stockFetch.getUserSegments(user));
	}

	@Test
	public void fetchUserStocksTest() {
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		user.setInternationalStockExchange(0);
		List<Stock> stocks = stockFetch.fetchUserStocks(stockDbMock, userDbMock, "AB123456");
		Assert.assertEquals("FOREX", stocks.get(0).getSegment());
	}

	@Test
	public void fetchTop5GainerStocksTest() {
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		user.setInternationalStockExchange(0);
		List<Stock> top5GainersStocks = stockFetch.fetchTop5GainerStocks(stockDbMock, userDbMock, "AB123456");
		Assert.assertEquals(5, top5GainersStocks.size());
	}

	@Test
	public void fetchTop5LoserStocksTest() {
		user.setInternationalDerivativeExchange(0);
		user.setInternationalCommodityExchange(0);
		user.setInternationalStockExchange(0);
		List<Stock> top5LosersStocks = stockFetch.fetchTop5GainerStocks(stockDbMock, userDbMock, "AB123456");
		Assert.assertEquals(5, top5LosersStocks.size());
	}
}
