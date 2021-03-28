package com.csci5308.stocki5.trade;

import com.csci5308.stocki5.trade.holding.Holding;

import java.util.List;

public class TradeDbMock implements TradeDbInterface {

	@Override
	public boolean insertTrade(Trade trade, boolean isHolding) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Trade getTrade(String tradeNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trade> getTodaysTradeByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Holding> getHoldingsByUserCode(String userCode) {
		return null;
	}

	@Override
	public boolean removeHolding(String tradeNumber) {
		return false;
	}

	@Override
	public boolean removeHoldingForAutoSell(String userCode, int stockId, int quantity) {
		return false;
	}

	@Override
	public List<Trade> getPendingTrades(TradeType tradeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBuyTrade(Trade trade, boolean isHolding) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSellTrade(Trade trade, boolean isHolding) {
		return false;
	}

	@Override
	public boolean updateBulkTradeStatus(List<Trade> trades) {
		// TODO Auto-generated method stub
		return false;
	}

}
