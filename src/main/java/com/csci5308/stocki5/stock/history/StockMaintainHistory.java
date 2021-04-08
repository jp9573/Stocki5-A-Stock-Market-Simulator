package com.csci5308.stocki5.stock.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;

@Service
public class StockMaintainHistory implements IStockMaintainHistory
{
	private static final String MAINTAIN_HISTORY_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static IStockMaintainHistory uniqueInstance = null;

	StockAbstractFactory stockFactory = StockAbstractFactory.instance();

	private StockMaintainHistory()
	{
	}

	public static IStockMaintainHistory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockMaintainHistory();
		}
		return uniqueInstance;
	}

	public boolean maintainStocksHistory(List<IStock> iStocks, int noOfVersions, IStockHistoryDb iStockHistoryDb)
	{
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat(MAINTAIN_HISTORY_TIMESTAMP_FORMAT);

		long historyId = date.getTime();
		String insertTimestamp = dateFormatter.format(date);

		List<IStockHistory> stocksHistorys = new ArrayList<>();
		Iterator<IStock> iStocksIterator = iStocks.iterator();
		IStockHistory iStockHistory;
		while (iStocksIterator.hasNext())
		{
			iStockHistory = stockFactory.createStockHistoryByHistoryId(historyId, insertTimestamp, iStocksIterator.next());
			stocksHistorys.add(iStockHistory);
		}

		int noOfStocks = iStocks.size();
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
		return iStockHistoryDb.insertStocksHistory(stocksHistorys);
	}
}
