package com.csci5308.stocki5.stock;

import org.junit.Assert;
import org.junit.Test;

public class StockDataUpdateTest {

    StockDbInterface dbInterface = new StockDbMock();
    StockDataUpdate stock = new StockDataUpdate(1, dbInterface);

    @Test
    public void updateStockTest() {
        Assert.assertTrue(stock.updateStock(dbInterface));
    }
}