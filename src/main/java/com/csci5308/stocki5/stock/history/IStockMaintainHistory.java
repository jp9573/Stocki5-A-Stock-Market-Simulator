package com.csci5308.stocki5.stock.history;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.IStock;

@Service
public interface IStockMaintainHistory
{
	public boolean maintainStocksHistory(List<IStock> stocks, int noOfVersions, IStockHistoryDb iStockHistoryDb);
}
