package com.csci5308.stocki5.stock.price;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.history.IStockMaintainHistory;
import com.csci5308.stocki5.trade.buy.ITradeBuy;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.sell.ITradeSell;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class StockScheduler implements IStockScheduler
{

	StockAbstractFactory stockFactory = StockAbstractFactory.instance();
	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();

	IStockPriceAlgorithm iStockPriceAlgorithm = stockFactory.createStockPriceAlgorithm();
	IStockPriceEod iStockPriceEod = stockFactory.createStockPriceEod();
	IStockDb iStockDb = stockFactory.createStockDb();
	IStockMaintainHistory iStockMaintainHistory = stockFactory.createStockMaintainHistory();
	ITradeBuy tradeBuy = tradeFactory.createTradeBuy();
	ITradeSell tradeSell = tradeFactory.createTradeSell();

	private static boolean isMarketHours = true;

	@Scheduled(fixedDelayString = "${stock.delay}")
	public void scheduleGenerateStockPrice()
	{
		if (isMarketHours)
		{
			iStockPriceAlgorithm.generateStockPrice(iStockDb, tradeBuy, tradeSell, iStockMaintainHistory);
		}
	}

	@Scheduled(cron = "0 0 9 * * ?")
	public void scheduleStockBod()
	{
		isMarketHours = true;
	}

	@Scheduled(cron = "0 0 18 * * ?")
	public void scheduleStockEod()
	{
		isMarketHours = false;
	}

	@Scheduled(cron = "0 5 18 * * ?")
	public void scheduleStockClosingPrice()
	{
		iStockPriceEod.setStockClosingPrice(iStockDb);
	}
}
