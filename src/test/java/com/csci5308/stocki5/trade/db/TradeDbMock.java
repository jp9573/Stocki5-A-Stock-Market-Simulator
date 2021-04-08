package com.csci5308.stocki5.trade.db;

import java.util.ArrayList;
import java.util.List;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.trade.holding.IHolding;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactoryMock;

public class TradeDbMock implements ITradeDb
{

	private static ITradeDb uniqueInstance = null;

	StockAbstractFactoryMock stockFactoryMock = StockAbstractFactoryMock.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	UserAbstractFactoryMock userFactoryMock = UserAbstractFactoryMock.instance();

	private TradeDbMock()
	{
	}

	public static ITradeDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new TradeDbMock();
		}
		return uniqueInstance;
	}

	@Override
	public boolean insertTrade(ITrade iTrade, boolean isHolding)
	{
		return true;
	}

	@Override
	public List<ITrade> getTodaysTradeByUserCode(String userCode)
	{

		List<ITrade> iTrades = new ArrayList<>();
		IStockDb iStockDb = stockFactoryMock.createStockDbMock();
		IUserDb iUserDb = userFactoryMock.createUserDbMock();
		if (userCode.equals("AB123456"))
		{
			iTrades.add(tradeFactory.createTradeWithData(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, iStockDb, iUserDb));
			iTrades.add(tradeFactory.createTradeWithData(userCode, 1, TradeType.SELL, 50, TradeStatus.EXECUTED, iStockDb, iUserDb));
			iTrades.add(tradeFactory.createTradeWithData(userCode, 2, TradeType.BUY, 100, TradeStatus.PENDING, iStockDb, iUserDb));
			iTrades.add(tradeFactory.createTradeWithData(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, iStockDb, iUserDb));
			iTrades.add(tradeFactory.createTradeWithData(userCode, 3, TradeType.SELL, 75, TradeStatus.FAILED, iStockDb, iUserDb));
		}
		return iTrades;
	}

	@Override
	public List<IHolding> getHoldingsByUserCode(String userCode)
	{
		List<IHolding> iHoldings = new ArrayList<>();
		IStockDb iStockDb = stockFactoryMock.createStockDbMock();
		IUserDb iUserDb = userFactoryMock.createUserDbMock();
		if (userCode.equals("AB123456"))
		{
			iHoldings.add(tradeFactory.createHoldingWithData(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, iStockDb, iUserDb, true));
			iHoldings.add(tradeFactory.createHoldingWithData(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, iStockDb, iUserDb, true));
			iHoldings.add(tradeFactory.createHoldingWithData(userCode, 2, TradeType.BUY, 100, TradeStatus.EXECUTED, iStockDb, iUserDb, true));
			iHoldings.add(tradeFactory.createHoldingWithData(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, iStockDb, iUserDb, true));
			iHoldings.add(tradeFactory.createHoldingWithData(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, iStockDb, iUserDb, true));
		}
		return iHoldings;
	}

	@Override
	public List<ITrade> getPendingTrades(TradeType tradeType)
	{
		List<ITrade> iTrades = new ArrayList<>();
		IStockDb iStockDb = stockFactoryMock.createStockDbMock();
		IUserDb iUserDb = userFactoryMock.createUserDbMock();
		if (tradeType == TradeType.BUY)
		{
			iTrades.add(tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 50, TradeStatus.PENDING, iStockDb, iUserDb));
			iTrades.add(tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 50, TradeStatus.PENDING, iStockDb, iUserDb));
			iTrades.add(tradeFactory.createTradeWithData("AB123456", 2, TradeType.BUY, 100, TradeStatus.PENDING, iStockDb, iUserDb));
			iTrades.add(tradeFactory.createTradeWithData("AB123456", 3, TradeType.BUY, 75, TradeStatus.PENDING, iStockDb, iUserDb));
			iTrades.add(tradeFactory.createTradeWithData("AB123456", 3, TradeType.BUY, 75, TradeStatus.PENDING, iStockDb, iUserDb));
		}
		return iTrades;
	}

	@Override
	public boolean updateBuyTrade(ITrade iTrade, boolean isHolding)
	{
		return true;
	}

	@Override
	public boolean updateSellTrade(ITrade iTrade, boolean isHolding)
	{
		return true;
	}

	@Override
	public boolean updateBulkTradeStatus(List<ITrade> iTrades)
	{
		return true;
	}

	@Override
	public boolean removeHolding(String tradeNumber)
	{
		if (tradeNumber == "ABC123")
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean removeHoldingForAutoSell(String userCode, int stockId, int quantity)
	{
		return true;
	}
}
