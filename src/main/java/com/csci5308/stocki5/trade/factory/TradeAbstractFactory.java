package com.csci5308.stocki5.trade.factory;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.observer.IObserver;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.buy.ITradeBuy;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.eod.ITradeEod;
import com.csci5308.stocki5.trade.holding.IHolding;
import com.csci5308.stocki5.trade.holding.ITradeHolding;
import com.csci5308.stocki5.trade.order.ITradeOrder;
import com.csci5308.stocki5.trade.sell.ITradeSell;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

@Service
public abstract class TradeAbstractFactory
{
	private static TradeAbstractFactory uniqueInstance = null;

	protected TradeAbstractFactory()
	{
	}

	public static TradeAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new TradeFactory();
		}
		return uniqueInstance;
	}

	public abstract ITrade createTrade();

	public abstract ITrade createTradeWithData(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, IStockDb iStockDb, IUserDb iUserDb);

	public abstract ITradeBuy createTradeBuy();

	public abstract IObserver createTradeBuyPendingObserver();

	public abstract ITradeSell createTradeSell();

	public abstract IObserver createTradeSellPendingObserver();

	public abstract ITradeEod createTradeEod();

	public abstract IHolding createHolding();

	public abstract IHolding createHoldingWithData(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, IStockDb iStockDb, IUserDb iUserDb, boolean isHolding);

	public abstract ITradeHolding createTradeHolding();

	public abstract ITradeOrder createTradeOrder();

	public abstract ITradeDb createTradeDb();

}
