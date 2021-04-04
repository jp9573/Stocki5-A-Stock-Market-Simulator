package com.csci5308.stocki5.stock.factory;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.price.IStockSchedulerMock;

public abstract class StockAbstractFactoryMock
{
	public abstract IStockDb createStockDbMock();
	
	public abstract IStockDbGainersLosers createStockDbGainersLosersMock();
	
	public abstract IStockHistoryDb createStockHistoryDbMock();

	public abstract IStockSchedulerMock createStockSchedulerMock();
}
