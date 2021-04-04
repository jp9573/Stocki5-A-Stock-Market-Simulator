package com.csci5308.stocki5.trade.eod;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.db.ITradeDb;

@Repository
public class TradeEod implements ITradeEod
{
	private static ITradeEod uniqueInstance = null;

	private TradeEod()
	{
	}

	public static ITradeEod instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new TradeEod();
		}
		return uniqueInstance;
	}
	
	public boolean markFailedBuyOrder(ITradeDb dbInterface)
	{
		List<ITrade> trades = dbInterface.getPendingTrades(TradeType.BUY);
		Iterator<ITrade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade trade = tradesIterator.next();
			trade.setStatus(TradeStatus.FAILED);
		}
		return dbInterface.updateBulkTradeStatus(trades);
	}

	public boolean markFailedSellOrder(ITradeDb dbInterface)
	{
		List<ITrade> trades = dbInterface.getPendingTrades(TradeType.SELL);
		Iterator<ITrade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade trade = tradesIterator.next();
			trade.setStatus(TradeStatus.FAILED);
		}
		return dbInterface.updateBulkTradeStatus(trades);
	}

}
