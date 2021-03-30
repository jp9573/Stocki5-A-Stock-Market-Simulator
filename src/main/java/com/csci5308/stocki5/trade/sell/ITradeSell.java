package com.csci5308.stocki5.trade.sell;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.ITradeDb;
import com.csci5308.stocki5.user.IUserDb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITradeSell
{

	public boolean sellStock(String userCode, int stockId, int quantity, IStockDb stockDbInterface,
			IUserDb userDbInterface, ITradeDb tradeDbInterface, String tradeBuyNumber);

	public boolean setSellPrice(String userCode, int stockId, int quantity, float sellPrice, IStockDb stockDbInterface,
			IUserDb userDbInterface, ITradeDb tradeDbInterface, String tradeBuyNumber);

	public void sellPendingTrades(ITradeDb dbInterface, IUserDb userDbInterface, List<Stock> stocks);
}
