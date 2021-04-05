package com.csci5308.stocki5.stock.price;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.observer.Subject;

public class StockPriceSubject extends Subject
{
	StockAbstractFactory stockFactory = StockAbstractFactory.instance();
	IStockPriceAlgorithm iStockPriceAlgorithm = stockFactory.createStockPriceAlgorithm();
	IStockDb iStockDb = stockFactory.createStockDb();

	public StockPriceSubject()
	{
		iStockPriceAlgorithm.generateStockPrice(iStockDb);
	}
}
