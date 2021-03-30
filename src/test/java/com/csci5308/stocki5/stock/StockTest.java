package com.csci5308.stocki5.stock;

import org.junit.Assert;
import org.junit.Test;

import com.csci5308.stocki5.stock.db.IStockDb;

public class StockTest {

    IStockDb dbInterface = new StockDbMock();
    Stock stock = new Stock(1, dbInterface);

    @Test
    public void testCalculateHighAndLow() {
        stock.calculateHighAndLow(16);
        Assert.assertEquals(16, stock.getHigh(),0);
    }

}