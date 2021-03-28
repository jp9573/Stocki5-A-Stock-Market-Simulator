package com.csci5308.stocki5.trade.sell;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.trade.TradeDbInterface;
import com.csci5308.stocki5.user.UserDbInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TradeSellInterface {

	public boolean sellStock(String userCode, int stockId, int quantity, StockDbInterface stockDbInterface,
                             UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface, String tradeBuyNumber);
	
	public boolean setSellPrice(String userCode, int stockId, int quantity, float sellPrice,
			StockDbInterface stockDbInterface, UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface, String tradeBuyNumber);
	
	public void sellPendingTrades(TradeDbInterface dbInterface, UserDbInterface userDbInterface, List<Stock> stocks);
}
