package com.csci5308.stocki5.trade.order;

import com.csci5308.stocki5.trade.ITrade;
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

public class TradeOrderTest {

    TradeAbstractFactoryMock tradeFactoryMock = TradeFactoryMock.instance();
    TradeAbstractFactory tradeFactory = TradeFactory.instance();
    private ITradeDb tradeDb = null;
    private ITradeOrder tradeOrder = null;

    @Before
    public void createObjects() {
        tradeDb = tradeFactoryMock.createTradeDbMock();
        tradeOrder = tradeFactory.createTradeOrder();
    }

    @After
    public void destroyObjects() {
        tradeDb = null;
        tradeOrder = null;
    }

    @Test
    public void fetchUserOrdersTest() {
        List<ITrade> userOrders = tradeOrder.fetchUserOrders("AB123456", tradeDb);
        Iterator<ITrade> userOrdersInterator = userOrders.iterator();
        while (userOrdersInterator.hasNext()){
            ITrade trade = userOrdersInterator.next();
            Assert.assertTrue(trade.generateTradeNumber());
            Assert.assertNotNull(trade.getTradeNumber());
            Assert.assertNotEquals(0, trade.getStockId());
            Assert.assertNotNull(trade.getBuySell());
            Assert.assertNotEquals(0, trade.getQuantity());
            Assert.assertNotNull(trade.getStatus());
            Assert.assertNotNull(trade.getStockDbInterface());
            Assert.assertNotNull(trade.getUserDbInterface());
        }
    }

    @Test
    public void fetchUserOrdersInvalidUserCodeTest() {
        List<ITrade> userOrders = tradeOrder.fetchUserOrders("AB1234", tradeDb);
        int orderListSize = userOrders.size();
        Assert.assertEquals(0, orderListSize);
    }
}