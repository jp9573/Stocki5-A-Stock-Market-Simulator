package com.csci5308.stocki5.stock;

import com.csci5308.stocki5.config.Stocki5DbConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
	public boolean updateStockData(Stock stock) {
		Connection connection = dbConnection.createConnection();
		try {
			String updateStockSQL = "UPDATE stock_data SET " + "symbol=?," + "open=?," + "high=?," + "low=?,"
					+ "price=?," + "latest_trading_date=?," + "previous_close=?," + "segment=?," + "WHERE stock_id=?";

			PreparedStatement statement = connection.prepareStatement(updateStockSQL);
			statement.setString(1, stock.getSymbol());
			statement.setFloat(2, stock.getOpen());
			statement.setFloat(3, stock.getHigh());
			statement.setFloat(4, stock.getLow());
			statement.setFloat(5, stock.getPrice());
			statement.setDate(6, new Date(stock.getLatestTradingDate().getTime()));
			statement.setFloat(7, stock.getPreviousClose());
			statement.setString(8, stock.getSegment());
			statement.setInt(9, stock.getStockId());
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
}
