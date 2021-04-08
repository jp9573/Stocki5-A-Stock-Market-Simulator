package com.csci5308.stocki5.trade.buy;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public class TradeBuy implements ITradeBuy
{
	private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");

	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();

	public boolean buyStock(String userCode, int stockId, int quantity, IStockDb iStockDb, IUserDb iUserDb, ITradeDb iTradeDb)
	{

		ITrade iTrade = tradeFactory.createTradeWithData(userCode, stockId, TradeType.BUY, quantity, TradeStatus.EXECUTED, iStockDb, iUserDb);
		boolean isTradeDetailsCreated = iTrade.createTradeDetails();
		boolean isFundSufficient = iTrade.isFundSufficient(iUserDb);
		boolean isTradeNumberGenerated = iTrade.generateTradeNumber();
		if (isFundSufficient && isTradeDetailsCreated && isTradeNumberGenerated)
		{
			IUser iUser = iUserDb.getUser(userCode);
			double updatedFunds = iUser.getFunds() - iTrade.getTotalBuyPrice();
			iUserDb.updateUserFunds(userCode, Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
			return iTradeDb.insertTrade(iTrade, true);
		}
		return false;
	}

	public boolean setBuyPrice(String userCode, int stockId, int quantity, float buyPrice, IStockDb iStockDb, IUserDb iUserDb, ITradeDb iTradeDb)
	{
		ITrade iTrade = tradeFactory.createTradeWithData(userCode, stockId, TradeType.BUY, quantity, TradeStatus.PENDING, iStockDb, iUserDb);
		boolean isTradeSetBuyPriceTradeDetailsCreated = createSetBuyPriceTradeDetails(iStockDb, iTrade, buyPrice);
		boolean isFundSufficient = iTrade.isFundSufficient(iUserDb);
		boolean isTradeNumberGenerated = iTrade.generateTradeNumber();
		if (isFundSufficient && isTradeSetBuyPriceTradeDetailsCreated && isTradeNumberGenerated)
		{
			return iTradeDb.insertTrade(iTrade, false);
		}
		return false;
	}

	public void buyPendingTrades(ITradeDb iTradeDb, IUserDb iUserDb, List<IStock> iStocks)
	{
		List<ITrade> iTrades = iTradeDb.getPendingTrades(TradeType.BUY);
		Map<String, Float> stocksMap = iStocks.stream().collect(Collectors.toMap(IStock::getSymbol, IStock::getPrice));

		Iterator<ITrade> tradesIterator = iTrades.iterator();
		while (tradesIterator.hasNext())
		{
			ITrade iTrade = tradesIterator.next();
			if (iTrade.getBuyPrice() >= stocksMap.get(iTrade.getSymbol()))
			{
				iTrade.setBuyPrice(stocksMap.get(iTrade.getSymbol()));
				iTrade.setTotalBuyPrice(iTrade.getQuantity() * iTrade.getBuyPrice());
				iTrade.setStatus(TradeStatus.EXECUTED);
				boolean isTradeUpdated = iTradeDb.updateBuyTrade(iTrade, true);
				if (isTradeUpdated)
				{
					IUser iUser = iUserDb.getUser(iTrade.getUserCode());
					double updatedFunds = iUser.getFunds() - (stocksMap.get(iTrade.getSymbol()) * iTrade.getQuantity());
					iUserDb.updateUserFunds(iTrade.getUserCode(), Double.parseDouble(DECIMAL_FORMAT.format(updatedFunds)));
				}
			}
		}
	}

	public boolean createSetBuyPriceTradeDetails(IStockDb iStockDb, ITrade iTrade, float buyPrice)
	{
		try
		{
			IStock iStock = iStockDb.getStock(iTrade.getStockId());
			iTrade.setSymbol(iStock.getSymbol());
			iTrade.setSegment(iStock.getSegment());
			iTrade.setBuyPrice(buyPrice);

			double totalBuyPrice = iTrade.getQuantity() * iTrade.getBuyPrice();
			iTrade.setTotalBuyPrice(totalBuyPrice);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

}
