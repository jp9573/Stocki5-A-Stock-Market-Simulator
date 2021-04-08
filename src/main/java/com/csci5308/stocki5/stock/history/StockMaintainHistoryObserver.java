package com.csci5308.stocki5.stock.history;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.observer.IObserver;

public class StockMaintainHistoryObserver implements IObserver
{
	private static final String PROPERTIES_FILE = "config.properties";

	StockAbstractFactory stockFactory = StockAbstractFactory.instance();
	IStockMaintainHistory iStockMaintainHistory = stockFactory.createStockMaintainHistory();
	IStockHistoryDb iStockHistoryDb = stockFactory.createStockHistoryDb();
	IStockDb iStockDb = stockFactory.createStockDb();

	private int noOfVersions;

	@Override
	public void update()
	{
		readProperties();
		List<IStock> iStocks = iStockDb.getStocks();
		iStockMaintainHistory.maintainStocksHistory(iStocks, noOfVersions, iStockHistoryDb);
	}

	private void readProperties()
	{
		InputStream inputStream = null;
		try
		{
			Properties prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
			if (inputStream == null)
			{
				throw new FileNotFoundException();
			} else
			{
				prop.load(inputStream);
			}
			this.noOfVersions = Integer.parseInt(prop.getProperty("history.noofversions"));
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

}
