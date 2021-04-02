package com.csci5308.stocki5.stock.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.stock.IStock;

@Repository
public interface IStockDb
{
	public List<IStock> getStocks();

	public List<IStock> getStocksBySegment(String segments);

	public IStock getStock(int stockId);

	public boolean updateStocks(List<IStock> stocks);
}
