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
	private static IStockDbGainersLosers uniqueInstance = null;

	StockAbstractFactory stockFactory = StockFactory.instance();

	private StockDbGainersLosersMock()
	{
	}

	public static IStockDbGainersLosers instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockDbGainersLosersMock();
		}
		return uniqueInstance;
	}

	@Override
	public List<IStock> getHighestPriceStocks(String segments, int limit)
	{
		List<IStock> iStocks = new ArrayList<>();
		final String[] stocksName = { "XYZ", "ABC", "DEW", "OBJ", "PQL", "LMN", "OYO", "BVN" };

		for (String stockName : stocksName)
		{
			IStock iStock = stockFactory.createStock();
			iStock.setSymbol(stockName);
			iStock.setOpen((float) Math.random());
			iStock.setHigh((float) Math.random());
			iStock.setLow((float) Math.random());
			iStock.setPrice((float) Math.random());
			Date latestTradingDate = null;
			try
			{
				latestTradingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			iStock.setLatestTradingDate(latestTradingDate);
			iStock.setPreviousClose(8);
			if (segments.contains("FOREX"))
			{
				iStock.setSegment("FOREX");
			} else if (segments.contains("IDE"))
			{
				iStock.setSegment("IDE");
			} else if (segments.contains("ISE"))
			{
				iStock.setSegment("ISE");
			}
			iStocks.add(iStock);
		}

		return iStocks.subList(0, limit);
	}

	@Override
	public List<IStock> getLowestPriceStocks(String segments, int limit)
	{
		List<IStock> iStocks = new ArrayList<>();
		final String[] stocksName = { "XYZ", "ABC", "DEW", "OBJ", "PQL", "LMN", "OYO", "BVN" };

		for (String aStockName : stocksName)
		{
			IStock iStock = stockFactory.createStock();
			iStock.setSymbol(aStockName);
			iStock.setOpen((float) Math.random());
			iStock.setHigh((float) Math.random());
			iStock.setLow((float) Math.random());
			iStock.setPrice((float) Math.random());
			Date latestTradingDate = null;
			try
			{
				latestTradingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			iStock.setLatestTradingDate(latestTradingDate);
			iStock.setPreviousClose(8);
			if (segments.contains("FOREX"))
			{
				iStock.setSegment("FOREX");
			} else if (segments.contains("IDE"))
			{
				iStock.setSegment("IDE");
			} else if (segments.contains("ISE"))
			{
				iStock.setSegment("ISE");
			}
			iStocks.add(iStock);
		}

		return iStocks.subList(0, limit);
	}
}
