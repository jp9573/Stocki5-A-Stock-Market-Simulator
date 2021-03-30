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
//            holdingList.add(new Holding(userCode, 1, TradeType.BUY, 50, TradeStatus.EXECUTED, stockDb, userDb));
//            holdingList.add(new Holding(userCode, 1, TradeType.SELL, 50, TradeStatus.EXECUTED, stockDb, userDb));
//            holdingList.add(new Holding(userCode, 2, TradeType.BUY, 100, TradeStatus.PENDING, stockDb, userDb));
//            holdingList.add(new Holding(userCode, 3, TradeType.BUY, 75, TradeStatus.EXECUTED, stockDb, userDb));
//            holdingList.add(new Holding(userCode, 3, TradeType.SELL, 75, TradeStatus.FAILED, stockDb, userDb));
        }
        return holdingList;
    }

    @Override
    public List<Trade> getPendingTrades(TradeType tradeType) {
        return null;
    }

    @Override
    public boolean updateBuyTrade(Trade trade, boolean isHolding) {
        return false;
    }

    @Override
    public boolean updateSellTrade(Trade trade, boolean isHolding) {
        return false;
    }

    @Override
    public boolean updateBulkTradeStatus(List<Trade> trades) {
        return false;
    }

    @Override
    public boolean removeHolding(String tradeNumber) {
        return false;
    }

    @Override
    public boolean removeHoldingForAutoSell(String userCode, int stockId, int quantity) {
        return false;
    }
}
