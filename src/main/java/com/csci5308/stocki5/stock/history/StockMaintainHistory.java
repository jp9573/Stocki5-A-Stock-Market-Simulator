package com.csci5308.stocki5.stock.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;

@Service
public class StockMaintainHistory implements IStockMaintainHistory
{
	static final String  MAINTAIN_HISTORY_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public void maintainStocksHistory(List<Stock> stocks, int noOfVersions, IStockHistoryDb iStockHistoryDb)
	{
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat(MAINTAIN_HISTORY_TIMESTAMP_FORMAT);

		long historyId = date.getTime();
		String insertTimestamp = dateFormatter.format(date);

		List<StockHistory> stocksHistorys = new ArrayList<>();
		Iterator<Stock> stocksIterator = stocks.iterator();
		while (stocksIterator.hasNext())
		{
			stocksHistorys.add(new StockHistory(historyId, insertTimestamp, stocksIterator.next()));
		}

		int noOfStocks = stocks.size();
		int currentNoOfStockHistory = iStockHistoryDb.getStocksHistoryCount();
		int currentNoOfVersions = (int) Math.floor(currentNoOfStockHistory / noOfStocks);

		if (currentNoOfVersions >= noOfVersions)
		{
			int extraVersions = currentNoOfVersions - noOfVersions;
			int nthVersion = extraVersions;
			long oldHistoryId = iStockHistoryDb.getNthStockHistoryId(nthVersion);
			oldHistoryId = oldHistoryId + 1;
			iStockHistoryDb.deleteStockHistoryLesserThanHistoryId(oldHistoryId);
		}
		iStockHistoryDb.insertStocksHistory(stocksHistorys);
	}
}
