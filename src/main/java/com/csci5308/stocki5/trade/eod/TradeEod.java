package com.csci5308.stocki5.trade.eod;

import java.util.List;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.ITradeDb;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import org.springframework.stereotype.Repository;

@Repository
public class TradeEod implements ITradeEod
{
	public void markFailedBuyOrder(ITradeDb dbInterface)
	{
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.BUY);
		for (Trade trade : trades)
		{
			trade.setStatus(TradeStatus.FAILED);
		}
		dbInterface.updateBulkTradeStatus(trades);
	}

	public void markFailedSellOrder(ITradeDb dbInterface)
	{
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.SELL);
		for (Trade trade : trades)
		{
			trade.setStatus(TradeStatus.FAILED);
		}
		dbInterface.updateBulkTradeStatus(trades);
	}

}
