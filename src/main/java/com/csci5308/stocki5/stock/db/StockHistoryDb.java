package com.csci5308.stocki5.stock.db;

import com.csci5308.stocki5.database.DbConnection;
import com.csci5308.stocki5.database.IDbConnection;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import com.csci5308.stocki5.stock.history.IStockHistory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class StockHistoryDb implements IStockHistoryDb
{
	private static IStockHistoryDb uniqueInstance = null;

	IDbConnection dbConnection = DbConnection.instance();
	StockAbstractFactory stockFactory = StockAbstractFactory.instance();

	private StockHistoryDb()
	{
	}

	public static IStockHistoryDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockHistoryDb();
		}
		return uniqueInstance;
	}

	@Override
	public List<IStockHistory> getStockHistoryBySymbol(String symbol)
	{
		List<IStockHistory> iStockHistories = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT history_id,stock_id,symbol,open,high,low,price,latest_trading_date,previous_close,segment,percent,insert_timestamp FROM stock_data_history WHERE symbol = \"" + symbol + "\"";
			ResultSet resultSet = statement.executeQuery(query);
			IStockHistory iStockHistory = stockFactory.createStockHistory();
			while (resultSet.next())
			{
				iStockHistory.setHistoryId(resultSet.getLong("history_id"));
				iStockHistory.setStockId(resultSet.getInt("stock_id"));
				iStockHistory.setSymbol(resultSet.getString("symbol"));
				iStockHistory.setOpen(resultSet.getFloat("open"));
				iStockHistory.setHigh(resultSet.getFloat("high"));
				iStockHistory.setLow(resultSet.getFloat("low"));
				iStockHistory.setPrice(resultSet.getFloat("price"));
				iStockHistory.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				iStockHistory.setPreviousClose(resultSet.getFloat("previous_close"));
				iStockHistory.setSegment(resultSet.getString("segment"));
				iStockHistory.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
				iStockHistory.setInsertTimestamp(resultSet.getTimestamp("insert_timestamp").toString());
				iStockHistories.add(iStockHistory);
			}
			return iStockHistories;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return iStockHistories;
		} finally
		{
			dbConnection.closeConnection(connection);
		}

	}

	@Override
	public boolean insertStocksHistory(List<IStockHistory> iStockHistories)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			String query = "INSERT INTO stock_data_history VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			Iterator<IStockHistory> iStockHistoriesIterator = iStockHistories.iterator();
			IStockHistory iStockHistory;
			while (iStockHistoriesIterator.hasNext())
			{
				iStockHistory = iStockHistoriesIterator.next();
				statement.setLong(1, iStockHistory.getHistoryId());
				statement.setInt(2, iStockHistory.getStockId());
				statement.setString(3, iStockHistory.getSymbol());
				statement.setFloat(4, iStockHistory.getOpen());
				statement.setFloat(5, iStockHistory.getHigh());
				statement.setFloat(6, iStockHistory.getLow());
				statement.setFloat(7, iStockHistory.getPrice());
				statement.setDate(8, (Date) iStockHistory.getLatestTradingDate());
				statement.setFloat(9, iStockHistory.getPreviousClose());
				statement.setString(10, iStockHistory.getSegment());
				statement.setFloat(11, iStockHistory.getPercentIncreaseDecrease());
				statement.setTimestamp(12, Timestamp.valueOf(iStockHistory.getInsertTimestamp()));
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
			String query = "DELETE FROM stock_data_history WHERE history_id < " + String.valueOf(historyId);
			PreparedStatement statement = connection.prepareStatement(query);
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
			String query = "SELECT COUNT(*) FROM stock_data_history";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
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
			String query = "SELECT history_id FROM stock_data_history GROUP BY history_id ORDER BY history_id ASC LIMIT 1 OFFSET " + String.valueOf(n) + ";";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
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
