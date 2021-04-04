package com.csci5308.stocki5.stock.price;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class StockPriceEod implements IStockPriceEod
{
	
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
