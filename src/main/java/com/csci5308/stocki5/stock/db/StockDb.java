package com.csci5308.stocki5.stock.db;

import com.csci5308.stocki5.database.DbConnection;
import com.csci5308.stocki5.database.IDbConnection;
import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.factory.StockAbstractFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class StockDb implements IStockDb
{
	final String STOCK_ATTRIBUTES = "stock_id,symbol,open,high,low,price,latest_trading_date,previous_close,segment,percent";

	private static IStockDb uniqueInstance = null;

	IDbConnection dbConnection = DbConnection.instance();
	StockAbstractFactory stockFactory = StockAbstractFactory.instance();

	private StockDb()
	{
	}

	public static IStockDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockDb();
		}
		return uniqueInstance;
	}

	@Override
	public IStock getStock(int stockId)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			IStock iStock = null;
			Statement statement = connection.createStatement();
			String query = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data WHERE stock_id=" + stockId;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next())
			{
				iStock = convertResultSet(resultSet);
			}
			return iStock;
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
	public List<IStock> getStocksBySegment(String segments)
	{
		List<IStock> iStocks = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data WHERE segment IN " + "(" + segments + ")";
			ResultSet resultSet = statement.executeQuery(query);
			IStock iStock = null;
			while (resultSet.next())
			{
				iStock = convertResultSet(resultSet);
				iStocks.add(iStock);
			}
			return iStocks;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return iStocks;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public List<IStock> getStocks()
	{
		List<IStock> iStocks = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data";
			ResultSet resultSet = statement.executeQuery(query);
			IStock iStock = null;
			while (resultSet.next())
			{
				iStock = convertResultSet(resultSet);
				iStocks.add(iStock);
			}
			return iStocks;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return iStocks;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean updateStocks(List<IStock> iStocks)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			Iterator<IStock> iStocksIterator = iStocks.iterator();
			IStock iStock;
			while (iStocksIterator.hasNext())
			{
				iStock = iStocksIterator.next();
				statement.addBatch("UPDATE stock_data SET " + "symbol='" + iStock.getSymbol() + "'," + "open=" + iStock.getOpen() + "," + "high=" + iStock.getHigh() + "," + "low=" + iStock.getLow() + "," + "price=" + iStock.getPrice() + "," + "previous_close=" + iStock.getPreviousClose() + "," + "segment='" + iStock.getSegment() + "'," + "percent=" + iStock.getPercentIncreaseDecrease() + " WHERE stock_id = " + iStock.getStockId());
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

	private IStock convertResultSet(ResultSet resultSet)
	{
		IStock iStock = stockFactory.createStock();
		try
		{
			iStock.setStockId(resultSet.getInt("stock_id"));
			iStock.setSymbol(resultSet.getString("symbol"));
			iStock.setOpen(resultSet.getFloat("open"));
			iStock.setHigh(resultSet.getFloat("high"));
			iStock.setLow(resultSet.getFloat("low"));
			iStock.setPrice(resultSet.getFloat("price"));
			iStock.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
			iStock.setPreviousClose(resultSet.getFloat("previous_close"));
			iStock.setSegment(resultSet.getString("segment"));
			iStock.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return iStock;
	}
}
