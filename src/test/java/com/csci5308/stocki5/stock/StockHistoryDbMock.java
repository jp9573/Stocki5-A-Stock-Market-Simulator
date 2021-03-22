package com.csci5308.stocki5.stock;

import java.util.List;

public class StockHistoryDbMock implements StockHistoryDbInterface{

    @Override
    public List<StockHistory> getStockHistory(String stockSymbol) {
        return null;
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
