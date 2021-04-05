package com.csci5308.stocki5.stock.price;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;

@Repository
public class StockPriceEod implements IStockPriceEod
{
	private static IStockPriceEod uniqueInstance = null;

	private StockPriceEod()
	{
	}

	public static IStockPriceEod instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockPriceEod();
		}
		return uniqueInstance;
	}

	public boolean setStockClosingPrice(IStockDb iStockDb)
	{
		List<IStock> iStocks = iStockDb.getStocks();
		Iterator<IStock> iStocksIterator = iStocks.iterator();
		IStock iStock;
		while (iStocksIterator.hasNext())
		{
			iStock = iStocksIterator.next();
			iStock.setPreviousClose(iStock.getPrice());
		}
		return iStockDb.updateStocks(iStocks);
	}
}
