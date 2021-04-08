package com.csci5308.stocki5.trade;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class TradeTest
{

	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
	private IStockDb iStockDb = null;
	private IUserDb iUserDb = null;
	private ITrade tradeFirst = null;
	private ITrade tradeSecond = null;
	private ITrade tradeThird = null;
	private ITrade tradeForth = null;

	@Before
	public void createObjects()
	{
		iStockDb = stockFactoryMock.createStockDbMock();
		iUserDb = userFactoryMock.createUserDbMock();
		tradeFirst = tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 10, TradeStatus.EXECUTED, iStockDb, iUserDb);
		tradeSecond = tradeFactory.createTradeWithData("AB12345678", 1, TradeType.BUY, 100, TradeStatus.EXECUTED, iStockDb, iUserDb);
		tradeThird = tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 100000, TradeStatus.EXECUTED, iStockDb, iUserDb);
		tradeForth = tradeFactory.createTradeWithData("AB123456", 999, TradeType.SELL, 10, TradeStatus.EXECUTED, iStockDb, iUserDb);
	}

	@After
	public void destroyObjects()
	{
		iStockDb = null;
		iUserDb = null;
		tradeFirst = null;
		tradeSecond = null;
		tradeThird = null;
		tradeForth = null;
	}

	@Test
	public void createTradeDetailsPositiveTest()
	{
		Assert.assertTrue(tradeFirst.createTradeDetails());
		Assert.assertEquals("ABC", tradeFirst.getSymbol());
		Assert.assertEquals("ISE", tradeFirst.getSegment());
		Assert.assertEquals(TradeType.BUY, tradeFirst.getBuySell());
		Assert.assertEquals(13.0, tradeFirst.getBuyPrice(), 0);
		Assert.assertEquals(130.0, tradeFirst.getTotalBuyPrice(), 0);
	}

	@Test
	public void createTradeDetailsNegativeTest()
	{
		Assert.assertTrue(tradeForth.createTradeDetails());
		Assert.assertNull(tradeForth.getSymbol());
		Assert.assertNull(tradeForth.getSegment());
		Assert.assertEquals(TradeType.SELL, tradeForth.getBuySell());
		Assert.assertEquals(0.0, tradeForth.getSellPrice(), 0);
		Assert.assertEquals(0.0, tradeForth.getTotalSellPrice(), 0);
	}

	@Test
	public void isFundSufficientPositiveTest()
	{
		Assert.assertTrue(tradeFirst.isFundSufficient(iUserDb));
	}

	@Test
	public void isFundSufficientNegativeTest()
	{
		Assert.assertTrue(tradeSecond.createTradeDetails());
		Assert.assertFalse(tradeSecond.isFundSufficient(iUserDb));
	}

	@Test
	public void isFundSufficientNegativeTwoTest()
	{
		Assert.assertTrue(tradeThird.createTradeDetails());
		Assert.assertFalse(tradeThird.isFundSufficient(iUserDb));
	}

	@Test
	public void generateTradeNumberTest()
	{
		tradeFirst.generateTradeNumber();
		Assert.assertTrue(tradeFirst.getTradeNumber().startsWith("AB123456"));
	}
}