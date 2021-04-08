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
	private static IStockDb uniqueInstance = null;

	StockAbstractFactory stockFactory = StockFactory.instance();

	private StockDbMock()
	{
	}

	public static IStockDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockDbMock();
		}
		return uniqueInstance;
	}

	@Override
	public IStock getStock(int stockId)
	{
		IStock iStock = stockFactory.createStock();
		if (stockId == 1)
		{
			iStock.setSymbol("ABC");
			iStock.setOpen(10);
			iStock.setHigh(15);
			iStock.setLow(5);
			iStock.setPrice(13);
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
			iStock.setSegment("ISE");
		}
		return iStock;
	}

	@Override
	public List<IStock> getStocks()
	{
		List<IStock> iStocks = new ArrayList<>();
		IStock iStock = stockFactory.createStock();
		iStock.setSymbol("ABC");
		iStock.setOpen(10);
		iStock.setHigh(15);
		iStock.setLow(5);
		iStock.setPrice(13);
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
		iStock.setSegment("ISE");
		iStocks.add(iStock);
		return iStocks;
	}

	@Override
	public boolean updateStocks(List<IStock> iStocks)
	{
		return true;
	}

	@Override
	public List<IStock> getStocksBySegment(String segments)
	{
		List<IStock> iStocks = new ArrayList<>();
		IStock iStock = stockFactory.createStock();
		iStock.setSymbol("ABC");
		iStock.setOpen(10);
		iStock.setHigh(15);
		iStock.setLow(5);
		iStock.setPrice(13);
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
		} else if (segments.contains("IDE"))
		{
			iStock.setSegment("IDE");
		} else if (segments.contains("ISE"))
		{
			iStock.setSegment("ISE");
		}
		iStocks.add(iStock);
		return iStocks;
	}
}
