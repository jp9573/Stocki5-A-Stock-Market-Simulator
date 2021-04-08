package com.csci5308.stocki5.trade.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.db.ITradeDb;

@Service
public class TradeOrder implements ITradeOrder
{
	private static ITradeOrder uniqueInstance = null;

	private TradeOrder()
	{
	}

	public static ITradeOrder instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new TradeOrder();
		}
		return uniqueInstance;
	}

	public List<ITrade> fetchUserOrders(String userCode, ITradeDb iTradeDb)
	{
		return iTradeDb.getTodaysTradeByUserCode(userCode);
	}

}
