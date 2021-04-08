package com.csci5308.stocki5.trade.eod;

import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class TradeScheduler implements ITradeScheduler
{
	private final String CRON_TIMING = "0 5 18 * * ?";

	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();
	ITradeEod iTradeEod = tradeFactory.createTradeEod();
	ITradeDb iTradeDb = tradeFactory.createTradeDb();

	@Scheduled(cron = CRON_TIMING)
	public boolean scheduleFailedBuyOrder()
	{
		return iTradeEod.markFailedBuyOrder(iTradeDb);
	}

	@Scheduled(cron = CRON_TIMING)
	public boolean scheduleFailedSellOrder()
	{
		return iTradeEod.markFailedSellOrder(iTradeDb);
	}
}
