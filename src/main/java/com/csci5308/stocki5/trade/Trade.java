package com.csci5308.stocki5.trade;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.user.IUser;
import com.csci5308.stocki5.user.db.IUserDb;

public class Trade implements ITrade
{
	private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");
	private final String DATE_FORMAT = "ddMMyyyyHHmmss";

	private String tradeNumber;
	private String userCode;
	private int stockId;
	private String symbol;
	private String segment;
	private TradeType buySell;
	private int quantity;
	private float buyPrice;
	protected float sellPrice;
	private double totalBuyPrice;
	protected double totalSellPrice;
	private TradeStatus status;
	private Date tradeDate;
	protected IStockDb iStockDb;
	protected IUserDb iUserDb;

	public Trade()
	{

	}

	public Trade(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, IStockDb iStockDb, IUserDb iUserDb)
	{
		this.userCode = userCode;
		this.stockId = stockId;
		this.buySell = buySell;
		this.quantity = quantity;
		this.status = status;
		this.iStockDb = iStockDb;
		this.iUserDb = iUserDb;
	}

	public String getTradeNumber()
	{
		return tradeNumber;
	}

	public void setTradeNumber(String tradeNumber)
	{
		this.tradeNumber = tradeNumber;
	}

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	public int getStockId()
	{
		return stockId;
	}

	public void setStockId(int stockId)
	{
		this.stockId = stockId;
	}

	public String getSymbol()
	{
		return symbol;
	}

	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	public String getSegment()
	{
		return segment;
	}

	public void setSegment(String segment)
	{
		this.segment = segment;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public float getBuyPrice()
	{
		return Float.parseFloat(DECIMAL_FORMAT.format(buyPrice));
	}

	public void setBuyPrice(float buyPrice)
	{
		this.buyPrice = buyPrice;
	}

	public float getSellPrice()
	{
		return Float.parseFloat(DECIMAL_FORMAT.format(sellPrice));
	}

	public void setSellPrice(float sellPrice)
	{
		this.sellPrice = sellPrice;
	}

	public TradeType getBuySell()
	{
		return buySell;
	}

	public void setBuySell(TradeType buySell)
	{
		this.buySell = buySell;
	}

	public double getTotalBuyPrice()
	{
		return Double.parseDouble(DECIMAL_FORMAT.format(totalBuyPrice));
	}

	public void setTotalBuyPrice(double totalBuyPrice)
	{
		this.totalBuyPrice = totalBuyPrice;
	}

	public double getTotalSellPrice()
	{
		return Double.parseDouble(DECIMAL_FORMAT.format(totalSellPrice));
	}

	public void setTotalSellPrice(double totalSellPrice)
	{
		this.totalSellPrice = totalSellPrice;
	}

	public TradeStatus getStatus()
	{
		return status;
	}

	public void setStatus(TradeStatus status)
	{
		this.status = status;
	}

	public void setTradeDate(Date tradeDate)
	{
		this.tradeDate = tradeDate;
	}

	public IStockDb getStockDbInterface()
	{
		return iStockDb;
	}

	public void setStockDbInterface(IStockDb iStockDb)
	{
		this.iStockDb = iStockDb;
	}

	public IUserDb getUserDbInterface()
	{
		return iUserDb;
	}

	public boolean isFundSufficient(IUserDb iUserDb)
	{
		this.iUserDb = iUserDb;
		IUser user = this.getUserDbInterface().getUser(this.userCode);
		boolean isSufficient = user.getFunds() >= this.getTotalBuyPrice();
		return isSufficient;
	}

	public boolean createTradeDetails()
	{
		try
		{
			IStock iStock = this.getStockDbInterface().getStock(this.getStockId());
			this.symbol = iStock.getSymbol();
			this.segment = iStock.getSegment();

			if (TradeType.BUY == this.getBuySell())
			{
				this.buyPrice = iStock.getPrice();
				this.totalBuyPrice = this.getQuantity() * this.getBuyPrice();
			} else if (TradeType.SELL == this.getBuySell())
			{
				this.sellPrice = iStock.getPrice();
				this.totalSellPrice = this.getQuantity() * this.getSellPrice();
			}
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public boolean generateTradeNumber()
	{
		try
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			Date date = new Date();
			String timestamp = simpleDateFormat.format(date);
			this.tradeNumber = this.getUserCode() + this.getSymbol() + timestamp;
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

}
