package com.csci5308.stocki5.trade.db;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.StockDbMock;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.holding.Holding;
import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.UserDbMock;

import java.util.*;

public class TradeDbMock implements ITradeDb{
    @Override
    public boolean insertTrade(Trade trade, boolean isHolding) {
        return true;
    }

    @Override
    public List<Trade> getTodaysTradeByUserCode(String userCode) {

        List<Trade> tradeList = new ArrayList<>();
        IStockDb stockDb = new StockDbMock();
        IUserDb userDb = new UserDbMock();
        if(userCode.equals("AB123456")) {
            tradeList.add(new Trade(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, stockDb, userDb));
            tradeList.add(new Trade(userCode, 1, TradeType.SELL, 50, TradeStatus.EXECUTED, stockDb, userDb));
            tradeList.add(new Trade(userCode, 2, TradeType.BUY, 100, TradeStatus.PENDING, stockDb, userDb));
            tradeList.add(new Trade(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, stockDb, userDb));
            tradeList.add(new Trade(userCode, 3, TradeType.SELL, 75, TradeStatus.FAILED, stockDb, userDb));
        }
        return tradeList;
    }

    @Override
    public List<Holding> getHoldingsByUserCode(String userCode) {
        List<Holding> holdingList = new ArrayList<>();
        IStockDb stockDb = new StockDbMock();
        IUserDb userDb = new UserDbMock();
        if(userCode.equals("AB123456")) {
            holdingList.add(new Holding(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, stockDb, userDb, true));
            holdingList.add(new Holding(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, stockDb, userDb, true));
            holdingList.add(new Holding(userCode, 2, TradeType.BUY, 100, TradeStatus.EXECUTED, stockDb, userDb, true));
            holdingList.add(new Holding(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, stockDb, userDb, true));
            holdingList.add(new Holding(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, stockDb, userDb, true));
        }
        return holdingList;
    }

    @Override
    public List<Trade> getPendingTrades(TradeType tradeType) {
        List<Trade> tradeList = new ArrayList<>();
        IStockDb stockDb = new StockDbMock();
        IUserDb userDb = new UserDbMock();
        if(tradeType == TradeType.BUY) {
            tradeList.add(new Trade("AB123456", 1, TradeType.BUY, 50, TradeStatus.PENDING, stockDb, userDb));
            tradeList.add(new Trade("AB123456", 1, TradeType.BUY, 50, TradeStatus.PENDING, stockDb, userDb));
            tradeList.add(new Trade("AB123456", 2, TradeType.BUY, 100, TradeStatus.PENDING, stockDb, userDb));
            tradeList.add(new Trade("AB123456", 3, TradeType.BUY, 75, TradeStatus.PENDING, stockDb, userDb));
            tradeList.add(new Trade("AB123456", 3, TradeType.BUY, 75, TradeStatus.PENDING, stockDb, userDb));
        }
        return tradeList;
    }

    @Override
    public boolean updateBuyTrade(Trade trade, boolean isHolding) {
        return true;
    }

    @Override
    public boolean updateSellTrade(Trade trade, boolean isHolding) {
        return true;
    }

    @Override
    public boolean updateBulkTradeStatus(List<Trade> trades) {
        return true;
    }

    @Override
    public boolean removeHolding(String tradeNumber) {
        if(tradeNumber == "ABC123"){
            return true;
        }
        return false;
    }

    @Override
    public boolean removeHoldingForAutoSell(String userCode, int stockId, int quantity) {
        return true;
    }
}
