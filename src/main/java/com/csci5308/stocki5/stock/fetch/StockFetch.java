package com.csci5308.stocki5.stock.fetch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbHighestLowest;
import com.csci5308.stocki5.user.IUserDb;

@Service
public class StockFetch implements IStockFetch
{

	public List<Stock> fetchUserStocks(IStockDb stockDbInterface, IUserDb userDbInterface, String userCode)
	{
		User user = userDbInterface.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<Stock> stocks = stockDbInterface.getStocksBySegment(segments);
		return stocks;
	}

	public List<Stock> fetchTop5GainerStocks(IStockDbHighestLowest iStockDbHighestLowest, IUserDb userDbInterface, String userCode)
	{
		User user = userDbInterface.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<Stock> top5Stocks = iStockDbHighestLowest.getHighestPriceStocks(segments, 5);
		return top5Stocks;
	}

	public List<Stock> fetchTop5LoserStocks(IStockDbHighestLowest iStockDbHighestLowest, IUserDb userDbInterface, String userCode)
	{
		User user = userDbInterface.getUser(userCode);
		String segments = getUserStockSegments(user);
		List<Stock> bottom5Stocks = iStockDbHighestLowest.getLowestPriceStocks(segments, 5);
		return bottom5Stocks;
	}

	public String getUserStockSegments(User user)
	{
		List<String> segmentsList = new ArrayList<String>();
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
