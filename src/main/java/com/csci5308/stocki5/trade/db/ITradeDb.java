package com.csci5308.stocki5.trade.db;

import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.holding.IHolding;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITradeDb
{

	public boolean insertTrade(ITrade trade, boolean isHolding);

	public List<ITrade> getTodaysTradeByUserCode(String userCode);

	public List<IHolding> getHoldingsByUserCode(String userCode);

	public List<ITrade> getPendingTrades(TradeType tradeType);

	public boolean updateBuyTrade(ITrade trade, boolean isHolding);

	public boolean updateSellTrade(ITrade trade, boolean isHolding);

	public boolean updateBulkTradeStatus(List<ITrade> trades);

	public boolean removeHolding(String tradeNumber);

	public boolean removeHoldingForAutoSell(String userCode, int stockId, int quantity);

}
