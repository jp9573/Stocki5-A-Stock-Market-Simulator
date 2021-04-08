package com.csci5308.stocki5.stock.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

@Service
public class StockPriceAlgorithm implements IStockPriceAlgorithm
{
	private static final String PROPERTIES_FILE = "config.properties";
	private static final String STOCK_PRICE_DECIMAL_FORMAT = "##.00";

	private static IStockPriceAlgorithm uniqueInstance = null;

	private int priceChangeLimit;

	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();
	ITradeDb tradeDb = tradeFactory.createTradeDb();

	UserAbstractFactory userFactory = UserAbstractFactory.instance();
	IUserDb userDb = userFactory.createUserDb();

	private StockPriceAlgorithm()
	{
		readProperties();
	}

	public static IStockPriceAlgorithm instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockPriceAlgorithm();
		}
		return uniqueInstance;
	}

	public boolean generateStockPrice(IStockDb iStockDb)
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
