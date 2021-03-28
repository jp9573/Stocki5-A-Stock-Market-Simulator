package com.csci5308.stocki5.trade.buy;

import java.util.List;

import com.csci5308.stocki5.trade.TradeDbInterface;
import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.UserDbInterface;

@Service
public interface TradeBuyInterface {

	public boolean buyStock(String userCode, int stockId, int quantity, StockDbInterface stockDbInterface,
			UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface);
	
	public boolean setBuyPrice(String userCode, int stockId, int quantity, float buyPrice,
			StockDbInterface stockDbInterface, UserDbInterface userDbInterface, TradeDbInterface tradeDbInterface);
	
	public void buyPendingTrades(TradeDbInterface dbInterface, UserDbInterface userDbInterface, List<Stock> stocks);
}
