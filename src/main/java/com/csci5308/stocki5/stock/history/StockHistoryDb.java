package com.csci5308.stocki5.stock.history;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.config.Stocki5DbConnection;

@Repository
public class StockHistoryDb implements IStockHistoryDb
{
	@Autowired
	Stocki5DbConnection dbConnection;
	
	@Override
	public List<StockHistory> getStockHistoryBySymbol(String symbol)
	{
		List<StockHistory> stockHistorys = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT history_id,stock_id,symbol,open,high,low,price,latest_trading_date,previous_close,segment,percent,insert_timestamp FROM stock_data_history WHERE symbol = \"" + symbol + "\"";
			ResultSet resultSet = statement.executeQuery(query);
			StockHistory stockHistory = new StockHistory();
			while (resultSet.next())
			{
				stockHistory.setHistoryId(resultSet.getLong("history_id"));
				stockHistory.setStockId(resultSet.getInt("stock_id"));
				stockHistory.setSymbol(resultSet.getString("symbol"));
				stockHistory.setOpen(resultSet.getFloat("open"));
				stockHistory.setHigh(resultSet.getFloat("high"));
				stockHistory.setLow(resultSet.getFloat("low"));
				stockHistory.setPrice(resultSet.getFloat("price"));
				stockHistory.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				stockHistory.setPreviousClose(resultSet.getFloat("previous_close"));
				stockHistory.setSegment(resultSet.getString("segment"));
				stockHistory.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
				stockHistory.setInsertTimestamp(resultSet.getTimestamp("insert_timestamp").toString());
				stockHistorys.add(stockHistory);
			}
			return stockHistorys;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return stockHistorys;
		} finally
		{
			dbConnection.closeConnection(connection);
		}

	}

	@Override
	public boolean insertStocksHistory(List<StockHistory> stocksHistorys)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			String query = "INSERT INTO stock_data_history VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			Iterator<StockHistory> stockHistoryIterator = stocksHistorys.iterator();
			StockHistory stockHistory;
			while (stockHistoryIterator.hasNext())
			{
				stockHistory = stockHistoryIterator.next();
				statement.setLong(1, stockHistory.getHistoryId());
				statement.setInt(2, stockHistory.getStockId());
				statement.setString(3, stockHistory.getSymbol());
				statement.setFloat(4, stockHistory.getOpen());
				statement.setFloat(5, stockHistory.getHigh());
				statement.setFloat(6, stockHistory.getLow());
				statement.setFloat(7, stockHistory.getPrice());
				statement.setDate(8, (Date) stockHistory.getLatestTradingDate());
				statement.setFloat(9, stockHistory.getPreviousClose());
				statement.setString(10, stockHistory.getSegment());
				statement.setFloat(11, stockHistory.getPercentIncreaseDecrease());
				statement.setTimestamp(12, Timestamp.valueOf(stockHistory.getInsertTimestamp()));
				statement.addBatch();
				statement.clearParameters();
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

	@Override
	public boolean deleteStockHistoryLesserThanHistoryId(long historyId)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			String deleteSQL = "DELETE FROM stock_data_history WHERE history_id < " + String.valueOf(historyId);
			PreparedStatement statement = connection.prepareStatement(deleteSQL);
			int result = statement.executeUpdate();
			return result > 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public int getStocksHistoryCount()
	{
		Connection connection = dbConnection.createConnection();
		int count = -1;
		try
		{
			String getQuery = "SELECT COUNT(*) FROM stock_data_history";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getQuery);
			resultSet.next();
			count = resultSet.getInt("COUNT(*)");
			return count;

		} catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public long getNthStockHistoryId(int n)
	{
		Connection connection = dbConnection.createConnection();
		long historyId = -1;
		try
		{
			String getQuery = "SELECT history_id FROM stock_data_history GROUP BY history_id ORDER BY history_id ASC LIMIT 1 OFFSET " + String.valueOf(n) + ";";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getQuery);
			resultSet.next();
			historyId = resultSet.getLong("history_id");
			return historyId;

		} catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}
}
