package com.csci5308.stocki5.trade.db;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.holding.Holding;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITradeDb
{

	public boolean insertTrade(Trade trade, boolean isHolding);

	public List<Trade> getTodaysTradeByUserCode(String userCode);

	public List<Holding> getHoldingsByUserCode(String userCode);

	public List<Trade> getPendingTrades(TradeType tradeType);

	public boolean updateBuyTrade(Trade trade, boolean isHolding);

	public boolean updateSellTrade(Trade trade, boolean isHolding);

	public boolean updateBulkTradeStatus(List<Trade> trades);

	public boolean removeHolding(String tradeNumber);

	public boolean removeHoldingForAutoSell(String userCode, int stockId, int quantity);

}
