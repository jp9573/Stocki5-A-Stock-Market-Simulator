package com.csci5308.stocki5.trade.sell;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TradeSell implements ITradeSell
{
	private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");

	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();

	@Override
	public boolean sellStock(String userCode, int stockId, int quantity, IStockDb stockDbInterface, IUserDb userDbInterface, ITradeDb tradeDbInterface, String tradeBuyNumber)
	{
		ITrade trade = tradeFactory.createTradeWithData(userCode, stockId, TradeType.SELL, quantity, TradeStatus.EXECUTED, stockDbInterface, userDbInterface);
		boolean isTradeDetailsCreated = trade.createTradeDetails();
		boolean isTradeNumberGenerated = trade.generateTradeNumber();
		if (isTradeDetailsCreated && isTradeNumberGenerated)
		{
			IUser user = userDbInterface.getUser(userCode);
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
		ITrade trade = tradeFactory.createTradeWithData(userCode, stockId, TradeType.SELL, quantity, TradeStatus.PENDING, stockDbInterface, userDbInterface);
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
		List<ITrade> trades = dbInterface.getPendingTrades(TradeType.SELL);
		Map<String, Float> stocksMap = stocks.stream().collect(Collectors.toMap(IStock::getSymbol, IStock::getPrice));

		Iterator<ITrade> tradesIterator = trades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade trade = tradesIterator.next();
			if (trade.getSellPrice() <= stocksMap.get(trade.getSymbol()))
			{
				dbInterface.removeHoldingForAutoSell(trade.getUserCode(), trade.getStockId(), trade.getQuantity());
				trade.setSellPrice(stocksMap.get(trade.getSymbol()));
				trade.setTotalSellPrice(trade.getQuantity() * trade.getBuyPrice());
				trade.setStatus(TradeStatus.EXECUTED);
				boolean isTradeUpdated = dbInterface.updateSellTrade(trade, false);
				if (isTradeUpdated)
				{
					IUser user = userDbInterface.getUser(trade.getUserCode());
					double updatedFunds = user.getFunds() + (stocksMap.get(trade.getSymbol()) * trade.getQuantity());
					userDbInterface.updateUserFunds(trade.getUserCode(), Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
				}
			}
		}
	}
}
