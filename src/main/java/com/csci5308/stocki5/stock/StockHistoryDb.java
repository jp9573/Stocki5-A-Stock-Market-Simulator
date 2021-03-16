package com.csci5308.stocki5.stock;

import com.csci5308.stocki5.config.Stocki5DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockHistoryDb implements StockHistoryDbInterface{
    @Autowired
    Stocki5DbConnection dbConnection;

    private List<StockHistory> executeGetQuery(String query){
        List<StockHistory> stockHistory = new ArrayList<StockHistory>();
        Connection connection = dbConnection.createConnection();
        try {
            Statement statement = connection.createStatement();
            String selectStockSql = query;
            ResultSet resultSet = statement.executeQuery(selectStockSql);
            StockHistory tempStockHistory = new StockHistory();
            while (resultSet.next()) {
                tempStockHistory.setHistoryId(resultSet.getLong("history_id"));
                tempStockHistory.setStockId(resultSet.getInt("stock_id"));
                tempStockHistory.setSymbol(resultSet.getString("symbol"));
                tempStockHistory.setOpen(resultSet.getFloat("open"));
                tempStockHistory.setHigh(resultSet.getFloat("high"));
                tempStockHistory.setLow(resultSet.getFloat("low"));
                tempStockHistory.setPrice(resultSet.getFloat("price"));
                tempStockHistory.setLatestTradingDate(resultSet.getDate("latest_trading_date"));
                tempStockHistory.setPreviousClose(resultSet.getFloat("previous_close"));
                tempStockHistory.setSegment(resultSet.getString("segment"));
                tempStockHistory.setPercentIncreaseDecrease(resultSet.getFloat("percent"));
                tempStockHistory.setInsertTimestamp(resultSet.getTimestamp("insert_timestamp").toString());
                stockHistory.add(tempStockHistory);
            }
            return stockHistory;
        } catch (SQLException e) {
            e.printStackTrace();
            return stockHistory;
        } finally {
            dbConnection.closeConnection(connection);
        }

    }

    @Override
    public List<StockHistory> getStockHistory(int stockId) {
        String getQuery = "SELECT * FROM stock_data_history WHERE stock_id = "+ String.valueOf(stockId);
        List<StockHistory> stockHistory = executeGetQuery(getQuery);
        return  stockHistory;
    }

    @Override
    public List<StockHistory> getAllStocksHistory(){
        String getQuery = "SELECT * FROM stock_data_history";
        List<StockHistory> stockHistory = executeGetQuery(getQuery);
        return  stockHistory;

    }

    @Override
    public boolean insertStocksHistoryBulk(List<StockHistory> stocksHistory) {
        Connection connection = dbConnection.createConnection();
        try {
            String insertSQL = "INSERT INTO stock_data_history VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertSQL);
            connection.setAutoCommit(false);
            for (StockHistory stockHistory : stocksHistory) {
                statement.setLong(1,stockHistory.getHistoryId());
                statement.setInt(2,stockHistory.getStockId());
                statement.setString(3,stockHistory.getSymbol());
                statement.setFloat(4,stockHistory.getOpen());
                statement.setFloat(5,stockHistory.getHigh());
                statement.setFloat(6,stockHistory.getLow());
                statement.setFloat(7,stockHistory.getPrice());
                statement.setDate(8, (Date) stockHistory.getLatestTradingDate());
                statement.setFloat(9,stockHistory.getPreviousClose());
                statement.setString(10,stockHistory.getSegment());
                statement.setFloat(11,stockHistory.getPercentIncreaseDecrease());
                statement.setTimestamp(12,Timestamp.valueOf(stockHistory.getInsertTimestamp()));
                statement.addBatch();
                statement.clearParameters();
            }
            int[] result = statement.executeBatch();
            connection.commit();
            return result.length > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }

    @Override
    public boolean deleteStockHistoryLesserThanHistoryId(long historyId) {
        Connection connection = dbConnection.createConnection();
        try {
            String deleteSQL = "DELETE FROM stock_data_history WHERE history_id < " + String.valueOf(historyId);
            PreparedStatement statement = connection.prepareStatement(deleteSQL);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbConnection.closeConnection(connection);
        }
    }
}
