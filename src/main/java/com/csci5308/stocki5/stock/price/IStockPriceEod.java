package com.csci5308.stocki5.stock.price;

import com.csci5308.stocki5.stock.db.IStockDb;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockPriceEod
{
	public boolean setStockClosingPrice(IStockDb iStockDb);
}
