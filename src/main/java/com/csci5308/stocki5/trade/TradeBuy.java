package com.csci5308.stocki5.trade;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.UserDbInterface;

@Service
public class TradeBuy implements TradeBuyInterface {

	public boolean buyStock(String userCode, int stockId, int quantity, StockDbInterface stockDbInterface,
			UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface) {

		Trade trade = new Trade(userCode, stockId, TradeType.BUY, quantity, TradeStatus.EXECUTED, stockDbInterface,
				userDbInterface);
		trade.createTradeDetails();

		boolean isFundSufficient = trade.isFundSufficient(userDbInterface);
		if (isFundSufficient) {
			trade.generateTradeNumber();
			tradeDbInterface.insertTrade(trade, true);
		}

		return isFundSufficient;
	}

	public boolean setBuyPrice(String userCode, int stockId, int quantity, float buyPrice,
			StockDbInterface stockDbInterface, UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface) {

		Trade trade = new Trade(userCode, stockId, TradeType.BUY, quantity, TradeStatus.PENDING, stockDbInterface,
				userDbInterface);
		trade.createSetBuyPriceTradeDetails(buyPrice);

		boolean isFundSufficient = trade.isSetBuyPriceFundSufficient(userDbInterface);
		if (isFundSufficient) {
			trade.generateTradeNumber();
			tradeDbInterface.insertTrade(trade, false);
		}

		return isFundSufficient;
	}

	public void buyPendingTrades(TradeDbInterface dbInterface, UserDbInterface userDbInterface, List<Stock> stocks) {
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.BUY);
		Map<String, Float> stocksMap = stocks.stream().collect(Collectors.toMap(Stock::getSymbol, Stock::getPrice));

		boolean isFundSufficient = false;

		for (Trade trade : trades) {
			if (trade.getBuyPrice() >= stocksMap.get(trade.getSymbol())) {
				trade.setBuyPrice(stocksMap.get(trade.getSymbol()));
				trade.setTotalBuyPrice(trade.getQuantity() * trade.getBuyPrice());
				isFundSufficient = trade.isFundSufficient(userDbInterface);
				if (isFundSufficient) {
					trade.setStatus(TradeStatus.EXECUTED);
					dbInterface.updateBuyTrade(trade, true);
				} else {
					trade.setStatus(TradeStatus.FAILED);
					dbInterface.updateBuyTrade(trade, false);
				}
			}
		}
	}

}
