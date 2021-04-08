package com.csci5308.stocki5.user.funds;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

@Service
public class UserFunds implements IUserFunds
{
	private static final String PROPERTIES_FILE = "config.properties";

	private int resetFundAmount;

	public UserFunds()
	{
		readProperties();
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
			this.resetFundAmount = Integer.parseInt(prop.getProperty("user.resetfundamount"));
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

	@Override
	public void setResetFundAmount(int resetFundAmount)
	{
		this.resetFundAmount = resetFundAmount;
	}

	@Override
	public boolean resetFunds(IUser iUser, IUserDb iUserDb)
	{
		if (iUser.getFunds() < resetFundAmount)
		{
			iUser.setFunds(resetFundAmount);
			return iUserDb.updateUserFunds(iUser.getUserCode(), resetFundAmount);
		} else
		{
			return false;
		}
	}
}
