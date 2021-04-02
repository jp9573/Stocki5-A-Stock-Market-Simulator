package com.csci5308.stocki5.trade.db;

import java.util.ArrayList;
import java.util.List;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.trade.factory.TradeFactoryMock;
import com.csci5308.stocki5.trade.holding.Holding;
import com.csci5308.stocki5.trade.holding.IHolding;
import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.db.UserDbMock;

public class TradeDbMock implements ITradeDb
{

	StockAbstractFactoryMock stockFactoryMock = StockFactoryMock.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();

	@Override
	public boolean insertTrade(ITrade trade, boolean isHolding)
	{
		return true;
	}

	@Override
	public List<ITrade> getTodaysTradeByUserCode(String userCode)
	{

		List<ITrade> tradeList = new ArrayList<>();
		IStockDb stockDb = stockFactoryMock.createStockDbMock();
		IUserDb userDb = new UserDbMock();
		if (userCode.equals("AB123456"))
		{
			tradeList.add(tradeFactory.createTradeWithData(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, stockDb, userDb));
			tradeList.add(tradeFactory.createTradeWithData(userCode, 1, TradeType.SELL, 50, TradeStatus.EXECUTED, stockDb, userDb));
			tradeList.add(tradeFactory.createTradeWithData(userCode, 2, TradeType.BUY, 100, TradeStatus.PENDING, stockDb, userDb));
			tradeList.add(tradeFactory.createTradeWithData(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, stockDb, userDb));
			tradeList.add(tradeFactory.createTradeWithData(userCode, 3, TradeType.SELL, 75, TradeStatus.FAILED, stockDb, userDb));
		}
		return tradeList;
	}

	@Override
	public List<IHolding> getHoldingsByUserCode(String userCode)
	{
		List<IHolding> holdingList = new ArrayList<>();
		IStockDb stockDb = stockFactoryMock.createStockDbMock();
		IUserDb userDb = new UserDbMock();
		if (userCode.equals("AB123456"))
		{
			holdingList.add(tradeFactory.createHoldingWithData(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, stockDb, userDb, true));
			holdingList.add(tradeFactory.createHoldingWithData(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, stockDb, userDb, true));
			holdingList.add(tradeFactory.createHoldingWithData(userCode, 2, TradeType.BUY, 100, TradeStatus.EXECUTED, stockDb, userDb, true));
			holdingList.add(tradeFactory.createHoldingWithData(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, stockDb, userDb, true));
			holdingList.add(tradeFactory.createHoldingWithData(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, stockDb, userDb, true));
		}
		return holdingList;
	}

	@Override
	public List<ITrade> getPendingTrades(TradeType tradeType)
	{
		List<ITrade> tradeList = new ArrayList<>();
		IStockDb stockDb = stockFactoryMock.createStockDbMock();
		IUserDb userDb = new UserDbMock();
		if (tradeType == TradeType.BUY)
		{
			tradeList.add(tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 50, TradeStatus.PENDING, stockDb, userDb));
			tradeList.add(tradeFactory.createTradeWithData("AB123456", 1, TradeType.BUY, 50, TradeStatus.PENDING, stockDb, userDb));
			tradeList.add(tradeFactory.createTradeWithData("AB123456", 2, TradeType.BUY, 100, TradeStatus.PENDING, stockDb, userDb));
			tradeList.add(tradeFactory.createTradeWithData("AB123456", 3, TradeType.BUY, 75, TradeStatus.PENDING, stockDb, userDb));
			tradeList.add(tradeFactory.createTradeWithData("AB123456", 3, TradeType.BUY, 75, TradeStatus.PENDING, stockDb, userDb));
		}
		return tradeList;
	}

	@Override
	public boolean updateBuyTrade(ITrade trade, boolean isHolding)
	{
		return true;
	}

	@Override
	public boolean updateSellTrade(ITrade trade, boolean isHolding)
	{
		return true;
	}

	@Override
	public boolean updateBulkTradeStatus(List<ITrade> trades)
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
