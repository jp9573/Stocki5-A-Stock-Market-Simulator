package com.csci5308.stocki5.stock;

import com.csci5308.stocki5.stock.history.StockHistory;
import com.csci5308.stocki5.stock.history.StockHistoryDbInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockHistoryDbMock implements StockHistoryDbInterface {

    @Override
    public List<StockHistory> getStockHistory(String stockSymbol) {
        List<StockHistory> stockHistories = new ArrayList<>();
        StockHistory stock = new StockHistory();
        if (stockSymbol.equals("ABC")) {
            stock.setSymbol("ABC");
            stock.setOpen(10);
            stock.setHigh(15);
            stock.setLow(5);
            stock.setPrice(13);
            Date latestTradingDate = null;
            try {
                latestTradingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            stock.setLatestTradingDate(latestTradingDate);
            stock.setPreviousClose(8);
            stock.setSegment("ISE");

            stockHistories.add(stock);
        }

        return stockHistories;
    }

    @Override
    public List<StockHistory> getAllStocksHistory() {
        return null;
    }

    @Override
    public boolean insertStocksHistoryBulk(List<StockHistory> stocksHistory) {
        return true;
    }

    @Override
    public boolean deleteStockHistoryLesserThanHistoryId(long historyId) {
        return true;
    }

    @Override
    public int getStocksHistoryCount() {
        return 0;
    }

    @Override
    public long getNthOldestStockHistoryId(int n) {
        return 0;
    }
}
