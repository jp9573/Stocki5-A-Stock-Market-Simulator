package com.csci5308.stocki5.trade;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.trade.factory.TradeFactoryMock;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import com.csci5308.stocki5.user.factory.UserFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeTest {

	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
    TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
    TradeAbstractFactory tradeFactory = TradeFactory.instance();
    UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();
    private ITradeDb tradeDb = null;
    private IStockDb stockDb = null;
    private IUserDb userDb = null;
    private ITrade tradeFirst = null;
    private ITrade tradeSecond = null;
    private ITrade tradeThird = null;
    private ITrade tradeForth = null;
    private ITrade tradeFifth = null;
    private ITrade tradeSixth = null;
    private ITrade tradeSeventh = null;

    @Before
    public void createObjects() {
        tradeDb = tradeFactoryMock.createTradeDbMock();
        stockDb = stockFactoryMock.createStockDbMock();
        userDb = userFactoryMock.createUserDbMock();
        tradeFirst = tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 10, TradeStatus.EXECUTED, stockDb, userDb);
        tradeSecond = tradeFactory.createTradeWithData("AB12345678", 1, TradeType.BUY, 100, TradeStatus.EXECUTED, stockDb, userDb);
        tradeThird = tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 100000, TradeStatus.EXECUTED, stockDb, userDb);
        tradeForth = tradeFactory.createTradeWithData("AB123456", 999, TradeType.SELL, 10, TradeStatus.EXECUTED, stockDb, userDb);
        tradeFifth = tradeFactory.createTradeWithData("AB123456", 999, TradeType.BUY, 10, TradeStatus.EXECUTED, stockDb, userDb);
        tradeSixth = tradeFactory.createTradeWithData("AB123456", 1, TradeType.SELL, 10, TradeStatus.EXECUTED, stockDb, userDb);
        tradeSeventh = tradeFactory.createTradeWithData("AB123456", 999, TradeType.SELL, 10, TradeStatus.EXECUTED, stockDb, userDb);
    }

    @After
    public void destroyObjects() {
        tradeDb = null;
        stockDb = null;
        userDb = null;
        tradeFirst = null;
        tradeSecond = null;
        tradeThird = null;
        tradeForth = null;
        tradeFifth = null;
        tradeSixth = null;
        tradeSeventh = null;
    }

    @Test
    public void createTradeDetailsPositiveTest() {
        Assert.assertTrue(tradeFirst.createTradeDetails());
        Assert.assertEquals("ABC", tradeFirst.getSymbol());
        Assert.assertEquals("ISE", tradeFirst.getSegment());
        Assert.assertEquals(TradeType.BUY, tradeFirst.getBuySell());
        Assert.assertEquals(13.0, tradeFirst.getBuyPrice(), 0);
        Assert.assertEquals(130.0, tradeFirst.getTotalBuyPrice(), 0);
    }

    @Test
    public void createTradeDetailsNegativeTest() {
        Assert.assertTrue(tradeForth.createTradeDetails());
        Assert.assertNull(tradeForth.getSymbol());
        Assert.assertNull(tradeForth.getSegment());
        Assert.assertEquals(TradeType.SELL, tradeForth.getBuySell());
        Assert.assertEquals(0.0, tradeForth.getSellPrice(), 0);
        Assert.assertEquals(0.0, tradeForth.getTotalSellPrice(), 0);
    }

    @Test
    public void isFundSufficientPositiveTest() {
        Assert.assertTrue(tradeFirst.isFundSufficient(userDb));
    }

    @Test
    public void isFundSufficientNegativeTest() {
        Assert.assertTrue(tradeSecond.createTradeDetails());
        Assert.assertFalse(tradeSecond.isFundSufficient(userDb));
    }

    @Test
    public void isFundSufficientNegativeTwoTest() {
        Assert.assertTrue(tradeThird.createTradeDetails());
        Assert.assertFalse(tradeThird.isFundSufficient(userDb));
    }

    @Test
    public void createSetBuyPriceTradeDetailsPositiveTest() {
        Assert.assertTrue(tradeFirst.createSetBuyPriceTradeDetails(13.0f));
        Assert.assertEquals("ABC", tradeFirst.getSymbol());
        Assert.assertEquals("ISE", tradeFirst.getSegment());
        Assert.assertEquals(TradeType.BUY, tradeFirst.getBuySell());
        Assert.assertEquals(13.0, tradeFirst.getBuyPrice(), 0);
        Assert.assertEquals(130.0, tradeFirst.getTotalBuyPrice(), 0);
    }

    @Test
    public void createSetBuyPriceTradeDetailsNegativeTest() {
        Assert.assertTrue(tradeFifth.createSetBuyPriceTradeDetails(0f));
        Assert.assertNull(tradeFifth.getSymbol());
        Assert.assertNull(tradeFifth.getSegment());
        Assert.assertEquals(TradeType.BUY, tradeFifth.getBuySell());
        Assert.assertEquals(0.0, tradeFifth.getBuyPrice(), 0);
        Assert.assertEquals(0.0, tradeFifth.getTotalBuyPrice(), 0);
    }

    @Test
    public void createSetSellPriceTradeDetailsPositiveTest() {
        Assert.assertTrue(tradeSixth.createSetSellPriceTradeDetails(13.0f));
        Assert.assertEquals("ABC", tradeSixth.getSymbol());
        Assert.assertEquals("ISE", tradeSixth.getSegment());
        Assert.assertEquals(TradeType.SELL, tradeSixth.getBuySell());
        Assert.assertEquals(13.0, tradeSixth.getSellPrice(), 0);
        Assert.assertEquals(130.0, tradeSixth.getTotalSellPrice(), 0);
    }

    @Test
    public void createSetSellPriceTradeDetailsNegativeTest() {
        Assert.assertTrue(tradeSeventh.createSetSellPriceTradeDetails(0f));
        Assert.assertNull(tradeSeventh.getSymbol());
        Assert.assertNull(tradeSeventh.getSegment());
        Assert.assertEquals(TradeType.SELL, tradeSeventh.getBuySell());
        Assert.assertEquals(0.0, tradeSeventh.getSellPrice(), 0);
        Assert.assertEquals(0.0, tradeSeventh.getTotalSellPrice(), 0);
    }

    @Test
    public void generateTradeNumberTest() {
    }
}