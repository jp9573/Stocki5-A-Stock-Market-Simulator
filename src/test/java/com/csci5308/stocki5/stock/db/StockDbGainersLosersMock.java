package com.csci5308.stocki5.stock.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;

public class StockDbGainersLosersMock implements IStockDbGainersLosers
{
	StockAbstractFactory stockFactory = StockFactory.instance();

	@Override
	public List<IStock> getHighestPriceStocks(String segments, int limit)
	{
		List<IStock> stockArrayList = new ArrayList<>();
		final String[] stocksName = { "XYZ", "ABC", "DEW", "OBJ", "PQL", "LMN", "OYO", "BVN" };

		for (String aStockName : stocksName)
		{
			IStock stock = stockFactory.createStock();
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
	public List<IStock> getLowestPriceStocks(String segments, int limit)
	{
		List<IStock> stockArrayList = new ArrayList<>();
		final String[] stocksName = { "XYZ", "ABC", "DEW", "OBJ", "PQL", "LMN", "OYO", "BVN" };

		for (String aStockName : stocksName)
		{
			IStock stock = stockFactory.createStock();
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
