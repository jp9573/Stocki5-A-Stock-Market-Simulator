package com.csci5308.stocki5.trade.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.stocki5.database.DbConnection;
import com.csci5308.stocki5.database.IDbConnection;
import com.csci5308.stocki5.trade.ITrade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.trade.factory.TradeAbstractFactory;
import com.csci5308.stocki5.trade.holding.IHolding;

@Repository
public class TradeDb implements ITradeDb
{
	final String TRADE_ATTRIBUTES = "tradeNumber,userCode,stockId,buySell,symbol,segment,quantity,buyPrice,sellPrice,totalBuyPrice,totalSellPrice,status,tradeDate";
	final String HOLDING_ATTRIBUTES = "tradeNumber,userCode,stockId,buySell,trade.symbol,trade.segment,quantity,buyPrice,price,totalBuyPrice,totalSellPrice,status,isHolding,tradeDate";

	private static ITradeDb uniqueInstance = null;

	TradeAbstractFactory tradeFactory = TradeAbstractFactory.instance();
	IDbConnection dbConnection = DbConnection.instance();

	private TradeDb()
	{
	}

	public static ITradeDb instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new TradeDb();
		}
		return uniqueInstance;
	}

	@Override
	public boolean insertTrade(ITrade iTrade, boolean isHolding)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			String insertTradeSql = "INSERT INTO trade VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertTradeSql);

			statement.setString(1, iTrade.getTradeNumber());
			statement.setString(2, iTrade.getUserCode());
			statement.setInt(3, iTrade.getStockId());
			statement.setString(4, iTrade.getSymbol());
			statement.setString(5, iTrade.getSegment());
			statement.setString(6, String.valueOf(iTrade.getBuySell()));
			statement.setInt(7, iTrade.getQuantity());
			statement.setFloat(8, iTrade.getBuyPrice());
			statement.setFloat(9, iTrade.getSellPrice());
			statement.setDouble(10, iTrade.getTotalBuyPrice());
			statement.setDouble(11, iTrade.getTotalSellPrice());
			statement.setString(12, String.valueOf(iTrade.getStatus()));
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
	public List<ITrade> getTodaysTradeByUserCode(String userCode)
	{
		List<ITrade> iTrades = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			String selectTradeSql = "SELECT " + TRADE_ATTRIBUTES + " FROM trade WHERE userCode=? AND tradeDate=? ORDER BY tradeDate DESC";
			PreparedStatement statement = connection.prepareStatement(selectTradeSql);

			statement.setString(1, userCode);
			statement.setDate(2, new Date(System.currentTimeMillis()));
			ResultSet resultSet = statement.executeQuery();

			ITrade iTrade = null;
			while (resultSet.next())
			{
				iTrade = convertResultSet(resultSet);
				iTrades.add(iTrade);
			}
			return iTrades;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return iTrades;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public List<IHolding> getHoldingsByUserCode(String userCode)
	{
		List<IHolding> iHoldings = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			String selectTradeSql = "SELECT " + HOLDING_ATTRIBUTES + " FROM trade INNER JOIN stock_data ON trade.stockId = stock_data.stock_id WHERE userCode=? AND tradeDate=? AND isHolding=1 ORDER BY tradeDate DESC";
			PreparedStatement statement = connection.prepareStatement(selectTradeSql);

			statement.setString(1, userCode);
			statement.setDate(2, new Date(System.currentTimeMillis()));
			ResultSet resultSet = statement.executeQuery();

			IHolding iHolding = null;
			while (resultSet.next())
			{
				iHolding = tradeFactory.createHolding();
				iHolding.setTradeNumber(resultSet.getString("tradeNumber"));
				iHolding.setUserCode(resultSet.getString("userCode"));
				iHolding.setStockId(resultSet.getInt("stockId"));
				iHolding.setBuySell(TradeType.valueOf(resultSet.getString("buySell")));
				iHolding.setSymbol(resultSet.getString("symbol"));
				iHolding.setSegment(resultSet.getString("segment"));
				iHolding.setQuantity(resultSet.getInt("quantity"));
				iHolding.setBuyPrice(resultSet.getFloat("buyPrice"));
				iHolding.setSellPrice(resultSet.getFloat("price"));
				iHolding.setTotalBuyPrice(resultSet.getDouble("totalBuyPrice"));
				iHolding.setTotalSellPrice(resultSet.getDouble("totalSellPrice"));
				iHolding.setStatus(TradeStatus.valueOf(resultSet.getString("status")));
				iHolding.setIsHolding(resultSet.getBoolean("isHolding"));
				iHolding.setTradeDate(resultSet.getDate("tradeDate"));
				iHoldings.add(iHolding);
			}
			return iHoldings;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return iHoldings;
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
			String removeHoldingSql = "UPDATE trade SET isHolding = ? " + "WHERE tradeNumber = (SELECT tradeNumber FROM trade " + "WHERE userCode = ? AND stockId = ? AND quantity = ? AND isHolding = ?)";
			PreparedStatement statement = connection.prepareStatement(removeHoldingSql);

			statement.setBoolean(1, false);
			statement.setString(2, userCode);
			statement.setInt(3, stockId);
			statement.setInt(4, quantity);
			statement.setBoolean(5, true);

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
	public List<ITrade> getPendingTrades(TradeType tradeType)
	{
		List<ITrade> iTrades = new ArrayList<>();
		Connection connection = dbConnection.createConnection();
		try
		{
			String selectTradeSql = "SELECT " + TRADE_ATTRIBUTES + " FROM trade WHERE status='PENDING' AND buySell=?";
			PreparedStatement statement = connection.prepareStatement(selectTradeSql);

			statement.setString(1, String.valueOf(tradeType));
			ResultSet resultSet = statement.executeQuery();

			ITrade iTrade = null;
			while (resultSet.next())
			{
				iTrade = convertResultSet(resultSet);
				iTrades.add(iTrade);
			}
			return iTrades;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return iTrades;
		} finally
		{
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean updateBuyTrade(ITrade iTrade, boolean isHolding)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			String updateUserSQL = "UPDATE trade SET buyPrice=?, totalBuyPrice=?, isHolding=?, status=? WHERE tradeNumber=?";
			PreparedStatement statement = connection.prepareStatement(updateUserSQL);

			statement.setFloat(1, iTrade.getBuyPrice());
			statement.setDouble(2, iTrade.getTotalBuyPrice());
			statement.setBoolean(3, isHolding);
			statement.setString(4, String.valueOf(iTrade.getStatus()));
			statement.setString(5, iTrade.getTradeNumber());
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
	public boolean updateSellTrade(ITrade iTrade, boolean isHolding)
	{
		Connection connection = dbConnection.createConnection();

		try
		{
			String updateSellTradeSQL = "UPDATE trade SET sellPrice=?, totalSellPrice=?, isHolding=?, status=? WHERE tradeNumber=?";
			PreparedStatement statement = connection.prepareStatement(updateSellTradeSQL);

			statement.setFloat(1, iTrade.getSellPrice());
			statement.setDouble(2, iTrade.getTotalSellPrice());
			statement.setBoolean(3, isHolding);
			statement.setString(4, String.valueOf(iTrade.getStatus()));
			statement.setString(5, iTrade.getTradeNumber());
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
	public boolean updateBulkTradeStatus(List<ITrade> iTrades)
	{
		Connection connection = dbConnection.createConnection();
		try
		{
			Statement statement = connection.createStatement();
			Iterator<ITrade> iTradessIterator = iTrades.iterator();
			ITrade iTrade;
			while (iTradessIterator.hasNext())
			{
				iTrade = iTradessIterator.next();
				statement.addBatch("UPDATE trade SET status = '" + iTrade.getStatus() + "' WHERE tradeNumber = '" + iTrade.getTradeNumber() + "'");
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

	private ITrade convertResultSet(ResultSet resultSet)
	{
		ITrade iTrade = tradeFactory.createTrade();
		try
		{
			iTrade.setTradeNumber(resultSet.getString("tradeNumber"));
			iTrade.setUserCode(resultSet.getString("userCode"));
			iTrade.setStockId(resultSet.getInt("stockId"));
			iTrade.setBuySell(TradeType.valueOf(resultSet.getString("buySell")));
			iTrade.setSymbol(resultSet.getString("symbol"));
			iTrade.setSegment(resultSet.getString("segment"));
			iTrade.setQuantity(resultSet.getInt("quantity"));
			iTrade.setBuyPrice(resultSet.getFloat("buyPrice"));
			iTrade.setSellPrice(resultSet.getFloat("sellPrice"));
			iTrade.setTotalBuyPrice(resultSet.getDouble("totalBuyPrice"));
			iTrade.setTotalSellPrice(resultSet.getDouble("totalSellPrice"));
			iTrade.setStatus(TradeStatus.valueOf(resultSet.getString("status")));
			iTrade.setTradeDate(resultSet.getDate("tradeDate"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return iTrade;
	}

}
