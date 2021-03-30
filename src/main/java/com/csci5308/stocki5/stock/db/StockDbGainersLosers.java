package com.csci5308.stocki5.stock.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.config.Stocki5DbConnection;
import com.csci5308.stocki5.stock.Stock;

@Repository
public class StockDbGainersLosers extends StockDb implements IStockDbGainersLosers
{
	@Autowired
	Stocki5DbConnection dbConnection;
	
	@Override
	public List<Stock> getHighestPriceStocks(String segments, int limit)
	{
		List<Stock> stocks = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data WHERE segment IN " + "(" + segments + ") ORDER BY percent DESC LIMIT " + limit;
			ResultSet resultSet = statement.executeQuery(query);
			Stock stock = null;
			while (resultSet.next())
			{
				stock = new Stock();
				stock.setStockId(resultSet.getInt("stock_id"));
				stock.setSymbol(resultSet.getString("symbol"));
				stock.setOpen(resultSet.getFloat("open"));
				stock.setHigh(resultSet.getFloat("high"));
				stock.setLow(resultSet.getFloat("low"));
				stock.setPrice(resultSet.getFloat("price"));
				stock.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				stock.setPreviousClose(resultSet.getFloat("previous_close"));
				stock.setSegment(resultSet.getString("segment"));
				stock.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
				stocks.add(stock);
			}
			return stocks;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return stocks;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public List<Stock> getLowestPriceStocks(String segments, int limit)
	{
		List<Stock> stocks = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data WHERE segment IN " + "(" + segments + ") ORDER BY percent LIMIT " + limit;
			ResultSet resultSet = statement.executeQuery(query);
			Stock stock = null;
			while (resultSet.next())
			{
				stock = new Stock();
				stock.setStockId(resultSet.getInt("stock_id"));
				stock.setSymbol(resultSet.getString("symbol"));
				stock.setOpen(resultSet.getFloat("open"));
				stock.setHigh(resultSet.getFloat("high"));
				stock.setLow(resultSet.getFloat("low"));
				stock.setPrice(resultSet.getFloat("price"));
				stock.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				stock.setPreviousClose(resultSet.getFloat("previous_close"));
				stock.setSegment(resultSet.getString("segment"));
				stock.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
				stocks.add(stock);
			}
			return stocks;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return stocks;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}
}
