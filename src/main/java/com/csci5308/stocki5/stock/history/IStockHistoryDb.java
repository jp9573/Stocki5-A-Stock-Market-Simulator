package com.csci5308.stocki5.stock.history;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockHistoryDb
{
	public List<StockHistory> getStockHistoryBySymbol(String symbol);

	public boolean insertStocksHistory(List<StockHistory> stocksHistory);

	public boolean deleteStockHistoryLesserThanHistoryId(long historyId);

	public int getStocksHistoryCount();

	public long getNthStockHistoryId(int n);
}
