package com.csci5308.stocki5.stock.price;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.history.IStockMaintainHistory;
import com.csci5308.stocki5.stock.history.StockHistoryDb;
import com.csci5308.stocki5.trade.TradeDb;
import com.csci5308.stocki5.trade.buy.ITradeBuy;
import com.csci5308.stocki5.trade.sell.ITradeSell;
import com.csci5308.stocki5.user.UserDb;

@Service
public class StockPriceAlgorithm implements IStockPriceAlgorithm
{
	static final String STOCK_PRICE_DECIMAL_FORMAT = "##.00";

	@Value("${history.noofversions}")
	private int noOfVersions;

	@Value("${stock.pricelimit}")
	private int priceChangeLimit;

	@Autowired
	StockHistoryDb stockHistoryDb;

	@Autowired
	UserDb userDb;

	@Autowired
	TradeDb tradeDb;

	public boolean generateStockPrice(IStockDb iStockDb, ITradeBuy iTradeBuy, ITradeSell iTradeSell, IStockMaintainHistory iStockMaintainHistory)
	{
		try
		{
			float newPrice = 0.00f;
			float percent = 0.00f;
			List<Stock> stocks = iStockDb.getStocks();
			Iterator<Stock> stocksIterator = stocks.iterator();
			Stock stock;
			while (stocksIterator.hasNext())
			{
				stock = stocksIterator.next();
				newPrice = stockPriceAlgorithm(stock.getPrice());
				percent = stockPricePercentIncreaseDecrease(newPrice, stock.getPreviousClose());
				stock.setPrice(newPrice);
				stock.setPercentIncreaseDecrease(percent);
				stock.calculateHighAndLow(newPrice);
			}
			iStockDb.updateStocks(stocks);
			iTradeBuy.buyPendingTrades(tradeDb, userDb, stocks);
			iTradeSell.sellPendingTrades(tradeDb, userDb, stocks);
			iStockMaintainHistory.maintainStocksHistory(stocks, noOfVersions, stockHistoryDb);
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public float stockPriceAlgorithm(float currentPrice)
	{
		DecimalFormat df = new DecimalFormat(STOCK_PRICE_DECIMAL_FORMAT);
		Random random = new Random();
		float maxPriceRange = currentPrice + priceChangeLimit;
		float minPriceRange = currentPrice - priceChangeLimit;
		if (minPriceRange < 0.00f)
		{
			minPriceRange = 0.00f;
		}
		if (maxPriceRange < 0.00f)
		{
			maxPriceRange = 1.00f;
		}
		float newPrice = minPriceRange + random.nextFloat() * (maxPriceRange - minPriceRange);
		float formatedNewPrice = Float.parseFloat(df.format(newPrice));
		return formatedNewPrice;
	}

	public float stockPricePercentIncreaseDecrease(float currentPrice, float previousClose)
	{
		DecimalFormat df = new DecimalFormat(STOCK_PRICE_DECIMAL_FORMAT);
		float percent = 0.00f;
		float formatedPercent = 0.00f;
		if (previousClose <= 0.00f)
		{
			formatedPercent = Float.parseFloat(df.format(percent));
		} else
		{
			percent = ((currentPrice - previousClose) / previousClose) * 100;
			formatedPercent = Float.parseFloat(df.format(percent));
		}
		return formatedPercent;
	}
}
