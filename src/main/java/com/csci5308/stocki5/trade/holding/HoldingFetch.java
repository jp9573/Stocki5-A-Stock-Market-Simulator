package com.csci5308.stocki5.trade.holding;

import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.trade.TradeDbInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldingFetch {
	
	public List<Holding> fetchUserHoldings(String userCode, TradeDbInterface tradeDbInterface, StockDbInterface stockDbInterface) {
		List<Holding> holdingList = tradeDbInterface.getHoldingsByUserCode(userCode);
		System.out.println(holdingList.size());
		for (int i = 0; i < holdingList.size(); i++) {
			Holding holding = holdingList.get(i);
			holding.setStockDbInterface(stockDbInterface);
			holding.calculateProfitLoss();
			holdingList.set(i, holding);
		}
		return holdingList;
	}

}
