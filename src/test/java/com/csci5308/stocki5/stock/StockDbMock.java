package com.csci5308.stocki5.stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockDbMock implements StockDbInterface{
    @Override
    public Stock getStockData(int stockId) {
        Stock stock = new Stock();
        if(stockId == 1){
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
}
