package com.csci5308.stocki5.user.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.database.DbConnection;
import com.csci5308.stocki5.database.IDbConnection;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.factory.UserAbstractFactory;

@Repository
public class UserDb implements IUserDb
{
	private final String USER_ATTRIBUTES = "userCode,password,firstName,lastName,emailId,contactNo,gender,country,address,province,dateOfBirth,funds,internationalStockExchange,internationalDerivativeExchange,internationalCommodityExchange,foreignExchange";

	private static IUserDb uniqueInstance = null;

	IDbConnection dbConnection = DbConnection.instance();
	UserAbstractFactory userFactory = UserAbstractFactory.instance();

	private UserDb()
	{
	}

	public static IUserDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new UserDb();
		}
		return uniqueInstance;
	}

	@Override
	public boolean insertUser(IUser iUser)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			String insertUserSql = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertUserSql);
			statement.setString(1, iUser.getUserCode());
			statement.setString(2, iUser.getFirstName());
			statement.setString(3, iUser.getLastName());
			statement.setString(4, iUser.getEmailId());
			statement.setString(5, iUser.getContactNo());
			statement.setString(6, iUser.getPassword());
			statement.setString(7, iUser.getConfirmPassword());
			statement.setDate(8, new Date(iUser.getDateOfBirth().getTime()));
			statement.setString(9, iUser.getGender());
			statement.setString(10, iUser.getAddress());
			statement.setString(11, iUser.getProvince());
			statement.setString(12, iUser.getCountry());
			statement.setInt(13, iUser.getInternationalStockExchange());
			statement.setInt(14, iUser.getInternationalDerivativeExchange());
			statement.setInt(15, iUser.getInternationalDerivativeExchange());
			statement.setInt(16, iUser.getForeignExchange());
			statement.setDouble(17, iUser.getFunds());
			statement.setString(18, iUser.getRole());
			int userCount = statement.executeUpdate();
			return userCount > 0;
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
	public boolean updateUser(IUser iUser)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			String updateUserSQL = "UPDATE user SET " + "firstName=?," + "lastName=?," + "emailId=?," + "contactNo=?," + "dateOfBirth=?," + "gender=?," + "address=?," + "province=?," + "country=?," + "internationalStockExchange=?," + "internationalDerivativeExchange=?," + "internationalCommodityExchange=?," + "foreignExchange=? " + "WHERE userCode=?";
			PreparedStatement statement = connection.prepareStatement(updateUserSQL);
			statement.setString(1, iUser.getFirstName());
			statement.setString(2, iUser.getLastName());
			statement.setString(3, iUser.getEmailId());
			statement.setString(4, iUser.getContactNo());
			statement.setDate(5, new Date(iUser.getDateOfBirth().getTime()));
			statement.setString(6, iUser.getGender());
			statement.setString(7, iUser.getAddress());
			statement.setString(8, iUser.getProvince());
			statement.setString(9, iUser.getCountry());
			statement.setInt(10, iUser.getInternationalStockExchange());
			statement.setInt(11, iUser.getInternationalDerivativeExchange());
			statement.setInt(12, iUser.getInternationalCommodityExchange());
			statement.setInt(13, iUser.getForeignExchange());
			statement.setString(14, iUser.getUserCode());
			int result = statement.executeUpdate();
			return result > 0;
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
	public IUser getUser(String userCode)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			IUser iUser = userFactory.createUser();
			Statement statement = connection.createStatement();
			String selectUserSql = "SELECT " + USER_ATTRIBUTES + " FROM user WHERE userCode='" + userCode + "'";
			ResultSet resultSet = statement.executeQuery(selectUserSql);
			while (resultSet.next())
			{
				convertResultSet(iUser, resultSet);
			}
			return iUser;
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
	public IUser getUserByEmail(String email)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			IUser iUser = userFactory.createUser();
			Statement statement = connection.createStatement();
			String selectSql = "SELECT " + USER_ATTRIBUTES + " FROM user where emailid='" + email + "'";
			ResultSet resultSet = statement.executeQuery(selectSql);
			while (resultSet.next())
			{
				convertResultSet(iUser, resultSet);
			}
			return iUser;
		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
			return null;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean updateUserPassword(IUser iUser)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			String updateUserSQL = "UPDATE user SET password=?, confirmPassword=? WHERE userCode=?";
			PreparedStatement statement = connection.prepareStatement(updateUserSQL);
			statement.setString(1, iUser.getPassword());
			statement.setString(2, iUser.getConfirmPassword());
			statement.setString(3, iUser.getUserCode());
			int result = statement.executeUpdate();
			return result > 0;
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
	public double getUserFunds(String userCode)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			double userFunds = 0;
			Statement statement = connection.createStatement();
			String selectUserFundsSql = "SELECT funds FROM user WHERE userCode='" + userCode + "'";
			ResultSet resultSet = statement.executeQuery(selectUserFundsSql);
			while (resultSet.next())
			{
				userFunds = resultSet.getDouble("funds");
			}
			return userFunds;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean updateUserFunds(String userCode, double amount)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			String updateUserFundsSQL = "UPDATE user SET " + "funds=? " + "WHERE userCode=?";
			PreparedStatement statement = connection.prepareStatement(updateUserFundsSQL);
			statement.setDouble(1, amount);
			statement.setString(2, userCode);
			int result = statement.executeUpdate();
			return result > 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	private void convertResultSet(IUser iUser, ResultSet resultSet) throws SQLException
	{
		iUser.setUserCode(resultSet.getString("userCode"));
		iUser.setPassword(resultSet.getString("password"));
		iUser.setFirstName(resultSet.getString("firstName"));
		iUser.setLastName(resultSet.getString("lastName"));
		iUser.setEmailId(resultSet.getString("emailId"));
		iUser.setContactNo(resultSet.getString("contactNo"));
		iUser.setGender(resultSet.getString("gender"));
		iUser.setCountry(resultSet.getString("country"));
		iUser.setAddress(resultSet.getString("address"));
		iUser.setProvince(resultSet.getString("province"));
		iUser.setDateOfBirth(resultSet.getDate("dateOfBirth"));
		iUser.setFunds(resultSet.getDouble("funds"));
		iUser.setInternationalStockExchange(resultSet.getInt("internationalStockExchange"));
		iUser.setInternationalDerivativeExchange(resultSet.getInt("internationalDerivativeExchange"));
		iUser.setInternationalCommodityExchange(resultSet.getInt("internationalCommodityExchange"));
		iUser.setForeignExchange(resultSet.getInt("foreignExchange"));
	}
}
