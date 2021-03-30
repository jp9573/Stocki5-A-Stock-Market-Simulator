package com.csci5308.stocki5.stock.fetch;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStockDb;
import com.csci5308.stocki5.stock.IStockDbHighestLowest;
import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.user.IUserDb;
import com.csci5308.stocki5.user.User;

@Service
public interface IStockFetch
{
	public List<Stock> fetchUserStocks(IStockDb stockDbInterface, IUserDb userDbInterface, String userCode);

	public List<Stock> fetchTop5GainerStocks(IStockDbHighestLowest iStockDbHighestLowest, IUserDb userDbInterface, String userCode);

	public List<Stock> fetchTop5LoserStocks(IStockDbHighestLowest iStockDbHighestLowest, IUserDb userDbInterface, String userCode);

	public String getUserStockSegments(User user);
}
