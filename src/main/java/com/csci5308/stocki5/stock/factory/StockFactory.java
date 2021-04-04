package com.csci5308.stocki5.stock.factory;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.*;
import com.csci5308.stocki5.stock.fetch.IStockFetch;
import com.csci5308.stocki5.stock.fetch.StockFetch;
import com.csci5308.stocki5.stock.history.IStockHistory;
import com.csci5308.stocki5.stock.history.IStockMaintainHistory;
import com.csci5308.stocki5.stock.history.StockHistory;
import com.csci5308.stocki5.stock.history.StockMaintainHistory;
import com.csci5308.stocki5.stock.prediction.IStockPrediction;
import com.csci5308.stocki5.stock.prediction.StockPrediction;
import com.csci5308.stocki5.stock.price.*;
import org.springframework.stereotype.Service;

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
		return new StockFetch();
	}

	@Override
	public IStockHistory createStockHistory()
	{
		return new StockHistory();
	}

	@Override
	public IStockHistory createStockHistoryByHistoryId(long historyId, String insertTimestamp, IStock iStock) {
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
		return new StockMaintainHistory();
	}

	@Override
	public IStockPrediction createStockPrediction()
	{
		return new StockPrediction();
	}

	@Override
	public IStockPriceAlgorithm createStockPriceAlgorithm()
	{
		return new StockPriceAlgorithm();
	}

	@Override
	public IStockPriceEod createStockPriceEod()
	{
		return new StockPriceEod();
	}

	@Override
	public IStockScheduler createStockScheduler()
	{
		return new StockScheduler();
	}

}
