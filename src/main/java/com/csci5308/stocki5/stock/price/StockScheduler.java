package com.csci5308.stocki5.stock.price;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.observer.IObserver;
import com.csci5308.stocki5.stock.observer.Subject;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;

@Service
@EnableScheduling
public class StockScheduler implements IStockScheduler
{
	private final String MARKET_START_TIMING = "0 0 9 * * ?";
	private final String MARKET_STOP_TIMING = "0 0 18 * * ?";
	private final String EOD_TIMING = "0 5 18 * * ?";

	Subject stockPriceSubject;
	StockAbstractFactory stockFactory = StockAbstractFactory.instance();
	IObserver stockMaintainHistoryObserver = stockFactory.createStockMaintainHistoryObserver();
	IStockPriceEod iStockPriceEod = stockFactory.createStockPriceEod();
	IStockDb iStockDb = stockFactory.createStockDb();

	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();
	IObserver tradeBuyPendingObserver = tradeFactory.createTradeBuyPendingObserver();
	IObserver tradeSellPendingObserver = tradeFactory.createTradeSellPendingObserver();

	private static boolean isMarketHours = true;

	@Scheduled(fixedDelayString = "${stock.delay}")
	public void scheduleGenerateStockPrice()
	{
		if (isMarketHours)
		{
			stockPriceSubject = stockFactory.createStockPriceSubject();
			stockPriceSubject.attach(tradeBuyPendingObserver);
			stockPriceSubject.attach(tradeSellPendingObserver);
			stockPriceSubject.attach(stockMaintainHistoryObserver);
			stockPriceSubject.notifyObservers();
		}
	}

	@Scheduled(cron = MARKET_START_TIMING)
	public void scheduleStockBod()
	{
		isMarketHours = true;
	}

	@Scheduled(cron = MARKET_STOP_TIMING)
	public void scheduleStockEod()
	{
		isMarketHours = false;
	}

	@Scheduled(cron = EOD_TIMING)
	public void scheduleStockClosingPrice()
	{
		iStockPriceEod.setStockClosingPrice(iStockDb);
	}
}
