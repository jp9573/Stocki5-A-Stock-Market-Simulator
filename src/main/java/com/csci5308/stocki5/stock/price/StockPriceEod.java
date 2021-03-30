package com.csci5308.stocki5.stock.price;

import java.util.Iterator;
import java.util.List;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;

import org.springframework.stereotype.Repository;

@Repository
public class StockPriceEod implements IStockPriceEod
{
	public boolean setStockClosingPrice(IStockDb iStockDb)
	{
		List<Stock> stocks = iStockDb.getStocks();
		Iterator<Stock> stocksIterator = stocks.iterator();
		Stock stock;
		while (stocksIterator.hasNext())
		{
			stock = stocksIterator.next();
			stock.setPreviousClose(stock.getPrice());
		}
		return iStockDb.updateStocks(stocks);
	}
}
