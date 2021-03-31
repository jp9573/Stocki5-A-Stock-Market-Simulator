package com.csci5308.stocki5.trade.buy;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.user.IUserDb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITradeBuy
{

	public boolean buyStock(String userCode, int stockId, int quantity, IStockDb stockDbInterface,
			IUserDb userDbInterface, ITradeDb tradeDbInterface);

	public boolean setBuyPrice(String userCode, int stockId, int quantity, float buyPrice, IStockDb stockDbInterface,
			IUserDb userDbInterface, ITradeDb tradeDbInterface);

	public void buyPendingTrades(ITradeDb dbInterface, IUserDb userDbInterface, List<Stock> stocks);
}
