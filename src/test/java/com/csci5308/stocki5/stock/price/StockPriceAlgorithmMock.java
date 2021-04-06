package com.csci5308.stocki5.stock.price;

public class StockPriceAlgorithmMock implements IStockPriceAlgorithmMock
{
	private static IStockPriceAlgorithmMock uniqueInstance = null;
	
	private StockPriceAlgorithmMock()
	{
	}

	public static IStockPriceAlgorithmMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockPriceAlgorithmMock();
		}
		return uniqueInstance;
	}
	@Override
	public boolean generateStockPrice()
	{
		return true;
	}

}
