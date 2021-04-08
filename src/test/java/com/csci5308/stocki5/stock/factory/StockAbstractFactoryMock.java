package com.csci5308.stocki5.stock.factory;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.observer.IObserverMock;
import com.csci5308.stocki5.stock.observer.Subject;
import com.csci5308.stocki5.stock.price.IStockPriceAlgorithmMock;
import com.csci5308.stocki5.stock.price.IStockSchedulerMock;

public abstract class StockAbstractFactoryMock
{
	private static StockAbstractFactoryMock uniqueInstance = null;

	protected StockAbstractFactoryMock()
	{
	}

	public static StockAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockFactoryMock();
		}
		return uniqueInstance;
	}

	public abstract IStockDb createStockDbMock();

	public abstract IStockDbGainersLosers createStockDbGainersLosersMock();

	public abstract IStockHistoryDb createStockHistoryDbMock();

	public abstract IStockSchedulerMock createStockSchedulerMock();

	public abstract IStockPriceAlgorithmMock createStockPriceAlgorithmMock();

	public abstract Subject createStockPriceSubjectMock();

	public abstract IObserverMock createStockMaintainHistoryObserverMock();
}
