package com.csci5308.stocki5.trade.order;

import java.util.List;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeDbInterface;
import org.springframework.stereotype.Service;

@Service
public class TradeFetch {
	
	public List<Trade> fetchUserOrders(String userCode, TradeDbInterface tradeDbInterface) {
		return tradeDbInterface.getTodaysTradeByUserCode(userCode);
	}

}
