package com.csci5308.stocki5.trade.order;

import java.util.List;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.ITradeDb;
import org.springframework.stereotype.Service;

@Service
public class TradeOrder implements ITradeOrder
{

	public List<Trade> fetchUserOrders(String userCode, ITradeDb tradeDbInterface)
	{
		return tradeDbInterface.getTodaysTradeByUserCode(userCode);
	}

}
