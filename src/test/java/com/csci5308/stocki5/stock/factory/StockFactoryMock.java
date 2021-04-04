package com.csci5308.stocki5.stock.factory;

import com.csci5308.stocki5.stock.db.*;
import com.csci5308.stocki5.stock.price.IStockSchedulerMock;
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

}
