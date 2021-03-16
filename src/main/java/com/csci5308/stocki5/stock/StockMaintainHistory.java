package com.csci5308.stocki5.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockMaintainHistory {

    public void maintainStocksHistory(List<Stock> stocks, int noOfVersions, StockHistoryDbInterface stockHistoryDb){
        Date date = new Date();
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateTimeFormat);

        long historyId = date.getTime();
        String insertTimestamp = dateFormatter.format(date);

        List<StockHistory> stocksHistory = new ArrayList<StockHistory>();
        for(Stock stock : stocks){
            stocksHistory.add(new StockHistory(historyId, insertTimestamp, stock));
        }

        int milliSecondsToSubtract = (5000/1000) * noOfVersions * 60000;
        long olderHistoryId = historyId - milliSecondsToSubtract;

        stockHistoryDb.deleteStockHistoryLesserThanHistoryId(olderHistoryId);

        stockHistoryDb.insertStocksHistoryBulk(stocksHistory);
    }
}
