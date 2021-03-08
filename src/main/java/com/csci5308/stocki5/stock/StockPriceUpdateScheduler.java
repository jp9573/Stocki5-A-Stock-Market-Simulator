package com.csci5308.stocki5.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class StockPriceUpdateScheduler extends StockPriceAlgorithm {

	@Autowired
	StockDb stockDb;

	@Scheduled(fixedDelay = 5000)
	public void scheduleGenerateStockPrice() {
		super.generateStockPrice(stockDb);
	}
}
