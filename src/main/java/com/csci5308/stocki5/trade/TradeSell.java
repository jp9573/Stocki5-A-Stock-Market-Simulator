package com.csci5308.stocki5.trade;

import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.UserDbInterface;
import org.springframework.stereotype.Service;

@Service
public class TradeSell {

	public boolean sellStock(String userCode, int stockId, int quantity, StockDbInterface stockDbInterface,
			UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface) {
		
		Trade trade = new Trade(userCode, stockId, TradeType.SELL, quantity, TradeStatus.EXECUTED,
				stockDbInterface, userDbInterface);
		trade.createTradeDetails();

		trade.generateTradeNumber();
		return tradeDbInterface.insertTrade(trade, false);
	}
}
