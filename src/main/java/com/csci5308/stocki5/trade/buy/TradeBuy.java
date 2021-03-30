package com.csci5308.stocki5.trade.buy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.ITradeDb;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.user.User;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.user.IUserDb;

import java.text.DecimalFormat;

@Service
public class TradeBuy implements ITradeBuy
{

	private DecimalFormat df = new DecimalFormat("##.00");

	public boolean buyStock(String userCode, int stockId, int quantity, IStockDb stockDbInterface,
			IUserDb userDbInterface, ITradeDb tradeDbInterface)
	{

		Trade trade = new Trade(userCode, stockId, TradeType.BUY, quantity, TradeStatus.EXECUTED, stockDbInterface,
				userDbInterface);
		trade.createTradeDetails();

		boolean isFundSufficient = trade.isFundSufficient(userDbInterface);
		if (isFundSufficient)
		{
			trade.generateTradeNumber();
			User user = userDbInterface.getUser(userCode);
			double updatedFunds = user.getFunds() - trade.getTotalBuyPrice();
			userDbInterface.updateUserFunds(userCode, Double.parseDouble(df.format(updatedFunds)));
			tradeDbInterface.insertTrade(trade, true);
		}

		return isFundSufficient;
	}

	public boolean setBuyPrice(String userCode, int stockId, int quantity, float buyPrice, IStockDb stockDbInterface,
			IUserDb userDbInterface, ITradeDb tradeDbInterface)
	{

		Trade trade = new Trade(userCode, stockId, TradeType.BUY, quantity, TradeStatus.PENDING, stockDbInterface,
				userDbInterface);
		trade.createSetBuyPriceTradeDetails(buyPrice);

		boolean isFundSufficient = trade.isSetBuyPriceFundSufficient(userDbInterface);
		if (isFundSufficient)
		{
			trade.generateTradeNumber();
			tradeDbInterface.insertTrade(trade, false);
		}

		return isFundSufficient;
	}

	public void buyPendingTrades(ITradeDb dbInterface, IUserDb userDbInterface, List<Stock> stocks)
	{
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.BUY);
		Map<String, Float> stocksMap = stocks.stream().collect(Collectors.toMap(Stock::getSymbol, Stock::getPrice));

		for (Trade trade : trades)
		{
			if (trade.getBuyPrice() >= stocksMap.get(trade.getSymbol()))
			{
				trade.setBuyPrice(stocksMap.get(trade.getSymbol()));
				trade.setTotalBuyPrice(trade.getQuantity() * trade.getBuyPrice());
				trade.setStatus(TradeStatus.EXECUTED);
				dbInterface.updateBuyTrade(trade, true);
				User user = userDbInterface.getUser(trade.getUserCode());
				double updatedFunds = user.getFunds() - (stocksMap.get(trade.getSymbol()) * trade.getQuantity());
				userDbInterface.updateUserFunds(trade.getUserCode(), Double.parseDouble(df.format(updatedFunds)));
			}
		}
	}

}
