package com.csci5308.stocki5.stock.history;

import com.csci5308.stocki5.stock.observer.IObserverMock;

public class StockMaintainHistoryObserverMock implements IObserverMock
{
	@Override
	public boolean update()
	{
		return true;
	}
}
