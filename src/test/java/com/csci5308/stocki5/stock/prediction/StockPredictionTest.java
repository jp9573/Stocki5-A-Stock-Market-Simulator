package com.csci5308.stocki5.stock.prediction;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.history.StockHistory;
import com.csci5308.stocki5.stock.history.StockHistoryDbMock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockPredictionTest {

    private StockHistoryDbMock stockHistoryDbMock = null;

    @Before
    public void createObjects() {
        stockHistoryDbMock = new StockHistoryDbMock();
    }

    @After
    public void destroyObjects() {
        stockHistoryDbMock = null;
    }

    @Test
    public void predictStockValueTest() {
        final String stockSymbol = "ABC";

        List<StockHistory> stockHistories = stockHistoryDbMock.getStockHistoryBySymbol(stockSymbol);

        int totalCount = stockHistories.size();
        List<Stock> stocks = new ArrayList<>();
        float sum = 0.00f;

        if (totalCount > 0) {

            for (StockHistory stockHistory : stockHistories) {
                sum += stockHistory.getPrice();
            }

            float mean = sum / totalCount;
            StockHistory lastHistoryStock = stockHistories.get(totalCount - 1);
            Stock predictedStock = new Stock();
            float changeInPercent = mean / lastHistoryStock.getPrice();

            predictedStock.setStockId(lastHistoryStock.getStockId());
            predictedStock.setSymbol(lastHistoryStock.getSymbol());
            predictedStock.setOpen(lastHistoryStock.getOpen());
            predictedStock.setHigh(lastHistoryStock.getHigh());
            predictedStock.setLow(lastHistoryStock.getLow());
            predictedStock.setPrice(mean);
            predictedStock.setLatestTradingDate(new Date());
            predictedStock.setPreviousClose(lastHistoryStock.getPreviousClose());
            predictedStock.setSegment(lastHistoryStock.getSegment());
            predictedStock.setPercentIncreaseDecrease(changeInPercent);

            stocks.add(predictedStock);
        }

        Assert.assertEquals(1, totalCount);
        Assert.assertEquals(1, stocks.size());
        Assert.assertEquals(13.0f, sum, 0.5);

    }
}
