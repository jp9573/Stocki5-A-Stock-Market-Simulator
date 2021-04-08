package com.csci5308.stocki5.stock.db;

import com.csci5308.stocki5.stock.history.IStockHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockHistoryDb
{
	public List<IStockHistory> getStockHistoryBySymbol(String symbol);

	public boolean insertStocksHistory(List<IStockHistory> iStockHistories);

	public boolean deleteStockHistoryLesserThanHistoryId(long historyId);

	public int getStocksHistoryCount();

	public long getNthStockHistoryId(int n);
}
