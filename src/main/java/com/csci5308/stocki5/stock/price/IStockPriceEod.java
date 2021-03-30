package com.csci5308.stocki5.stock.price;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.stock.db.IStockDb;

@Repository
public interface IStockPriceEod
{
	public void setStockClosingPrice(IStockDb stockDbInterface);
}
