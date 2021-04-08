package com.csci5308.stocki5.stock.fetch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.StockSegment;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public class StockFetch implements IStockFetch
{
	private final int LIMIT = 5;
	private static IStockFetch uniqueInstance = null;

	private StockFetch()
	{
	}

	public static IStockFetch instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockFetch();
		}
		return uniqueInstance;
	}

	public List<IStock> fetchUserStocks(IStockDb iStockDb, IUserDb iUserDb, String userCode)
	{
		IUser iUser = iUserDb.getUser(userCode);
		String segments = generateStockSegmentsList(iUser);
		List<IStock> iStocks = iStockDb.getStocksBySegment(segments);
		return iStocks;
	}

	public List<IStock> fetchTopGainerStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode)
	{
		IUser iUser = iUserDb.getUser(userCode);
		String segments = generateStockSegmentsList(iUser);
		List<IStock> topStocks = iStockDbGainersLosers.getHighestPriceStocks(segments, LIMIT);
		return topStocks;
	}

	public List<IStock> fetchTopLoserStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode)
	{
		IUser iUser = iUserDb.getUser(userCode);
		String segments = generateStockSegmentsList(iUser);
		List<IStock> bottomStocks = iStockDbGainersLosers.getLowestPriceStocks(segments, LIMIT);
		return bottomStocks;
	}

	public String generateStockSegmentsList(IUser iUser)
	{
		List<String> segmentsList = new ArrayList<>();

		if (iUser.getForeignExchange() == 1)
		{
			segmentsList.add(String.valueOf(StockSegment.FOREX));
		}
		if (iUser.getInternationalDerivativeExchange() == 1)
		{
			segmentsList.add(String.valueOf(StockSegment.IDE));
		}
		if (iUser.getInternationalCommodityExchange() == 1)
		{
			segmentsList.add(String.valueOf(StockSegment.ICE));
		}
		if (iUser.getInternationalStockExchange() == 1)
		{
			segmentsList.add(String.valueOf(StockSegment.ISE));
		}

		String segments = String.join(",", segmentsList.stream().map(segment -> ("'" + segment + "'")).collect(Collectors.toList()));
		return segments;
	}
}
