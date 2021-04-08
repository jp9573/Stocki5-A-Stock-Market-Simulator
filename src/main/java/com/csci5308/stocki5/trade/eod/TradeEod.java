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

	public boolean markFailedBuyOrder(ITradeDb iTradeDb)
	{
		List<ITrade> iTrades = iTradeDb.getPendingTrades(TradeType.BUY);
		Iterator<ITrade> tradesIterator = iTrades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade iTrade = tradesIterator.next();
			iTrade.setStatus(TradeStatus.FAILED);
		}
		return iTradeDb.updateBulkTradeStatus(iTrades);
	}

	public boolean markFailedSellOrder(ITradeDb iTradeDb)
	{
		List<ITrade> iTrades = iTradeDb.getPendingTrades(TradeType.SELL);
		Iterator<ITrade> tradesIterator = iTrades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade iTrade = tradesIterator.next();
			iTrade.setStatus(TradeStatus.FAILED);
		}
		return iTradeDb.updateBulkTradeStatus(iTrades);
	}

}
