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

	private IStockDb iStockDb = null;
	private IUserDb iUserDb = null;
	private ITradeDb iTradeDb = null;
	private ITradeBuy iTradeBuy = null;
	private ITrade tradeFirst = null;
	private ITrade tradeSecond = null;

	@Before
	public void createObjects()
	{
		iStockDb = stockFactoryMock.createStockDbMock();
		iUserDb = userFactoryMock.createUserDbMock();
		iTradeDb = tradeFactoryMock.createTradeDbMock();
		iTradeBuy = tradeFactory.createTradeBuy();
		tradeFirst = tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 10, TradeStatus.EXECUTED, iStockDb, iUserDb);
		tradeSecond = tradeFactory.createTradeWithData("AB123456", 999, TradeType.BUY, 10, TradeStatus.EXECUTED, iStockDb, iUserDb);
	}

	@After
	public void destroyObjects()
	{
		iStockDb = null;
		iUserDb = null;
		iTradeDb = null;
		iTradeBuy = null;
		tradeFirst = null;
		tradeSecond = null;
	}

	@Test
	public void buyStockPositiveTest()
	{
		Assert.assertTrue(iTradeBuy.buyStock("AB123456", 1, 50, iStockDb, iUserDb, iTradeDb));
	}

	@Test
	public void buyStockFundValidationTest()
	{
		Assert.assertFalse(iTradeBuy.buyStock("AB12345678", 1, 50, iStockDb, iUserDb, iTradeDb));
	}

	@Test
	public void buyStockQuantityValidationTest()
	{
		Assert.assertFalse(iTradeBuy.buyStock("AB12345678", 1, 500, iStockDb, iUserDb, iTradeDb));
	}

	@Test
	public void setBuyPricePositiveTest()
	{
		Assert.assertTrue(iTradeBuy.setBuyPrice("AB123456", 1, 50, 500, iStockDb, iUserDb, iTradeDb));
	}

	@Test
	public void setBuyPriceFundValidationTest()
	{
		Assert.assertFalse(iTradeBuy.setBuyPrice("AB12345678", 1, 50, 500, iStockDb, iUserDb, iTradeDb));
	}

	@Test
	public void setBuyPriceQuantityValidationTest()
	{
		Assert.assertFalse(iTradeBuy.setBuyPrice("AB12345678", 1, 500, 500, iStockDb, iUserDb, iTradeDb));
	}

	@Test
	public void createSetBuyPriceTradeDetailsPositiveTest()
	{
		Assert.assertTrue(iTradeBuy.createSetBuyPriceTradeDetails(iStockDb, tradeFirst, 13.0f));
		Assert.assertEquals("ABC", tradeFirst.getSymbol());
		Assert.assertEquals("ISE", tradeFirst.getSegment());
		Assert.assertEquals(TradeType.BUY, tradeFirst.getBuySell());
		Assert.assertEquals(13.0, tradeFirst.getBuyPrice(), 0);
		Assert.assertEquals(130.0, tradeFirst.getTotalBuyPrice(), 0);
	}

	@Test
	public void createSetBuyPriceTradeDetailsNegativeTest()
	{
		Assert.assertTrue(iTradeBuy.createSetBuyPriceTradeDetails(iStockDb, tradeSecond, 0f));
		Assert.assertNull(tradeSecond.getSymbol());
		Assert.assertNull(tradeSecond.getSegment());
		Assert.assertEquals(TradeType.BUY, tradeSecond.getBuySell());
		Assert.assertEquals(0.0, tradeSecond.getBuyPrice(), 0);
		Assert.assertEquals(0.0, tradeSecond.getTotalBuyPrice(), 0);
	}
}