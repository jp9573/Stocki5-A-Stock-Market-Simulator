package com.csci5308.stocki5.stock.fetch;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockFetch implements IStockFetch
{
	private final int LIMIT = 5;

	public List<IStock> fetchUserStocks(IStockDb iStockDb, IUserDb iUserDb, String userCode)
	{
		IUser user = iUserDb.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<IStock> iStocks = iStockDb.getStocksBySegment(segments);
		return iStocks;
	}

	public List<IStock> fetchTopGainerStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode)
	{
		IUser user = iUserDb.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<IStock> topStocks = iStockDbGainersLosers.getHighestPriceStocks(segments, LIMIT);
		return topStocks;
	}

	public List<IStock> fetchTopLoserStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode)
	{
		IUser user = iUserDb.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<IStock> bottomStocks = iStockDbGainersLosers.getLowestPriceStocks(segments, LIMIT);
		return bottomStocks;
	}

	public String getUserStockSegments(IUser user)
	{
		List<String> segmentsList = new ArrayList<>();
		if (user.getForeignExchange() == 1)
		{
			segmentsList.add("FOREX");
		}
		if (user.getInternationalDerivativeExchange() == 1)
		{
			segmentsList.add("IDE");
		}
		if (user.getInternationalCommodityExchange() == 1)
		{
			segmentsList.add("ICE");
		}
		if (user.getInternationalStockExchange() == 1)
		{
			segmentsList.add("ISE");
		}

		String segments = "";
		for (int i = 0; i < segmentsList.size(); i++)
		{
			if (i < (segmentsList.size() - 1))
			{
				segments += "'" + segmentsList.get(i) + "',";
			} else
			{
				segments += "'" + segmentsList.get(i) + "'";
			}
		}
		return segments;
	}
}
