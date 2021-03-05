package com.csci5308.stocki5.stock;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

public class StockPriceAlgorithm {

	@Autowired
	StockDbInterface stockDbInterface;

	public void generateStockPrice() {

	}

	public float stockPriceAlgorithm(float currentPrice) {
		System.out.println(currentPrice);
		DecimalFormat df = new DecimalFormat("##.00");
		Random random = new Random();
		float maxPriceRange = currentPrice + 10;
		float minPriceRange = currentPrice - 10;
		float newPrice = minPriceRange + random.nextFloat() * (maxPriceRange - minPriceRange);
		float formatedNewPrice = Float.parseFloat(df.format(newPrice));
		System.out.println(formatedNewPrice);
		return formatedNewPrice;
	}

}
