package com.csci5308.stocki5.trade.order;

import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.db.ITradeDb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITradeOrder 
{
	public List<ITrade> fetchUserOrders(String userCode, ITradeDb tradeDbInterface);
}
