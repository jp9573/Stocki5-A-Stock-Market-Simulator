package com.csci5308.stocki5.stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockDbMock implements StockDbInterface {
	@Override
	public Stock getStockData(int stockId) {
		Stock stock = new Stock();
		if (stockId == 1) {
			stock.setSymbol("ABC");
			stock.setOpen(10);
			stock.setHigh(15);
			stock.setLow(5);
			stock.setPrice(13);
			Date latestTradingDate = null;
			try {
				latestTradingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			stock.setLatestTradingDate(latestTradingDate);
			stock.setPreviousClose(8);
			stock.setSegment("ISE");
		}
		return stock;
	}

	@Override
	public boolean updateStockData(Stock stock) {
		System.out.println(stock.getSymbol());
		return true;
	}

	@Override
	public List<Stock> getStocks() {
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = new Stock();
		stock.setSymbol("ABC");
		stock.setOpen(10);
		stock.setHigh(15);
		stock.setLow(5);
		stock.setPrice(13);
		Date latestTradingDate = null;
		try {
			latestTradingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stock.setLatestTradingDate(latestTradingDate);
		stock.setPreviousClose(8);
		stock.setSegment("ISE");
		stocks.add(stock);
		return stocks;
	}

	@Override
	public boolean updateStockBulk(List<Stock> stocks) {
		return true;
	}

	@Override
	public List<Stock> getStocksBySegment(String segments) {
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = new Stock();
		stock.setSymbol("ABC");
		stock.setOpen(10);
		stock.setHigh(15);
		stock.setLow(5);
		stock.setPrice(13);
		Date latestTradingDate = null;
		try {
			latestTradingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stock.setLatestTradingDate(latestTradingDate);
		stock.setPreviousClose(8);
		if (segments.contains("FOREX")) {
			stock.setSegment("FOREX");
		} else if (segments.contains("IDE")) {
			stock.setSegment("IDE");
		} else if (segments.contains("IDE")) {
			stock.setSegment("IDE");
		} else if (segments.contains("ISE")) {
			stock.setSegment("ISE");
		}
		stocks.add(stock);
		return stocks;
	}
}
