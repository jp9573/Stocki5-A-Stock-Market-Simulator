package com.csci5308.stocki5.stock.history;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockHistoryDbInterface {
    public List<StockHistory> getStockHistory(String stockSymbol);

    public List<StockHistory> getAllStocksHistory();

    public boolean insertStocksHistoryBulk(List<StockHistory> stocksHistory);

    public boolean deleteStockHistoryLesserThanHistoryId(long historyId);

    public int getStocksHistoryCount();

    public long getNthOldestStockHistoryId(int n);

}
