package com.csci5308.stocki5.stock;

import java.util.Date;

public class Stock {

	private int stockId;

	private String symbol;

	private float open;

	private float high;

	private float low;

	private float price;

	private Date latestTradingDate;

	private float previousClose;
	
	private float percentIncreaseDecrease;

	private String segment;

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getOpen() {
		return open;
	}

	public void setOpen(float open) {
		this.open = open;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getLatestTradingDate() {
		return latestTradingDate;
	}

	public void setLatestTradingDate(Date latestTradingDate) {
		this.latestTradingDate = latestTradingDate;
	}

	public float getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(float previousClose) {
		this.previousClose = previousClose;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}
	
	public float getPercentIncreaseDecrease() {
		return percentIncreaseDecrease;
	}

	public void setPercentIncreaseDecrease(float percentIncreaseDecrease) {
		this.percentIncreaseDecrease = percentIncreaseDecrease;
	}

	public Stock() {
		this.stockId = 0;
		this.symbol = null;
		this.open = 0.00f;
		this.high = 0.00f;
		this.low = 0.00f;
		this.price = 0.00f;
		this.latestTradingDate = null;
		this.previousClose = 0.00f;
		this.segment = null;
		this.percentIncreaseDecrease = 0.00f;
	}

	public Stock(int stockId, StockDbInterface dbInterface) {
		Stock stock = dbInterface.getStockData(stockId);
		this.stockId = stockId;
		this.symbol = stock.getSymbol();
		this.open = stock.getOpen();
		this.high = stock.getHigh();
		this.low = stock.getLow();
		this.price = stock.getPrice();
		this.latestTradingDate = stock.getLatestTradingDate();
		this.previousClose = stock.getPreviousClose();
		this.segment = stock.getSegment();
		this.percentIncreaseDecrease = stock.getPercentIncreaseDecrease();
	}

	public void calculateHighAndLow(float newPrice) {
		if (newPrice > this.getHigh()) {
			this.setHigh(newPrice);
		}
		if (newPrice < this.getLow()) {
			this.setLow(newPrice);
		}
	}

}
