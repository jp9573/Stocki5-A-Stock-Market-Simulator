package com.csci5308.stocki5.stock;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IStockDb
{
	public List<Stock> getStocks();

	public List<Stock> getStocksBySegment(String segments);

	public Stock getStock(int stockId);

	public boolean updateStocks(List<Stock> stocks);

}
