package com.csci5308.stocki5.stock;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPrediction {

    public List<Stock> predictStockValue(StockHistoryDb stockDbInterface, String stock) {
        List<StockHistory> stockHistories = stockDbInterface.getStockHistory(stock);
        return null;
    }
}
