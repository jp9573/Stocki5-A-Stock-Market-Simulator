package com.csci5308.stocki5.trade.holding;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.db.ITradeDb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITradeHolding
{
	public List<IHolding> fetchUserHoldings(String userCode, ITradeDb iTradeDb, IStockDb iStockDb);
}
