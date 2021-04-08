package com.csci5308.stocki5.trade.holding;

import com.csci5308.stocki5.trade.ITrade;

public interface IHolding extends ITrade
{
	public void setIsHolding(boolean isHolding);

	public double getProfitLoss();

	public void setProfitLoss(double profitLoss);

	public void calculateProfitLoss();
}
