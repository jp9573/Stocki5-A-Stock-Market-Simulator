package com.csci5308.stocki5.user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.database.DbConnection;
import com.csci5308.stocki5.database.IDbConnection;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;
import com.csci5308.stocki5.user.password.IUserOtp;

@Repository
public class UserOtpDb implements IUserOtpDb
{
	final String USER_OTP_ATTRIBUTES = "userCode,otp,validity";

	private static IUserOtpDb uniqueInstance = null;

	IDbConnection dbConnection = DbConnection.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();

	private UserOtpDb()
	{
	}

	public static IUserOtpDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserOtpDb();
		}
		return uniqueInstance;
	}

	private boolean executeDelete(String deleteSQL)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(deleteSQL);
			int result = statement.executeUpdate();
			if (result > 0)
			{
				return true;
			} else
			{
				return false;
			}
		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
			return false;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean insertOtp(IUserOtp iUserOtp)
	{
		Connection connection = dbConnection.createConnection();
		String insertUserOtpSql = "INSERT INTO user_otp VALUES (?,?,?)";

		try
		{
			PreparedStatement statement = connection.prepareStatement(insertUserOtpSql);
			statement.setString(1, iUserOtp.getUserCode());
			statement.setInt(2, iUserOtp.getOtp());
			statement.setTimestamp(3, Timestamp.valueOf(iUserOtp.getValidity()));
			int otpCount = statement.executeUpdate();
			if (otpCount > 0)
			{
				return true;
			} else
			{
				return false;
			}
		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
			return false;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public IUserOtp getOtp(int otp)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			IUserOtp iUserOtp = userFactory.createUserOtp();
			String selectUserSql = "SELECT " + USER_OTP_ATTRIBUTES + " FROM user_otp WHERE otp='" + String.valueOf(otp) + "'";
			PreparedStatement statement = connection.prepareStatement(selectUserSql);
			ResultSet resultSet = statement.executeQuery(selectUserSql);
			while (resultSet.next())
			{
				iUserOtp.setUserCode(resultSet.getString("userCode"));
				iUserOtp.setOtp(resultSet.getInt("otp"));
				iUserOtp.setValidity(resultSet.getTimestamp("validity").toString());
			}
			return iUserOtp;
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
	public boolean deleteOtp(int otp)
	{
		String deleteUserOtpSQL = "DELETE FROM user_otp WHERE otp=" + String.valueOf(otp);
		return executeDelete(deleteUserOtpSQL);
	}

	@Override
	public boolean deleteOtpByUserCode(String userCode)
	{
		String deleteUserOtpSQL = "DELETE FROM user_otp WHERE userCode='" + userCode + "'";
		return executeDelete(deleteUserOtpSQL);
	}
}
