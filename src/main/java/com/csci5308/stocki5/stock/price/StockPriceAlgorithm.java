package com.csci5308.stocki5.stock.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.factory.TradeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.factory.StockFactory;
import com.csci5308.stocki5.stock.history.IStockHistoryDb;
import com.csci5308.stocki5.stock.history.IStockMaintainHistory;
import com.csci5308.stocki5.trade.buy.ITradeBuy;
import com.csci5308.stocki5.trade.db.TradeDb;
import com.csci5308.stocki5.trade.sell.ITradeSell;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public class StockPriceAlgorithm implements IStockPriceAlgorithm
{
	private static final String PROPERTIES_FILE = "config.properties";
	static final String STOCK_PRICE_DECIMAL_FORMAT = "##.00";

	private int noOfVersions;
	private int priceChangeLimit;

	StockAbstractFactory stockFactory = StockFactory.instance();
	TradeAbstractFactory tradeFactory = TradeFactory.instance();
	IStockHistoryDb iStockHistoryDb = stockFactory.createStockHistoryDb();
	ITradeDb tradeDb = tradeFactory.createTradeDb();

	@Autowired
	IUserDb userDb;

	public StockPriceAlgorithm()
	{
		readProperties();
	}

	public boolean generateStockPrice(IStockDb iStockDb, ITradeBuy iTradeBuy, ITradeSell iTradeSell, IStockMaintainHistory iStockMaintainHistory)
	{
		try
		{
			float newPrice = 0.00f;
			float percent = 0.00f;
			List<IStock> iStocks = iStockDb.getStocks();
			Iterator<IStock> iStocksIterator = iStocks.iterator();
			IStock iStock;
			while (iStocksIterator.hasNext())
			{
				iStock = iStocksIterator.next();
				newPrice = stockPriceAlgorithm(iStock.getPrice());
				percent = stockPricePercentIncreaseDecrease(newPrice, iStock.getPreviousClose());
				iStock.setPrice(newPrice);
				iStock.setPercentIncreaseDecrease(percent);
				iStock.calculateHighAndLow(newPrice);
			}
			iStockDb.updateStocks(iStocks);
			iTradeBuy.buyPendingTrades(tradeDb, userDb, iStocks);
			iTradeSell.sellPendingTrades(tradeDb, userDb, iStocks);
			iStockMaintainHistory.maintainStocksHistory(iStocks, noOfVersions, iStockHistoryDb);
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

	private void readProperties()
	{
		InputStream inputStream = null;
		try
		{
			Properties prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
			if (inputStream == null)
			{
				throw new FileNotFoundException();
			} else
			{
				prop.load(inputStream);
			}
			this.noOfVersions = Integer.parseInt(prop.getProperty("history.noofversions"));
			this.priceChangeLimit = Integer.parseInt(prop.getProperty("stock.pricelimit"));
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
}
