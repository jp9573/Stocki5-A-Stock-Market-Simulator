package com.csci5308.stocki5.trade.holding;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactoryMock;
import com.csci5308.stocki5.stock.factory.StockFactoryMock;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import com.csci5308.stocki5.trade.factory.TradeFactoryMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class TradeHoldingTest {

	StockAbstractFactoryMock stockFactoryMock = StockFactoryMock.instance();
    TradeAbstractFactoryMock tradeFactoryMock = TradeFactoryMock.instance();
    TradeAbstractFactory tradeFactory = TradeFactory.instance();
    private ITradeHolding tradeHolding = null;
    private ITradeDb tradeDb = null;
    private IStockDb stockDb = null;

    @Before
    public void createObjects() {
        stockDb = stockFactoryMock.createStockDbMock();
        tradeDb = tradeFactoryMock.createTradeDbMock();
        tradeHolding = tradeFactory.createTradeHolding();
    }

    @After
    public void destroyObjects() {
        stockDb = null;
        tradeDb = null;
        tradeHolding = null;
    }

    @Test
    public void fetchUserHoldingsTest() {
        List<IHolding> holdingList = tradeHolding.fetchUserHoldings("AB123456", tradeDb, stockDb);
        Iterator<IHolding> holdingIterator = holdingList.iterator();

        while (holdingIterator.hasNext()){
            IHolding holding = holdingIterator.next();
            Assert.assertNotEquals(0, holding.getStockId());
            Assert.assertNotNull(holding.getBuySell());
            Assert.assertNotEquals(0, holding.getQuantity());
            Assert.assertNotNull(holding.getStatus());
            Assert.assertNotNull(holding.getStockDbInterface());
            Assert.assertNotNull(holding.getUserDbInterface());
        }
    }

    @Test
    public void fetchUserHoldingsInvalidUserTest() {
        List<IHolding> holdingList = tradeHolding.fetchUserHoldings("AB1234", tradeDb, stockDb);
        int holdingListSize = holdingList.size();
        Assert.assertEquals(0, holdingListSize);
    }
}