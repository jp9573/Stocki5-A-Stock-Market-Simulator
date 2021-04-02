package com.csci5308.stocki5.stock.prediction;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.history.IStockHistoryDb;

@Service
public interface IStockPrediction
{
	public List<IStock> predictStockValue(IStockHistoryDb iStockHistoryDb, String symbol);
}
