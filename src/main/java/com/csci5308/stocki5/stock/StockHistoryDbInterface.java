package com.csci5308.stocki5.stock;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockHistoryDbInterface {
    public List<StockHistory> getStockHistory(int stockId);

    public List<StockHistory> getAllStocksHistory();

    public boolean insertStocksHistoryBulk(List<StockHistory> stocksHistory);

    public boolean deleteStockHistoryLesserThanHistoryId(long historyId);

}
