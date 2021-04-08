package com.csci5308.stocki5.trade;

import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.user.db.IUserDb;

import java.util.Date;

public interface ITrade
{
	public String getTradeNumber();

	public void setTradeNumber(String tradeNumber);

	public String getUserCode();

	public void setUserCode(String userCode);

	public int getStockId();

	public void setStockId(int stockId);

	public String getSymbol();

	public void setSymbol(String symbol);

	public String getSegment();

	public void setSegment(String segment);

	public int getQuantity();

	public void setQuantity(int quantity);

	public float getBuyPrice();

	public void setBuyPrice(float buyPrice);

	public float getSellPrice();

	public void setSellPrice(float sellPrice);

	public TradeType getBuySell();

	public void setBuySell(TradeType buySell);

	public double getTotalBuyPrice();

	public void setTotalBuyPrice(double totalBuyPrice);

	public double getTotalSellPrice();

	public void setTotalSellPrice(double totalSellPrice);

	public TradeStatus getStatus();

	public void setStatus(TradeStatus status);

	public void setTradeDate(Date tradeDate);

	public IStockDb getStockDbInterface();

	public void setStockDbInterface(IStockDb iStockDb);

	public IUserDb getUserDbInterface();

	public boolean isFundSufficient(IUserDb iUserDb);

	public boolean createTradeDetails();

	public boolean generateTradeNumber();
}
