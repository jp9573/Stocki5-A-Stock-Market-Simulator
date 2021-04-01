package com.csci5308.stocki5.trade.holding;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.StockDbMock;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.db.UserDbMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HoldingTest {

    private Holding holdingFirst = null;
    private Holding holdingSecond = null;
    private IStockDb stockDb = null;
    private IUserDb userDb = null;

    @Before
    public void createObjects() {
        stockDb = new StockDbMock();
        userDb = new UserDbMock();
        holdingFirst = new Holding("AB123456", 1, TradeType.BUY, 10, TradeStatus.EXECUTED, stockDb, userDb, true);
        holdingSecond = new Holding("AB1234567", 999, TradeType.BUY, 5000, TradeStatus.EXECUTED, stockDb, userDb, true);
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