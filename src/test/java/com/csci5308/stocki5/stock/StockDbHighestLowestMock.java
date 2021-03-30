package com.csci5308.stocki5.stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csci5308.stocki5.stock.db.IStockDbHighestLowest;

public class StockDbHighestLowestMock implements IStockDbHighestLowest
{

	@Override
	public List<Stock> getHighestPriceStocks(String segments, int limit)
	{
		List<Stock> stockArrayList = new ArrayList<Stock>();
		final String[] stocksName = { "XYZ", "ABC", "DEW", "OBJ", "PQL", "LMN", "OYO", "BVN" };

		for (String aStockName : stocksName)
		{
			Stock stock = new Stock();
			stock.setSymbol(aStockName);
			stock.setOpen((float) Math.random());
			stock.setHigh((float) Math.random());
			stock.setLow((float) Math.random());
			stock.setPrice((float) Math.random());
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
			} else if (segments.contains("ISE"))
			{
				stock.setSegment("ISE");
			}
			stockArrayList.add(stock);
		}

		return stockArrayList.subList(0, limit);
	}

	@Override
	public List<Stock> getLowestPriceStocks(String segments, int limit)
	{
		List<Stock> stockArrayList = new ArrayList<Stock>();
		final String[] stocksName = { "XYZ", "ABC", "DEW", "OBJ", "PQL", "LMN", "OYO", "BVN" };

		for (String aStockName : stocksName)
		{
			Stock stock = new Stock();
			stock.setSymbol(aStockName);
			stock.setOpen((float) Math.random());
			stock.setHigh((float) Math.random());
			stock.setLow((float) Math.random());
			stock.setPrice((float) Math.random());
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
			} else if (segments.contains("ISE"))
			{
				stock.setSegment("ISE");
			}
			stockArrayList.add(stock);
		}

		return stockArrayList.subList(0, limit);
	}
}
