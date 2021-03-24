package com.csci5308.stocki5.trade;

import java.text.DecimalFormat;
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
	PENDING, EXECUTED, FAILED
}

public class Trade {

	DecimalFormat df = new DecimalFormat("##.00");

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
	protected StockDbInterface stockDbInterface;
	protected UserDbInterface userDbInterface;

	public Trade() {

	}

	public Trade(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status,
			StockDbInterface stockDbInterface, UserDbInterface userDbInterface) {
		this.userCode = userCode;
		this.stockId = stockId;
		this.buySell = buySell;
		this.quantity = quantity;
		this.status = status;
		this.stockDbInterface = stockDbInterface;
		this.userDbInterface = userDbInterface;
	}

	public String getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getBuyPrice() {
		return Float.parseFloat(df.format(buyPrice));
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public float getSellPrice() {
		return Float.parseFloat(df.format(sellPrice));
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public TradeType getBuySell() {
		return buySell;
	}

	public void setBuySell(TradeType buySell) {
		this.buySell = buySell;
	}

	public double getTotalBuyPrice() {
		return Double.parseDouble(df.format(totalBuyPrice));
	}

	public void setTotalBuyPrice(double totalBuyPrice) {
		this.totalBuyPrice = totalBuyPrice;
	}

	public double getTotalSellPrice() {
		return Double.parseDouble(df.format(totalSellPrice));
	}

	public void setTotalSellPrice(double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}

	public TradeStatus getStatus() {
		return status;
	}

	public void setStatus(TradeStatus status) {
		this.status = status;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public StockDbInterface getStockDbInterface() {
		return stockDbInterface;
	}

	public void setStockDbInterface(StockDbInterface stockDbInterface) {
		this.stockDbInterface = stockDbInterface;
	}

	public UserDbInterface getUserDbInterface() {
		return userDbInterface;
	}

	public void setUserDbInterface(UserDbInterface userDbInterface) {
		this.userDbInterface = userDbInterface;
	}

	public boolean isFundSufficient(UserDbInterface userDbInterface) {
		this.userDbInterface = userDbInterface;
		User user = this.getUserDbInterface().getUser(this.userCode);
		boolean isSufficient = user.getFunds() >= this.getTotalBuyPrice();
		if (isSufficient) {
			double updatedFunds = user.getFunds() - this.getTotalBuyPrice();
			this.getUserDbInterface().updateUserFunds(user.getUserCode(), Double.parseDouble(df.format(updatedFunds)));
		}
		return isSufficient;
	}
	
	public boolean isSetBuyPriceFundSufficient(UserDbInterface userDbInterface) {
		this.userDbInterface = userDbInterface;
		User user = this.getUserDbInterface().getUser(this.userCode);
		boolean isSufficient = user.getFunds() >= this.getTotalBuyPrice();
		return isSufficient;
	}

	public void createTradeDetails() {
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
	}

	public void createSetBuyPriceTradeDetails(float buyPrice) {
		Stock stock = this.getStockDbInterface().getStockData(this.getStockId());
		this.symbol = stock.getSymbol();
		this.segment = stock.getSegment();

		this.buyPrice = buyPrice;
		this.totalBuyPrice = this.getQuantity() * this.getBuyPrice();
	}

	public void generateTradeNumber() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String timestamp = simpleDateFormat.format(date);
		this.tradeNumber = this.getUserCode() + this.getSymbol() + timestamp;
	}

}
