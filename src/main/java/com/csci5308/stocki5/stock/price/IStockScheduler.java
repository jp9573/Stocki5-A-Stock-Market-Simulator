package com.csci5308.stocki5.stock.price;

public interface IStockScheduler
{
	public void scheduleGenerateStockPrice();

	public void scheduleStockBod();

	public void scheduleStockEod();

	public void scheduleStockClosingPrice();
}
