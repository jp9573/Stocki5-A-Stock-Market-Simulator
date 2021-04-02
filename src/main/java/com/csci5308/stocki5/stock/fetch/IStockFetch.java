package com.csci5308.stocki5.stock.fetch;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.stock.db.IStockDbGainersLosers;
import com.csci5308.stocki5.user.db.IUserDb;
import com.csci5308.stocki5.user.User;

@Service
public interface IStockFetch
{
	public List<IStock> fetchUserStocks(IStockDb iStockDb, IUserDb iUserDb, String userCode);

	public List<IStock> fetchTopGainerStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode);

	public List<IStock> fetchTopLoserStocks(IStockDbGainersLosers iStockDbGainersLosers, IUserDb iUserDb, String userCode);

	public String getUserStockSegments(User user);
}
