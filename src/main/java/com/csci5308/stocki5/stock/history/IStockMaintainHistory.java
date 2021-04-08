package com.csci5308.stocki5.stock.history;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockHistoryDb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStockMaintainHistory
{
	public boolean maintainStocksHistory(List<IStock> iStocks, int noOfVersions, IStockHistoryDb iStockHistoryDb);
}
