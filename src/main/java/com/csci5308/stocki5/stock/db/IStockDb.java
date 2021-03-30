package com.csci5308.stocki5.stock.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.stock.Stock;

@Repository
public interface IStockDb
{
	public List<Stock> getStocks();

	public List<Stock> getStocksBySegment(String segments);

	public Stock getStock(int stockId);

	public boolean updateStocks(List<Stock> stocks);

}
