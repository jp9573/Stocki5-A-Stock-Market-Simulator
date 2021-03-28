package com.csci5308.stocki5.trade.eod;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.trade.ITradeDb;

@Service
public interface ITradeEod
{
	public void markFailedBuyOrder(ITradeDb dbInterface);
	
	public void markFailedSellOrder(ITradeDb dbInterface);
}
