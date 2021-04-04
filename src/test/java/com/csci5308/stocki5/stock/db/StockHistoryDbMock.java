package com.csci5308.stocki5.stock.db;

import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.stock.history.IStockHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockHistoryDbMock implements IStockHistoryDb
{
	private static IStockHistoryDb uniqueInstance = null;

	StockAbstractFactory stockFactory = StockFactory.instance();

	private StockHistoryDbMock() { }

	public static IStockHistoryDb instance(){
		if(null == uniqueInstance){
			uniqueInstance = new StockHistoryDbMock();
		}
		return uniqueInstance;
	}
	
	@Override
	public List<IStockHistory> getStockHistoryBySymbol(String symbol)
	{
		List<IStockHistory> stockHistories = new ArrayList<>();
		IStockHistory stock = stockFactory.createStockHistory();
		if (symbol.equals("ABC"))
		{
			stock.setSymbol("ABC");
			stock.setOpen(10);
			stock.setHigh(15);
			stock.setLow(5);
			stock.setPrice(13);
			Date latestTradingDate = null;
			try
			{
				latestTradingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			stock.setLatestTradingDate(latestTradingDate);
			stock.setPreviousClose(8);
			stock.setSegment("ISE");

			stockHistories.add(stock);
		}

		return stockHistories;
	}

	@Override
	public boolean insertStocksHistory(List<IStockHistory> stocksHistory)
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
