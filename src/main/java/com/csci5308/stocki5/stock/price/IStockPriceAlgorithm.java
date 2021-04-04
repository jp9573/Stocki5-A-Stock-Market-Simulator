package com.csci5308.stocki5.stock.price;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.history.IStockMaintainHistory;
import com.csci5308.stocki5.trade.buy.ITradeBuy;
import com.csci5308.stocki5.trade.sell.ITradeSell;
import org.springframework.stereotype.Service;

@Service
public interface IStockPriceAlgorithm
{
	public boolean generateStockPrice(IStockDb iStockDb, ITradeBuy iTradeBuy, ITradeSell iTradeSell, IStockMaintainHistory iStockMaintainHistory);

	public float stockPriceAlgorithm(float currentPrice);

	public float stockPricePercentIncreaseDecrease(float currentPrice, float previousClose);
}
