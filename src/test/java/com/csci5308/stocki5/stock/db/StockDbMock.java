package com.csci5308.stocki5.stock.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;

public class StockDbMock implements IStockDb
{
	StockAbstractFactory stockFactory = StockFactory.instance();
	
	@Override
	public IStock getStock(int stockId)
	{
		IStock stock = stockFactory.createStock();
		if (stockId == 1)
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
		}
		return stock;
	}

	@Override
	public List<IStock> getStocks()
	{
		List<IStock> stocks = new ArrayList<>();
		IStock stock = stockFactory.createStock();
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
		stocks.add(stock);
		return stocks;
	}

	@Override
	public boolean updateStocks(List<IStock> stocks)
	{
		return true;
	}

	@Override
	public List<IStock> getStocksBySegment(String segments)
	{
		List<IStock> stocks = new ArrayList<>();
		IStock stock = stockFactory.createStock();
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
		if (segments.contains("FOREX"))
		{
			stock.setSegment("FOREX");
		} else if (segments.contains("IDE"))
		{
			stock.setSegment("IDE");
		} else if (segments.contains("IDE"))
		{
			stock.setSegment("IDE");
		} else if (segments.contains("ISE"))
		{
			stock.setSegment("ISE");
		}
		stocks.add(stock);
		return stocks;
	}
}
