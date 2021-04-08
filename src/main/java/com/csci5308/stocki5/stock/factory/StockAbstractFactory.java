package com.csci5308.stocki5.stock.factory;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.fetch.IStockFetch;
import com.csci5308.stocki5.stock.history.IStockHistory;
import com.csci5308.stocki5.stock.history.IStockMaintainHistory;
import com.csci5308.stocki5.stock.observer.IObserver;
import com.csci5308.stocki5.stock.observer.Subject;
import com.csci5308.stocki5.stock.prediction.IStockPrediction;
import com.csci5308.stocki5.stock.price.IStockPriceAlgorithm;
import com.csci5308.stocki5.stock.price.IStockPriceEod;
import com.csci5308.stocki5.stock.price.IStockScheduler;

@Service
public abstract class StockAbstractFactory
{
	private static StockAbstractFactory uniqueInstance = null;

	protected StockAbstractFactory()
	{
	}

	public static StockAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockFactory();
		}
		return uniqueInstance;
	}

	public abstract IStock createStock();

	public abstract IStock createStockById(int stockId, IStockDb iStockDb);

	public abstract IStock createStockFromStock(IStock iStock);

	public abstract IStockDb createStockDb();

	public abstract IStockDbGainersLosers createStockDbGainersLosers();

	public abstract IStockFetch createStockFetch();

	public abstract IStockHistory createStockHistory();

	public abstract IStockHistory createStockHistoryByHistoryId(long historyId, String insertTimestamp, IStock iStock);

	public abstract IStockHistoryDb createStockHistoryDb();

	public abstract IStockMaintainHistory createStockMaintainHistory();

	public abstract IObserver createStockMaintainHistoryObserver();

	public abstract IStockPrediction createStockPrediction();

	public abstract IStockPriceAlgorithm createStockPriceAlgorithm();

	public abstract Subject createStockPriceSubject();

	public abstract IStockPriceEod createStockPriceEod();

	public abstract IStockScheduler createStockScheduler();
}
