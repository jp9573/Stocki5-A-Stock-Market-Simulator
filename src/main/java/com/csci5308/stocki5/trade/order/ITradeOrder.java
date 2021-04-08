package com.csci5308.stocki5.trade.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.db.ITradeDb;

@Service
public interface ITradeOrder
{
	public List<ITrade> fetchUserOrders(String userCode, ITradeDb iTradeDb);
}
