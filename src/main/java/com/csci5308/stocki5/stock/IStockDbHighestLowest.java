package com.csci5308.stocki5.stock;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IStockDbHighestLowest
{
	public List<Stock> getHighestPriceStocks(String segments, int limit);

	public List<Stock> getLowestPriceStocks(String segments, int limit);
}
