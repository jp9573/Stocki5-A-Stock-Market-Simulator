package com.csci5308.stocki5.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.csci5308.stocki5.config.Stocki5DbConnection;

@Repository
public class UserDb implements IUserDb {
    @Autowired
    Stocki5DbConnection dbConnection;

    @Override
    public boolean insertUser(User user) {
        Connection connection = dbConnection.createConnection();
        try {
            String insertUserSql = String.format("INSERT INTO %s VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", userTable);
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
            statement.setString(18, user.getRole());
            int userCount = statement.executeUpdate();
            return userCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = dbConnection.createConnection();
        try {
            String updateUserSQL = String.format("UPDATE %s SET %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? WHERE %s=?",
                    userTable, firstNameColumn, lastNameColumn, emailIdColumn, contactNoColumn, dateOfBirthColumn, genderColumn, addressColumn, provinceColumn, countryColumn,
                    internationalStockExchangeColumn, internationalDerivativeExchangeColumn,
                    internationalCommodityExchangeColumn, foreignExchangeColumn, userCodeColumn);
            PreparedStatement statement = connection.prepareStatement(updateUserSQL);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmailId());
            statement.setString(4, user.getContactNo());
            statement.setDate(5, new Date(user.getDateOfBirth().getTime()));
            statement.setString(6, user.getGender());
            statement.setString(7, user.getAddress());
            statement.setString(8, user.getProvince());
            statement.setString(9, user.getCountry());
            statement.setInt(10, user.getInternationalStockExchange());
            statement.setInt(11, user.getInternationalDerivativeExchange());
            statement.setInt(12, user.getInternationalCommodityExchange());
            statement.setInt(13, user.getForeignExchange());
            statement.setString(14, user.getUserCode());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    @Override
    public User getUser(String userCode) {
        Connection connection = dbConnection.createConnection();
        try {
            User user = new User();
            Statement statement = connection.createStatement();
            String selectUserSql = String.format("SELECT * FROM %s WHERE %s='%s'", userTable, userCodeColumn, userCode);
            ResultSet resultSet = statement.executeQuery(selectUserSql);
            while (resultSet.next()) {
                setUserValueFromResultSet(user, resultSet);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Connection connection = dbConnection.createConnection();
        try {
            User user = new User();
            Statement statement = connection.createStatement();
            String selectSql = String.format("SELECT * FROM %s where %s='%s'", userTable, emailIdColumn, email);
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                setUserValueFromResultSet(user, resultSet);
            }
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    @Override
    public boolean updateUserPassword(User user) {
        Connection connection = dbConnection.createConnection();
        try {
            String updateUserSQL = String.format("UPDATE %s SET %s=?, %s=? WHERE %s=?", userTable, passwordColumn, confirmPasswordColumn, userCodeColumn);
            PreparedStatement statement = connection.prepareStatement(updateUserSQL);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getConfirmPassword());
            statement.setString(3, user.getUserCode());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    @Override
    public double getUserFunds(String userCode) {
        Connection connection = dbConnection.createConnection();
        try {
            double userFunds = 0;
            Statement statement = connection.createStatement();
            String selectUserFundsSql = String.format("SELECT %s FROM %s WHERE %s='%s'", fundsColumn, userTable, userCodeColumn, userCode);
            ResultSet resultSet = statement.executeQuery(selectUserFundsSql);
            while (resultSet.next()) {
                userFunds = resultSet.getDouble(fundsColumn);
            }
            return userFunds;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    @Override
    public boolean updateUserFunds(String userCode, double amount) {
        Connection connection = dbConnection.createConnection();
        try {
            String updateUserFundsSQL = String.format("UPDATE %s SET %s=? WHERE %s=?", userTable, fundsColumn, userCodeColumn);
            PreparedStatement statement = connection.prepareStatement(updateUserFundsSQL);
            statement.setDouble(1, amount);
            statement.setString(2, userCode);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    private void setUserValueFromResultSet(User user, ResultSet resultSet) throws SQLException {
        user.setUserCode(resultSet.getString(userCodeColumn));
        user.setPassword(resultSet.getString(passwordColumn));
        user.setFirstName(resultSet.getString(firstNameColumn));
        user.setLastName(resultSet.getString(lastNameColumn));
        user.setEmailId(resultSet.getString(emailIdColumn));
        user.setContactNo(resultSet.getString(contactNoColumn));
        user.setGender(resultSet.getString(genderColumn));
        user.setCountry(resultSet.getString(countryColumn));
        user.setAddress(resultSet.getString(addressColumn));
        user.setProvince(resultSet.getString(provinceColumn));
        user.setDateOfBirth(resultSet.getDate(dateOfBirthColumn));
        user.setInternationalStockExchange(resultSet.getInt(internationalStockExchangeColumn));
        user.setInternationalDerivativeExchange(resultSet.getInt(internationalDerivativeExchangeColumn));
        user.setInternationalCommodityExchange(resultSet.getInt(internationalCommodityExchangeColumn));
        user.setForeignExchange(resultSet.getInt(foreignExchangeColumn));
    }
}
