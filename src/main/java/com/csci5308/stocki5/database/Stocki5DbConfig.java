package com.csci5308.stocki5.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Stocki5DbConfig implements IStocki5DbConfig
{
	private static final String PROPERTIES_FILE = "config.properties";

	private static IStocki5DbConfig uniqueInstance = null;

	private String driver;
	private String database;
	private String username;
	private String password;

	private Stocki5DbConfig()
	{
		readProperties();
	}

	public static IStocki5DbConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new Stocki5DbConfig();
		}
		return uniqueInstance;
	}

	@Override
	public String getDriver()
	{
		return driver;
	}

	@Override
	public String getDatabase()
	{
		return database;
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public String getPassword()
	{
		return password;
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
