package com.csci5308.stocki5.stock.fetch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.User;

@Service
public class StockFetch implements IStockFetch
{
	private final int LIMIT = 5;
	
	public List<Stock> fetchUserStocks(IStockDb iStockDb, IUserDb iUserDb, String userCode)
	{
		User user = iUserDb.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<Stock> stocks = iStockDb.getStocksBySegment(segments);
		return stocks;
	}

	public List<Stock> fetchTopGainerStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode)
	{
		User user = iUserDb.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<Stock> topStocks = iStockDbGainersLosers.getHighestPriceStocks(segments, LIMIT);
		return topStocks;
	}

	public List<Stock> fetchTopLoserStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode)
	{
		User user = iUserDb.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<Stock> bottomStocks = iStockDbGainersLosers.getLowestPriceStocks(segments, LIMIT);
		return bottomStocks;
	}
	
	public String getUserStockSegments(User user)
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
