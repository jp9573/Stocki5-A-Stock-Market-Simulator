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
import java.util.List;

@Repository
public class StockDbGainersLosers implements IStockDbGainersLosers
{
	final String STOCK_ATTRIBUTES = "stock_id,symbol,open,high,low,price,latest_trading_date,previous_close,segment,percent";

	private static IStockDbGainersLosers uniqueInstance = null;

	IDbConnection dbConnection = DbConnection.instance();
	StockAbstractFactory stockFactory = StockAbstractFactory.instance();

	private StockDbGainersLosers()
	{
	}

	public static IStockDbGainersLosers instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new StockDbGainersLosers();
		}
		return uniqueInstance;
	}

	@Override
	public List<IStock> getHighestPriceStocks(String segments, int limit)
	{
		List<IStock> iStocks = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data WHERE segment IN " + "(" + segments + ") ORDER BY percent DESC LIMIT " + limit;
			ResultSet resultSet = statement.executeQuery(query);
			IStock iStock = null;
			while (resultSet.next())
			{
				iStock = stockFactory.createStock();
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
	public List<IStock> getLowestPriceStocks(String segments, int limit)
	{
		List<IStock> iStocks = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			String query = "SELECT " + STOCK_ATTRIBUTES + " FROM stock_data WHERE segment IN " + "(" + segments + ") ORDER BY percent LIMIT " + limit;
			ResultSet resultSet = statement.executeQuery(query);
			IStock iStock = null;
			while (resultSet.next())
			{
				iStock = stockFactory.createStock();
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
}
