package com.csci5308.stocki5.stock.price;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.db.IStockDb;

@Service
public interface IStockPriceAlgorithm
{
	public boolean generateStockPrice(IStockDb iStockDb);

	public float stockPriceAlgorithm(float currentPrice);

	public float stockPricePercentIncreaseDecrease(float currentPrice, float previousClose);
}
