package com.csci5308.stocki5.stock.prediction;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.history.IStockHistory;

@Service
public class StockPrediction implements IStockPrediction
{
	private static final String STOCK_PRICE_DECIMAL_FORMAT = "##.00";
	private static IStockPrediction uniqueInstance = null;

	StockAbstractFactory stockFactory = StockAbstractFactory.instance();

	private StockPrediction()
	{
	}

	public static IStockPrediction instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockPrediction();
		}
		return uniqueInstance;
	}

	public List<IStock> predictStockValue(IStockHistoryDb iStockHistoryDb, String symbol)
	{
		DecimalFormat df = new DecimalFormat(STOCK_PRICE_DECIMAL_FORMAT);
		List<IStockHistory> iStockHistories = iStockHistoryDb.getStockHistoryBySymbol(symbol);
		int totalCount = iStockHistories.size();
		if (totalCount > 0)
		{
			float sum = 0.00f;

			Iterator<IStockHistory> iStockHistoriesIterator = iStockHistories.iterator();
			IStockHistory iStockHistory;
			while (iStockHistoriesIterator.hasNext())
			{
				iStockHistory = iStockHistoriesIterator.next();
				sum += iStockHistory.getPrice();
			}

			float mean = sum / totalCount;
			IStockHistory lastHistoryStock = iStockHistories.get(totalCount - 1);
			IStock predictedStock = stockFactory.createStock();
			float changeInPercent = mean / lastHistoryStock.getPrice();

			predictedStock.setStockId(lastHistoryStock.getStockId());
			predictedStock.setSymbol(lastHistoryStock.getSymbol());
			predictedStock.setOpen(lastHistoryStock.getOpen());
			predictedStock.setHigh(lastHistoryStock.getHigh());
			predictedStock.setLow(lastHistoryStock.getLow());
			predictedStock.setPrice(Float.parseFloat(df.format(mean)));
			predictedStock.setLatestTradingDate(new Date());
			predictedStock.setPreviousClose(lastHistoryStock.getPreviousClose());
			predictedStock.setSegment(lastHistoryStock.getSegment());
			predictedStock.setPercentIncreaseDecrease(Float.parseFloat(df.format(changeInPercent)));
			List<IStock> iStocks = new ArrayList<>();
			iStocks.add(predictedStock);
			return iStocks;
		} else
		{
			return null;
		}
	}
}
