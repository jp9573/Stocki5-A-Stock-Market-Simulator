package com.csci5308.stocki5.stock.price;

public class StockSchedulerMock implements IStockSchedulerMock
{

	@Override
	public boolean scheduleGenerateStockPrice()
	{
		return true;
	}

	@Override
	public boolean scheduleStockBod()
	{
		return true;
	}

	@Override
	public boolean scheduleStockEod()
	{
		return true;
	}

	@Override
	public boolean scheduleStockClosingPrice()
	{
		return true;
	}

}
