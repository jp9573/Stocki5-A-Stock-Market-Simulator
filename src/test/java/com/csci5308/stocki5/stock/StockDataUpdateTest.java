package com.csci5308.stocki5.stock;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class StockDataUpdateTest extends TestCase {

    StockDbInterface dbInterface = new StockDbMock();
    StockDataUpdate stock = new StockDataUpdate(1, dbInterface);
    @Test
    public void testUpdateStock() {
        Assert.assertTrue(stock.updateStock(dbInterface));
    }
}