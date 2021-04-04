package com.csci5308.stocki5.stock.history;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class StockMaintainHistory implements IStockMaintainHistory
{
	static final String MAINTAIN_HISTORY_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

	StockAbstractFactory stockFactory = StockAbstractFactory.instance();

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
