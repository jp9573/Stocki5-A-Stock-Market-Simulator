package com.csci5308.stocki5.trade.sell;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.StockDbMock;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDbMock;
import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.UserDbMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeSellTest {

    private IStockDb stockDb = null;
    private IUserDb userDb = null;
    private ITradeDb tradeDb = null;
    private ITradeSell tradeSell = null;

    @Before
    public void createObjects() {
        stockDb = new StockDbMock();
        userDb = new UserDbMock();
        tradeDb = new TradeDbMock();
        tradeSell = new TradeSell();
    }

    @After
    public void destroyObjects() {
        stockDb = null;
        userDb = null;
        tradeDb = null;
        tradeSell = null;
    }

    @Test
    public void sellStockPositiveTest() {
        Assert.assertTrue(tradeSell.sellStock("AB123456", 1, 50, stockDb, userDb, tradeDb, "ABC123"));
    }

    @Test
    public void sellStockNegativeTest() {
        Assert.assertFalse(tradeSell.sellStock("AB123456", 999, 50, stockDb, userDb, tradeDb, "ABC1234"));
    }

    @Test
    public void setSellPricePositiveTest() {
        Assert.assertTrue(tradeSell.setSellPrice("AB123456", 1, 50, 500, stockDb, userDb, tradeDb, "ABC123"));
    }

    @Test
    public void setSellPriceNegativeTest() {
        Assert.assertFalse(tradeSell.setSellPrice("AB123456", 1, 50, 500, stockDb, userDb, tradeDb, "ABC1234"));
    }

}