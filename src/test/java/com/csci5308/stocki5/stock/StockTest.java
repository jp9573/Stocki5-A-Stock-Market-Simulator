package com.csci5308.stocki5.stock;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class StockTest extends TestCase {

    StockDbInterface dbInterface = new StockDbMock();
    Stock stock = new Stock(1, dbInterface);
    @Test
    public void testCalculateHighAndLow() {
        stock.calculateHighAndLow(16);
        Assert.assertEquals(16, stock.getHigh(),0);
    }
}