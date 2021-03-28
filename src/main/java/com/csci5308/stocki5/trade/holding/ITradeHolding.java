package com.csci5308.stocki5.trade.holding;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStockDb;
import com.csci5308.stocki5.trade.ITradeDb;

@Service
public interface ITradeHolding
{
	public List<Holding> fetchUserHoldings(String userCode, ITradeDb tradeDbInterface, IStockDb stockDbInterface);
}
