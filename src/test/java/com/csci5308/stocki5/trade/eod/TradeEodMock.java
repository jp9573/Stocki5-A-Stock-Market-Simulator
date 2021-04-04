package com.csci5308.stocki5.trade.eod;

public class TradeEodMock implements ITradeScheduler
{
	private static ITradeScheduler uniqueInstance = null;

	private TradeEodMock()
	{
	}

	public static ITradeScheduler instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new TradeEodMock();
		}
		return uniqueInstance;
	}
	
	@Override
	public boolean scheduleFailedBuyOrder()
	{
		return true;
	}

	@Override
	public boolean scheduleFailedSellOrder()
	{
		return true;
	}

}
