package com.csci5308.stocki5.trade.holding;

import java.text.DecimalFormat;

import com.csci5308.stocki5.stock.IStock;
import com.csci5308.stocki5.stock.db.IStockDb;
import com.csci5308.stocki5.trade.Trade;
import com.csci5308.stocki5.trade.TradeStatus;
import com.csci5308.stocki5.trade.TradeType;
import com.csci5308.stocki5.user.db.IUserDb;

public class Holding extends Trade implements IHolding
{

	private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");

	private boolean isHolding;
	private double profitLoss;

	public Holding()
	{
		super();
	}

	public Holding(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, IStockDb iStockDb, IUserDb iUserDb, boolean isHolding)
	{
		super(userCode, stockId, buySell, quantity, status, iStockDb, iUserDb);
		this.isHolding = isHolding;
	}

	public void setIsHolding(boolean isHolding)
	{
		this.isHolding = isHolding;
	}

	public double getProfitLoss()
	{
		return Double.parseDouble(DECIMAL_FORMAT.format(profitLoss));
	}

	public void setProfitLoss(double profitLoss)
	{
		this.profitLoss = profitLoss;
	}

	public void calculateProfitLoss()
	{
		IStock iStock = this.getStockDbInterface().getStock(this.getStockId());
		float price = iStock.getPrice();
		this.setSellPrice(price);

		float totalSellPrice = this.getQuantity() * this.getSellPrice();
		this.setTotalSellPrice(totalSellPrice);

		double profitLoss = this.getTotalSellPrice() - this.getTotalBuyPrice();
		this.setProfitLoss(profitLoss);
	}

}
