package com.csci5308.stocki5.trade;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TradeFetch {
	
	public List<Trade> fetchUserOrders(String userCode, TradeDbInterface tradeDbInterface) {
		return tradeDbInterface.getTodaysTradeByUserCode(userCode);
	}

}
