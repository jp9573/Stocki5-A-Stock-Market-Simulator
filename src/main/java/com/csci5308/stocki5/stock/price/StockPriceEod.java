package com.csci5308.stocki5.stock.price;

import java.util.List;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;

import org.springframework.stereotype.Repository;

@Repository
public class StockPriceEod implements IStockPriceEod
{
	public void setStockClosingPrice(IStockDb stockDbInterface)
	{
		List<Stock> stocks = stockDbInterface.getStocks();
		for (Stock stock : stocks)
		{
			stock.setPreviousClose(stock.getPrice());
		}
		stockDbInterface.updateStocks(stocks);
	}
}
