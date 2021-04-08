package com.csci5308.stocki5.trade.sell;

import java.util.List;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.observer.IObserver;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

public class TradeSellPendingObserver implements IObserver
{
	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();
	ITradeDb iTradeDb = tradeFactory.createTradeDb();
	ITradeSell iTradeSell = tradeFactory.createTradeSell();

	StockAbstractFactory stockFactory = StockAbstractFactory.instance();
	IStockDb iStockDb = stockFactory.createStockDb();

	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserDb iUserDb = userFactory.createUserDb();

	@Override
	public void update()
	{
		List<IStock> iStocks = iStockDb.getStocks();
		iTradeSell.sellPendingTrades(iTradeDb, iUserDb, iStocks);
	}

}
