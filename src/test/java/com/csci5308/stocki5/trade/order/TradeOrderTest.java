package com.csci5308.stocki5.trade.order;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactoryMock;
import com.csci5308.stocki5.trade.factory.TradeFactory;

public class TradeOrderTest
{

	TradeAbstractFactoryMock tradeFactoryMock = TradeAbstractFactoryMock.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	private ITradeDb iTradeDb = null;
	private ITradeOrder iTradeOrder = null;

	@Before
	public void createObjects()
	{
		iTradeDb = tradeFactoryMock.createTradeDbMock();
		iTradeOrder = tradeFactory.createTradeOrder();
	}

	@After
	public void destroyObjects()
	{
		iTradeDb = null;
		iTradeOrder = null;
	}

	@Test
	public void fetchUserOrdersTest()
	{
		List<ITrade> userOrders = iTradeOrder.fetchUserOrders("AB123456", iTradeDb);
		Iterator<ITrade> userOrdersInterator = userOrders.iterator();
		while (userOrdersInterator.hasNext())
		{
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
	public void fetchUserOrdersInvalidUserCodeTest()
	{
		List<ITrade> userOrders = iTradeOrder.fetchUserOrders("AB1234", iTradeDb);
		int orderListSize = userOrders.size();
		Assert.assertEquals(0, orderListSize);
	}
}