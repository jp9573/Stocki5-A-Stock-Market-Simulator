package com.csci5308.stocki5.trade.eod;

import com.csci5308.stocki5.trade.db.TradeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class TradeScheduler
{
	private final String CRON_TIMING = "0 5 18 * * ?";

	@Autowired
	ITradeEod iTradeEod;

	@Autowired
	TradeDb tradeDb;

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
