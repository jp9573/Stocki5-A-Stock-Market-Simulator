package com.csci5308.stocki5.stock.db;

import com.csci5308.stocki5.stock.IStock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockDbGainersLosers
{
	public List<IStock> getHighestPriceStocks(String segments, int limit);

	public List<IStock> getLowestPriceStocks(String segments, int limit);
}
