package com.csci5308.stocki5.trade.holding;

import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.db.ITradeDb;

@Service
public class TradeHolding implements ITradeHolding
{
	private static ITradeHolding uniqueInstance = null;

	private TradeHolding()
	{
	}

	public static ITradeHolding instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new TradeHolding();
		}
		return uniqueInstance;
	}

	public List<IHolding> fetchUserHoldings(String userCode, ITradeDb iTradeDb, IStockDb iStockDb)
	{
		List<IHolding> iHoldings = iTradeDb.getHoldingsByUserCode(userCode);
		ListIterator<IHolding> iHoldingsIterator = iHoldings.listIterator();
		while (iHoldingsIterator.hasNext())
		{
			IHolding iHolding = iHoldingsIterator.next();
			iHolding.setStockDbInterface(iStockDb);
			iHolding.calculateProfitLoss();
			iHoldingsIterator.set(iHolding);
		}
		return iHoldings;
	}

}
