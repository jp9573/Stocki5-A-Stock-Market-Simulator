package com.csci5308.stocki5.stock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StockPredictionTest {

    private StockDbMock stockDbMock = null;

    @Before
    public void createObjects() {
        stockDbMock = new StockDbMock();
    }

    @After
    public void destroyObjects() {
        stockDbMock = null;
    }

    @Test
    public void predictStockValueTest() {

    }
}
