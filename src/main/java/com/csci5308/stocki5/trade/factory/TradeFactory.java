package com.csci5308.stocki5.trade.factory;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.buy.ITradeBuy;
import com.csci5308.stocki5.trade.buy.TradeBuy;
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
import com.csci5308.stocki5.user.IUserDb;

public class TradeFactory extends TradeAbstractFactory {

    private static TradeAbstractFactory uniqueInstance = null;

    public static TradeAbstractFactory instance()
    {
        if (null == uniqueInstance)
        {
            uniqueInstance = new TradeFactory();
        }
        return uniqueInstance;
    }

    @Override
    public ITrade createTrade() {
        return new Trade();
    }

    @Override
    public ITrade createTradeWithData(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, IStockDb stockDbInterface, IUserDb userDbInterface) {
        return new Trade(userCode, stockId, buySell, quantity, status, stockDbInterface, userDbInterface);
    }

    @Override
    public ITradeBuy createTradeBuy() {
        return new TradeBuy();
    }

    @Override
    public ITradeSell createTradeSell() {
        return new TradeSell();
    }

    @Override
    public ITradeEod createTradeEod() {
        return new TradeEod();
    }

    @Override
    public IHolding createHolding() {
        return new Holding();
    }

    @Override
    public IHolding createHoldingWithData(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, IStockDb stockDbInterface, IUserDb userDbInterface, boolean isHolding) {
        return new Holding(userCode, stockId, buySell, quantity, status, stockDbInterface, userDbInterface, isHolding);
    }

    @Override
    public ITradeHolding createTradeHolding() {
        return new TradeHolding();
    }

    @Override
    public ITradeOrder createTradeOrder() {
        return new TradeOrder();
    }

    @Override
    public ITradeDb createTradeDb() {
        return new TradeDb();
    }
}
