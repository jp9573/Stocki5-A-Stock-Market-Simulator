package com.csci5308.stocki5.stock.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.stock.Stock;

@Repository
public interface IStockDbGainersLosers
{
	public List<Stock> getHighestPriceStocks(String segments, int limit);

	public List<Stock> getLowestPriceStocks(String segments, int limit);
}
