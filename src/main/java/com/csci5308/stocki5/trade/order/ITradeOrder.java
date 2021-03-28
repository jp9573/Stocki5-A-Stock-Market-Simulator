package com.csci5308.stocki5.trade.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.ITradeDb;

@Service
public interface ITradeOrder 
{
	public List<Trade> fetchUserOrders(String userCode, ITradeDb tradeDbInterface);
}
