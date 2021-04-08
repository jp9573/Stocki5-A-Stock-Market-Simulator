package com.csci5308.stocki5.trade.sell;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
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
	public boolean sellStock(String userCode, int stockId, int quantity, IStockDb iStockDb, IUserDb iUserDb, ITradeDb iTradeDb, String tradeBuyNumber)
	{
		ITrade iTrade = tradeFactory.createTradeWithData(userCode, stockId, TradeType.SELL, quantity, TradeStatus.EXECUTED, iStockDb, iUserDb);
		boolean isTradeDetailsCreated = iTrade.createTradeDetails();
		boolean isTradeNumberGenerated = iTrade.generateTradeNumber();
		if (isTradeDetailsCreated && isTradeNumberGenerated)
		{
			IUser iUser = iUserDb.getUser(userCode);
			double updatedFunds = iUser.getFunds() + iTrade.getTotalSellPrice();
			boolean isHoldingRemoved = iTradeDb.removeHolding(tradeBuyNumber);
			if (isHoldingRemoved)
			{
				iUserDb.updateUserFunds(userCode, Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
				return iTradeDb.insertTrade(iTrade, false);
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean setSellPrice(String userCode, int stockId, int quantity, float sellPrice, IStockDb iStockDb, IUserDb iUserDb, ITradeDb iTradeDb, String tradeBuyNumber)
	{
		ITrade iTrade = tradeFactory.createTradeWithData(userCode, stockId, TradeType.SELL, quantity, TradeStatus.PENDING, iStockDb, iUserDb);
		boolean isSetSellPriceTradeDetailsCreated = createSetSellPriceTradeDetails(iStockDb, iTrade, sellPrice);
		boolean isTradeNumberGenerated = iTrade.generateTradeNumber();
		boolean isHoldingRemoved = iTradeDb.removeHolding(tradeBuyNumber);
		if (isSetSellPriceTradeDetailsCreated && isTradeNumberGenerated && isHoldingRemoved)
		{
			return iTradeDb.insertTrade(iTrade, false);
		}
		return false;
	}

	@Override
	public void sellPendingTrades(ITradeDb dbInterface, IUserDb userDbInterface, List<IStock> stocks)
	{
		List<ITrade> iTrades = dbInterface.getPendingTrades(TradeType.SELL);
		Map<String, Float> stocksMap = stocks.stream().collect(Collectors.toMap(IStock::getSymbol, IStock::getPrice));

		Iterator<ITrade> tradesIterator = iTrades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade iTrade = tradesIterator.next();
			if (iTrade.getSellPrice() <= stocksMap.get(iTrade.getSymbol()))
			{
				dbInterface.removeHoldingForAutoSell(iTrade.getUserCode(), iTrade.getStockId(), iTrade.getQuantity());
				iTrade.setSellPrice(stocksMap.get(iTrade.getSymbol()));
				iTrade.setTotalSellPrice(iTrade.getQuantity() * iTrade.getSellPrice());
				iTrade.setStatus(TradeStatus.EXECUTED);
				boolean isTradeUpdated = dbInterface.updateSellTrade(iTrade, false);
				if (isTradeUpdated)
				{
					IUser iUser = userDbInterface.getUser(iTrade.getUserCode());
					double updatedFunds = iUser.getFunds() + (stocksMap.get(iTrade.getSymbol()) * iTrade.getQuantity());
					userDbInterface.updateUserFunds(iTrade.getUserCode(), Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
				}
			}
		}
	}

	public boolean createSetSellPriceTradeDetails(IStockDb stockDbInterface, ITrade iTrade, float sellPrice)
	{
		try
		{
			IStock iStock = stockDbInterface.getStock(iTrade.getStockId());
			iTrade.setSymbol(iStock.getSymbol());
			iTrade.setSegment(iStock.getSegment());
			iTrade.setSellPrice(sellPrice);

			double totalSellPrice = iTrade.getQuantity() * iTrade.getSellPrice();
			iTrade.setTotalSellPrice(totalSellPrice);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}
}
