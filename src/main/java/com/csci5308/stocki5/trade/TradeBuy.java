package com.csci5308.stocki5.trade;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.UserDbInterface;

@Service
public class TradeBuy {

	public boolean buyStock(String userCode, int stockId, int quantity, StockDbInterface stockDbInterface,
			UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface) {
		
		Trade trade = new Trade(userCode, stockId, TradeType.BUY, quantity, TradeStatus.EXECUTED, true,
				stockDbInterface, userDbInterface);
		trade.createTradeDetails();

		boolean isFundSufficient = trade.isFundSufficient();
		if (isFundSufficient) {
			trade.generateTradeNumber();
			tradeDbInterface.insertTrade(trade);
		}

		return isFundSufficient;
	}
}
