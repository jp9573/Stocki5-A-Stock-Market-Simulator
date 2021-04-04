package com.csci5308.stocki5.trade.factory;

import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDbMock;

public abstract class TradeAbstractFactoryMock
{
    private static TradeAbstractFactoryMock uniqueInstance = null;

    protected TradeAbstractFactoryMock(){ }

    public static TradeAbstractFactoryMock instance()
    {
        if (null == uniqueInstance)
        {
            uniqueInstance = new TradeFactoryMock();
        }
        return uniqueInstance;
    }

    public abstract ITradeDb createTradeDbMock();

}
