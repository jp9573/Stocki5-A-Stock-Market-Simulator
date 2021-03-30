package com.csci5308.stocki5.trade.eod;

import com.csci5308.stocki5.trade.db.ITradeDb;
import org.springframework.stereotype.Service;

@Service
public interface ITradeEod
{
	public void markFailedBuyOrder(ITradeDb dbInterface);
	
	public void markFailedSellOrder(ITradeDb dbInterface);
}
