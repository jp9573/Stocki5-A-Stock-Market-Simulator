package com.csci5308.stocki5.trade.eod;

import com.csci5308.stocki5.trade.Trade;
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
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.BUY);
		Iterator<Trade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			Trade trade = tradesIterator.next();
			trade.setStatus(TradeStatus.FAILED);
		}
		dbInterface.updateBulkTradeStatus(trades);
	}

	public void markFailedSellOrder(ITradeDb dbInterface)
	{
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.SELL);
		Iterator<Trade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			Trade trade = tradesIterator.next();
			trade.setStatus(TradeStatus.FAILED);
		}
		dbInterface.updateBulkTradeStatus(trades);
	}

}
