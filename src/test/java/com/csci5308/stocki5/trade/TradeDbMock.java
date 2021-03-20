package com.csci5308.stocki5.trade;

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

}
