package com.csci5308.stocki5.trade.sell;

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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeSellTest
{

	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	private IStockDb stockDb = null;
	private IUserDb userDb = null;
	private ITradeDb tradeDb = null;
	private ITradeSell tradeSell = null;
	private ITrade tradeSixth = null;
	private ITrade tradeSeventh = null;

	@Before
	public void createObjects()
	{
		stockDb = stockFactoryMock.createStockDbMock();
		userDb = userFactoryMock.createUserDbMock();
		tradeDb = tradeFactoryMock.createTradeDbMock();
		tradeSell = tradeFactory.createTradeSell();
		tradeSixth = tradeFactory.createTradeWithData("AB123456", 1, TradeType.SELL, 10, TradeStatus.EXECUTED, stockDb, userDb);
		tradeSeventh = tradeFactory.createTradeWithData("AB123456", 999, TradeType.SELL, 10, TradeStatus.EXECUTED, stockDb, userDb);
	}

	@After
	public void destroyObjects()
	{
		stockDb = null;
		userDb = null;
		tradeDb = null;
		tradeSell = null;
		tradeSixth = null;
		tradeSeventh = null;
	}

	@Test
	public void sellStockPositiveTest()
	{
		Assert.assertTrue(tradeSell.sellStock("AB123456", 1, 50, stockDb, userDb, tradeDb, "ABC123"));
	}

	@Test
	public void sellStockNegativeTest()
	{
		Assert.assertFalse(tradeSell.sellStock("AB123456", 999, 50, stockDb, userDb, tradeDb, "ABC1234"));
	}

	@Test
	public void setSellPricePositiveTest()
	{
		Assert.assertTrue(tradeSell.setSellPrice("AB123456", 1, 50, 500, stockDb, userDb, tradeDb, "ABC123"));
	}

	@Test
	public void setSellPriceNegativeTest()
	{
		Assert.assertFalse(tradeSell.setSellPrice("AB123456", 1, 50, 500, stockDb, userDb, tradeDb, "ABC1234"));
	}

	@Test
	public void createSetSellPriceTradeDetailsPositiveTest()
	{
		Assert.assertTrue(tradeSell.createSetSellPriceTradeDetails(stockDb, tradeSixth, 13.0f));
		Assert.assertEquals("ABC", tradeSixth.getSymbol());
		Assert.assertEquals("ISE", tradeSixth.getSegment());
		Assert.assertEquals(TradeType.SELL, tradeSixth.getBuySell());
		Assert.assertEquals(13.0, tradeSixth.getSellPrice(), 0);
		Assert.assertEquals(130.0, tradeSixth.getTotalSellPrice(), 0);
	}

	@Test
	public void createSetSellPriceTradeDetailsNegativeTest()
	{
		Assert.assertTrue(tradeSell.createSetSellPriceTradeDetails(stockDb, tradeSeventh, 0f));
		Assert.assertNull(tradeSeventh.getSymbol());
		Assert.assertNull(tradeSeventh.getSegment());
		Assert.assertEquals(TradeType.SELL, tradeSeventh.getBuySell());
		Assert.assertEquals(0.0, tradeSeventh.getSellPrice(), 0);
		Assert.assertEquals(0.0, tradeSeventh.getTotalSellPrice(), 0);
	}

}