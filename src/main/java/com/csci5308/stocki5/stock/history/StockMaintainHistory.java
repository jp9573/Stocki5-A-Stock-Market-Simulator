package com.csci5308.stocki5.stock.history;

import com.csci5308.stocki5.stock.Stock;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockMaintainHistory implements IStockMaintainHistory
{

	public void maintainStocksHistory(List<Stock> stocks, int noOfVersions, StockHistoryDbInterface stockHistoryDb)
	{
		Date date = new Date();
		String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateTimeFormat);

		long historyId = date.getTime();
		String insertTimestamp = dateFormatter.format(date);

		List<StockHistory> stocksHistory = new ArrayList<>();
		for (Stock stock : stocks)
		{
			stocksHistory.add(new StockHistory(historyId, insertTimestamp, stock));
		}

		int noOfStocks = stocks.size();
		int currentNoOfStockHistory = stockHistoryDb.getStocksHistoryCount();
		int currentNoOfVersions = (int) Math.floor(currentNoOfStockHistory / noOfStocks);

		if (currentNoOfVersions >= noOfVersions)
		{
			int extraVersions = currentNoOfVersions - noOfVersions;
			int nthVersion = extraVersions;
			long oldHistoryId = stockHistoryDb.getNthOldestStockHistoryId(nthVersion);
			oldHistoryId = oldHistoryId + 1;
			stockHistoryDb.deleteStockHistoryLesserThanHistoryId(oldHistoryId);
		}
		stockHistoryDb.insertStocksHistoryBulk(stocksHistory);
	}
}
