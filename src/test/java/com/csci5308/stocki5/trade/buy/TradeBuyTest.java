package com.csci5308.stocki5.trade.buy;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.StockDbMock;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDbMock;
import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.db.UserDbMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeBuyTest {

    private IStockDb stockDb = null;
    private IUserDb userDb = null;
    private ITradeDb tradeDb = null;
    private ITradeBuy tradeBuy = null;

    @Before
    public void createObjects() {
        stockDb = new StockDbMock();
        userDb = new UserDbMock();
        tradeDb = new TradeDbMock();
        tradeBuy = new TradeBuy();
    }

    @After
    public void destroyObjects() {
        stockDb = null;
        userDb = null;
        tradeDb = null;
        tradeBuy = null;
    }

    @Test
    public void buyStockPositiveTest() {
        Assert.assertTrue(tradeBuy.buyStock("AB123456", 1, 50, stockDb, userDb, tradeDb));
    }

    @Test
    public void buyStockFundValidationTest() {
        Assert.assertFalse(tradeBuy.buyStock("AB12345678", 1, 50, stockDb, userDb, tradeDb));
    }

    @Test
    public void buyStockQuantityValidationTest() {
        Assert.assertFalse(tradeBuy.buyStock("AB12345678", 1, 500, stockDb, userDb, tradeDb));
    }

    @Test
    public void setBuyPricePositiveTest() {
        Assert.assertTrue(tradeBuy.setBuyPrice("AB123456", 1, 50, 500, stockDb, userDb, tradeDb));
    }

    @Test
    public void setBuyPriceFundValidationTest() {
        Assert.assertFalse(tradeBuy.setBuyPrice("AB12345678", 1, 50, 500, stockDb, userDb, tradeDb));
    }

    @Test
    public void setBuyPriceQuantityValidationTest() {
        Assert.assertFalse(tradeBuy.setBuyPrice("AB12345678", 1, 500, 500, stockDb, userDb, tradeDb));
    }
}