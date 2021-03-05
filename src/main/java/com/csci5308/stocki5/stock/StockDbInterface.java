package com.csci5308.stocki5.stock;

import org.springframework.stereotype.Repository;

@Repository
public interface StockDbInterface
{
	public Stock getStockData(int stockId);
	public boolean updateStockData(Stock stock);
}
