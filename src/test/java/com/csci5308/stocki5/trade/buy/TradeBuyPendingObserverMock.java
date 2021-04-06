package com.csci5308.stocki5.trade.buy;

import com.csci5308.stocki5.stock.observer.IObserverMock;

public class TradeBuyPendingObserverMock implements IObserverMock
{

	@Override
	public boolean update()
	{
		return true;
	}

}
