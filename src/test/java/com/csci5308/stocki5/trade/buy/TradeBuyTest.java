package com.csci5308.stocki5.trade.buy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class TradeBuyTest
{

	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();

	private IStockDb stockDb = null;
	private IUserDb userDb = null;
	private ITradeDb tradeDb = null;
	private ITradeBuy tradeBuy = null;
	private ITrade tradeFirst = null;
	private ITrade tradeSecond = null;

	@Before
	public void createObjects()
	{
		stockDb = stockFactoryMock.createStockDbMock();
		userDb = userFactoryMock.createUserDbMock();
		tradeDb = tradeFactoryMock.createTradeDbMock();
		tradeBuy = tradeFactory.createTradeBuy();
		tradeFirst = tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 10, TradeStatus.EXECUTED, stockDb, userDb);
		tradeSecond = tradeFactory.createTradeWithData("AB123456", 999, TradeType.BUY, 10, TradeStatus.EXECUTED, stockDb, userDb);
	}

	@After
	public void destroyObjects()
	{
		stockDb = null;
		userDb = null;
		tradeDb = null;
		tradeBuy = null;
		tradeFirst = null;
		tradeSecond = null;
	}

	@Test
	public void buyStockPositiveTest()
	{
		Assert.assertTrue(tradeBuy.buyStock("AB123456", 1, 50, stockDb, userDb, tradeDb));
	}

	@Test
	public void buyStockFundValidationTest()
	{
		Assert.assertFalse(tradeBuy.buyStock("AB12345678", 1, 50, stockDb, userDb, tradeDb));
	}

	@Test
	public void buyStockQuantityValidationTest()
	{
		Assert.assertFalse(tradeBuy.buyStock("AB12345678", 1, 500, stockDb, userDb, tradeDb));
	}

	@Test
	public void setBuyPricePositiveTest()
	{
		Assert.assertTrue(tradeBuy.setBuyPrice("AB123456", 1, 50, 500, stockDb, userDb, tradeDb));
	}

	@Test
	public void setBuyPriceFundValidationTest()
	{
		Assert.assertFalse(tradeBuy.setBuyPrice("AB12345678", 1, 50, 500, stockDb, userDb, tradeDb));
	}

	@Test
	public void setBuyPriceQuantityValidationTest()
	{
		Assert.assertFalse(tradeBuy.setBuyPrice("AB12345678", 1, 500, 500, stockDb, userDb, tradeDb));
	}

	@Test
	public void createSetBuyPriceTradeDetailsPositiveTest()
	{
		Assert.assertTrue(tradeBuy.createSetBuyPriceTradeDetails(stockDb, tradeFirst, 13.0f));
		Assert.assertEquals("ABC", tradeFirst.getSymbol());
		Assert.assertEquals("ISE", tradeFirst.getSegment());
		Assert.assertEquals(TradeType.BUY, tradeFirst.getBuySell());
		Assert.assertEquals(13.0, tradeFirst.getBuyPrice(), 0);
		Assert.assertEquals(130.0, tradeFirst.getTotalBuyPrice(), 0);
	}

	@Test
	public void createSetBuyPriceTradeDetailsNegativeTest()
	{
		Assert.assertTrue(tradeBuy.createSetBuyPriceTradeDetails(stockDb, tradeSecond, 0f));
		Assert.assertNull(tradeSecond.getSymbol());
		Assert.assertNull(tradeSecond.getSegment());
		Assert.assertEquals(TradeType.BUY, tradeSecond.getBuySell());
		Assert.assertEquals(0.0, tradeSecond.getBuyPrice(), 0);
		Assert.assertEquals(0.0, tradeSecond.getTotalBuyPrice(), 0);
	}
}