package com.csci5308.stocki5.trade;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDbInterface;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TradeSell implements TradeSellInterface{

	DecimalFormat df = new DecimalFormat("##.00");

	@Override
	public boolean sellStock(String userCode, int stockId, int quantity, StockDbInterface stockDbInterface,
			UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface, String tradeBuyNumber) {

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

	@Override
	public boolean setSellPrice(String userCode, int stockId, int quantity, float sellPrice, StockDbInterface stockDbInterface, UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface, String tradeBuyNumber) {

		tradeDbInterface.removeHolding(tradeBuyNumber);

		Trade trade = new Trade(userCode, stockId, TradeType.SELL, quantity, TradeStatus.PENDING, stockDbInterface,
				userDbInterface);
		trade.createSetSellPriceTradeDetails(sellPrice);

		trade.generateTradeNumber();
		return tradeDbInterface.insertTrade(trade, false);
	}

	@Override
	public void sellPendingTrades(TradeDbInterface dbInterface, UserDbInterface userDbInterface, List<Stock> stocks) {
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.SELL);
		Map<String, Float> stocksMap = stocks.stream().collect(Collectors.toMap(Stock::getSymbol, Stock::getPrice));

		for (Trade trade : trades) {
			if (trade.getSellPrice() <= stocksMap.get(trade.getSymbol())) {
				dbInterface.removeHoldingForAutoSell(trade.getUserCode(), trade.getStockId(), trade.getQuantity());
				trade.setSellPrice(stocksMap.get(trade.getSymbol()));
				trade.setTotalSellPrice(trade.getQuantity() * trade.getBuyPrice());
				trade.setStatus(TradeStatus.EXECUTED);
				dbInterface.updateSellTrade(trade, false);
				User user = userDbInterface.getUser(trade.getUserCode());
				double updatedFunds = user.getFunds() + (stocksMap.get(trade.getSymbol()) * trade.getQuantity());
				userDbInterface.updateUserFunds(trade.getUserCode(), Double.parseDouble(df.format(updatedFunds)));
			}
		}
	}
}
