package com.csci5308.stocki5.stock.history;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.Stock;
import org.springframework.stereotype.Service;

@Service
public class StockHistory extends Stock implements IStockHistory
{
	private long historyId;
	private String insertTimestamp;

	public StockHistory()
	{
		super();
		historyId = 0;
		insertTimestamp = null;
	}

	public StockHistory(long historyId, String insertTimestamp, IStock iStock)
	{
		super(iStock);
		this.historyId = historyId;
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
