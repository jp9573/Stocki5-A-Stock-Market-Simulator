package com.csci5308.stocki5.trade.buy;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.User;

@Service
public class TradeBuy implements ITradeBuy
{

	private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");

	public boolean buyStock(String userCode, int stockId, int quantity, IStockDb stockDbInterface, IUserDb userDbInterface, ITradeDb tradeDbInterface)
	{

		Trade trade = new Trade(userCode, stockId, TradeType.BUY, quantity, TradeStatus.EXECUTED, stockDbInterface, userDbInterface);
		boolean isTradeDetailsCreated = trade.createTradeDetails();
		boolean isFundSufficient = trade.isFundSufficient(userDbInterface);
		boolean isTradeNumberGenerated = trade.generateTradeNumber();
		if (isFundSufficient && isTradeDetailsCreated && isTradeNumberGenerated)
		{
			User user = userDbInterface.getUser(userCode);
			double updatedFunds = user.getFunds() - trade.getTotalBuyPrice();
			userDbInterface.updateUserFunds(userCode, Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
			return tradeDbInterface.insertTrade(trade, true);
		}
		return false;
	}

	public boolean setBuyPrice(String userCode, int stockId, int quantity, float buyPrice, IStockDb stockDbInterface, IUserDb userDbInterface, ITradeDb tradeDbInterface)
	{

		Trade trade = new Trade(userCode, stockId, TradeType.BUY, quantity, TradeStatus.PENDING, stockDbInterface, userDbInterface);
		boolean isTradeSetBuyPriceTradeDetailsCreated = trade.createSetBuyPriceTradeDetails(buyPrice);
		boolean isFundSufficient = trade.isFundSufficient(userDbInterface);
		boolean isTradeNumberGenerated = trade.generateTradeNumber();
		if (isFundSufficient && isTradeSetBuyPriceTradeDetailsCreated && isTradeNumberGenerated)
		{
			return tradeDbInterface.insertTrade(trade, false);
		}
		return false;
	}

	public void buyPendingTrades(ITradeDb dbInterface, IUserDb userDbInterface, List<IStock> stocks)
	{
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.BUY);
		Map<String, Float> stocksMap = stocks.stream().collect(Collectors.toMap(IStock::getSymbol, IStock::getPrice));

		Iterator<Trade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			Trade trade = tradesIterator.next();
			if (trade.getBuyPrice() >= stocksMap.get(trade.getSymbol()))
			{
				trade.setBuyPrice(stocksMap.get(trade.getSymbol()));
				trade.setTotalBuyPrice(trade.getQuantity() * trade.getBuyPrice());
				trade.setStatus(TradeStatus.EXECUTED);
				boolean isTradeUpdated = dbInterface.updateBuyTrade(trade, true);
				if (isTradeUpdated)
				{
					User user = userDbInterface.getUser(trade.getUserCode());
					double updatedFunds = user.getFunds() - (stocksMap.get(trade.getSymbol()) * trade.getQuantity());
					userDbInterface.updateUserFunds(trade.getUserCode(), Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
				}
			}
		}
	}

}
