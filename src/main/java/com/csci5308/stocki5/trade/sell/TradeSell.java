package com.csci5308.stocki5.trade.sell;

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
public class TradeSell implements ITradeSell
{
	private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");

	@Override
	public boolean sellStock(String userCode, int stockId, int quantity, IStockDb stockDbInterface, IUserDb userDbInterface, ITradeDb tradeDbInterface, String tradeBuyNumber)
	{
		Trade trade = new Trade(userCode, stockId, TradeType.SELL, quantity, TradeStatus.EXECUTED, stockDbInterface, userDbInterface);
		boolean isTradeDetailsCreated = trade.createTradeDetails();
		boolean isTradeNumberGenerated = trade.generateTradeNumber();
		if (isTradeDetailsCreated && isTradeNumberGenerated)
		{
			User user = userDbInterface.getUser(userCode);
			double updatedFunds = user.getFunds() + trade.getTotalSellPrice();
			boolean isHoldingRemoved = tradeDbInterface.removeHolding(tradeBuyNumber);
			if (isHoldingRemoved)
			{
				userDbInterface.updateUserFunds(userCode, Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
				return tradeDbInterface.insertTrade(trade, false);
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean setSellPrice(String userCode, int stockId, int quantity, float sellPrice, IStockDb stockDbInterface, IUserDb userDbInterface, ITradeDb tradeDbInterface, String tradeBuyNumber)
	{
		Trade trade = new Trade(userCode, stockId, TradeType.SELL, quantity, TradeStatus.PENDING, stockDbInterface, userDbInterface);
		boolean isSetSellPriceTradeDetailsCreated = trade.createSetSellPriceTradeDetails(sellPrice);
		boolean isTradeNumberGenerated = trade.generateTradeNumber();
		boolean isHoldingRemoved = tradeDbInterface.removeHolding(tradeBuyNumber);
		if (isSetSellPriceTradeDetailsCreated && isTradeNumberGenerated && isHoldingRemoved)
		{
			return tradeDbInterface.insertTrade(trade, false);
		}
		return false;
	}

	@Override
	public void sellPendingTrades(ITradeDb dbInterface, IUserDb userDbInterface, List<IStock> stocks)
	{
		List<Trade> trades = dbInterface.getPendingTrades(TradeType.SELL);
		Map<String, Float> stocksMap = stocks.stream().collect(Collectors.toMap(IStock::getSymbol, IStock::getPrice));

		Iterator<Trade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			Trade trade = tradesIterator.next();
			if (trade.getSellPrice() <= stocksMap.get(trade.getSymbol()))
			{
				dbInterface.removeHoldingForAutoSell(trade.getUserCode(), trade.getStockId(), trade.getQuantity());
				trade.setSellPrice(stocksMap.get(trade.getSymbol()));
				trade.setTotalSellPrice(trade.getQuantity() * trade.getBuyPrice());
				trade.setStatus(TradeStatus.EXECUTED);
				boolean isTradeUpdated = dbInterface.updateSellTrade(trade, false);
				if (isTradeUpdated)
				{
					User user = userDbInterface.getUser(trade.getUserCode());
					double updatedFunds = user.getFunds() + (stocksMap.get(trade.getSymbol()) * trade.getQuantity());
					userDbInterface.updateUserFunds(trade.getUserCode(), Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
				}
			}
		}
	}
}
