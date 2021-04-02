package com.csci5308.stocki5.trade.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.config.Stocki5DbConnection;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.holding.Holding;

@Repository
public class TradeDb implements ITradeDb
{
	final String TRADE_ATTRIBUTES = "tradeNumber,userCode,stockId,buySell,symbol,segment,quantity,buyPrice,sellPrice,totalBuyPrice,totalSellPrice,status,tradeDate";
	final String HOLDING_ATTRIBUTES = "tradeNumber,userCode,stockId,buySell,symbol,segment,quantity,buyPrice,price,totalBuyPrice,totalSellPrice,status,isHolding,tradeDate";

	Stocki5DbConnection dbConnection = new Stocki5DbConnection();

	@Override
	public boolean insertTrade(Trade trade, boolean isHolding)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			String insertTradeSql = "INSERT INTO trade VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertTradeSql);

			statement.setString(1, trade.getTradeNumber());
			statement.setString(2, trade.getUserCode());
			statement.setInt(3, trade.getStockId());
			statement.setString(4, trade.getSymbol());
			statement.setString(5, trade.getSegment());
			statement.setString(6, String.valueOf(trade.getBuySell()));
			statement.setInt(7, trade.getQuantity());
			statement.setFloat(8, trade.getBuyPrice());
			statement.setFloat(9, trade.getSellPrice());
			statement.setDouble(10, trade.getTotalBuyPrice());
			statement.setDouble(11, trade.getTotalSellPrice());
			statement.setString(12, String.valueOf(trade.getStatus()));
			statement.setBoolean(13, isHolding);
			statement.setDate(14, new Date(System.currentTimeMillis()));
			int tradeCount = statement.executeUpdate();
			if (tradeCount > 0)
			{
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
	public List<Trade> getTodaysTradeByUserCode(String userCode)
	{
		List<Trade> trades = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			String selectTradeSql = "SELECT "+TRADE_ATTRIBUTES+" FROM trade WHERE userCode=? AND tradeDate=? ORDER BY tradeDate DESC";
			PreparedStatement statement = connection.prepareStatement(selectTradeSql);

			statement.setString(1, userCode);
			statement.setDate(2, new Date(System.currentTimeMillis()));
			ResultSet resultSet = statement.executeQuery();

			Trade trade = null;
			while (resultSet.next())
			{
				trade = new Trade();
				trade.setTradeNumber(resultSet.getString("tradeNumber"));
				trade.setUserCode(resultSet.getString("userCode"));
				trade.setStockId(resultSet.getInt("stockId"));
				trade.setBuySell(TradeType.valueOf(resultSet.getString("buySell")));
				trade.setSymbol(resultSet.getString("symbol"));
				trade.setSegment(resultSet.getString("segment"));
				trade.setQuantity(resultSet.getInt("quantity"));
				trade.setBuyPrice(resultSet.getFloat("buyPrice"));
				trade.setSellPrice(resultSet.getFloat("sellPrice"));
				trade.setTotalBuyPrice(resultSet.getDouble("totalBuyPrice"));
				trade.setTotalSellPrice(resultSet.getDouble("totalSellPrice"));
				trade.setStatus(TradeStatus.valueOf(resultSet.getString("status")));
				trade.setTradeDate(resultSet.getDate("tradeDate"));
				trades.add(trade);
			}
			return trades;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return trades;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public List<Holding> getHoldingsByUserCode(String userCode)
	{
		List<Holding> holdings = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			String selectTradeSql = "SELECT "+HOLDING_ATTRIBUTES+" FROM trade INNER JOIN stock_data ON trade.stockId = stock_data.stock_id WHERE userCode=? AND tradeDate=? AND isHolding=1 ORDER BY tradeDate DESC";
			PreparedStatement statement = connection.prepareStatement(selectTradeSql);

			statement.setString(1, userCode);
			statement.setDate(2, new Date(System.currentTimeMillis()));
			ResultSet resultSet = statement.executeQuery();

			Holding holding = null;
			while (resultSet.next())
			{
				holding = new Holding();
				holding.setTradeNumber(resultSet.getString("tradeNumber"));
				holding.setUserCode(resultSet.getString("userCode"));
				holding.setStockId(resultSet.getInt("stockId"));
				holding.setBuySell(TradeType.valueOf(resultSet.getString("buySell")));
				holding.setSymbol(resultSet.getString("symbol"));
				holding.setSegment(resultSet.getString("segment"));
				holding.setQuantity(resultSet.getInt("quantity"));
				holding.setBuyPrice(resultSet.getFloat("buyPrice"));
				holding.setSellPrice(resultSet.getFloat("price"));
				holding.setTotalBuyPrice(resultSet.getDouble("totalBuyPrice"));
				holding.setTotalSellPrice(resultSet.getDouble("totalSellPrice"));
				holding.setStatus(TradeStatus.valueOf(resultSet.getString("status")));
				holding.setIsHolding(resultSet.getBoolean("isHolding"));
				holding.setTradeDate(resultSet.getDate("tradeDate"));
				holdings.add(holding);
			}
			return holdings;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return holdings;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean removeHolding(String tradeNumber)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			String removeHoldingSql = "UPDATE trade SET isHolding = ? WHERE tradeNumber = ?";
			PreparedStatement statement = connection.prepareStatement(removeHoldingSql);

			statement.setBoolean(1, false);
			statement.setString(2, tradeNumber);

			int tradeCount = statement.executeUpdate();
			if (tradeCount > 0)
			{
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
	public boolean removeHoldingForAutoSell(String userCode, int stockId, int quantity)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			String removeHoldingSql = "UPDATE trade SET isHolding = ? "
					+ "WHERE tradeNumber = (SELECT tradeNumber FROM trade "
					+ "WHERE userCode = ? AND stockId = ? AND quantity = ? AND isHolding = ?)";
			PreparedStatement statement = connection.prepareStatement(removeHoldingSql);

			statement.setBoolean(1, false);
			statement.setString(2, userCode);
			statement.setInt(3, stockId);
			statement.setInt(4, quantity);

			int tradeCount = statement.executeUpdate();
			if (tradeCount > 0)
			{
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
	public List<Trade> getPendingTrades(TradeType tradeType)
	{
		List<Trade> trades = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			String selectTradeSql = "SELECT "+TRADE_ATTRIBUTES+" FROM trade WHERE status='PENDING' AND buySell=?";
			PreparedStatement statement = connection.prepareStatement(selectTradeSql);

			statement.setString(1, String.valueOf(tradeType));
			ResultSet resultSet = statement.executeQuery();

			Trade trade = null;
			while (resultSet.next())
			{
				trade = new Trade();
				trade.setTradeNumber(resultSet.getString("tradeNumber"));
				trade.setUserCode(resultSet.getString("userCode"));
				trade.setStockId(resultSet.getInt("stockId"));
				trade.setBuySell(TradeType.valueOf(resultSet.getString("buySell")));
				trade.setSymbol(resultSet.getString("symbol"));
				trade.setSegment(resultSet.getString("segment"));
				trade.setQuantity(resultSet.getInt("quantity"));
				trade.setBuyPrice(resultSet.getFloat("buyPrice"));
				trade.setSellPrice(resultSet.getFloat("sellPrice"));
				trade.setTotalBuyPrice(resultSet.getDouble("totalBuyPrice"));
				trade.setTotalSellPrice(resultSet.getDouble("totalSellPrice"));
				trade.setStatus(TradeStatus.valueOf(resultSet.getString("status")));
				trade.setTradeDate(resultSet.getDate("tradeDate"));
				trades.add(trade);
			}
			return trades;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return trades;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean updateBuyTrade(Trade trade, boolean isHolding)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			String updateUserSQL = "UPDATE trade SET buyPrice=?, totalBuyPrice=?, isHolding=?, status=? WHERE tradeNumber=?";
			PreparedStatement statement = connection.prepareStatement(updateUserSQL);

			statement.setFloat(1, trade.getBuyPrice());
			statement.setDouble(2, trade.getTotalBuyPrice());
			statement.setBoolean(3, isHolding);
			statement.setString(4, String.valueOf(trade.getStatus()));
			statement.setString(5, trade.getTradeNumber());
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
	public boolean updateSellTrade(Trade trade, boolean isHolding)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			String updateSellTradeSQL = "UPDATE trade SET sellPrice=?, totalSellPrice=?, isHolding=?, status=? WHERE tradeNumber=?";
			PreparedStatement statement = connection.prepareStatement(updateSellTradeSQL);

			statement.setFloat(1, trade.getSellPrice());
			statement.setDouble(2, trade.getTotalSellPrice());
			statement.setBoolean(3, isHolding);
			statement.setString(4, String.valueOf(trade.getStatus()));
			statement.setString(5, trade.getTradeNumber());
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
	public boolean updateBulkTradeStatus(List<Trade> trades)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			for (Trade trade : trades)
			{
				statement.addBatch("UPDATE trade SET status = '" + trade.getStatus() + "' WHERE tradeNumber = '"
						+ trade.getTradeNumber() + "'");
			}
			int[] result = statement.executeBatch();
			return result.length > 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

}
