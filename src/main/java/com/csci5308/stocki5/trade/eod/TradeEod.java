package com.csci5308.stocki5.trade.eod;

import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.db.ITradeDb;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class TradeEod implements ITradeEod
{
	public void markFailedBuyOrder(ITradeDb dbInterface)
	{
		List<ITrade> trades = dbInterface.getPendingTrades(TradeType.BUY);
		Iterator<ITrade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade trade = tradesIterator.next();
			trade.setStatus(TradeStatus.FAILED);
		}
		dbInterface.updateBulkTradeStatus(trades);
	}

	public void markFailedSellOrder(ITradeDb dbInterface)
	{
		List<ITrade> trades = dbInterface.getPendingTrades(TradeType.SELL);
		Iterator<ITrade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade trade = tradesIterator.next();
			trade.setStatus(TradeStatus.FAILED);
		}
		dbInterface.updateBulkTradeStatus(trades);
	}

}
