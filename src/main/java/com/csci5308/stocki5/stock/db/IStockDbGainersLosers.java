package com.csci5308.stocki5.stock.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.stock.IStock;

@Repository
public interface IStockDbGainersLosers
{
	public List<IStock> getHighestPriceStocks(String segments, int limit);

	public List<IStock> getLowestPriceStocks(String segments, int limit);
}
