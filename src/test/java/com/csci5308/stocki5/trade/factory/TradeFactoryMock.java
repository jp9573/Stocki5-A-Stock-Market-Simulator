package com.csci5308.stocki5.trade.factory;

import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDbMock;

public class TradeFactoryMock extends TradeAbstractFactoryMock{

    private static TradeFactoryMock uniqueInstance = null;

    public static TradeAbstractFactoryMock instance()
    {
        if (null == uniqueInstance)
        {
            uniqueInstance = new TradeFactoryMock();
        }
        return uniqueInstance;
    }

    @Override
    public ITradeDb createTradeDbMock() {
        return new TradeDbMock();
    }
}
