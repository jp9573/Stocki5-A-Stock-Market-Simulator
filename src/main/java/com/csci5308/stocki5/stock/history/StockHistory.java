package com.csci5308.stocki5.stock.history;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;

import org.springframework.stereotype.Service;

@Service
public class StockHistory extends Stock
{
	private long historyId;
	private String insertTimestamp;

	public StockHistory()
	{
		super();
		historyId = 0;
		insertTimestamp = null;
	}

	public StockHistory(int stockId, IStockDb dbInterface)
	{
		super(stockId, dbInterface);
	}

	public StockHistory(long historyIdId, String insertTimestamp, Stock stock)
	{
		super(stock);
		this.historyId = historyIdId;
		this.insertTimestamp = insertTimestamp;
	}

	public long getHistoryId()
	{
		return historyId;
	}

	public void setHistoryId(long historyId)
	{
		this.historyId = historyId;
	}

	public String getInsertTimestamp()
	{
		return insertTimestamp;
	}

	public void setInsertTimestamp(String insertTimestamp)
	{
		this.insertTimestamp = insertTimestamp;
	}

}
