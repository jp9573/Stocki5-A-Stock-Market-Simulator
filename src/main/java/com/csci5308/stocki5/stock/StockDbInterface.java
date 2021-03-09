package com.csci5308.stocki5.stock;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface StockDbInterface
{
	public List<Stock> getStocks();

	public List<Stock> getStocksBySegment(String segments);

	public List<Stock> getHighestPriceStocks(String segments, int limit);

	public List<Stock> getLowestPriceStocks(String segments, int limit);

	public Stock getStockData(int stockId);

	public boolean updateStockData(Stock stock);

	public boolean updateStockBulk(List<Stock> stocks);

}
