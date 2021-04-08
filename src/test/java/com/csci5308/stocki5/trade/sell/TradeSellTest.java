package com.csci5308.stocki5.trade.sell;

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

public class TradeSellTest
{

	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	private IStockDb iStockDb = null;
	private IUserDb iUserDb = null;
	private ITradeDb iTradeDb = null;
	private ITradeSell tradeSell = null;
	private ITrade tradeFirst = null;
	private ITrade tradeSecond = null;

	@Before
	public void createObjects()
	{
		iStockDb = stockFactoryMock.createStockDbMock();
		iUserDb = userFactoryMock.createUserDbMock();
		iTradeDb = tradeFactoryMock.createTradeDbMock();
		tradeSell = tradeFactory.createTradeSell();
		tradeFirst = tradeFactory.createTradeWithData("AB123456", 1, TradeType.SELL, 10, TradeStatus.EXECUTED, iStockDb, iUserDb);
		tradeSecond = tradeFactory.createTradeWithData("AB123456", 999, TradeType.SELL, 10, TradeStatus.EXECUTED, iStockDb, iUserDb);
	}

	@After
	public void destroyObjects()
	{
		iStockDb = null;
		iUserDb = null;
		iTradeDb = null;
		tradeSell = null;
		tradeFirst = null;
		tradeSecond = null;
	}

	@Test
	public void sellStockPositiveTest()
	{
		Assert.assertTrue(tradeSell.sellStock("AB123456", 1, 50, iStockDb, iUserDb, iTradeDb, "ABC123"));
	}

	@Test
	public void sellStockNegativeTest()
	{
		Assert.assertFalse(tradeSell.sellStock("AB123456", 999, 50, iStockDb, iUserDb, iTradeDb, "ABC1234"));
	}

	@Test
	public void setSellPricePositiveTest()
	{
		Assert.assertTrue(tradeSell.setSellPrice("AB123456", 1, 50, 500, iStockDb, iUserDb, iTradeDb, "ABC123"));
	}

	@Test
	public void setSellPriceNegativeTest()
	{
		Assert.assertFalse(tradeSell.setSellPrice("AB123456", 1, 50, 500, iStockDb, iUserDb, iTradeDb, "ABC1234"));
	}

	@Test
	public void createSetSellPriceTradeDetailsPositiveTest()
	{
		Assert.assertTrue(tradeSell.createSetSellPriceTradeDetails(iStockDb, tradeFirst, 13.0f));
		Assert.assertEquals("ABC", tradeFirst.getSymbol());
		Assert.assertEquals("ISE", tradeFirst.getSegment());
		Assert.assertEquals(TradeType.SELL, tradeFirst.getBuySell());
		Assert.assertEquals(13.0, tradeFirst.getSellPrice(), 0);
		Assert.assertEquals(130.0, tradeFirst.getTotalSellPrice(), 0);
	}

	@Test
	public void createSetSellPriceTradeDetailsNegativeTest()
	{
		Assert.assertTrue(tradeSell.createSetSellPriceTradeDetails(iStockDb, tradeSecond, 0f));
		Assert.assertNull(tradeSecond.getSymbol());
		Assert.assertNull(tradeSecond.getSegment());
		Assert.assertEquals(TradeType.SELL, tradeSecond.getBuySell());
		Assert.assertEquals(0.0, tradeSecond.getSellPrice(), 0);
		Assert.assertEquals(0.0, tradeSecond.getTotalSellPrice(), 0);
	}

}