package com.csci5308.stocki5.trade.factory;

import com.csci5308.stocki5.stock.observer.IObserverMock;
import com.csci5308.stocki5.trade.buy.TradeBuyPendingObserverMock;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDbMock;
import com.csci5308.stocki5.trade.eod.ITradeScheduler;
import com.csci5308.stocki5.trade.eod.TradeEodMock;
import com.csci5308.stocki5.trade.sell.TradeSellPendingObserverMock;

public class TradeFactoryMock extends TradeAbstractFactoryMock
{

	@Override
	public ITradeDb createTradeDbMock()
	{
		return TradeDbMock.instance();
	}

	@Override
	public ITradeScheduler createTradeSchedulerMock()
	{
		return TradeEodMock.instance();
	}

	@Override
	public IObserverMock createTradeBuyPendingObserverMock()
	{
		return new TradeBuyPendingObserverMock();
	}

	@Override
	public IObserverMock createTradeSellPendingObserverMock()
	{
		return new TradeSellPendingObserverMock();
	}
}
