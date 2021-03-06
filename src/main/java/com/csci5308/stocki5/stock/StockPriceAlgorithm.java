package com.csci5308.stocki5.stock;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class StockPriceAlgorithm {

	public void generateStockPrice(StockDbInterface stockDbInterface) {
		float newPrice = 0.00f;
		float percent = 0.00f;
		List<Stock> stocks = stockDbInterface.getStocks();
		for (Stock stock : stocks) {
			newPrice = stockPriceAlgorithm(stock.getPrice());
			percent = stockPricePercentIncreaseDecrease(newPrice, stock.getPreviousClose());
			stock.setPrice(newPrice);
			stock.setPercentIncreaseDecrease(percent);
			stock.calculateHighAndLow(newPrice);
		}
		stockDbInterface.updateStockBulk(stocks);
	}

	public float stockPriceAlgorithm(float currentPrice) {
		DecimalFormat df = new DecimalFormat("##.00");
		Random random = new Random();
		float maxPriceRange = currentPrice + 10;
		float minPriceRange = currentPrice - 10;
		if (minPriceRange < 0.00f) {
			minPriceRange = 0.00f;
		}
		if (maxPriceRange < 0.00f) {
			maxPriceRange = 1.00f;
		}
		float newPrice = minPriceRange + random.nextFloat() * (maxPriceRange - minPriceRange);
		float formatedNewPrice = Float.parseFloat(df.format(newPrice));
		return formatedNewPrice;
	}

	public float stockPricePercentIncreaseDecrease(float currentPrice, float previousClose) {
		DecimalFormat df = new DecimalFormat("##.00");
		float percent = 0.00f;
		float formatedPercent = 0.00f;
		if (previousClose <= 0.00f) {
			formatedPercent = Float.parseFloat(df.format(percent));
		} else {
			percent = ((currentPrice - previousClose) / previousClose) * 100;
			formatedPercent = Float.parseFloat(df.format(percent));
		}
		return formatedPercent;
	}

}
