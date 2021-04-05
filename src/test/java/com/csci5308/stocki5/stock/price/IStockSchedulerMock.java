package com.csci5308.stocki5.stock.price;

public interface IStockSchedulerMock
{
	public boolean scheduleGenerateStockPrice();
	
	public boolean scheduleStockBod();
	
	public boolean scheduleStockEod();
	
	public boolean scheduleStockClosingPrice();
}
