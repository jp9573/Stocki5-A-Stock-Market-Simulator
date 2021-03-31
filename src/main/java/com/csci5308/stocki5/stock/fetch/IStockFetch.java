package com.csci5308.stocki5.stock.fetch;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.User;

@Service
public interface IStockFetch
{
	public List<Stock> fetchUserStocks(IStockDb iStockDb, IUserDb iUserDb, String userCode);

	public List<Stock> fetchTopGainerStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode);

	public List<Stock> fetchTopLoserStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode);

	public String getUserStockSegments(User user);
}
