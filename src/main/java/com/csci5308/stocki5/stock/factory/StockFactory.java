package com.csci5308.stocki5.stock.factory;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.db.StockDb;
import com.csci5308.stocki5.stock.db.StockDbGainersLosers;
import com.csci5308.stocki5.stock.db.StockHistoryDb;
import com.csci5308.stocki5.stock.fetch.IStockFetch;
import com.csci5308.stocki5.stock.fetch.StockFetch;
import com.csci5308.stocki5.stock.history.IStockHistory;
import com.csci5308.stocki5.stock.history.IStockMaintainHistory;
import com.csci5308.stocki5.stock.history.StockHistory;
import com.csci5308.stocki5.stock.history.StockMaintainHistory;
import com.csci5308.stocki5.stock.history.StockMaintainHistoryObserver;
import com.csci5308.stocki5.stock.observer.IObserver;
import com.csci5308.stocki5.stock.observer.Subject;
import com.csci5308.stocki5.stock.prediction.IStockPrediction;
import com.csci5308.stocki5.stock.prediction.StockPrediction;
import com.csci5308.stocki5.stock.price.IStockPriceAlgorithm;
import com.csci5308.stocki5.stock.price.IStockPriceEod;
import com.csci5308.stocki5.stock.price.IStockScheduler;
import com.csci5308.stocki5.stock.price.StockPriceAlgorithm;
import com.csci5308.stocki5.stock.price.StockPriceEod;
import com.csci5308.stocki5.stock.price.StockPriceSubject;
import com.csci5308.stocki5.stock.price.StockScheduler;

@Service
public class StockFactory extends StockAbstractFactory
{
	@Override
	public IStock createStock()
	{
		return new Stock();
	}

	@Override
	public IStock createStockById(int stockId, IStockDb iStockDb)
	{
		return new Stock(stockId, iStockDb);
	}

	@Override
	public IStock createStockFromStock(IStock iStock)
	{
		return new Stock(iStock);
	}

	@Override
	public IStockDb createStockDb()
	{
		return StockDb.instance();
	}

	@Override
	public IStockDbGainersLosers createStockDbGainersLosers()
	{
		return StockDbGainersLosers.instance();
	}

	@Override
	public IStockFetch createStockFetch()
	{
		return StockFetch.instance();
	}

	@Override
	public IStockHistory createStockHistory()
	{
		return new StockHistory();
	}

	@Override
	public IStockHistory createStockHistoryByHistoryId(long historyId, String insertTimestamp, IStock iStock)
	{
		return new StockHistory(historyId, insertTimestamp, iStock);
	}

	@Override
	public IStockHistoryDb createStockHistoryDb()
	{
		return StockHistoryDb.instance();
	}

	@Override
	public IStockMaintainHistory createStockMaintainHistory()
	{
		return StockMaintainHistory.instance();
	}

	@Override
	public IObserver createStockMaintainHistoryObserver()
	{
		return new StockMaintainHistoryObserver();
	}

	@Override
	public IStockPrediction createStockPrediction()
	{
		return StockPrediction.instance();
	}

	@Override
	public IStockPriceAlgorithm createStockPriceAlgorithm()
	{
		return StockPriceAlgorithm.instance();
	}

	@Override
	public Subject createStockPriceSubject()
	{
		return new StockPriceSubject();
	}

	@Override
	public IStockPriceEod createStockPriceEod()
	{
		return StockPriceEod.instance();
	}

	@Override
	public IStockScheduler createStockScheduler()
	{
		return new StockScheduler();
	}

}
