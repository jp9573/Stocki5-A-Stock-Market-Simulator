package com.csci5308.stocki5.stock;

public class StockDataUpdate extends Stock {

    public StockDataUpdate(int stockId, StockDbInterface dbInterface) {
        super(stockId, dbInterface);
    }

    public boolean updateStock(StockDbInterface dbInterface){
        return dbInterface.updateStockData(this);
    }
}
