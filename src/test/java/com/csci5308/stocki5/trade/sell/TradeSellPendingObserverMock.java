package com.csci5308.stocki5.trade.sell;

import com.csci5308.stocki5.stock.observer.IObserverMock;

public class TradeSellPendingObserverMock implements IObserverMock
{
	
	@Override
	public boolean update()
	{
		return true;
	}
	

}
