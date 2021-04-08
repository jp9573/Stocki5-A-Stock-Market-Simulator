package com.csci5308.stocki5.database;

import java.sql.Connection;

public interface IDbConnection
{
	public Connection createConnection();

	public void closeConnection(Connection connection);
}
