package com.csci5308.stocki5.trade.factory;

import com.csci5308.stocki5.stock.observer.IObserverMock;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.eod.ITradeScheduler;

public abstract class TradeAbstractFactoryMock
{
	private static TradeAbstractFactoryMock uniqueInstance = null;

	protected TradeAbstractFactoryMock()
	{
	}

	public static TradeAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new TradeFactoryMock();
		}
		return uniqueInstance;
	}

	public abstract ITradeDb createTradeDbMock();

	public abstract ITradeScheduler createTradeSchedulerMock();

	public abstract IObserverMock createTradeBuyPendingObserverMock();

	public abstract IObserverMock createTradeSellPendingObserverMock();

}
