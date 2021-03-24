package com.csci5308.stocki5.trade;

import com.csci5308.stocki5.user.User;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.UserDbInterface;

import java.text.DecimalFormat;

@Service
public class TradeBuy {

	public boolean buyStock(String userCode, int stockId, int quantity, StockDbInterface stockDbInterface,
			UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface) {

		DecimalFormat df = new DecimalFormat("##.00");
		
		Trade trade = new Trade(userCode, stockId, TradeType.BUY, quantity, TradeStatus.EXECUTED,
				stockDbInterface, userDbInterface);
		trade.createTradeDetails();

		boolean isFundSufficient = trade.isFundSufficient();
		if (isFundSufficient) {
			trade.generateTradeNumber();
			User user = userDbInterface.getUser(userCode);
			double updatedFunds = user.getFunds() - trade.getTotalBuyPrice();
			userDbInterface.updateUserFunds(userCode, Double.parseDouble(df.format(updatedFunds)));
			tradeDbInterface.insertTrade(trade, true);
		}

		return isFundSufficient;
	}
}
