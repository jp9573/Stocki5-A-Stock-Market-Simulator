package com.csci5308.stocki5.stock.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.db.StockDb;
import com.csci5308.stocki5.stock.history.StockMaintainHistory;
import com.csci5308.stocki5.trade.buy.TradeBuy;
import com.csci5308.stocki5.trade.sell.TradeSell;

@Service
@EnableScheduling
public class StockScheduler
{
	@Autowired
	IStockPriceAlgorithm iStockPriceAlgorithm;

	@Autowired
	IStockPriceEod iStockPriceEod;

	@Autowired
	StockDb stockDb;

	@Autowired
	TradeBuy tradeBuy;

	@Autowired
	TradeSell tradeSell;

	@Autowired
	StockMaintainHistory stockMaintainHistory;

	private static boolean isMarketHours = true;

	@Scheduled(fixedDelayString = "${stock.delay}")
	public void scheduleGenerateStockPrice()
	{
		if (isMarketHours)
		{
			iStockPriceAlgorithm.generateStockPrice(stockDb, tradeBuy, tradeSell, stockMaintainHistory);
		}
	}

	@Scheduled(cron = "0 0 9 * * ?")
	public void scheduleStockBod()
	{
		StockScheduler.isMarketHours = true;
	}

	@Scheduled(cron = "0 0 18 * * ?")
	public void scheduleStockEod()
	{
		StockScheduler.isMarketHours = false;
		iStockPriceEod.setStockClosingPrice(stockDb);
	}

}
