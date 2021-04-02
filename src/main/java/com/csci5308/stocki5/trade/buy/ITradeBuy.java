package com.csci5308.stocki5.trade.buy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.user.IUserDb;

@Service
public interface ITradeBuy
{

	public boolean buyStock(String userCode, int stockId, int quantity, IStockDb stockDbInterface, IUserDb userDbInterface, ITradeDb tradeDbInterface);

	public boolean setBuyPrice(String userCode, int stockId, int quantity, float buyPrice, IStockDb stockDbInterface, IUserDb userDbInterface, ITradeDb tradeDbInterface);

	public void buyPendingTrades(ITradeDb dbInterface, IUserDb userDbInterface, List<IStock> stocks);
}
