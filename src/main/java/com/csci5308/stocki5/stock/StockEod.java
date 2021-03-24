package com.csci5308.stocki5.stock;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class StockEod {

	public void setStockClosingPrice(StockDbInterface stockDbInterface) {
		List<Stock> stocks = stockDbInterface.getStocks();
		for (Stock stock : stocks) {
			stock.setPreviousClose(stock.getPrice());
		}
		stockDbInterface.updateStockBulk(stocks);
	}
}
