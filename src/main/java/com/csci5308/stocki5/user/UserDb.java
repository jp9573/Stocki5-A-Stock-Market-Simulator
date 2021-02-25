package com.csci5308.stocki5.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.config.Stocki5DbConnection;

@Repository
public class UserDb implements UserDbInterface
{
	@Autowired
	Stocki5DbConnection dbConnection;

	@Override
	public boolean insertUser(User user)
	{
		user.setFunds(50000.0);
		user.setInternationalCommodityExchange(1);
		user.setInternationalDerivativeExchange(1);
		user.setInternationalStockExchange(1);
		user.setForeignExchange(1);

		Connection connection = dbConnection.createConnection();

		try
		{
			String insertUserSql = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertUserSql);

			statement.setString(1, user.getUserCode());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmailId());
			statement.setString(5, user.getContactNo());
			statement.setString(6, user.getPassword());
			statement.setString(7, user.getConfirmPassword());
			statement.setDate(8, new Date(user.getDateOfBirth().getTime()));
			statement.setString(9, user.getGender());
			statement.setString(10, user.getAddress());
			statement.setString(11, user.getProvince());
			statement.setString(12, user.getCountry());
			statement.setInt(13, user.getInternationalStockExchange());
			statement.setInt(14, user.getInternationalDerivativeExchange());
			statement.setInt(15, user.getInternationalDerivativeExchange());
			statement.setInt(16, user.getForeignExchange());
			statement.setDouble(17, user.getFunds());
			int userCount = statement.executeUpdate();
			if (userCount > 0)
			{
				System.out.println(userCount);
				return true;
			} else
			{
				return false;
			}
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
	public boolean updateUser(User user)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String userCode)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
