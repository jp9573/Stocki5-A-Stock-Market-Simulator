package com.csci5308.stocki5.trade.eod;

import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class TradeScheduler
{
	private final String CRON_TIMING = "0 5 18 * * ?";

	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	ITradeEod iTradeEod = tradeFactory.createTradeEod();
	ITradeDb tradeDb = tradeFactory.createTradeDb();

	@Scheduled(cron = CRON_TIMING)
	public void scheduleFailedBuyOrder()
	{
		iTradeEod.markFailedBuyOrder(tradeDb);
	}

	@Scheduled(cron = CRON_TIMING)
	public void scheduleFailedSellOrder()
	{
		iTradeEod.markFailedSellOrder(tradeDb);
	}

}
