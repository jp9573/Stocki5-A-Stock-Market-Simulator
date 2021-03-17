package com.csci5308.stocki5.trade;

import com.csci5308.stocki5.stock.Stock;
import com.csci5308.stocki5.stock.StockDbInterface;
import com.csci5308.stocki5.user.UserDbInterface;

public class Holding extends Trade{
    private boolean isHolding;
    private double profitLoss;

    public Holding(){
        super();
    }

    public Holding(String userCode, int stockId, TradeType buySell, int quantity, TradeStatus status,
                   StockDbInterface stockDbInterface, UserDbInterface userDbInterface, boolean isHolding){
        super(userCode, stockId, buySell, quantity, status, stockDbInterface, userDbInterface);
        this.isHolding = isHolding;
    }

    public boolean getIsHolding() {
        return isHolding;
    }

    public void setIsHolding(boolean isHolding) {
        this.isHolding = isHolding;
    }

    public double getProfitLoss() {
        return Double.parseDouble(df.format(profitLoss));
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public void calculateProfitLoss(){
        Stock stock = this.getStockDbInterface().getStockData(this.getStockId());
        float price = stock.getPrice();
        this.setSellPrice(price);
        float totalSellPrice = this.getQuantity() * this.getSellPrice();
        this.setTotalSellPrice(totalSellPrice);
        double profitLoss = this.getTotalSellPrice()-this.getTotalBuyPrice();
        this.setProfitLoss(profitLoss);
    }

}
