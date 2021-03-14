package com.csci5308.stocki5.trade;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.User;
import com.csci5308.stocki5.user.UserDbInterface;

enum TradeType {
	BUY, SELL
}

enum TradeStatus {
	PENDING, EXECUTED
}

public class Trade {

	private String orderCode;
	private String userCode;
	private int stockId;
	private String symbol;
	private String segment;
	private TradeType buySell;
	private int quantity;
	private float buyPrice;
	private float sellPrice;
	private double totalBuyPrice;
	private double totalSellPrice;
	private TradeStatus status;
	private boolean isHolding;
	private double profitLoss;
	private StockDbInterface stockDbInterface;
	private UserDbInterface userDbInterface;

	public Trade(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status, boolean isHolding,
			StockDbInterface stockDbInterface, UserDbInterface userDbInterface) {
		this.userCode = userCode;
		this.stockId = stockId;
		this.buySell = buySell;
		this.quantity = quantity;
		this.status = status;
		this.isHolding = isHolding;
		this.stockDbInterface = stockDbInterface;
		this.userDbInterface = userDbInterface;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public int getStockId() {
		return stockId;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getSegment() {
		return segment;
	}

	public TradeType getBuySell() {
		return buySell;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public double getTotalBuyPrice() {
		return totalBuyPrice;
	}

	public double getTotalSellPrice() {
		return totalSellPrice;
	}

	public TradeStatus getStatus() {
		return status;
	}

	public boolean isHolding() {
		return isHolding;
	}

	public double getProfitLoss() {
		return profitLoss;
	}

	public StockDbInterface getStockDbInterface() {
		return stockDbInterface;
	}

	public UserDbInterface getUserDbInterface() {
		return userDbInterface;
	}

	public void generateOrderCode() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String timestamp = simpleDateFormat.format(date);
		this.orderCode = this.getUserCode() + this.getSymbol() + timestamp;
		System.out.println(this.getOrderCode());
	}

	public void addOrderDetails() {
		Stock stock = this.getStockDbInterface().getStockData(this.getStockId());
		this.symbol = stock.getSymbol();
		this.segment = stock.getSegment();

		if (TradeType.BUY == this.getBuySell()) {
			this.buyPrice = stock.getPrice();
			this.totalBuyPrice = this.getQuantity() * this.getBuyPrice();
		} else if (TradeType.SELL == this.getBuySell()) {
			this.sellPrice = stock.getPrice();
			this.totalSellPrice = this.getQuantity() * this.getSellPrice();
		}

		this.profitLoss = this.getSellPrice() - this.getBuyPrice();
	}

	public boolean isSufficientFunds() {
		User user = this.getUserDbInterface().getUser(this.userCode);
		return user.getFunds() >= this.getTotalBuyPrice();
	}

}
