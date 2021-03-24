package com.csci5308.stocki5.trade;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TradeEod {

	public void markFailedBuyOrder(TradeDbInterface dbInterface) {
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.BUY);
		for (Trade trade : trades) {
			trade.setStatus(TradeStatus.FAILED);
		}
		dbInterface.updateBulkTradeStatus(trades);
	}

}
