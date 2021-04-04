package com.csci5308.stocki5.trade.factory;

import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.db.TradeDbMock;

public class TradeFactoryMock extends TradeAbstractFactoryMock{

    @Override
    public ITradeDb createTradeDbMock() {
        return TradeDbMock.instance();
    }
}
