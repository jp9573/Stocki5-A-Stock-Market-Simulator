package com.csci5308.stocki5.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection implements IDbConnection
{
	private static DbConnection uniqueInstance = null;

	private IStocki5DbConfig stocki5DbConfig = Stocki5DbConfig.instance();

	private DbConnection()
	{
	}

	public static IDbConnection instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new DbConnection();
		}
		return uniqueInstance;
	}

	@Override
	public Connection createConnection()
	{
		Connection connection = null;
		try
		{
			Class.forName(stocki5DbConfig.getDriver());
			connection = DriverManager.getConnection(stocki5DbConfig.getDatabase().trim(), stocki5DbConfig.getUsername().trim(), stocki5DbConfig.getPassword().trim());
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void closeConnection(Connection connection)
	{
		try
		{
			connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
