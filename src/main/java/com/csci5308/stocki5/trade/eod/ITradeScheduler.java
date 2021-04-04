package com.csci5308.stocki5.trade.eod;

public interface ITradeScheduler
{
	public boolean scheduleFailedBuyOrder();
	
	public boolean scheduleFailedSellOrder();
}
