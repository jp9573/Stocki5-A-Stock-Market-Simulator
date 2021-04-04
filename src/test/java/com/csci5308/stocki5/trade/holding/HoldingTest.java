package com.csci5308.stocki5.trade.holding;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;
import com.csci5308.stocki5.user.factory.UserFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HoldingTest {

	StockAbstractFactoryMock stockFactoryMock = StockFactoryMock.instance();
    TradeAbstractFactory tradeFactory = TradeFactory.instance();
    UserAbstractFactoryMock userFactoryMock = UserFactoryMock.instance();
    private IHolding holdingFirst = null;
    private IHolding holdingSecond = null;
    private IStockDb stockDb = null;
    private IUserDb userDb = null;

    @Before
    public void createObjects() {
        stockDb = stockFactoryMock.createStockDbMock();
        userDb = userFactoryMock.createUserDbMock();
        holdingFirst = tradeFactory.createHoldingWithData("AB123456", 1, TradeType.BUY, 10, TradeStatus.EXECUTED, stockDb, userDb, true);
        holdingSecond = tradeFactory.createHoldingWithData("AB1234567", 999, TradeType.BUY, 5000, TradeStatus.EXECUTED, stockDb, userDb, true);
    }

    @After
    public void destroyObjects() {
        holdingFirst = null;
        holdingSecond = null;
        stockDb = null;
        userDb = null;
    }

    @Test
    public void calculateProfitLossPositiveTest() {
        Assert.assertTrue(holdingFirst.createTradeDetails());
        holdingFirst.calculateProfitLoss();
        Assert.assertEquals("ABC", holdingFirst.getSymbol());
        Assert.assertEquals("ISE", holdingFirst.getSegment());
        Assert.assertEquals(TradeType.BUY, holdingFirst.getBuySell());
        Assert.assertEquals(13.0, holdingFirst.getBuyPrice(), 0);
        Assert.assertEquals(13.0, holdingFirst.getSellPrice(), 0);
        Assert.assertEquals(130.0, holdingFirst.getTotalBuyPrice(), 0);
        Assert.assertEquals(130.0, holdingFirst.getTotalSellPrice(), 0);
        Assert.assertEquals(0.0, holdingFirst.getProfitLoss(), 0);
    }

    @Test
    public void calculateProfitLossNegativeTest() {
        Assert.assertTrue(holdingSecond.createTradeDetails());
        holdingSecond.calculateProfitLoss();
        Assert.assertNull(holdingSecond.getSymbol());
        Assert.assertNull(holdingSecond.getSegment());
        Assert.assertEquals(TradeType.BUY, holdingSecond.getBuySell());
        Assert.assertEquals(0.0, holdingSecond.getSellPrice(), 0);
        Assert.assertEquals(0.0, holdingSecond.getTotalSellPrice(), 0);
        Assert.assertEquals(0.0, holdingFirst.getTotalBuyPrice(), 0);
        Assert.assertEquals(0.0, holdingFirst.getTotalSellPrice(), 0);
        Assert.assertEquals(0.0, holdingFirst.getProfitLoss(), 0);
    }
}