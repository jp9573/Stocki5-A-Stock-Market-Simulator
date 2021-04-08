package com.csci5308.stocki5.stock.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.stock.history.IStockHistory;

public class StockHistoryDbMock implements IStockHistoryDb
{
	private static IStockHistoryDb uniqueInstance = null;

	StockAbstractFactory stockFactory = StockFactory.instance();

	private StockHistoryDbMock()
	{
	}

	public static IStockHistoryDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockHistoryDbMock();
		}
		return uniqueInstance;
	}

	@Override
	public List<IStockHistory> getStockHistoryBySymbol(String symbol)
	{
		List<IStockHistory> iStockHistories = new ArrayList<>();
		IStockHistory iStockHistory = stockFactory.createStockHistory();
		if (symbol.equals("ABC"))
		{
			iStockHistory.setSymbol("ABC");
			iStockHistory.setOpen(10);
			iStockHistory.setHigh(15);
			iStockHistory.setLow(5);
			iStockHistory.setPrice(13);
			Date latestTradingDate = null;
			try
			{
				latestTradingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			iStockHistory.setLatestTradingDate(latestTradingDate);
			iStockHistory.setPreviousClose(8);
			iStockHistory.setSegment("ISE");

			iStockHistories.add(iStockHistory);
		}

		return iStockHistories;
	}

	@Override
	public boolean insertStocksHistory(List<IStockHistory> iStockHistories)
	{
		return true;
	}

	@Override
	public boolean deleteStockHistoryLesserThanHistoryId(long historyId)
	{
		return true;
	}

	@Override
	public int getStocksHistoryCount()
	{
		return 0;
	}

	@Override
	public long getNthStockHistoryId(int n)
	{
		return 0;
	}
}
