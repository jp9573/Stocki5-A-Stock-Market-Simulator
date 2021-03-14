package com.csci5308.stocki5.trade;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.StockDbMock;
import com.csci5308.stocki5.user.UserDbMock;

public class TradeTest {

	Trade trade = null;
	StockDbMock stockDbMock = null;
	UserDbMock userDbMock = null;

	@Before
	public void createTrade() {
		stockDbMock = new StockDbMock();
		userDbMock = new UserDbMock();
		trade = new Trade("AB123456", 1, TradeType.BUY, 5, TradeStatus.EXECUTED, true, stockDbMock, userDbMock);
	}
	
	@After
	public void destroyTrade() {
		stockDbMock = null;
		userDbMock = null;
		trade = null;
	}
	
	@Test
	public void addTradeDetailsTest() {
		trade.addTradeDetails();
		Assert.assertNotNull(trade.getSymbol());
	}
}
