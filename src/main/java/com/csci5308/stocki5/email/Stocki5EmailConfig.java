package com.csci5308.stocki5.email;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Stocki5EmailConfig implements IStocki5EmailConfig
{
	private static final String PROPERTIES_FILE = "config.properties";

	private static IStocki5EmailConfig uniqueInstance = null;

	private String host;
	private String username;
	private String password;
	private int port;

	private Stocki5EmailConfig()
	{
		readProperties();
	}

	public static IStocki5EmailConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new Stocki5EmailConfig();
		}
		return uniqueInstance;
	}

	@Override
	public String getHost()
	{
		return host;
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

	@Override
	public int getPort()
	{
		return port;
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
			this.host = prop.getProperty("smtp.host");
			this.username = prop.getProperty("smtp.username");
			this.password = prop.getProperty("smtp.password");
			this.port = Integer.parseInt(prop.getProperty("smtp.port"));
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
