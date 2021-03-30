package com.csci5308.stocki5.stock.prediction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.history.IStockHistoryDb;
import com.csci5308.stocki5.stock.history.StockHistory;

@Service
public class StockPrediction implements IStockPrediction
{
	public List<Stock> predictStockValue(IStockHistoryDb iStockHistoryDb, String symbol)
	{
		List<StockHistory> stockHistorys = iStockHistoryDb.getStockHistoryBySymbol(symbol);
		int totalCount = stockHistorys.size();
		if (totalCount > 0)
		{
			float sum = 0.00f;
			for (StockHistory stockHistory : stockHistorys)
			{
				sum += stockHistory.getPrice();
			}

			float mean = sum / totalCount;
			StockHistory lastHistoryStock = stockHistorys.get(totalCount - 1);
			Stock predictedStock = new Stock();
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
			List<Stock> stocks = new ArrayList<>();
			stocks.add(predictedStock);
			return stocks;
		} else
		{
			return null;
		}
	}
}
