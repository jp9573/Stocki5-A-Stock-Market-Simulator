package com.csci5308.stocki5.stock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.config.Stocki5DbConnection;

@Repository
public class StockDb implements StockDbInterface {
	@Autowired
	Stocki5DbConnection dbConnection;

	@Override
	public Stock getStockData(int stockId) {
		Connection connection = dbConnection.createConnection();
		try {
			Stock stock = new Stock();
			Statement statement = connection.createStatement();
			String selectStockSql = "SELECT * FROM stock_data WHERE stock_id=" + stockId;
			ResultSet resultSet = statement.executeQuery(selectStockSql);
			while (resultSet.next()) {
				stock.setStockId(resultSet.getInt("stock_id"));
				stock.setSymbol(resultSet.getString("symbol"));
				stock.setOpen(resultSet.getFloat("open"));
				stock.setHigh(resultSet.getFloat("high"));
				stock.setLow(resultSet.getFloat("low"));
				stock.setPrice(resultSet.getFloat("price"));
				stock.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				stock.setPreviousClose(resultSet.getFloat("previous_close"));
				stock.setSegment(resultSet.getString("segment"));
				stock.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
			}

			return stock;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public List<Stock> getStocksBySegment(String segments) {
		List<Stock> stocks = new ArrayList<Stock>();
		Connection connection = dbConnection.createConnection();
		try {
			Statement statement = connection.createStatement();
			String selectStockSql = "SELECT * FROM stock_data WHERE segment IN " + "(" + segments + ")";
			ResultSet resultSet = statement.executeQuery(selectStockSql);
			Stock stock = null;
			while (resultSet.next()) {
				stock = new Stock();
				stock.setStockId(resultSet.getInt("stock_id"));
				stock.setSymbol(resultSet.getString("symbol"));
				stock.setOpen(resultSet.getFloat("open"));
				stock.setHigh(resultSet.getFloat("high"));
				stock.setLow(resultSet.getFloat("low"));
				stock.setPrice(resultSet.getFloat("price"));
				stock.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				stock.setPreviousClose(resultSet.getFloat("previous_close"));
				stock.setSegment(resultSet.getString("segment"));
				stock.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
				stocks.add(stock);
			}
			return stocks;
		} catch (SQLException e) {
			e.printStackTrace();
			return stocks;
		} finally {
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public List<Stock> getHighestPriceStocks(String segments, int limit) {
		List<Stock> stocks = new ArrayList<Stock>();
		Connection connection = dbConnection.createConnection();
		try {
			Statement statement = connection.createStatement();
			String selectStockSql = "SELECT * FROM stock_data WHERE segment IN " + "(" + segments + ") ORDER BY percent DESC LIMIT " + limit;
			ResultSet resultSet = statement.executeQuery(selectStockSql);
			Stock stock = null;
			while (resultSet.next()) {
				stock = new Stock();
				stock.setStockId(resultSet.getInt("stock_id"));
				stock.setSymbol(resultSet.getString("symbol"));
				stock.setOpen(resultSet.getFloat("open"));
				stock.setHigh(resultSet.getFloat("high"));
				stock.setLow(resultSet.getFloat("low"));
				stock.setPrice(resultSet.getFloat("price"));
				stock.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				stock.setPreviousClose(resultSet.getFloat("previous_close"));
				stock.setSegment(resultSet.getString("segment"));
				stock.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
				stocks.add(stock);
			}
			return stocks;
		} catch (SQLException e) {
			e.printStackTrace();
			return stocks;
		} finally {
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public List<Stock> getLowestPriceStocks(String segments, int limit) {
		List<Stock> stocks = new ArrayList<Stock>();
		Connection connection = dbConnection.createConnection();
		try {
			Statement statement = connection.createStatement();
			String selectStockSql = "SELECT * FROM stock_data WHERE segment IN " + "(" + segments + ") ORDER BY percent LIMIT " + limit;
			ResultSet resultSet = statement.executeQuery(selectStockSql);
			Stock stock = null;
			while (resultSet.next()) {
				stock = new Stock();
				stock.setStockId(resultSet.getInt("stock_id"));
				stock.setSymbol(resultSet.getString("symbol"));
				stock.setOpen(resultSet.getFloat("open"));
				stock.setHigh(resultSet.getFloat("high"));
				stock.setLow(resultSet.getFloat("low"));
				stock.setPrice(resultSet.getFloat("price"));
				stock.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				stock.setPreviousClose(resultSet.getFloat("previous_close"));
				stock.setSegment(resultSet.getString("segment"));
				stock.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
				stocks.add(stock);
			}
			return stocks;
		} catch (SQLException e) {
			e.printStackTrace();
			return stocks;
		} finally {
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean updateStockData(Stock stock) {
		Connection connection = dbConnection.createConnection();
		try {
			String updateStockSQL = "UPDATE stock_data SET " + "symbol=?," + "open=?," + "high=?," + "low=?,"
					+ "price=?," + "latest_trading_date=?," + "previous_close=?," + "segment=?," + "percent=?"
					+ " WHERE stock_id = ?";

			PreparedStatement statement = connection.prepareStatement(updateStockSQL);
			statement.setString(1, stock.getSymbol());
			statement.setFloat(2, stock.getOpen());
			statement.setFloat(3, stock.getHigh());
			statement.setFloat(4, stock.getLow());
			statement.setFloat(5, stock.getPrice());
			statement.setDate(6, new Date(stock.getLatestTradingDate().getTime()));
			statement.setFloat(7, stock.getPreviousClose());
			statement.setString(8, stock.getSegment());
			statement.setFloat(9, stock.getPercentIncreaseDecrease());
			statement.setInt(10, stock.getStockId());
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
	public List<Stock> getStocks() {
		List<Stock> stocks = new ArrayList<Stock>();
		Connection connection = dbConnection.createConnection();
		try {
			Statement statement = connection.createStatement();
			String selectStockSql = "SELECT * FROM stock_data";
			ResultSet resultSet = statement.executeQuery(selectStockSql);
			Stock stock = null;
			while (resultSet.next()) {
				stock = new Stock();
				stock.setStockId(resultSet.getInt("stock_id"));
				stock.setSymbol(resultSet.getString("symbol"));
				stock.setOpen(resultSet.getFloat("open"));
				stock.setHigh(resultSet.getFloat("high"));
				stock.setLow(resultSet.getFloat("low"));
				stock.setPrice(resultSet.getFloat("price"));
				stock.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
				stock.setPreviousClose(resultSet.getFloat("previous_close"));
				stock.setSegment(resultSet.getString("segment"));
				stock.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
				stocks.add(stock);
			}
			return stocks;
		} catch (SQLException e) {
			e.printStackTrace();
			return stocks;
		} finally {
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean updateStockBulk(List<Stock> stocks) {
		Connection connection = dbConnection.createConnection();
		try {
			Statement statement = connection.createStatement();
			for (Stock stock : stocks) {
				statement.addBatch("UPDATE stock_data SET " + "symbol='" + stock.getSymbol() + "'," + "open="
						+ stock.getOpen() + "," + "high=" + stock.getHigh() + "," + "low=" + stock.getLow() + ","
						+ "price=" + stock.getPrice() + "," + "previous_close=" + stock.getPreviousClose() + ","
						+ "segment='" + stock.getSegment() + "'," + "percent=" + stock.getPercentIncreaseDecrease()
						+ " WHERE stock_id = " + stock.getStockId());
				System.out.println("UPDATE stock_data SET " + "symbol='" + stock.getSymbol() + "'," + "open="
						+ stock.getOpen() + "," + "high=" + stock.getHigh() + "," + "low=" + stock.getLow() + ","
						+ "price=" + stock.getPrice() + "," + "previous_close=" + stock.getPreviousClose() + ","
						+ "segment='" + stock.getSegment() + "'," + "percent=" + stock.getPercentIncreaseDecrease()
						+ " WHERE stock_id = " + stock.getStockId());
			}
			int[] result = statement.executeBatch();
			return result.length > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbConnection.closeConnection(connection);
		}
	}

}
