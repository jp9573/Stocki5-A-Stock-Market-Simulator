package com.csci5308.stocki5.stock.prediction;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.history.StockHistoryDb;

@Service
public interface IStockPrediction
{
	public List<Stock> predictStockValue(StockHistoryDb stockDbInterface, String stock);
}
