package com.csci5308.stocki5.stock.history;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.stock.Stock;

@Service
public interface IStockMaintainHistory
{
	public void maintainStocksHistory(List<Stock> stocks, int noOfVersions, IStockHistoryDb iStockHistoryDb);
}
