package com.csci5308.stocki5.stock.factory;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.db.StockDbGainersLosersMock;
import com.csci5308.stocki5.stock.db.StockDbMock;
import com.csci5308.stocki5.stock.db.StockHistoryDbMock;
import com.csci5308.stocki5.stock.history.StockMaintainHistoryObserverMock;
import com.csci5308.stocki5.stock.observer.IObserverMock;
import com.csci5308.stocki5.stock.observer.Subject;
import com.csci5308.stocki5.stock.price.IStockPriceAlgorithmMock;
import com.csci5308.stocki5.stock.price.IStockSchedulerMock;
import com.csci5308.stocki5.stock.price.StockPriceAlgorithmMock;
import com.csci5308.stocki5.stock.price.StockPriceSubjectMock;
import com.csci5308.stocki5.stock.price.StockSchedulerMock;

public class StockFactoryMock extends StockAbstractFactoryMock
{

	@Override
	public IStockDb createStockDbMock()
	{
		return StockDbMock.instance();
	}

	@Override
	public IStockDbGainersLosers createStockDbGainersLosersMock()
	{
		return StockDbGainersLosersMock.instance();
	}

	@Override
	public IStockHistoryDb createStockHistoryDbMock()
	{
		return StockHistoryDbMock.instance();
	}

	@Override
	public IStockSchedulerMock createStockSchedulerMock()
	{
		return new StockSchedulerMock();
	}

	@Override
	public IStockPriceAlgorithmMock createStockPriceAlgorithmMock()
	{
		return StockPriceAlgorithmMock.instance();
	}

	@Override
	public Subject createStockPriceSubjectMock()
	{
		return new StockPriceSubjectMock();
	}

	@Override
	public IObserverMock createStockMaintainHistoryObserverMock()
	{
		return new StockMaintainHistoryObserverMock();
	}

}
