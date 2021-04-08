package com.csci5308.stocki5.trade.buy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public interface ITradeBuy
{
	public boolean buyStock(String userCode, int stockId, int quantity, IStockDb iStockDb, IUserDb iUserDb, ITradeDb iTradeDb);

	public boolean setBuyPrice(String userCode, int stockId, int quantity, float buyPrice, IStockDb iStockDb, IUserDb iUserDb, ITradeDb iTradeDb);

	public void buyPendingTrades(ITradeDb iTradeDb, IUserDb iUserDb, List<IStock> stocks);

	public boolean createSetBuyPriceTradeDetails(IStockDb iUserDb, ITrade trade, float buyPrice);
}
