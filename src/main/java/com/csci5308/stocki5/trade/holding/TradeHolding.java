package com.csci5308.stocki5.trade.holding;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.db.ITradeDb;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;

@Service
public class TradeHolding implements ITradeHolding
{

	public List<Holding> fetchUserHoldings(String userCode, ITradeDb tradeDbInterface,
			IStockDb stockDbInterface)
	{
		List<Holding> holdingList = tradeDbInterface.getHoldingsByUserCode(userCode);
		ListIterator<Holding> holdingListIterator = holdingList.listIterator();
		while (holdingListIterator.hasNext())
		{
			Holding holding = holdingListIterator.next();
			holding.setStockDbInterface(stockDbInterface);
			holding.calculateProfitLoss();
			holdingListIterator.set(holding);
		}
		return holdingList;
	}

}
