package com.csci5308.stocki5.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class Stocki5DbConnection
{
	private static final String PROPERTIES_FILE = "config.properties";

	private String driver;
	private String database;
	private String username;
	private String password;

	public Stocki5DbConnection()
	{
		readProperties();
	}

	public Connection createConnection()
	{
		Connection connection = null;
		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(database.trim(), username.trim(), password.trim());

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return connection;
	}

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

	private void readProperties()
	{
		InputStream inputStream = null;
		try
		{
			Properties prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
			if (inputStream == null)
			{
				throw new FileNotFoundException();
			} else
			{
				prop.load(inputStream);
			}
			this.driver = prop.getProperty("jdbc.driverClassName");
			this.database = prop.getProperty("jdbc.url");
			this.username = prop.getProperty("jdbc.username");
			this.password = prop.getProperty("jdbc.password");
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inputStream.close();
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

}
