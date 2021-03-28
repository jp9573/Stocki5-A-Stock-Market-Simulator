package com.csci5308.stocki5.stock.price;

import java.util.Date;

import com.csci5308.stocki5.stock.StockDb;
import com.csci5308.stocki5.trade.sell.TradeSell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.trade.buy.TradeBuy;

@Service
@EnableScheduling
public class StockScheduler {

	@Autowired
	StockPriceAlgorithm stockPriceAlgorithm;

	@Autowired
	StockEod stockEod;

	@Autowired
	StockDb stockDb;

	@Autowired
	TradeBuy tradeBuy;

	@Autowired
	TradeSell tradeSell;

	private static boolean isMarketHours = true;

	@Scheduled(fixedDelay = 5000)
	public void scheduleGenerateStockPrice() {
		if (isMarketHours) {
			stockPriceAlgorithm.generateStockPrice(stockDb, tradeBuy, tradeSell);
		}
	}
	
	@Scheduled(cron = "0 0 9 * * ?")
	public void scheduleStockBod() {
		StockScheduler.isMarketHours = true;
	}
	
	@Scheduled(cron = "0 0 18 * * ?")
	public void scheduleStockEod() {
		System.out.println("Eod process called at " + new Date(System.currentTimeMillis()));
		StockScheduler.isMarketHours = false;
		stockEod.setStockClosingPrice(stockDb);
	}

}
