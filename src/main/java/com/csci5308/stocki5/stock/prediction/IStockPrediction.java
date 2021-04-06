package com.csci5308.stocki5.stock.prediction;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStockPrediction
{
	public List<IStock> predictStockValue(IStockHistoryDb iStockHistoryDb, String symbol);
}
