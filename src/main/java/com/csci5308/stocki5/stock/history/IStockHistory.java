package com.csci5308.stocki5.stock.history;

import com.csci5308.stocki5.stock.IStock;

public interface IStockHistory extends IStock
{
	public long getHistoryId();

	public void setHistoryId(long historyId);

	public String getInsertTimestamp();

	public void setInsertTimestamp(String insertTimestamp);
}
