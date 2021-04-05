package com.csci5308.stocki5.stock;

import java.util.Date;

public interface IStock
{
	public int getStockId();

	public void setStockId(int stockId);

	public String getSymbol();

	public void setSymbol(String symbol);

	public float getOpen();

	public void setOpen(float open);

	public float getHigh();

	public void setHigh(float high);

	public float getLow();

	public void setLow(float low);

	public float getPrice();

	public void setPrice(float price);

	public Date getLatestTradingDate();

	public void setLatestTradingDate(Date latestTradingDate);

	public float getPreviousClose();

	public void setPreviousClose(float previousClose);

	public String getSegment();

	public void setSegment(String segment);

	public float getPercentIncreaseDecrease();

	public void setPercentIncreaseDecrease(float percentIncreaseDecrease);

	public void calculateHighAndLow(float newPrice);
}
