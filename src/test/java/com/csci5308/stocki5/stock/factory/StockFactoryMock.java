package com.csci5308.stocki5.stock.factory;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.db.StockDbGainersLosersMock;
import com.csci5308.stocki5.stock.db.StockDbMock;
import com.csci5308.stocki5.stock.history.IStockHistoryDb;
import com.csci5308.stocki5.stock.history.StockHistoryDbMock;
import com.csci5308.stocki5.stock.price.IStockSchedulerMock;
import com.csci5308.stocki5.stock.price.StockSchedulerMock;

public class StockFactoryMock extends StockAbstractFactoryMock
{

	private static StockFactoryMock uniqueInstance = null;

	public static StockAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockFactoryMock();
		}
		return uniqueInstance;
	}

	@Override
	public IStockDb createStockDbMock()
	{
		return new StockDbMock();
	}

	@Override
	public IStockDbGainersLosers createStockDbGainersLosersMock()
	{
		return new StockDbGainersLosersMock();
	}

	@Override
	public IStockHistoryDb createStockHistoryDbMock()
	{
		return new StockHistoryDbMock();
	}

	@Override
	public IStockSchedulerMock createStockSchedulerMock()
	{
		return new StockSchedulerMock();
	}

}
