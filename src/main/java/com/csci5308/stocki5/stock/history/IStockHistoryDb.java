package com.csci5308.stocki5.stock.history;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IStockHistoryDb
{
	public List<IStockHistory> getStockHistoryBySymbol(String symbol);

	public boolean insertStocksHistory(List<IStockHistory> stocksHistory);

	public boolean deleteStockHistoryLesserThanHistoryId(long historyId);

	public int getStocksHistoryCount();

	public long getNthStockHistoryId(int n);
}
