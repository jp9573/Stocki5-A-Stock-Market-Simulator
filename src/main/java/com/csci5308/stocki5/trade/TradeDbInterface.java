package com.csci5308.stocki5.trade;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface TradeDbInterface {
	
	public boolean insertTrade(Trade trade, boolean isHolding);
	
	public Trade getTrade(String tradeNumber);

	public List<Trade> getTodaysTradeByUserCode(String userCode);

	public List<Holding> getHoldingsByUserCode(String userCode);

}
