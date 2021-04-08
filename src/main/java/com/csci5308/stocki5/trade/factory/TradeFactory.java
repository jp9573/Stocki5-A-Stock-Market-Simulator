package com.csci5308.stocki5.trade.factory;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.observer.IObserver;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.buy.ITradeBuy;
import com.csci5308.stocki5.trade.buy.TradeBuy;
import com.csci5308.stocki5.trade.buy.TradeBuyPendingObserver;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDb;
import com.csci5308.stocki5.trade.eod.ITradeEod;
import com.csci5308.stocki5.trade.eod.TradeEod;
import com.csci5308.stocki5.trade.holding.Holding;
import com.csci5308.stocki5.trade.holding.IHolding;
import com.csci5308.stocki5.trade.holding.ITradeHolding;
import com.csci5308.stocki5.trade.holding.TradeHolding;
import com.csci5308.stocki5.trade.order.ITradeOrder;
import com.csci5308.stocki5.trade.order.TradeOrder;
import com.csci5308.stocki5.trade.sell.ITradeSell;
import com.csci5308.stocki5.trade.sell.TradeSell;
import com.csci5308.stocki5.trade.sell.TradeSellPendingObserver;
import com.csci5308.stocki5.user.db.IUserDb;

public class TradeFactory extends TradeAbstractFactory
{

	@Override
	public ITrade createTrade()
	{
		return new Trade();
	}

	@Override
	public ITrade createTradeWithData(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, IStockDb iStockDb, IUserDb iUserDb)
	{
		return new Trade(userCode, stockId, buySell, quantity, status, iStockDb, iUserDb);
	}

	@Override
	public ITradeBuy createTradeBuy()
	{
		return new TradeBuy();
	}

	@Override
	public IObserver createTradeBuyPendingObserver()
	{
		return new TradeBuyPendingObserver();
	}

	@Override
	public ITradeSell createTradeSell()
	{
		return new TradeSell();
	}

	@Override
	public IObserver createTradeSellPendingObserver()
	{
		return new TradeSellPendingObserver();
	}

	@Override
	public ITradeEod createTradeEod()
	{
		return TradeEod.instance();
	}

	@Override
	public IHolding createHolding()
	{
		return new Holding();
	}

	@Override
	public IHolding createHoldingWithData(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, IStockDb iStockDb, IUserDb iUserDb, boolean isHolding)
	{
		return new Holding(userCode, stockId, buySell, quantity, status, iStockDb, iUserDb, isHolding);
	}

	@Override
	public ITradeHolding createTradeHolding()
	{
		return TradeHolding.instance();
	}

	@Override
	public ITradeOrder createTradeOrder()
	{
		return TradeOrder.instance();
	}

	@Override
	public ITradeDb createTradeDb()
	{
		return TradeDb.instance();
	}

}
