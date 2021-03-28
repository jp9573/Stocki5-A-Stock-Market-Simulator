package com.csci5308.stocki5.trade.order;

import java.util.List;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeDbInterface;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
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

	public void markFailedSellOrder(TradeDbInterface dbInterface) {
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.SELL);
		for (Trade trade : trades) {
			trade.setStatus(TradeStatus.FAILED);
		}
		dbInterface.updateBulkTradeStatus(trades);
	}

}
