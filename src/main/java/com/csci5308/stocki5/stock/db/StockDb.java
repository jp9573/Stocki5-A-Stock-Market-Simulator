package com.csci5308.stocki5.stock.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.config.Stocki5DbConnection;
import com.csci5308.stocki5.stock.Stock;

@Repository
public class StockDb implements IStockDb
{
	final String STOCK_ATTRIBUTES = "stock_id,symbol,open,high,low,price,latest_trading_date,previous_close,segment,percent";

	@Autowired
	Stocki5DbConnection dbConnection;
	
	@Override
	public Stock getStock(int stockId)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			Stock stock = new Stock();
			Statement statement = connection.createStatement();
			String selectStockSql = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data WHERE stock_id=" + stockId;
			ResultSet resultSet = statement.executeQuery(selectStockSql);
			while (resultSet.next())
			{
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
			}
			return stock;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public List<Stock> getStocksBySegment(String segments)
	{
		List<Stock> stocks = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String selectStockSql = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data WHERE segment IN " + "(" + segments + ")";
			ResultSet resultSet = statement.executeQuery(selectStockSql);
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
	public List<Stock> getStocks()
	{
		List<Stock> stocks = new ArrayList<Stock>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String selectStockSql = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data";
			ResultSet resultSet = statement.executeQuery(selectStockSql);
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
	public boolean updateStocks(List<Stock> stocks)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			Iterator<Stock> stocksIterator = stocks.iterator();
			Stock stock;
			while (stocksIterator.hasNext())
			{
				stock = stocksIterator.next();
				statement.addBatch("UPDATE stock_data SET " + "symbol='" + stock.getSymbol() + "'," + "open=" + stock.getOpen() + "," + "high=" + stock.getHigh() + "," + "low=" + stock.getLow() + "," + "price=" + stock.getPrice() + "," + "previous_close=" + stock.getPreviousClose() + "," + "segment='" + stock.getSegment() + "'," + "percent=" + stock.getPercentIncreaseDecrease() + " WHERE stock_id = " + stock.getStockId());
			}
			int[] result = statement.executeBatch();
			return result.length > 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

}
