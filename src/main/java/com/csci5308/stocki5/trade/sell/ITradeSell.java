package com.csci5308.stocki5.trade.sell;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.db.ITradeDb;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITradeSell
{
	public boolean sellStock(String userCode, int stockId, int quantity, IStockDb iStockDb, IUserDb iUserDb, ITradeDb iTradeDb, String tradeBuyNumber);

	public boolean setSellPrice(String userCode, int stockId, int quantity, float sellPrice, IStockDb iStockDb, IUserDb iUserDb, ITradeDb iTradeDb, String tradeBuyNumber);

	public void sellPendingTrades(ITradeDb iTradeDb, IUserDb iUserDb, List<IStock> iStocks);

	public boolean createSetSellPriceTradeDetails(IStockDb iStockDb, ITrade iTrade, float sellPrice);
}
