package com.csci5308.stocki5.stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDbInterface;

@Service
public class StockFetch {

    public List<Stock> fetchUserStocks(StockDbInterface stockDbInterface, UserDbInterface userDbInterface,
                                       String userCode) {
        User user = userDbInterface.getUser(userCode);
        String segments = getUserSegments(user);
        List<Stock> stocks = stockDbInterface.getStocksBySegment(segments);
        return stocks;
    }

    public List<Stock> fetchTop5GainerStocks(StockDbInterface stockDbInterface, UserDbInterface userDbInterface,
                                             String userCode) {
        User user = userDbInterface.getUser(userCode);
        String segments = getUserSegments(user);
        List<Stock> top5Stocks = stockDbInterface.getHighestPriceStocks(segments, 5);
        return top5Stocks;
    }

    public List<Stock> fetchTop5LoserStocks(StockDbInterface stockDbInterface, UserDbInterface userDbInterface,
                                             String userCode) {
        User user = userDbInterface.getUser(userCode);
        String segments = getUserSegments(user);
        List<Stock> bottom5Stocks = stockDbInterface.getLowestPriceStocks(segments, 5);
        return bottom5Stocks;
    }

    public String getUserSegments(User user) {
        List<String> segmentsList = new ArrayList<String>();
        if (user.getForeignExchange() == 1) {
            segmentsList.add("FOREX");
        }
        if (user.getInternationalDerivativeExchange() == 1) {
            segmentsList.add("IDE");
        }
        if (user.getInternationalCommodityExchange() == 1) {
            segmentsList.add("ICE");
        }
        if (user.getInternationalStockExchange() == 1) {
            segmentsList.add("ISE");
        }

        String segments = "";
        for (int i = 0; i < segmentsList.size(); i++) {
            if (i < (segmentsList.size() - 1)) {
                segments += "'" + segmentsList.get(i) + "',";
            } else {
                segments += "'" + segmentsList.get(i) + "'";
            }
        }
        return segments;
    }

}
