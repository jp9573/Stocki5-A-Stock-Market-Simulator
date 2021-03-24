package com.csci5308.stocki5.trade;

import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDbInterface;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class TradeSell {

	public boolean sellStock(String userCode, int stockId, int quantity, StockDbInterface stockDbInterface,
			UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface, String tradeBuyNumber) {

		DecimalFormat df = new DecimalFormat("##.00");

		tradeDbInterface.removeHolding(tradeBuyNumber);

		Trade trade = new Trade(userCode, stockId, TradeType.SELL, quantity, TradeStatus.EXECUTED,
				stockDbInterface, userDbInterface);
		trade.createTradeDetails();

		trade.generateTradeNumber();
		User user = userDbInterface.getUser(userCode);
		double updatedFunds = user.getFunds() + trade.getTotalSellPrice();
		userDbInterface.updateUserFunds(userCode, Double.parseDouble(df.format(updatedFunds)));

		return tradeDbInterface.insertTrade(trade, false);
	}
}
