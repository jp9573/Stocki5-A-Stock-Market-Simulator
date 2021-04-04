package com.csci5308.stocki5.stock.prediction;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.history.IStockHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockPrediction implements IStockPrediction
{
	StockAbstractFactory stockFactory = StockAbstractFactory.instance();

	public List<IStock> predictStockValue(IStockHistoryDb iStockHistoryDb, String symbol)
	{
		List<IStockHistory> iStockHistories = iStockHistoryDb.getStockHistoryBySymbol(symbol);
		int totalCount = iStockHistories.size();
		if (totalCount > 0)
		{
			float sum = 0.00f;
			for (IStockHistory iStockHistory : iStockHistories)
			{
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
			predictedStock.setPrice(mean);
			predictedStock.setLatestTradingDate(new Date());
			predictedStock.setPreviousClose(lastHistoryStock.getPreviousClose());
			predictedStock.setSegment(lastHistoryStock.getSegment());
			predictedStock.setPercentIncreaseDecrease(changeInPercent);
			List<IStock> iStocks = new ArrayList<>();
			iStocks.add(predictedStock);
			return iStocks;
		} else
		{
			return null;
		}
	}
}
